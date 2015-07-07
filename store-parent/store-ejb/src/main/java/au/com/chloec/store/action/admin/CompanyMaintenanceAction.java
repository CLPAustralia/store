package au.com.chloec.store.action.admin;

import java.util.ArrayList;
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

import au.com.chloec.store.domain.Company;

@Stateful
@Name("companyMaintenance")
@Scope(ScopeType.SESSION)
@Restrict("#{identity.loggedIn}")
public class CompanyMaintenanceAction implements CompanyMaintenance {

	@Logger
	private Log log;
	
	@In
	private EntityManager entityManager;

	private String searchString;
	private int pageSize = 10;
	private int page;
	private boolean nextPageAvailable;

	@DataModel
	private List<Company> companies;
	
	@In(create = true)
	@Out
	@DataModelSelection
	private Company company;
	
	public void find() {
		page = 0;
		queryCompanies();
	}

	public void nextPage() {
		page++;
		queryCompanies();
	}

	private void queryCompanies() {
		@SuppressWarnings("unchecked")
		List<Company> results = entityManager
				.createQuery("select c from Company c where lower(c.name) like #{companyPattern} ")
				.setMaxResults(pageSize + 1).setFirstResult(page * pageSize).getResultList();

		nextPageAvailable = results.size() > pageSize;
		if (nextPageAvailable) {
			companies = new ArrayList<Company>(results.subList(0, pageSize));
		} else {
			companies = results;
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

	@Factory(value = "companyPattern", scope = ScopeType.EVENT)
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
		log.info(company.getId());
	}
	
	public void save() {
		entityManager.persist(company);
	}
	
	public void add() {
		this.company = new Company();
	}
}
