import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mom and Dad on 11/2/2016.
 */

public class ShoppingCart {
    private List<SaleItem> products;

    ShoppingCart(){
        products = new ArrayList<SaleItem>();  //emptylist
    }

    public void addItem(SaleItem saleItem){
        products.add(saleItem);
    }

    public void printContents(){

        System.out.println("CART CONTENTS 1: No Formatting - Count Displayed While Looping - Use of Overloaded toString()");
        for (int i = 0; i < products.size(); i++) {
            System.out.printf("%s%d%s%s%n", "Item #", i+1, ": ", products.get(i));
        }
        System.out.println();

        System.out.println("CART CONTENTS 2:  Used text formatting - Tallied Up Total");
        System.out.printf("%s%-20s%-5s%n", "AMT  ", "ITEM", "COST") ;
        double total = 0;
        for (SaleItem saleItem: products) {
            Product product = saleItem.getProduct();
            double cost = saleItem.getCost();
            System.out.printf("%3d%s%-20s%5.2f%n", saleItem.getQuantity(),"  ", product.getItemName(), cost) ;
            //BEN can you call two methods back-to-back saleItem.getProduct.getItemName()
            total += cost;
        }
        System.out.printf("%24s%6.2f","TOTAL COST", total);  //TODO make it bold

    }

}
