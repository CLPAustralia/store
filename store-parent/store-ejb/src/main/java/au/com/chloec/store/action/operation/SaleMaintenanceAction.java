package au.com.chloec.store.action.operation;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Destroy;
import org.jboss.seam.annotations.End;
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

import au.com.chloec.store.action.admin.EnumMaintenanceAction;
import au.com.chloec.store.domain.InventoryItem;
import au.com.chloec.store.domain.Invoice;
import au.com.chloec.store.domain.InvoiceItem;
import au.com.chloec.store.domain.Product;
import au.com.chloec.store.domain.User;

@Stateful
@Name("saleMaintenance")
@Scope(ScopeType.SESSION)
@Restrict("#{identity.loggedIn}")
public class SaleMaintenanceAction implements SaleMaintenance {

	@Logger
	private Log log;

	@In
	private EntityManager entityManager;

	@In
	private User user;
	
	private String searchString;
	private int pageSize = 10;
	private int page;
	private boolean nextPageAvailable;

	@DataModel
	private List<InventoryItem> inventoryItems;
	
	@In(required = false)
	@Out(required = false)
	@DataModelSelection
	private InventoryItem inventoryItem;

	@In(create = true)
	@Out(required = false)
	private Invoice invoice;
	
	@In(create = true)
	private EnumMaintenanceAction enumMaintenance;
	
	@Begin(join = true)
	public void find() {
		page = 0;
		queryProducts();
	}

	public void nextPage() {
		page++;
		queryProducts();
	}

	private void queryProducts() {
		String queryString = "select i from InventoryItem i "
				+ "join i.product p "
				+ "where i.quantity > 0 "
				+ "and("
				+ "lower(p.name) like #{saleProductPattern} "
				+ "or lower(p.displayName) like #{saleProductPattern} "
				+ "or lower(p.productCode) like #{saleProductPattern}  "
				+ "or lower(p.factoryCode) like #{saleProductPattern} or lower(p.factoryBarcode) like #{saleProductPattern}"
				+ ")";
		@SuppressWarnings("unchecked")
		List<InventoryItem> results = entityManager
				.createQuery(queryString )
				.setMaxResults(pageSize + 1).setFirstResult(page * pageSize).getResultList();

		nextPageAvailable = results.size() > pageSize;
		if (nextPageAvailable) {
			inventoryItems = new ArrayList<InventoryItem>(results.subList(0, pageSize));
		} else {
			inventoryItems = results;
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
		log.info(inventoryItem.getId());
	}
	
	public void addInvoiceItem() {
		final Product product = inventoryItem.getProduct();
		 InvoiceItem invoiceItem = (InvoiceItem) CollectionUtils.find(invoice.getInvoiceItems(), new Predicate() {			
			@Override
			public boolean evaluate(Object obj) {
				InvoiceItem invoiceItem = (InvoiceItem) obj; 
				return invoiceItem.getProduct().equals(product);
			}
		});
		if (invoiceItem == null) {
			invoice.getInvoiceItems().add(new InvoiceItem(product, product.getRetailPrice()));
		} else {
			invoiceItem.add();
		}
		inventoryItem.setQuantity(inventoryItem.getQuantity() - 1);
	}
	
	@End
	public void confirm() {
		invoice.setStatus(enumMaintenance.getInvoiceStatusCompleted());
		invoice.setLastUpdateDate(Calendar.getInstance().getTime());
		invoice.setLastUpdateUser(user);
		entityManager.persist(invoice);
//		entityManager.flush();
//		entityManager.persist(inventoryItem);
//		entityManager.flush();
	}
	
	@End
	public void cancel() {
		for (final InvoiceItem invoiceItem : invoice.getInvoiceItems()) {
			InventoryItem inventoryItem = (InventoryItem) CollectionUtils.find(inventoryItems, new Predicate() {				
				@Override
				public boolean evaluate(Object obj) {
					InventoryItem inventoryItem = (InventoryItem) obj;
					return inventoryItem.getProduct().equals(invoiceItem.getProduct());
				}
			});
			inventoryItem.setQuantity(inventoryItem.getQuantity() + invoiceItem.getQuantity());
		}
		invoice = null;
	}
	
}
