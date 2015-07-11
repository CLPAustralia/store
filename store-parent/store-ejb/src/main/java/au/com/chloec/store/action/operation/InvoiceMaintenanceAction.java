package au.com.chloec.store.action.operation;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;

import org.apache.commons.lang.StringUtils;
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

import au.com.chloec.store.domain.Invoice;

@Stateful
@Name("invoiceMaintenance")
@Scope(ScopeType.SESSION)
@Restrict("#{identity.loggedIn}")
public class InvoiceMaintenanceAction implements InvoiceMaintenance {

	@Logger
	private Log log;

	@In
	private EntityManager entityManager;

	private String searchString;

	private int pageSize = 10;
	private int page;
	private boolean nextPageAvailable;

	@DataModel
	private List<Invoice> invoices;

	@In(required = false)
	@Out(required = false)
	@DataModelSelection
	private Invoice invoice;

	public void find() {
		page = 0;
		queryInvoices();
	}

	public void nextPage() {
		page++;
		queryInvoices();
	}

	@SuppressWarnings("unchecked")
	private void queryInvoices() {
		String queryString;
		if (StringUtils.isBlank(searchString)) {
			queryString = "select i from Invoice i order by i.lastUpdateDate desc";
		} else {
			queryString = "select i from Invoice i where i.id = #{searchString}  order by i.lastUpdateDate desc";
		}
		List<Invoice> results = entityManager.createQuery(queryString).setMaxResults(pageSize + 1).setFirstResult(page * pageSize).getResultList();

		nextPageAvailable = results.size() > pageSize;
		if (nextPageAvailable) {
			invoices = new ArrayList<Invoice>(results.subList(0, pageSize));
		} else {
			invoices = results;
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
		log.info(invoice.getId());
	}

//	@Factory("invoices")
//	public List<Invoice> invoices() {
//		queryInvoices();
//		return invoices;
//	}
}
