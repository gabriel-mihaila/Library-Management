package presentation;

import bll.ClientBLL;
import bll.OrderrBLL;
import bll.ProductBLL;
import exceptions.NotExistException;
import model.Client;
import model.Orderr;
import model.Product;
import start.Start;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The type Controller.
 */
public class Controller {
    private View view;
    /**
     * The constant LOGGER.
     */
    protected static final Logger LOGGER = Logger.getLogger(Start.class.getName());

    /**
     * Instantiates a new Controller.
     *
     * @param view the view
     */
    public Controller(View view){
        this.view = view;
        operationsListeners();
    }

    private void operationsListeners(){
        view.getSearchAllClientsButton().addActionListener(e -> {
            Class<?> type = chooseClass();
            String[] header;
            String[] row = new String[type.getDeclaredFields().length];

            view.getTable1().getTableHeader().setVisible(true);
            view.getTable1().setVisible(true);
            header = createTableHeader(type);
            DefaultTableModel table = (DefaultTableModel) view.getTable1().getModel();
            table.setColumnCount(0);
            table.setRowCount(0);
            table.setColumnIdentifiers(header);
            ClientBLL clientBLL = new ClientBLL();
            ArrayList<Client> clients;
            clients = clientBLL.findAllClients();

            Object value;
            for(Client client : clients){
                int i = 0;
                for(Field field : type.getDeclaredFields()){
                    field.setAccessible(true);
                    try {
                        value = field.get(client);
                        row[i++] = value.toString();
                    } catch (IllegalAccessException illegalAccessException) {
                        illegalAccessException.printStackTrace();
                    }
                }
                table.addRow(row);
            }
        });

        view.getAddNewClientButton().addActionListener(e -> {
            String name, address, email;
            int age;

            name = view.getNameClientField().getText();
            address = view.getAddressClientField().getText();
            email = view.getEmailClientField().getText();
            age = Integer.parseInt(view.getAgeClientField().getText());

            Client newClient = new Client(name, address, email, age);
            ClientBLL clientBLL = new ClientBLL();
            try{
                view.getTable1().getTableHeader().setVisible(false);
                view.getTable1().setVisible(false);
                clientBLL.insertClient(newClient);
                JOptionPane.showMessageDialog(view,"<html><font color=#000000> Client adaugat cu success! </font> ","INFO",JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception ex) {
                    LOGGER.log(Level.INFO, ex.getMessage());
                    JOptionPane.showMessageDialog(view,"<html><font color=#ff0000>" + ex.getMessage() + " </font> ","INFO",JOptionPane.INFORMATION_MESSAGE);
                }
        });

        view.getUpdateAnExistingIdButton().addActionListener(e -> {
            String name, address, email;
            int id, age;
            name = view.getNameUpdateClientField().getText();
            address = view.getAddressUpdateClientField().getText();
            email = view.getEmailUpdateClientField().getText();
            id = Integer.parseInt(view.getIdUpdateClientField().getText());
            age = Integer.parseInt(view.getAgeUpdateClientField().getText());

            Client updateClient = new Client(name, address, email, age);
            ClientBLL clientBLL = new ClientBLL();

            try{
                view.getTable1().getTableHeader().setVisible(false);
                view.getTable1().setVisible(false);
                clientBLL.updateClient(id, updateClient);
                JOptionPane.showMessageDialog(view,"<html><font color=#000000> Client modificat cu success! </font> ","INFO",JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception ex) {
                LOGGER.log(Level.INFO, ex.getMessage());
                JOptionPane.showMessageDialog(view,"<html><font color=#ff0000>" + ex.getMessage() + " </font> ","INFO",JOptionPane.INFORMATION_MESSAGE);
            }
        });

        view.getDeleteTheClientButton().addActionListener(e -> {
            String field, value;
            field = Objects.requireNonNull(view.getDeleteClientComboBox().getSelectedItem()).toString();
            value = view.getDeleteClientField().getText();

            ClientBLL clientBLL = new ClientBLL();
            try{
                view.getTable1().getTableHeader().setVisible(false);
                view.getTable1().setVisible(false);
                clientBLL.deleteClient(field, value);
                JOptionPane.showMessageDialog(view,"<html><font color=#000000> Client(i) sters(i) cu success! </font> ","INFO",JOptionPane.INFORMATION_MESSAGE);

            } catch (NotExistException ex) {
                JOptionPane.showMessageDialog(view,"<html><font color=#ff0000>" + ex.getMessage() + " </font> ","INFO",JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                LOGGER.log(Level.INFO, ex.getMessage());
                JOptionPane.showMessageDialog(view,"<html><font color=#ff0000>" + ex.getMessage() + " </font> ","INFO",JOptionPane.INFORMATION_MESSAGE);
            }

        });

        view.getSearchAllProductsButton().addActionListener(e -> {
            Class<?> type = chooseClass();
            String[] header;
            String[] row = new String[type.getDeclaredFields().length];

            view.getTable1().getTableHeader().setVisible(true);
            view.getTable1().setVisible(true);
            header = createTableHeader(type);
            DefaultTableModel table = (DefaultTableModel) view.getTable1().getModel();
            table.setColumnCount(0);
            table.setRowCount(0);
            table.setColumnIdentifiers(header);
            ProductBLL productBLL = new ProductBLL();
            ArrayList<Product> products;
            products = productBLL.findAllProducts();

            Object value;
            for(Product client : products){
                int i = 0;
                for(Field field : type.getDeclaredFields()){
                    field.setAccessible(true);
                    try {
                        value = field.get(client);
                        row[i++] = value.toString();
                    } catch (IllegalAccessException illegalAccessException) {
                        illegalAccessException.printStackTrace();
                    }
                }
                table.addRow(row);
            }
        });

        view.getAddNewProductButton().addActionListener(e -> {
            String name;
            int quantity;
            double price;

            name = view.getNameAddProductField().getText();
            quantity = Integer.parseInt(view.getQuantityAddProductField().getText());
            price = Double.parseDouble(view.getPriceAddProductField().getText());

            Product newProduct = new Product(name, quantity, price);
            ProductBLL clientBLL = new ProductBLL();
            try{
                view.getTable1().getTableHeader().setVisible(false);
                view.getTable1().setVisible(false);
                clientBLL.insertProduct(newProduct);
                JOptionPane.showMessageDialog(view,"<html><font color=#000000> Produs adaugat cu success! </font> ","INFO",JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception ex) {
                LOGGER.log(Level.INFO, ex.getMessage());
                JOptionPane.showMessageDialog(view,"<html><font color=#ff0000>" + ex.getMessage() + " </font> ","INFO",JOptionPane.INFORMATION_MESSAGE);
            }
        });

        view.getUpdateAnExistingIdButton1().addActionListener(e -> {
            String name;
            int id, quantity;
            double price;

            name = view.getNameUpdateProductField().getText();
            quantity = Integer.parseInt(view.getQuantityUpdateProductField().getText());
            price = Double.parseDouble(view.getPriceUpdateProductField().getText());
            id = Integer.parseInt(view.getIdUpdateProductField().getText());

            Product updateProduct = new Product(name, quantity, price);
            ProductBLL productBLL = new ProductBLL();

            try{
                view.getTable1().getTableHeader().setVisible(false);
                view.getTable1().setVisible(false);
                productBLL.updateProduct(id, updateProduct);
                JOptionPane.showMessageDialog(view,"<html><font color=#000000> Produs modificat cu success! </font> ","INFO",JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception ex) {
                LOGGER.log(Level.INFO, ex.getMessage());
                JOptionPane.showMessageDialog(view,"<html><font color=#ff0000>" + ex.getMessage() + " </font> ","INFO",JOptionPane.INFORMATION_MESSAGE);
            }

        });

        view.getDeleteTheProductButton().addActionListener(e -> {
            String field, value;
            field = Objects.requireNonNull(view.getDeleteProductComboBox().getSelectedItem()).toString();
            value = view.getDeleteProductField().getText();

            ProductBLL productBLL = new ProductBLL();
            try{
                view.getTable1().getTableHeader().setVisible(false);
                view.getTable1().setVisible(false);
                productBLL.deleteProduct(field, value);
                JOptionPane.showMessageDialog(view,"<html><font color=#000000> Produs(e) sters(e) cu success! </font> ","INFO",JOptionPane.INFORMATION_MESSAGE);

            } catch (NotExistException ex) {
                JOptionPane.showMessageDialog(view,"<html><font color=#ff0000>" + ex.getMessage() + " </font> ","INFO",JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                LOGGER.log(Level.INFO, ex.getMessage());
                JOptionPane.showMessageDialog(view,"<html><font color=#ff0000>" + ex.getMessage() + " </font> ","INFO",JOptionPane.INFORMATION_MESSAGE);
            }
        });

        view.getSearchAllOrdersButton().addActionListener(e -> {
            Class<?> type = chooseClass();
            String[] header;
            String[] row = new String[type.getDeclaredFields().length];

            view.getTable1().getTableHeader().setVisible(true);
            view.getTable1().setVisible(true);
            header = createTableHeader(type);
            DefaultTableModel table = (DefaultTableModel) view.getTable1().getModel();
            table.setColumnCount(0);
            table.setRowCount(0);
            table.setColumnIdentifiers(header);
            OrderrBLL orderrBLL = new OrderrBLL();
            ArrayList<Orderr> orders;
            orders = orderrBLL.findAllOrders();

            Object value;
            for(Orderr client : orders){
                int i = 0;
                for(Field field : type.getDeclaredFields()){
                    field.setAccessible(true);
                    try {
                        value = field.get(client);
                        row[i++] = value.toString();
                    } catch (IllegalAccessException illegalAccessException) {
                        illegalAccessException.printStackTrace();
                    }
                }
                table.addRow(row);
            }
        });

        view.getAddNewOrderButton().addActionListener(e -> {
            int clientID, productID, quantity;

            clientID = Integer.parseInt(view.getClientidAddOrderFiled().getText());
            productID = Integer.parseInt(view.getProductidAddOrderField().getText());
            quantity = Integer.parseInt(view.getQuantityAddOrderField().getText());

            Orderr newOrderr = new Orderr(clientID, productID, quantity);
            OrderrBLL orderrBLL = new OrderrBLL();
            try{
                view.getTable1().getTableHeader().setVisible(false);
                view.getTable1().setVisible(false);
                orderrBLL.insertOrder(newOrderr);
                JOptionPane.showMessageDialog(view,"<html><font color=#000000> Order adaugat cu success! </font> ","INFO",JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception ex) {
                LOGGER.log(Level.INFO, ex.getMessage());
                JOptionPane.showMessageDialog(view,"<html><font color=#ff0000>" + ex.getMessage() + " </font> ","INFO",JOptionPane.INFORMATION_MESSAGE);
            }
        });

        view.getUpdateAnExistingOrderButton().addActionListener(e -> {
            int id, quantity;
            id = Integer.parseInt(view.getIdUpdateOrderField().getText());
            quantity = Integer.parseInt(view.getQuantityUpdateOrderField().getText());

            OrderrBLL orderrBLL = new OrderrBLL();

            try{
                view.getTable1().getTableHeader().setVisible(false);
                view.getTable1().setVisible(false);
                orderrBLL.updateOrder(id, quantity);
                JOptionPane.showMessageDialog(view,"<html><font color=#000000> Order modificat cu success! </font> ","INFO",JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception ex) {
                LOGGER.log(Level.INFO, ex.getMessage());
                JOptionPane.showMessageDialog(view,"<html><font color=#ff0000>" + ex.getMessage() + " </font> ","INFO",JOptionPane.INFORMATION_MESSAGE);
            }
        });

        view.getDeleteTheOrderButton().addActionListener(e -> {
            int id;

            id  = Integer.parseInt(view.getDeleteOrderField().getText());
            OrderrBLL orderrBLL = new OrderrBLL();
            try{
                view.getTable1().getTableHeader().setVisible(false);
                view.getTable1().setVisible(false);
                orderrBLL.deleteOrder(id);
                JOptionPane.showMessageDialog(view,"<html><font color=#000000> Order sters cu success! </font> ","INFO",JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception ex) {
                LOGGER.log(Level.INFO, ex.getMessage());
                JOptionPane.showMessageDialog(view,"<html><font color=#ff0000>" + ex.getMessage() + " </font> ","INFO",JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    /**
     * Choose class class.
     *
     * @return the class
     */
    public Class<?> chooseClass(){
        if(view.getTablesTabbedPane().getSelectedIndex() == 0){
            return Client.class;
        } else {
            if(view.getTablesTabbedPane().getSelectedIndex() == 1){
                return  Product.class;
            }
            else{
                return  Orderr.class;
            }
        }
    }

    /**
     * Create table header string [ ].
     *
     * @param type the type
     * @return the string [ ]
     */
    public String[] createTableHeader(Class<?> type){
        String[] header = new String[type.getDeclaredFields().length];
        ArrayList<ArrayList<Object>> objects = new ArrayList<>();

        int i = 0;

        for(Field field : type.getDeclaredFields()){
            field.setAccessible(true);
            header[i++] = field.getName();
        }

        return header;
    }

    /**
     * Gets view.
     *
     * @return the view
     */
    public View getView() {
        return view;
    }

    /**
     * Sets view.
     *
     * @param view the view
     */
    public void setView(View view) {
        this.view = view;
    }
}
