package au.com.chloec.store.action.operation;

import java.util.List;

import javax.ejb.Local;

import au.com.chloec.store.domain.Invoice;

@Local
public interface InvoiceMaintenance {

	public int getPageSize();

	public void setPageSize(int pageSize);

	public String getSearchString();

	public void setSearchString(String searchString);
	
	public void find();

	public void nextPage();

	public boolean isNextPageAvailable();

	public void destroy();

	public void edit();

//	public List<Invoice> invoices();
}
