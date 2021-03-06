package au.com.chloec.store.action.operation;

import java.math.BigDecimal;
import java.util.Date;

import javax.ejb.Local;

import au.com.chloec.store.domain.InvoiceItem;

@Local
public interface InvoiceMaintenance {

	public int getPageSize();

	public void setPageSize(int pageSize);

	public String getSearchString();

	public void setSearchString(String searchString);
	
	public Date getSearchDate();

	public void setSearchDate(Date searchDate);
	
	public void find();

	public void nextPage();

	public boolean isNextPageAvailable();

	public void destroy();

	public void edit();

//	public List<Invoice> invoices();
	
	public BigDecimal getTotal();

	public BigDecimal getSubtotal(InvoiceItem invoiceItem);
}
