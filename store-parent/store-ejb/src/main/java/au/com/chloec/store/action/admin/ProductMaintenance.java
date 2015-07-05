package au.com.chloec.store.action.admin;

import javax.ejb.Local;

@Local
public interface ProductMaintenance {

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
	
	public void save();
	
	public void add();
}
