package farmfresh.business;

/**
 * Created by Mom and Dad on 11/4/2016.
 */
public class LineItem {
    //PRIVATE INSTANCE VARIABLES
    private Product product;
    private int quantity;

    //PUBLIC METHODS: SETTERS AND GETTERS

    public void setProduct(Product item) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    //PUBLIC METHODS:  SPECIAL FUNCTIONALITY
    public double getTotal(){
        return product.getPrice() * quantity;
    }

    public String getTotalCurrencyFormat(){
        return "currencyFormat";
    }
}
