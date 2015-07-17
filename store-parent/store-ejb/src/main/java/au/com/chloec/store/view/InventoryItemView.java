package au.com.chloec.store.view;

import java.io.Serializable;
import java.math.BigDecimal;

import org.jboss.seam.annotations.Name;

import au.com.chloec.store.domain.InventoryItem;
import au.com.chloec.store.domain.Product;
import au.com.chloec.store.utils.ProductUtil;

@Name("inventoryItemView")
public class InventoryItemView extends InventoryItem implements Serializable {

	private static final long serialVersionUID = 1L;
	private InventoryItem inventoryItem;

	public InventoryItemView(InventoryItem inventoryItem) {
		this.inventoryItem = inventoryItem;
	}

	public Product getProduct() {
		return inventoryItem.getProduct();
	}

	public String getName() {
		return getProduct().getDisplayName() != null ? getProduct().getDisplayName() : getProduct().getName();
	}

	public String getBarcode() {
		return getProduct().getFactoryBarcode();
	}
	
	public String getCode() {
		return getProduct().getProductCode() != null ? getProduct().getProductCode() : getProduct().getFactoryCode();
	}

	public String getGender() {
		return getProduct().getGender().getName();
	}

	public BigDecimal getRetailPrice() {
		return getProduct().getRetailPrice();
	}

	public String getCategory() {
		return getProduct().getCategory().getName();
	}
	
	public String getLabel() {
		return getProduct().getLabel().getName();
	}

	public String getColor() {
		return ProductUtil.getOptionIgnoreCase(getProduct(), "color");
	}

	public String getSize() {
		return ProductUtil.getOptionIgnoreCase(getProduct(), "size");
	}
	
	public Integer getQuantity() {
		return inventoryItem.getQuantity();
	}

	public void reduceQuantityByOne() {
		inventoryItem.setQuantity(inventoryItem.getQuantity() - 1);
	}

	public void increaseQuantity(Integer quantity) {
		inventoryItem.setQuantity(inventoryItem.getQuantity() + quantity);
	}
}
