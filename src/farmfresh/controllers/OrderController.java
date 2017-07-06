package farmfresh.controllers;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import farmfresh.business.*;
import farmfresh.data.InvoiceDB;
import farmfresh.data.ProductDB;
import farmfresh.data.UserDB;
import farmfresh.util.CookieUtil;
import farmfresh.util.MailUtil;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.mail.*;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by Mom and Dad on 11/8/2016.
 *
 * Purpose:  to manage any actions relating to Adding, Updating or Deleting Items
 * from the Cart.
 *
 * NOTE:  The Cart object and it's LineItems are logical not physical - Meaning
 * they exist in memory and are not persisted to the Database.  When a user Checkouts
 * their cart, pays and completes their Order - the Order and it's Line Items will
 * be saved to the Database.
 *
 * @author Amy Radtke
 * @version 1.0  07/01/2017
 */
public class OrderController extends HttpServlet {

    private static final String defaultURL = "/cart/cart.jsp";

    /**
     *  Handles no URLs at this time
     */
    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException, ServletException {

        String requestURI = request.getRequestURI();
        String url = defaultURL;

        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);

    }//End - doGet()

    /**
     *  Handles URLs ending in /addItem, /updateItem, and /removeItem
     */
    @Override
    public void doPost(HttpServletRequest request,
                      HttpServletResponse response) throws IOException, ServletException {

        String requestURI = request.getRequestURI();
        String url = "";

        if (requestURI.endsWith("/addItem")){
            url = addItem(request, response);
        }else if (requestURI.endsWith("/updateItem")) {
            url = updateItem(request, response);
        }else if (requestURI.endsWith("/removeItem")){
            url = removeItem(request, response);
        }

        getServletContext()
                .getRequestDispatcher(url)
                .forward(request,response);

    }//End - doPost

    /**
     * <br>
     * Adds the given number of Products to the Cart
     * <br><br>
     *  If a Line Item for that Product already exists in the Cart
     *  its Quantity is increased based on the Quantity given.
     *  If the Line Item for that Product doesn't already exist
     *  a Line Item is created for that Product and Quantity is set
     *  The URL for the Products page is returned - initiating display of that window.
     * <br><br>
     * Request object Parameters:<br>
     * "productId" - the Product's Unique ID
     * "productQuantity" - the number of items (of that product) to add to the cart
     * <br><br>
     * Session object Attributes:<br>
     * "cart" - Cart object
     *
     * @return  URL to /catalog/products.jsp which displays the Products page
     */
    private String addItem(HttpServletRequest request, HttpServletResponse response){

        HttpSession session = request.getSession();

        Cart cart =(Cart)session.getAttribute("cart");

        if (cart == null)
            cart = new Cart();

        String productId = request.getParameter("productId");
        String quantityString = request.getParameter("productQuantity");
        int quantityInt = Integer.parseInt(quantityString);
        LineItem lineItem = cart.getLineItem(Long.parseLong(productId));

        // If a lineItem exists for that product - increase its quantity
        // else add a new lineItem for that product
        if (lineItem != null){
            lineItem.increaseQuantity(quantityInt);
        } else {
            Product product = ProductDB.selectProduct(productId);

            if (product != null) {
                lineItem = new LineItem();
                lineItem.setProduct(product);
                lineItem.setQuantity(quantityInt);
                cart.addLineItem(lineItem);
            }
        }
        session.setAttribute("cart", cart);

        return "/catalog/products.jsp";
    }

    /**
     * <br>
     * Update the Quantity of the given Product's LineItem
     * If the Quantity is Zero - Delete the LineItem from the Cart
     * The URL for the Cart page is returned - initiating display of that window.
     * <br><br>
     * Request object Parameters:<br>
     * "productId" - the Product's Unique ID
     * "updateQuantity" - the number of items (of that product) to add to the cart
     * <br><br>
     * Session object Attributes:<br>
     * "cart" - Cart object
     *
     * @return  URL to /cart/cart.jsp which displays the Cart page
     */
    private String updateItem(HttpServletRequest request, HttpServletResponse response){

        String productId = request.getParameter("productId");
        String quantity = request.getParameter("updateQuantity");

        HttpSession session = request.getSession();
        Cart cart =(Cart)session.getAttribute("cart");

        int quantityInt;
        try{
            quantityInt = Integer.parseInt(quantity);
            if (quantityInt < 0)
                quantityInt = 0;
        }catch (NumberFormatException ex){
            quantityInt = 0;
        }

        if (cart != null) {
            LineItem lineItem = cart.getLineItem(Long.parseLong(productId));
            if (lineItem != null)
                lineItem.setQuantity(quantityInt);

            if (quantityInt == 0)
                cart.removeLineItem(lineItem);
        }
        return "/cart/cart.jsp";

    }//End - updateItem()

    /**
     * <br>
     * Remove a Product's LineItem from the Cart
     * The URL for the Cart page is returned - initiating display of that window.
     * <br><br>
     * Request object Parameters:<br>
     * "cart" - the Product's Unique ID
     * "productId" - the number of items (of that product) to add to the cart
     * <br><br>
     * Session object Attributes:<br>
     * "cart" - Cart object
     *
     * @return  URL to /cart/cart.jsp which displays the Cart page
     */
    private String removeItem(HttpServletRequest request, HttpServletResponse response){

        HttpSession session = request.getSession();
        Cart cart =(Cart)session.getAttribute("cart");
        String productId = request.getParameter("productId");
        LineItem lineItem = cart.getLineItem(Long.parseLong(productId));
        if (lineItem != null){
                cart.removeLineItem(lineItem);
       }
        return "/cart/cart.jsp";

    }//End - removeItem()

}//End - OrderController.java
