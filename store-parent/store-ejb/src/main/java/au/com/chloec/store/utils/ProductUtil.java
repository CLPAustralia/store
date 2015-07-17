package au.com.chloec.store.utils;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

import au.com.chloec.store.domain.Product;
import au.com.chloec.store.domain.ProductOption;

public class ProductUtil {

	private ProductUtil() {
	}

	public static String getOptionIgnoreCase(final Product product, final String optionName) {
		ProductOption productOption = (ProductOption) CollectionUtils.find(product.getProductOptions(), new Predicate() {
			@Override
			public boolean evaluate(Object obj) {
				ProductOption productOption = (ProductOption) obj;
				return productOption.getOptionKey().equalsIgnoreCase(optionName);
			}
		});
		return productOption != null ? productOption.getOptionValue() : "";

	}
}
