package au.com.chloec.store.action.admin;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;

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

import au.com.chloec.store.domain.Product;
import au.com.chloec.store.domain.User;

@Stateful
@Name("productMaintenance")
@Scope(ScopeType.SESSION)
@Restrict("#{identity.loggedIn}")
public class ProductMaintenanceAction implements ProductMaintenance {

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
	private List<Product> products;
	
	@In(create = true)
	@Out
	@DataModelSelection
	private Product product;
	
	public void find() {
		page = 0;
		queryProducts();
	}

	public void nextPage() {
		page++;
		queryProducts();
	}

	private void queryProducts() {
		String queryString = "select p "
				+ "from Product p "
				+ "where lower(p.name) like #{productPattern} "
				+ "or lower(p.factoryBarcode) like #{productPattern} "
				+ "or lower(p.displayName) like #{productPattern} "
				+ "or lower(p.productCode) like #{productPattern}  "
				+ "or lower(p.factoryCode) like #{productPattern}";
		@SuppressWarnings("unchecked")
		List<Product> results = entityManager
				.createQuery(queryString )
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

	@Factory(value = "productPattern", scope = ScopeType.EVENT)
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
		product.setLastUpdateDate(Calendar.getInstance().getTime());
		product.setLastUpdateUser(user);
		entityManager.persist(product);
	}
	
	public void update() {
		entityManager.merge(product);
		entityManager.refresh(product);
	}
	
	public void add() {
		this.product = new Product();
	}
	
	@SuppressWarnings("unchecked")
	@Factory("allProducts")
	public List<Product> getAllProducts() {
		return entityManager.createQuery("select p from Product p").getResultList();
	}
}
