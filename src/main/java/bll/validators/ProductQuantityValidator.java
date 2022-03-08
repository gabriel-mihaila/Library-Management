package bll.validators;

import model.Product;

/**
 * The type Product quantity validator.
 */
public class ProductQuantityValidator implements Validator<Product>{
    @Override
    public void validate(Product product) {
        if(product.getQuantity() < 0){
            throw new IllegalArgumentException("Invalid product quantity!");
        }
    }
}
