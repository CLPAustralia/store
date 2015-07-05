package au.com.chloec.store.action.operation;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Destroy;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.log.Log;

import au.com.chloec.store.action.admin.ProductMaintenance;
import au.com.chloec.store.domain.Invoice;
import au.com.chloec.store.domain.InvoiceItem;
import au.com.chloec.store.domain.Product;

@Stateful
@Name("saleMaintenance")
@Scope(ScopeType.SESSION)
@Restrict("#{identity.loggedIn}")
public class SaleMaintenanceAction implements ProductMaintenance {

	@Logger
	private Log log;
	
//	@PersistenceContext(type=EXTENDED)
	@In
	private EntityManager entityManager;

	private String searchString;
	private int pageSize = 10;
	private int page;
	private boolean nextPageAvailable;

	@DataModel
	private List<Product> products;
	
	@DataModelSelection
	@Out(required = false, scope = ScopeType.SESSION)
	@In(required = false, scope = ScopeType.SESSION)
	private Product product;
	
	@In(create = true)
	@Out
	private Invoice invoice;
	
	public void find() {
		page = 0;
		queryProducts();
	}

	public void nextPage() {
		page++;
		queryProducts();
	}

	private void queryProducts() {
		@SuppressWarnings("unchecked")
		List<Product> results = entityManager
				.createQuery("select p from Product p where lower(p.name) like #{saleProductPattern} or lower(p.displayName) like #{saleProductPattern} or lower(p.productCode) like #{saleProductPattern}  or lower(p.factoryCode) like #{saleProductPattern} or lower(p.factoryBarcode) like #{saleProductPattern}")
				.setMaxResults(pageSize + 1).setFirstResult(page * pageSize).getResultList();

		nextPageAvailable = results.size() > pageSize;
		if (nextPageAvailable) {
			products = new ArrayList<Product>(results.subList(0, pageSize));
		} else {
			products = results;
		}
	}

	public boolean isNextPageAvailable() {
		return nextPageAvailable;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	@Factory(value = "saleProductPattern", scope = ScopeType.EVENT)
	public String getSearchPattern() {
		return searchString == null ? "%" : '%' + searchString.toLowerCase().replace('*', '%') + '%';
	}

	public String getSearchString() {
		return searchString;
	}

	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

	@Remove
	@Destroy
	public void destroy() {
	}
	
	public void edit() {
		log.info(product.getId());
	}
	
	public void save() {
		entityManager.persist(product);
	}
	
	public void add() {
		 InvoiceItem invoiceItem = (InvoiceItem) CollectionUtils.find(invoice.getInvoiceItems(), new Predicate() {			
			@Override
			public boolean evaluate(Object obj) {
				InvoiceItem invoiceItem = (InvoiceItem) obj; 
				return invoiceItem.getProduct().equals(product);
			}
		});
		if (invoiceItem == null) {
			invoice.getInvoiceItems().add(new InvoiceItem(product));
		} else {
			invoiceItem.add();
		}
	}
	
//
//	@Factory("product")
//	public Product product() {
//		return new Product();
//	}
}