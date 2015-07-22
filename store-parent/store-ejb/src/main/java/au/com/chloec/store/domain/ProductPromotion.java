package au.com.chloec.store.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.jboss.seam.annotations.Name;

@Entity
@Table(name = "product_promotion")
@Name("productPromotion")
@EqualsAndHashCode(callSuper=true)
@ToString
public class ProductPromotion extends AbstractDomainObject implements Serializable, Comparable<ProductPromotion> {

	private static final long serialVersionUID = 1L;
	private Long id;
	private EnumInstance discountUnit;
	private BigDecimal discountAmount;
	private Date effectiveFromDate;
	private Date effectiveToDate;
	private Product product;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "promotion_id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne
	@JoinColumn(name = "discount_unit_instance_id")
	public EnumInstance getDiscountUnit() {
		return discountUnit;
	}

	public void setDiscountUnit(EnumInstance discountUnit) {
		this.discountUnit = discountUnit;
	}

	@Column(name = "discount_amount")
	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "effective_from_date")
	@NotNull
	public Date getEffectiveFromDate() {
		return effectiveFromDate;
	}

	public void setEffectiveFromDate(Date effectiveFromDate) {
		this.effectiveFromDate = effectiveFromDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "effective_to_date")
	public Date getEffectiveToDate() {
		return effectiveToDate;
	}

	public void setEffectiveToDate(Date effectiveToDate) {
		this.effectiveToDate = effectiveToDate;
	}

	@ManyToOne
	@JoinColumn(name = "product_id")
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public int compareTo(ProductPromotion productPromotion) {
		return this.effectiveFromDate.compareTo(productPromotion.effectiveFromDate);
	}
	
	
}
