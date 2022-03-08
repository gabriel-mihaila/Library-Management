package dao;
import connection.ConnectionFactory;
import model.Orderr;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;

/**
 * The type Orderr dao.
 */
public class OrderrDAO extends AbstractDAO<Orderr> {
    /**
     * Instantiates a new Orderr dao.
     *
     * @param type the type
     */
    public OrderrDAO(Class<Orderr> type) {
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
        stringBuilder.append("UPDATE orderr");
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
            LOGGER.log(Level.WARNING, Orderr.class + "OrderrDAO:update " + e.getMessage());

        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

    }

    /**
     * Create delete by id query string.
     *
     * @param id the id
     * @return the string
     */
    public String createDeleteByIDQuery(int id){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("DELETE FROM orderr WHERE id = ");
        stringBuilder.append(id);

        return  stringBuilder.toString();
    }

    /**
     * Delete int.
     *
     * @param id the id
     * @return the int
     */
    public int delete(int id){
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createDeleteByIDQuery(id);

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.executeUpdate();
            return 1;

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, Orderr.class + "DAO:delete " + e.getMessage());
            return 0;

        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

    }
}
