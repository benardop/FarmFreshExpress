package FarmFresh.business;

import java.util.*;

/**
 * Created by Mom and Dad on 11/4/2016.
 */
public class Invoice {
    // PRIVATE MEMBER VARIABLES
    private User user;
    private List<LineItem> lineItemList;
    private Date invoiceDate;
    private int invoiceNumber;

    //PUBLIC METHODS:  SETTERS AND GETTERS
    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setLineItemList(List<LineItem> lineItemList) {
        this.lineItemList = lineItemList;
    }

    public List<LineItem> getLineItemList() {
        return lineItemList;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceNumber(int invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public int getInvoiceNumber() {
        return invoiceNumber;
    }

    //PUBLIC METHODS:  SPECIALTY FUNCTIONALITY
    public double getInvoiceTotal(){
        double total = 0;
        for (LineItem lineItem: lineItemList) {
            total += lineItem.getTotal();
        }
        return total;  //TODO Needs to be coded
    }

    public String getInvoiceTotalCurrencyFormat(){
        return "Currency Format";  //TODO Needs to be coded
    }

}
