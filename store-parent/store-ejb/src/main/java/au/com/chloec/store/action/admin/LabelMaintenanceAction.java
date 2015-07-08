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

import au.com.chloec.store.domain.Label;
import au.com.chloec.store.domain.User;

@Stateful
@Name("labelMaintenance")
@Scope(ScopeType.SESSION)
@Restrict("#{identity.loggedIn}")
public class LabelMaintenanceAction implements LabelMaintenance {

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
	private List<Label> labels;
	
	@In(create = true)
	@Out
	@DataModelSelection
	private Label label;
	
	public void find() {
		page = 0;
		queryLabels();
	}

	public void nextPage() {
		page++;
		queryLabels();
	}

	private void queryLabels() {
		@SuppressWarnings("unchecked")
		List<Label> results = entityManager
				.createQuery("select l from Label l where lower(l.name) like #{labelPattern} ")
				.setMaxResults(pageSize + 1).setFirstResult(page * pageSize).getResultList();

		nextPageAvailable = results.size() > pageSize;
		if (nextPageAvailable) {
			labels = new ArrayList<Label>(results.subList(0, pageSize));
		} else {
			labels = results;
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

	@Factory(value = "labelPattern", scope = ScopeType.EVENT)
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
		log.info(label.getId());
	}
	
	public void save() {
		label.setLastUpdateDate(Calendar.getInstance().getTime());
		label.setLastUpdateUser(user);
		entityManager.persist(label);
	}
	
	public void add() {
		this.label = new Label();
	}

	@SuppressWarnings("unchecked")
	@Factory("allLabels")
	public List<Label> getAllLabels() {
		return entityManager.createQuery("select l from Label l").getResultList();
	}
}
