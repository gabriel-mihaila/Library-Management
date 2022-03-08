package presentation;

import javax.swing.*;
import java.awt.*;

public class View extends Component {

    private JTable table1;
    private JTabbedPane tablesTabbedPane;
    private JPanel totalPanel;
    private JTabbedPane clientTabbedPane;
    private JButton searchAllClientsButton;
    private JPanel tableZonePanel;
    private JScrollPane tableScrollPane;
    private JTextField nameClientField;
    private JTextField addressClientField;
    private JTextField emailClientField;
    private JTextField ageClientField;
    private JButton addNewClientButton;
    private JTextField idUpdateClientField;
    private JTextField nameUpdateClientField;
    private JTextField addressUpdateClientField;
    private JTextField emailUpdateClientField;
    private JTextField ageUpdateClientField;
    private JButton updateAnExistingIdButton;
    private JComboBox deleteClientComboBox;
    private JTextField deleteClientField;
    private JButton deleteTheClientButton;
    private JTabbedPane tabbedPane1;
    private JButton searchAllProductsButton;
    private JTextField nameAddProductField;
    private JTextField quantityAddProductField;
    private JTextField priceAddProductField;
    private JButton addNewProductButton;
    private JTextField idUpdateProductField;
    private JTextField nameUpdateProductField;
    private JTextField quantityUpdateProductField;
    private JTextField priceUpdateProductField;
    private JButton updateAnExistingIdButton1;
    private JComboBox deleteProductComboBox;
    private JTextField deleteProductField;
    private JButton deleteTheProductButton;
    private JTabbedPane tabbedPane2;
    private JButton searchAllOrdersButton;
    private JTextField clientidAddOrderFiled;
    private JTextField productidAddOrderField;
    private JTextField quantityAddOrderField;
    private JButton addNewOrderButton;
    private JTextField idUpdateOrderField;
    private JTextField quantityUpdateOrderField;
    private JButton updateAnExistingOrderButton;
    private JTextField deleteOrderField;
    private JButton deleteTheOrderButton;

    public View() {

    }

    public JTable getTable1() {
        return table1;
    }

    public JTabbedPane getTablesTabbedPane() {
        return tablesTabbedPane;
    }

    public JPanel getTotalPanel() {
        return totalPanel;
    }

    public JTabbedPane getClientTabbedPane() {
        return clientTabbedPane;
    }

    public JButton getSearchAllClientsButton() {
        return searchAllClientsButton;
    }

    public JScrollPane getTableScrollPane() {
        return tableScrollPane;
    }

    public JPanel getTableZonePanel() {
        return tableZonePanel;
    }

    public JTextField getNameClientField() {
        return nameClientField;
    }

    public JTextField getAddressClientField() {
        return addressClientField;
    }

    public JTextField getEmailClientField() {
        return emailClientField;
    }

    public JTextField getAgeClientField() {
        return ageClientField;
    }

    public JButton getAddNewClientButton() {
        return addNewClientButton;
    }

    public JTextField getIdUpdateClientField() {
        return idUpdateClientField;
    }

    public JTextField getNameUpdateClientField() {
        return nameUpdateClientField;
    }

    public JTextField getAddressUpdateClientField() {
        return addressUpdateClientField;
    }

    public JTextField getEmailUpdateClientField() {
        return emailUpdateClientField;
    }

    public JTextField getAgeUpdateClientField() {
        return ageUpdateClientField;
    }

    public JButton getUpdateAnExistingIdButton() {
        return updateAnExistingIdButton;
    }

    public JComboBox getDeleteClientComboBox() {
        return deleteClientComboBox;
    }

    public JTextField getDeleteClientField() {
        return deleteClientField;
    }

    public JButton getDeleteTheClientButton() {
        return deleteTheClientButton;
    }

    public JTabbedPane getTabbedPane1() {
        return tabbedPane1;
    }

    public JButton getSearchAllProductsButton() {
        return searchAllProductsButton;
    }

    public JTextField getNameAddProductField() {
        return nameAddProductField;
    }

    public JTextField getQuantityAddProductField() {
        return quantityAddProductField;
    }

    public JTextField getPriceAddProductField() {
        return priceAddProductField;
    }

    public JButton getAddNewProductButton() {
        return addNewProductButton;
    }

    public JTextField getIdUpdateProductField() {
        return idUpdateProductField;
    }

    public JTextField getNameUpdateProductField() {
        return nameUpdateProductField;
    }

    public JTextField getQuantityUpdateProductField() {
        return quantityUpdateProductField;
    }

    public JTextField getPriceUpdateProductField() {
        return priceUpdateProductField;
    }

    public JButton getUpdateAnExistingIdButton1() {
        return updateAnExistingIdButton1;
    }

    public JComboBox getDeleteProductComboBox() {
        return deleteProductComboBox;
    }

    public JTextField getDeleteProductField() {
        return deleteProductField;
    }

    public JButton getDeleteTheProductButton() {
        return deleteTheProductButton;
    }

    public JTabbedPane getTabbedPane2() {
        return tabbedPane2;
    }

    public JButton getSearchAllOrdersButton() {
        return searchAllOrdersButton;
    }

    public JTextField getClientidAddOrderFiled() {
        return clientidAddOrderFiled;
    }

    public JTextField getProductidAddOrderField() {
        return productidAddOrderField;
    }

    public JTextField getQuantityAddOrderField() {
        return quantityAddOrderField;
    }

    public JButton getAddNewOrderButton() {
        return addNewOrderButton;
    }

    public JTextField getIdUpdateOrderField() {
        return idUpdateOrderField;
    }

    public JTextField getQuantityUpdateOrderField() {
        return quantityUpdateOrderField;
    }

    public JButton getUpdateAnExistingOrderButton() {
        return updateAnExistingOrderButton;
    }

    public JTextField getDeleteOrderField() {
        return deleteOrderField;
    }

    public JButton getDeleteTheOrderButton() {
        return deleteTheOrderButton;
    }
}
