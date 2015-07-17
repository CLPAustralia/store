package au.com.chloec.store.action.operation;

import java.util.Date;

import javax.ejb.Local;

@Local
public interface JournalMaintenance {

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

	public void add();
	
	public void save();
	
}
