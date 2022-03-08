package model;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import dao.AbstractDAO;

import java.io.FileOutputStream;

/**
 * The type Orderr.
 */
public class Orderr {
    private int id;
    private int clientID;
    private int productID;
    private int quantity;

    /**
     * Instantiates a new Orderr.
     */
    public Orderr(){

    }

    /**
     * Instantiates a new Orderr.
     *
     * @param id        the id
     * @param clientID  the client id
     * @param productID the product id
     * @param quantity  the quantity
     */
    public Orderr(int id, int clientID, int productID, int quantity) {
        this.id = id;
        this.clientID = clientID;
        this.productID = productID;
        this.quantity = quantity;
    }

    /**
     * Instantiates a new Orderr.
     *
     * @param clientID  the client id
     * @param productID the product id
     * @param quantity  the quantity
     */
    public Orderr(int clientID, int productID, int quantity) {
        this.clientID = clientID;
        this.productID = productID;
        this.quantity = quantity;
    }

    /**
     * Write pdf.
     *
     * @param billID the bill id
     */
    public void writePDF(int billID) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("order_details.pdf"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        document.open();
        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, BaseColor.BLACK);
        String client, product;
        int quantity;
        client = new AbstractDAO<>(Client.class).findById(this.clientID).getName();
        product = new AbstractDAO<>(Product.class).findById(this.productID).getName();
        quantity = this.quantity;
        Chunk chunk1, chunk2, chunk3, chunk4, chunk5;
        chunk1 = new Chunk("Order with details: ", font);
        chunk2 = new Chunk("Client: " + client, font);
        chunk3 = new Chunk("Product: " + product, font);
        chunk4 = new Chunk("Quantity: " + quantity, font);
        if(billID == 1){ //insert
            chunk5 = new Chunk("Order sent successfully!", font);
        }
        else if(billID == 2){ //delete
            chunk5 = new Chunk("Order returned successfully!", font);
        } else { //update
            chunk5 = new Chunk("Order updated successfully!", font);
        }
        try {
            document.add(new Paragraph(chunk1));
            document.add(new Paragraph(chunk2));
            document.add(new Paragraph(chunk3));
            document.add(new Paragraph(chunk4));
            document.add(new Paragraph(chunk5));

        } catch (DocumentException e) {
            e.printStackTrace();
        }
        document.close();
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets client id.
     *
     * @return the client id
     */
    public int getClientID() {
        return clientID;
    }

    /**
     * Sets client id.
     *
     * @param clientID the client id
     */
    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    /**
     * Gets product id.
     *
     * @return the product id
     */
    public int getProductID() {
        return productID;
    }

    /**
     * Sets product id.
     *
     * @param productID the product id
     */
    public void setProductID(int productID) {
        this.productID = productID;
    }

    /**
     * Gets quantity.
     *
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets quantity.
     *
     * @param quantity the quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String toString(){
        return "Order [id = " + id + ", clientID = " + clientID + ", priductID = " + productID + ", quantity = " + quantity + "]";
    }
}
