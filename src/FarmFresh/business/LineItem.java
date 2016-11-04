package FarmFresh.business;

/**
 * Created by Mom and Dad on 11/4/2016.
 */
public class LineItem {
    //PRIVATE INSTANCE VARIABLES
    private Product item;
    private int quantity;

    //PUBLIC METHODS: SETTERS AND GETTERS

    public void setItem(Product item) {
        this.item = item;
    }

    public Product getItem() {
        return item;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    //PUBLIC METHODS:  SPECIAL FUNCTIONALITY
    public double getTotal(){
        return item.getPrice() * quantity; //TODO code this
    }

    public String getTotalCurrencyFormat(){
        return "currencyFormat";
    }
}
