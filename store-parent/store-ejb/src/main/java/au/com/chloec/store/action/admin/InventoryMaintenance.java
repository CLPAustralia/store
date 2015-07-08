package au.com.chloec.store.action.admin;

import java.util.List;

import javax.ejb.Local;

import au.com.chloec.store.domain.Company;

@Local
public interface InventoryMaintenance {

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
	
	public void update();

	public void save();
	
	public void add();

}
