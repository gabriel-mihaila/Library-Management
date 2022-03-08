package dao;

import connection.ConnectionFactory;
import model.Orderr;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;

/**
 * The type Product dao.
 */
public class ProductDAO extends AbstractDAO<Product> {
    /**
     * Instantiates a new Product dao.
     *
     * @param type the type
     */
    public ProductDAO(Class<Product> type) {
        super(type);
    }

    /**
     * Create update quantity query string.
     *
     * @param id       the id
     * @param quantity the quantity
     * @return the string
     */
    public String createUpdateQuantityQuery(int id, int quantity){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("UPDATE product");
        stringBuilder.append(" SET quantity = ");
        stringBuilder.append(quantity);
        stringBuilder.append(" WHERE id = ");
        stringBuilder.append(id);

        return  stringBuilder.toString();
    }

    /**
     * Update.
     *
     * @param id       the id
     * @param quantity the quantity
     */
    public void update(int id, int quantity) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createUpdateQuantityQuery(id, quantity);
        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.execute();

        }catch (SQLException e) {
            LOGGER.log(Level.WARNING, Orderr.class + "ProductDAO:update " + e.getMessage());
            ///todo in gui
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

    }
}
