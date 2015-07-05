package au.com.chloec.store.action.admin;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.security.Restrict;

import au.com.chloec.store.domain.EnumInstance;

@Stateless
@Name("enumMaintenance")
@Restrict("#{identity.loggedIn}")
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class EnumMaintenanceAction {

	public static final String DOMAIN_NAME_GENDER = "gender";
	public static final String DOMAIN_NAME_SIZE = "size";
	public static final String DOMAIN_NAME_COLOR = "color";
	public static final String DOMAIN_NAME_CATEGORY = "category";
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Factory("genders")
	public List<EnumInstance> getGenderInstances() {
		return getInstances(DOMAIN_NAME_GENDER);
	}

	@Factory("sizes")
	public List<EnumInstance> getSizeInstances() {
		return getInstances(DOMAIN_NAME_SIZE);
	}

	@Factory("colors")
	public List<EnumInstance> getColorInstances() {
		return getInstances(DOMAIN_NAME_COLOR);
	}

	@Factory("categories")
	public List<EnumInstance> getCategoryInstances() {
		return getInstances(DOMAIN_NAME_CATEGORY);
	}
	
	@SuppressWarnings("unchecked")
	private List<EnumInstance> getInstances(String domainName) {
		return entityManager.createQuery("select e from EnumInstance e where e.domain.name = :domainName").setParameter("domainName", domainName).getResultList();
	}

	@Factory("defaultCategory")
	public EnumInstance getDefaultCategory() {
		return getCategoryInstances().get(0);
	}
	
	@Factory("defaultGender")
	public EnumInstance getDefaultGender() {
		return getGenderInstances().get(0);
	}
	
	@Factory("defaultSize")
	public EnumInstance getDefaultSize() {
		return getSizeInstances().get(0);
	}
	
	@Factory(value = "defaultColor")
	public EnumInstance getDefaultColor() {
		return getColorInstances().get(0);
	}
	
	
}
