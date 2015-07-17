package au.com.chloec.store.action.admin;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.security.Restrict;

import au.com.chloec.store.domain.EnumInstance;

@Stateless
@Name("enumMaintenance")
@Restrict("#{identity.loggedIn}")
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class EnumMaintenanceAction {

	public static final String DOMAIN_NAME_PRODUCT_CATEGORY = "Product Category";
	public static final String DOMAIN_NAME_COMPANY_CATEGORY = "Company Category";
	public static final String DOMAIN_NAME_JOURNAL_CATEGORY = "Journal Category";
	public static final String DOMAIN_NAME_GENDER = "Gender";
	public static final String DOMAIN_NAME_SIZE = "Size";
	public static final String DOMAIN_NAME_COLOR = "Color";
	public static final String DOMAIN_NAME_PAYMENT_TYPE = "Payment Type";
	public static final String DOMAIN_NAME_INVOICE_STATUS = "Invoice Status";
	public static final String DOMAIN_NAME_INVOICE_STATUS_COMPLETED = "Completed";
	
	public static final String DOMAIN_NAME_DISCOUNT_UNIT = "Discount Unit";
	public static final String DOMAIN_NAME_DISCOUNT_UNIT_PERCENTAGE = "Percentage";

	@PersistenceContext
	private EntityManager entityManager;
	
	@Factory("allGenders")
	public List<EnumInstance> getGenderInstances() {
		return getInstances(DOMAIN_NAME_GENDER);
	}

	@Factory("allSizes")
	public List<EnumInstance> getSizeInstances() {
		return getInstances(DOMAIN_NAME_SIZE);
	}

	@Factory("allColors")
	public List<EnumInstance> getColorInstances() {
		return getInstances(DOMAIN_NAME_COLOR);
	}

	@Factory("allProductCategories")
	public List<EnumInstance> getProductCategoryInstances() {
		return getInstances(DOMAIN_NAME_PRODUCT_CATEGORY);
	}
	
	@Factory("allCompanyCategories")
	public List<EnumInstance> getCompanyCategoryInstances() {
		return getInstances(DOMAIN_NAME_COMPANY_CATEGORY);
	}
	
	@Factory("allPaymentTypes")
	public List<EnumInstance> getPaymentTyeInstances() {
		return getInstances(DOMAIN_NAME_PAYMENT_TYPE);
	}
	
	@SuppressWarnings("unchecked")
	private List<EnumInstance> getInstances(String domainName) {
		return entityManager.createQuery("select e from EnumInstance e where e.domain.name = :domainName").setParameter("domainName", domainName).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Factory("allInvoiceStatus")
	public List<EnumInstance> getInvoiceStatusInstances() {
		return entityManager.createQuery("select e from EnumInstance e where e.domain.name = :domainName").setParameter("domainName", DOMAIN_NAME_INVOICE_STATUS).getResultList();
	}
	
	@Factory("invoiceStatusComplete")
	public EnumInstance getInvoiceStatusCompleted() {
		List<EnumInstance> invoiceEnumInstances = getInvoiceStatusInstances();
		return (EnumInstance) CollectionUtils.find(invoiceEnumInstances, new Predicate() {			
			@Override
			public boolean evaluate(Object obj) {
				EnumInstance invoiceEnumInstance = (EnumInstance) obj;
				return invoiceEnumInstance.getName().equals("Completed");
			}
		});
	}
		
	@SuppressWarnings("unchecked")
	@Factory("allDiscountUnits")
	public List<EnumInstance> getDiscountUnitInstances() {
		return entityManager.createQuery("select e from EnumInstance e where e.domain.name = :domainName").setParameter("domainName", DOMAIN_NAME_DISCOUNT_UNIT).getResultList();
	}
	
	@Factory("discountUnitPercentage")
	public EnumInstance getDiscountUnitPercentage() {
		List<EnumInstance> discountUnitEnumInstances = getDiscountUnitInstances();
		return (EnumInstance) CollectionUtils.find(discountUnitEnumInstances, new Predicate() {			
			@Override
			public boolean evaluate(Object obj) {
				EnumInstance invoiceEnumInstance = (EnumInstance) obj;
				return invoiceEnumInstance.getName().equals("Percentage");
			}
		});
	}

	@Factory("allJournalCategories")
	public List<EnumInstance> getJournalCategoryInstances() {
		return getInstances(DOMAIN_NAME_JOURNAL_CATEGORY);
	}
	
	@Factory("journalCategoryGeneral")
	public EnumInstance getJournalCategoryGeneral() {
		List<EnumInstance> journalEnumInstances = getJournalCategoryInstances();
		return (EnumInstance) CollectionUtils.find(journalEnumInstances, new Predicate() {			
			@Override
			public boolean evaluate(Object obj) {
				EnumInstance journalEnumInstance = (EnumInstance) obj;
				return journalEnumInstance.getName().equals("General");
			}
		});
	}
		
}
