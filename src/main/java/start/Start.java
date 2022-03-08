package start;

import java.sql.SQLException;
import java.util.logging.Logger;

import presentation.Controller;
import presentation.View;

import javax.swing.*;


public class Start {
    protected static final Logger LOGGER = Logger.getLogger(Start.class.getName());

    public static void main(String[] args) throws SQLException {

        JFrame frame = new JFrame("Order management simulator");
        View view = new View();
        frame.setContentPane(view.getTotalPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        new Controller(view);

        /*ClientBLL clientBll = new ClientBLL();
        ProductBLL productBLL = new ProductBLL();
        OrderrBLL orderBLL = new OrderrBLL();

        ArrayList<Client> clients = null;
        ArrayList<Product> products = null;
        ArrayList<Orderr> orders = null;

        Client client1 = null;
        Product product1 = null;
        Orderr orderr1 = null;
        Client client2 = new Client("Bagut Ana-Maria", "Str. Stadionuui 7", "bagut_ana@gmail.com", 21);
        Product product2 = new Product("suruburi", 41, 6.3);
        Orderr orderr2 = new Orderr(3,2,3);

        int sizee;

        try {*/
           // client1 = clientBll.findClientById(100);
           // product1 = new AbstractDAO<>(Product.class).findById(100);
           // clients = clientBll.findAllClients();
           // clientBll.deleteClient("age","30");
            //clientBll.insertClient(client2);
            //System.out.println(new AbstractDAO<>(Client.class).createUpdateQuery(2, client2));
          //  clientBll.updateClient(5, client2);

            //product1 = new AbstractDAO<>(Product.class).findById(3);
            //products = productBLL.findAllProducts();
           // productBLL.insertProduct(product2);
            //productBLL.deleteProduct("name", "cuie");
           // productBLL.updateProduct(4, product2);

            //orderr1 = new AbstractDAO<>(Orderr.class).findById(2);
            //orders = orderBLL.findAllOrders();
           // orderBLL.insertOrder(orderr2);
           // orderBLL.deleteOrder(8);
            //orderBLL.updateOrder(4, 5);
            //orderBLL.writePDF();


        /*} //catch (NotExistException e) {
           //System.out.println(e.getMessage());
        //}
        catch (Exception ex) {
            LOGGER.log(Level.INFO, ex.getMessage());
            JOptionPane.showMessageDialog(null,"<html><font color=#ff0000>" + ex.getMessage() + " </font> ","INFO",JOptionPane.INFORMATION_MESSAGE);
        }*/
        // obtain field-value pairs for object through reflection
       // ReflectionExample.retrieveProperties(client1);

    }

}
