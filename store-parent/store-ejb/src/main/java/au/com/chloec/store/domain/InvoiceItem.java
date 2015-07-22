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
import javax.persistence.Transient;

import org.jboss.seam.annotations.Name;

import au.com.chloec.store.utils.ProductUtil;

@Entity
@Table(name = "invoice_item")
@Name("invoiceItem")
public class InvoiceItem extends AbstractDomainObject implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Product product;	
	private Invoice invoice;
	private BigDecimal unitPrice;
	private EnumInstance discountUnit;
	private Double discountAmount = Double.valueOf(0);
	private Integer quantity;

	public InvoiceItem(){}
	
	public InvoiceItem(Product product, BigDecimal unitPrice, Invoice invoice) {
		this.product = product;
		this.unitPrice = unitPrice;
		this.invoice = invoice;
		this.quantity = 1;
	}
	
	public InvoiceItem(Product product, BigDecimal unitPrice, Invoice invoice, EnumInstance discountUnit, Double discountAmount) {
		this.product = product;
		this.unitPrice = unitPrice;
		this.invoice = invoice;
		this.quantity = 1;
		this.discountUnit = discountUnit;
		this.discountAmount = discountAmount;
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

	@Transient
	public String getProductColor() {
		return ProductUtil.getOptionIgnoreCase(product, "color");
	}

	@Transient
	public String getProductSize() {
		return ProductUtil.getOptionIgnoreCase(product, "size");
	}

	@Column(name = "discount_amount")
	public Double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(Double discountAmount) {
		this.discountAmount = discountAmount;
	}

	@ManyToOne
	@JoinColumn(name = "discount_unit_instance_id")
	public EnumInstance getDiscountUnit() {
		return discountUnit;
	}

	public void setDiscountUnit(EnumInstance discountUnit) {
		this.discountUnit = discountUnit;
	}
	
	public boolean hasDiscount() {
		return this.discountUnit != null && this.discountAmount > 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		InvoiceItem other = (InvoiceItem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "InvoiceItem [id=" + id + ", product=" + product + ", invoice="
				+ invoice + ", unitPrice=" + unitPrice + ", discountUnit="
				+ discountUnit + ", discountAmount=" + discountAmount
				+ ", quantity=" + quantity + "]";
	}
	
}
