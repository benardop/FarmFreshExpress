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

/**
 * Created by Mom and Dad on 11/8/2016.
 *
 * Purpose:  to manage any actions relating to Adding, Updating or Deleting Items from the Cart.
 *
 * OUTSTANDING QUESTIONS:
 * could order controller functionality be merged into checkoutcontroller functionality or
 * do they need to be seperate due to something relating to security (securing the OrderController
 * logic - to make certain the user has logged in before having any access to Order logic.
 */
public class OrderController extends HttpServlet {

    private static final String defaultURL = "/cart/cart.jsp";
//        private static final String defaultURL = "";


    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException, ServletException {

        String requestURI = request.getRequestURI();
        String url = defaultURL;

//        if (requestURI.endsWith("/showCart")){
//            url = showCart(request, response);
//        }else if (requestURI.endsWith("/checkUser")){
////            url = checkOut(request, response);
//        }

        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);

    }
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

// DELETE THIS - ITS IN THE CHECKOUT CONTROLLER
// else if (requestURI.endsWith("/checkOut")){
////            url = checkOut(request, response);
//        }else
//
//
//        if (requestURI.endsWith("/displayInvoice")){
//            url = displayInvoice(request, response);
//        }else if (requestURI.endsWith("/displayUser")){
//            url = "/cart/user.jsp";
//        }else if (requestURI.endsWith("/processUser")) {
//            url = processUser(request, response);
//        }else if (requestURI.endsWith("/displayCreditCard")){
//            url = "/cart/credit_card.jsp";
//        }else if (requestURI.endsWith("/completeOrder")){
//            url = completeOrder(request, response);
//        }

        getServletContext()
                .getRequestDispatcher(url)
                .forward(request,response);
    }



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
//        return defaultURL;  //defaultURL = "/cart/cart.jsp"
        return "/catalog/products.jsp";
    }

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
    }

    private String removeItem(HttpServletRequest request, HttpServletResponse response){

        HttpSession session = request.getSession();
        Cart cart =(Cart)session.getAttribute("cart");
        String productId = request.getParameter("productId");
        LineItem lineItem = cart.getLineItem(Long.parseLong(productId));
        if (lineItem != null){
                cart.removeLineItem(lineItem);
       }
        return "/cart/cart.jsp";
    }



////    private String showCart(HttpServletRequest request, HttpServletResponse response){
////        HttpSession session = request.getSession();
////        Cart cart =(Cart)session.getAttribute("cart");
////
////        if (cart == null || cart.getCount() == 0){
////            request.setAttribute("emptyCart", "Your cart is empty.");
////        }else {
////            request.getSession().setAttribute("cart", cart);
////        }
////        return defaultURL;  //defaultURL = "/cart/cart.jsp"
////    }

}
