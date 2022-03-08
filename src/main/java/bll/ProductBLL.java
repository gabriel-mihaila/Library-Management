package bll;

import bll.validators.ProductNameValidator;
import bll.validators.ProductPriceValidator;
import bll.validators.ProductQuantityValidator;
import bll.validators.Validator;
import dao.AbstractDAO;
import exceptions.NotExistException;
import model.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * The type Product bll.
 */
public class ProductBLL {
    private final List<Validator<Product>> validators;

    /**
     * Instantiates a new Product bll.
     */
    public ProductBLL(){
        validators = new ArrayList<>();
        validators.add(new ProductNameValidator());
        validators.add(new ProductPriceValidator());
        validators.add(new ProductQuantityValidator());
    }

    /**
     * Find all products array list.
     *
     * @return the array list
     */
    public ArrayList<Product> findAllProducts(){
        ArrayList<Product> products = new ArrayList<>(new AbstractDAO<>(Product.class).findAll());
        if(products.size() == 0) {
            throw new NoSuchElementException("No such product found!");
        }
        return products;
    }

    /**
     * Delete product.
     *
     * @param field the field
     * @param value the value
     * @throws NotExistException the not exist exception
     */
    public void deleteProduct(String field, String value) throws NotExistException {
        int existingItem, oldProductTableSize, newProductTableSize;
        ArrayList<Product> products = new ArrayList<>(new AbstractDAO<>(Product.class).findAll());
        oldProductTableSize = products.size();
        existingItem = new AbstractDAO<>(Product.class).delete(field, value);
        products = new ArrayList<>(new AbstractDAO<>(Product.class).findAll());
        newProductTableSize = products.size();

        if(existingItem == 1 && oldProductTableSize == newProductTableSize){
            throw new NotExistException("The product doesn't exist in db!");
        }
    }

    /**
     * Insert product.
     *
     * @param product the product
     */
    public void insertProduct(Product product) {
        for(Validator<Product> v : validators) {
            v.validate(product);
        }
        new AbstractDAO<>(Product.class).insert(product);
    }

    /**
     * Update product.
     *
     * @param id      the id
     * @param product the product
     * @throws NotExistException the not exist exception
     */
    public void updateProduct(int id, Product product) throws NotExistException {
        for(Validator<Product> v : validators){
            v.validate(product);
        }
        Product st = new AbstractDAO<>(Product.class).findById(id);

        if (st == null) {
            throw new NotExistException("The product doesn't exist in db, you cannot update this row!");
        }

        new AbstractDAO<>(Product.class).update(id, product);
    }
}
