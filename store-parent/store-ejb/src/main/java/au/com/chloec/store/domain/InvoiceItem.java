package au.com.chloec.store.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.EqualsAndHashCode;

import org.jboss.seam.annotations.Name;

@Entity
@Name("invoiceItem")
@EqualsAndHashCode(callSuper=true)
public class InvoiceItem extends AbstractDomainObjectWithId implements Serializable {

	private static final long serialVersionUID = 1L;

	private Product product;
	
	private Invoice invoice;
	
	private Integer quantity;

	public InvoiceItem(){}
	
	public InvoiceItem(Product product) {
		this.product = product;
		this.quantity = 1;
	}
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@ManyToOne
	@JoinColumn(name = "invoice_id")
	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	public void add() {
		quantity++;
	}
		
}
