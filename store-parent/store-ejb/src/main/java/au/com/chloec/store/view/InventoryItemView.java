package au.com.chloec.store.view;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.jboss.seam.annotations.Name;

import au.com.chloec.store.action.admin.EnumMaintenanceAction;
import au.com.chloec.store.domain.InventoryItem;
import au.com.chloec.store.domain.Product;
import au.com.chloec.store.domain.ProductPromotion;
import au.com.chloec.store.utils.ProductUtil;

@Name("inventoryItemView")
public class InventoryItemView extends InventoryItem implements Serializable {

	private static final long serialVersionUID = 1L;
	private InventoryItem inventoryItem;
	private EnumMaintenanceAction enumMaintenance;

	public InventoryItemView(InventoryItem inventoryItem, EnumMaintenanceAction enumMaintenance) {
		this.inventoryItem = inventoryItem;
		this.enumMaintenance = enumMaintenance;
	}

	public Product getProduct() {
		return inventoryItem.getProduct();
	}

	public String getName() {
		return getProduct().getDisplayName() != null ? getProduct().getDisplayName() : getProduct().getName();
	}

	public String getBarcode() {
		return getProduct().getProductBarcode() != null ? getProduct().getProductBarcode() : getProduct().getFactoryBarcode();
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

	public BigDecimal getPromotedPrice() {
		BigDecimal price = getProduct().getRetailPrice();		
		List<ProductPromotion> productPromotions = getProduct().getProductPromotions();
		for (ProductPromotion productPromotion : productPromotions) {
			if (productPromotion.getDiscountUnit().equals(enumMaintenance.getDiscountUnitPercentage())) {
				price = price.multiply(BigDecimal.ONE.subtract(productPromotion.getDiscountAmount().divide(BigDecimal.valueOf(100))));
			} else {
				price = price.subtract(productPromotion.getDiscountAmount());
			}
		}
		return price;
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
