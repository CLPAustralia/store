package au.com.chloec.store.action.operation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Destroy;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.log.Log;
import org.joda.time.DateTime;

import au.com.chloec.store.action.admin.EnumMaintenanceAction;
import au.com.chloec.store.domain.EnumInstance;
import au.com.chloec.store.domain.Invoice;
import au.com.chloec.store.domain.InvoiceItem;

@Stateful
@Name("invoiceMaintenance")
@Scope(ScopeType.SESSION)
@Restrict("#{identity.loggedIn}")
public class InvoiceMaintenanceAction implements InvoiceMaintenance {

	@Logger
	private Log log;

	@In
	private EntityManager entityManager;

	private Date searchDate;
	private String searchString;

	private int pageSize = 10;
	private int page;
	private boolean nextPageAvailable;

	@In(create = true)
	private EnumMaintenanceAction enumMaintenance;

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

		List<Invoice> results;
		Query query;
		if (searchDate == null) {
			query = entityManager.createQuery("select i from Invoice i order by i.lastUpdateDate desc");
		} else {
			DateTime startDate = new DateTime(searchDate).withTimeAtStartOfDay();
			DateTime endDate = startDate.plusDays(1).withTimeAtStartOfDay();
			query = entityManager.createQuery("select i from Invoice i where i.creationDate between :startDate and :endDate order by i.lastUpdateDate desc")
					.setParameter("startDate", startDate.toDate())
					.setParameter("endDate", endDate.toDate());
		}
		results = query.setMaxResults(pageSize + 1).setFirstResult(page * pageSize).getResultList();

		nextPageAvailable = results.size() > pageSize;
		if (nextPageAvailable) {
			invoices = new ArrayList<Invoice>(results.subList(0, pageSize));
		} else {
			invoices = results;
		}
	}

	// @SuppressWarnings("unchecked")
	// private void queryInvoices() {
	// String queryString;
	// if (StringUtils.isBlank(searchString)) {
	// queryString = "select i from Invoice i order by i.lastUpdateDate desc";
	// } else {
	// queryString = "select i from Invoice i where i.id = #{searchString}  order by i.lastUpdateDate desc";
	// }
	// List<Invoice> results = entityManager.createQuery(queryString).setMaxResults(pageSize + 1).setFirstResult(page * pageSize).getResultList();
	//
	// nextPageAvailable = results.size() > pageSize;
	// if (nextPageAvailable) {
	// invoices = new ArrayList<Invoice>(results.subList(0, pageSize));
	// } else {
	// invoices = results;
	// }
	// }

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

	public Date getSearchDate() {
		return searchDate;
	}

	public void setSearchDate(Date searchDate) {
		this.searchDate = searchDate;
	}

	@Remove
	@Destroy
	public void destroy() {
	}

	public void edit() {
		log.info(invoice.getId());
	}

	// @Factory("invoices")
	// public List<Invoice> invoices() {
	// queryInvoices();
	// return invoices;
	// }
	
	public BigDecimal getTotal() {
		BigDecimal total = BigDecimal.ZERO;
		for (InvoiceItem invoiceItem : invoice.getInvoiceItems()) {
			total = total.add(getSubtotal(invoiceItem));
		}
		return total;
	}

	public BigDecimal getSubtotal(InvoiceItem invoiceItem) {
		BigDecimal unitPrice = invoiceItem.getUnitPrice();
		BigDecimal discountedPrice = BigDecimal.ZERO;
		EnumInstance discountUnit = invoiceItem.getDiscountUnit();
		if (!invoiceItem.hasDiscount()) {
			discountedPrice = unitPrice;
		} else if (discountUnit.equals(enumMaintenance.getDiscountUnitPercentage())) {
			discountedPrice = unitPrice.multiply(BigDecimal.valueOf(1 - invoiceItem.getDiscountAmount() / 100));
		} else {
			discountedPrice = unitPrice.subtract(BigDecimal.valueOf(invoiceItem.getDiscountAmount()));
		}
		BigDecimal quantity = BigDecimal.valueOf(invoiceItem.getQuantity());	
		return discountedPrice.multiply(quantity);		
	}

}
