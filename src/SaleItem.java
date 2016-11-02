/**
 * Created by Mom and Dad on 11/2/2016.
 *
 * Class:  SaleItem
 * Purpose:  To hold a Pair:  Product and Quantity
 * e.g.  10 apples  [quantity of 10;  product is apples]
 */
public class SaleItem {
    private Product product;
    private int quantity;
    private double cost;
    //TODO - decide if we will do a measurement as well as quantity.  e.g.  lbs, item, bunch, etc.
    //TODO  e.g.  3lbs of onions, 12 apples, 1 bunch of radishes

    //CONSTRUCTORS
    SaleItem(Product product, int quantity){
        this.product = product;
        this.quantity = quantity;
        this.cost = quantity * product.getPrice();
    }

    //GETTERS AND SETTERS
    public int getQuantity() {
        return quantity;
    }

    public Product getProduct() {
        return product;
    }

    public double getCost() {
        return cost;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    //METHODS
    public String toString() {
        return quantity + " " + product.getItemName();
    }

}
