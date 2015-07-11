package au.com.chloec.store.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.jboss.seam.annotations.Name;

@Entity
@Table(name = "invoice_item")
@Name("invoiceItem")
@EqualsAndHashCode(callSuper=false,of={"id"})
@ToString
public class InvoiceItem extends AbstractDomainObject implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Product product;	
	private Invoice invoice;
	private BigDecimal unitPrice;
	private Integer quantity;

	public InvoiceItem(){}
	
	public InvoiceItem(Product product, BigDecimal unitPrice, Invoice invoice) {
		this.product = product;
		this.unitPrice = unitPrice;
		this.invoice = invoice;
		this.quantity = 1;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "invoice_item_id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

}
