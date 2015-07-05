package au.com.chloec.store.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.EqualsAndHashCode;

import org.jboss.seam.annotations.Name;

@Entity
@Name("inventoryItem")
@EqualsAndHashCode(callSuper=true)
public class InventoryItem extends AbstractDomainObjectWithId implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Product product;
	
	private Integer quantity;

	@ManyToOne
	@JoinColumn(name = "product_id")
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
}
