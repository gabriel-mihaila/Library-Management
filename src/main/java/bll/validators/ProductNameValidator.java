package bll.validators;

import model.Product;

/**
 * The type Product name validator.
 */
public class ProductNameValidator implements Validator<Product> {

    @Override
    public void validate(Product product) {
        if(product.getName() == null || product.getName().length() == 0){
            throw new IllegalArgumentException("The product does not have a name");
        }
    }
}
