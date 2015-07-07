package au.com.chloec.store.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.EqualsAndHashCode;

import org.jboss.seam.annotations.Name;

@Entity
@Name("invoice")
@EqualsAndHashCode(callSuper=true)
public class Invoice extends AbstractDomainObject implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private List<InvoiceItem> invoiceItems = new ArrayList<InvoiceItem>();
	private EnumInstance status;		
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "invoice_id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "invoice_id", referencedColumnName = "invoice_id")
	public List<InvoiceItem> getInvoiceItems() {
		return invoiceItems;
	}

	public void setInvoiceItems(List<InvoiceItem> invoiceItems) {
		this.invoiceItems = invoiceItems;
	}

	@ManyToOne
	@JoinColumn(name = "status_instance_id")
	public EnumInstance getStatus() {
		return status;
	}

	public void setStatus(EnumInstance status) {
		this.status = status;
	}
		
}
