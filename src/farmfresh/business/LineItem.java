package farmfresh.business;

/**
 * Created by Mom and Dad on 11/4/2016.
 */
public class LineItem {
    //PRIVATE INSTANCE VARIABLES
    private Product product;
    private int quantity;

    //CONSTRUCTOR
    public LineItem(){
        //All variables automatically set to default values  //TODO - is this correct?
    }

    //PUBLIC METHODS: GETTERS AND SETTERS
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product item) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    //PUBLIC METHODS:  SPECIAL FUNCTIONALITY
    public double getTotal(){
        return product.getPrice() * quantity;
    }

    public String getTotalCurrencyFormat(){
        return "currencyFormat";
    }

}
