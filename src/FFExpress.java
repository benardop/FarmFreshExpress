/**
         * Created by Mom and Dad on 11/2/2016.
         *
         * Class:  FFExpress --- is a class set up to start the application off...
         *         FFExpress - stands for Farm Fresh Express
         *
         */
        public class FFExpress {
    public static void main(String[] args) {
        Customer customer1 = new Customer();

        Produce product1 = new Produce("Gala Apples", 1);
        Produce product2 = new Produce("Rome Apples", 1);
        Produce product3 = new Produce("Peaches", 1.25);
        Produce product4 = new Produce("Vadalia Onions",.5);

        SpecialtyItem specialtyItem1 = new SpecialtyItem("Jam", 5.45);
        SpecialtyItem specialtyItem2 = new SpecialtyItem("Jelly", 5);
        SpecialtyItem specialtyItem3 = new SpecialtyItem("Soap", 6);

        SaleItem saleItem1 = new SaleItem(product1, 2);
        SaleItem saleItem2 = new SaleItem(product2, 5);
        SaleItem saleItem3 = new SaleItem(specialtyItem1, 10);

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(saleItem1);
        shoppingCart.addItem(saleItem2);
        shoppingCart.addItem(saleItem3);

        shoppingCart.printContents();
    }
}