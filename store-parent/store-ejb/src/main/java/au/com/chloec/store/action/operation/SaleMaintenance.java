package au.com.chloec.store.action.operation;

import javax.ejb.Local;

import au.com.chloec.store.domain.Invoice;

@Local
public interface SaleMaintenance {

	public int getPageSize();

	public void setPageSize(int pageSize);

	public String getSearchString();

	public void setSearchString(String searchString);

	public String getSearchPattern();

	public void find();

	public void nextPage();

	public boolean isNextPageAvailable();

	public void destroy();

	public void edit();
	
	public void addInvoiceItem();

	public void confirm();
	
	public void cancel();

}
