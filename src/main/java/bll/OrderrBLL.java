package bll;
import bll.validators.OrderQuantityValidator;
import bll.validators.Validator;
import dao.AbstractDAO;
import dao.OrderrDAO;
import dao.ProductDAO;
import model.Orderr;
import model.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * The type Orderr bll.
 */
public class OrderrBLL {
    private final List<Validator<Orderr>> validators;

    /**
     * Instantiates a new Orderr bll.
     */
    public OrderrBLL(){
        validators = new ArrayList<>();
        validators.add(new OrderQuantityValidator());
    }

    /**
     * Find all orders array list.
     *
     * @return the array list
     */
    public ArrayList<Orderr> findAllOrders(){
        ArrayList<Orderr> orders = new ArrayList<>(new AbstractDAO<>(Orderr.class).findAll());
        if(orders.size() == 0) {
            throw new NoSuchElementException("No such order found!");
        }
        return orders;
    }

    /**
     * Insert order.
     *
     * @param orderr the orderr
     */
    public void insertOrder(Orderr orderr) {
        for(Validator<Orderr> v : validators) {
            v.validate(orderr);
        }
        new AbstractDAO<>(Orderr.class).insert(orderr);
        int newQuantity;
        newQuantity = (new AbstractDAO<>(Product.class).findById(orderr.getProductID()).getQuantity()) - orderr.getQuantity();
        new ProductDAO(Product.class).update(orderr.getProductID(), newQuantity);
        orderr.writePDF(1);
    }

    /**
     * Delete order.
     *
     * @param id the id
     */
    public void deleteOrder(int id){
        int newQuantity;
        Orderr findOrderr;
        findOrderr = new AbstractDAO<>(Orderr.class).findById(id);
        newQuantity = findOrderr.getQuantity() + new AbstractDAO<>(Product.class).findById(findOrderr.getProductID()).getQuantity();
        findOrderr.writePDF(2);
        new ProductDAO(Product.class).update(findOrderr.getProductID(), newQuantity);
        new OrderrDAO(Orderr.class).delete(id);
    }

    /**
     * Update order.
     *
     * @param id       the id
     * @param quantity the quantity
     */
    public void updateOrder(int id, int quantity){
        Orderr oldOrderr, newOrderr;
        int newQuantity;
        if(quantity <= 0){
            throw new IllegalArgumentException("Invalid order quantity!");
        }
        oldOrderr = new AbstractDAO<>(Orderr.class).findById(id);
        newQuantity = new AbstractDAO<>(Product.class).findById(oldOrderr.getProductID()).getQuantity();
        if(oldOrderr.getQuantity() < quantity){
            newQuantity = newQuantity - (quantity - oldOrderr.getQuantity());
        }
        else{
            newQuantity = newQuantity + (oldOrderr.getQuantity() - quantity);
        }
        if(newQuantity < 0){
            throw new IllegalArgumentException("Invalid order quantity!");
        }
        new OrderrDAO(Orderr.class).update(id, quantity);
        newOrderr = new AbstractDAO<>(Orderr.class).findById(id);
        newOrderr.writePDF(3);
        new ProductDAO(Product.class).update(oldOrderr.getProductID(), newQuantity);
    }
}
