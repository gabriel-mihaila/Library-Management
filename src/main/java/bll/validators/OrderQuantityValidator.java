package bll.validators;

import dao.AbstractDAO;
import model.Orderr;
import model.Product;

/**
 * The type Order quantity validator.
 */
public class OrderQuantityValidator implements Validator<Orderr>{
    @Override
    public void validate(Orderr orderr) {
        if(orderr.getQuantity() > new AbstractDAO<>(Product.class).findById(orderr.getProductID()).getQuantity() || orderr.getQuantity() <= 0){
            throw new IllegalArgumentException("Invalid order quantity!");
        }
    }
}
