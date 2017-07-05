package farmfresh.business;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Purpose: Invoice represents an Invoice created once a User makes a purchase.
 * In essence, all of the items of a Cart are copied to the Invoice upon Order completion.
 * The invoice contains a List of the Products and how many of each Product was purchased.
 * NOTE:  The invoiceNumber field and uniquely identifies and Invoice.
 * The invoiceNumber field is stored as the InvoiceID field in the Invoice table.
 *
 * @author Amy Radtke
 * @version 1.0  07/01/2017
 */
public class Invoice {

    /**
     * User that Invoice belongs to
     */
    private User user;
    /**
     * A List of the Products and how many of each Product was purchased.
     */
    private List<LineItem> lineItems;

    /**
     * Date invoice was saved to the Database
     */
    private Date invoiceDate;
    /**
     * Number that Uniquely identifies the Invoice (InvoiceID in the Invoice table)
     */
    private long invoiceNumber;

    /**
     * Flag indicating if Invoice has been processed.
     * It is processed when the items is shipped.
     */
    private boolean isProcessed;

    //CONSTRUCTOR
    public Invoice(){
        //All variables automatically set to default values
    }

    /**
     * @return User that Invoice belongs to
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user User that Invoice belongs to
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return all LineItems associated with the Invoice.
     * A LineItem records what the Product is and how many have been ordered
     */
    public List<LineItem> getLineItems() {
        return lineItems;
    }

    /**
     * @param lineItems a List of LineItems to be saved to the Invoice
     * A LineItem records what the Product is and how many have been ordered
     */
    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }
    /**
     * @return Date Invoice was saved to the Database
     */
    public Date getInvoiceDate() {
        return invoiceDate;
    }

    /**
     * @param invoiceDate Invoice was saved to the Database
     */
    public void setInvoiceDate(Date invoiceDate) {this.invoiceDate = invoiceDate;}

    /**
    * @return Invoice Number the number that uniquely identifies an Invoice.
     * The invoiceNumber field is stored as the InvoiceID field in the Invoice table.
    */
    public long getInvoiceNumber() {
        return invoiceNumber;
    }

    /**
     * @param invoiceNumber the number that uniquely identifies an Invoice.
     * The invoiceNumber field is stored as the InvoiceID field in the Invoice table.
     */
    public void setInvoiceNumber(long invoiceNumber) {this.invoiceNumber = invoiceNumber;}

    //************************************************************************
    // isProcessed flag is only manipulated through SQL logic.
    //************************************************************************

    /**
     * @return Total Cost of all Products purchased on Invoicce  (e.g.  10 apples cost $5)
     */
    public double getTotalCost(){
        double invoiceTotal = 0;
        for (LineItem lineItem: lineItems) {
            invoiceTotal += lineItem.getTotalCost();
        }
        return invoiceTotal;
    }

    /**
     * @return A Formatted String in Currency Format containing Total Cost
     * of the Items on the Invoice.  Called within jsp files.
     */
    public String getTotalCostInCurrencyFormat(){
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        return currency.format(getTotalCost());
    }

    /**
     * @return A Formatted String in Date Format containing the
     * Date the Invoice was Saved to the Database.  Called within jsp files.
     */
    public String getInvoiceDateInSimpleDateFormat(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy' 'HH:mm:ss");
        return dateFormat.format(getInvoiceDate());
    }

}//End - Invoice.java
