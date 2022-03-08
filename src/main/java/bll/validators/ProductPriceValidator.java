package bll.validators;

import model.Product;

/**
 * The type Product price validator.
 */
public class ProductPriceValidator implements Validator<Product>{
    private static final double MIN_PRICE = 0.0f;
    private static final double MAX_PRICE = 10000.0f;

    @Override
    public void validate(Product product) {
        if(product.getPrice() < MIN_PRICE || product.getPrice() > MAX_PRICE){
            throw new IllegalArgumentException("The ProductPrice limit is not respected!");
        }
    }
}
