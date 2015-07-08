package au.com.chloec.store.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.jboss.seam.annotations.Name;

@Entity
@Table(name = "product")
@Name("product")
@EqualsAndHashCode(callSuper=false,of={"id"})
@ToString
public class Product extends AbstractDomainObject implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String name;
	private String displayName;

	private String factoryBarcode;
	private String factoryCode;
	private String productCode;
	
	private EnumInstance gender;	
	
	private String summary;
	private String description;
	
	private BigDecimal wholesalePrice;
	private BigDecimal retailPrice;
	
	private EnumInstance category;
	private Label label;
	
	private List<ProductOption> productOptions;
	
	private byte[] thumbnail;
	
	private byte[] picture;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "product_name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "display_name")
	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	@Column(name = "factory_barcode")
	public String getFactoryBarcode() {
		return factoryBarcode;
	}

	public void setFactoryBarcode(String factoryBarcode) {
		this.factoryBarcode = factoryBarcode;
	}

	@Column(name = "factory_code")
	public String getFactoryCode() {
		return factoryCode;
	}

	public void setFactoryCode(String factoryCode) {
		this.factoryCode = factoryCode;
	}

	@Column(name = "product_code")
	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	@ManyToOne
	@JoinColumn(name = "gender_instance_id")
	public EnumInstance getGender() {
		return gender;
	}

	public void setGender(EnumInstance gender) {
		this.gender = gender;
	}
	
	public void setGender(String t) {
		System.out.println(t);
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "wholesale_price")
	public BigDecimal getWholesalePrice() {
		return wholesalePrice;
	}

	public void setWholesalePrice(BigDecimal wholesalePrice) {
		this.wholesalePrice = wholesalePrice;
	}

	@Column(name = "retail_price")
	public BigDecimal getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(BigDecimal retailPrice) {
		this.retailPrice = retailPrice;
	}

	@ManyToOne
	@JoinColumn(name = "category_instance_id")
	public EnumInstance getCategory() {
		return category;
	}

	public void setCategory(EnumInstance category) {
		this.category = category;
	}

	@ManyToOne
	@JoinColumn(name = "label_id")
	public Label getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		this.label = label;
	}

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "product_id", referencedColumnName = "product_id")
	public List<ProductOption> getProductOptions() {
		return productOptions;
	}

	public void setProductOptions(List<ProductOption> productOptions) {
		this.productOptions = productOptions;
	}

	@Lob
	public byte[] getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(byte[] thumbnail) {
		this.thumbnail = thumbnail;
	}

	@Lob
	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

}
