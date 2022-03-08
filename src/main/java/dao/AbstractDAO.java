package dao;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;

/**
 * The type Abstract dao.
 *
 * @param <T> the type parameter
 */
public class AbstractDAO<T> {
    /**
     * The constant LOGGER.
     */
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    private final Class<T> type;

    /**
     * Instantiates a new Abstract dao.
     *
     * @param type the type
     */
    @SuppressWarnings("unchecked")
    public AbstractDAO(Class<T> type) {
        this.type = type;

    }

    private String createSelectAllQuery(){
        StringBuilder stringBuilder =new StringBuilder();
        stringBuilder.append("SELECT ");
        stringBuilder.append(" * ");
        stringBuilder.append("FROM ");
        stringBuilder.append(type.getSimpleName());
        return stringBuilder.toString();
    }

    /**
     * Find all list.
     *
     * @return the list
     */
    public List<T> findAll() {
        Connection connection = null;
        PreparedStatement statement= null;
        ResultSet resultSet = null;
        String query = createSelectAllQuery();
        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            return createObjects(resultSet);
        }
        catch (SQLException e){
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findall " + e.getMessage());
        }
        return null;
    }

    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }

    /**
     * Find by id t.
     *
     * @param id the id
     * @return the t
     */
    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
           LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());

        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T)ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Last id int.
     *
     * @param objects the objects
     * @return the int
     */
    public int lastID(List<T> objects){
        int id = 0;
        Field field;
        for(T object : objects){
            try {
                field = object.getClass().getDeclaredField("id");
                field.setAccessible(true);
                try {
                    id = field.getInt(object);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        return id;
    }

    /**
     * Create insert query string.
     *
     * @param t the t
     * @return the string
     */
    public String createInsertQuery(T t){
        int id = lastID(new AbstractDAO<T>(this.type).findAll()) + 1;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("INSERT INTO ");
        stringBuilder.append(type.getSimpleName());
        stringBuilder.append(" (");
        for(Field field : t.getClass().getDeclaredFields()){
            field.setAccessible(true);
            stringBuilder.append(field.getName());
            stringBuilder.append(",");
        }
        stringBuilder.setLength(stringBuilder.length() - 1);
        stringBuilder.append(") ");
        stringBuilder.append("VALUES (");
        for(Field field : t.getClass().getDeclaredFields()){
            field.setAccessible(true);
            try {
                stringBuilder.append("\'");
                if(field.getName().equals("id")){
                    stringBuilder.append(id);
                }else{
                    stringBuilder.append(field.get(t));
                }
                stringBuilder.append("\'");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            stringBuilder.append(",");
        }
        stringBuilder.setLength(stringBuilder.length() - 1);
        stringBuilder.append(")");

        return stringBuilder.toString();
    }

    /**
     * Insert.
     *
     * @param t the t
     */
    public void insert(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createInsertQuery(t);
        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.execute();
        }catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
            ///todo in gui
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

    }

    /**
     * Create update query string.
     *
     * @param id the id
     * @param t  the t
     * @return the string
     */
    public String createUpdateQuery(int id, T t){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("UPDATE ");
        stringBuilder.append(type.getSimpleName());
        stringBuilder.append(" SET ");
        for(Field field : t.getClass().getDeclaredFields()){
            field.setAccessible(true);
            if(!field.getName().equals("id")){
                stringBuilder.append(field.getName());
                stringBuilder.append("=");
                try {
                    stringBuilder.append("\'");
                    stringBuilder.append(field.get(t));
                    stringBuilder.append("\'");
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                stringBuilder.append(",");
            }

        }
        stringBuilder.setLength(stringBuilder.length() - 1);
        stringBuilder.append(" WHERE id = ");
        stringBuilder.append(id);

        return stringBuilder.toString();
    }

    /**
     * Update.
     *
     * @param id the id
     * @param t  the t
     */
    public void update(int id, T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createUpdateQuery(id, t);
        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.execute();
        }catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:update " + e.getMessage());
            ///todo in gui
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * Create delete query string.
     *
     * @param field the field
     * @param value the value
     * @return the string
     */
    public String createDeleteQuery(String field, String value){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("DELETE FROM ");
        stringBuilder.append(type.getSimpleName());
        stringBuilder.append(" WHERE " + field + " = " + "\'" + value + "\'");
        return stringBuilder.toString();
    }

    /**
     * Delete int.
     *
     * @param field the field
     * @param value the value
     * @return the int
     */
    public int delete(String field, String value){
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createDeleteQuery(field, value);

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.executeUpdate();
            return 1;

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:delete " + e.getMessage());
            return 0;
            ///todo in gui
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

    }
}
