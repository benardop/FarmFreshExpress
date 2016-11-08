package FarmFresh.controllers;

import FarmFresh.business.Cart;
import FarmFresh.business.LineItem;
import FarmFresh.business.Product;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Created by Mom and Dad on 11/8/2016.
 */
public class OrderController extends HttpServlet {

    private static final String defaultURL = "/cart/cart.jsp";

    @Override
    public void doPost(HttpServletRequest request,
                      HttpServletResponse response) throws IOException, ServletException {

        String requestURI = request.getRequestURI();
        String url = "";
        if (requestURI.endsWith("/addItem")){
            url = addItem(request, response);
        }else if (requestURI.endsWith("/updateItem")){
            url = updateItem(request, response);
        }
//        else if (requestURI.endsWith("/removeItem")){
//            url = removeItem(request, response);
//        }else if (requestURI.endsWith("/checkUser")){
//            url = checkUser(request, response);
//        }else if (requestURI.endsWith("/processUser")){
//            url = processUser(request, response);
//        }else if (requestURI.endsWith("/displayInvoice")){
//            url = displayInvoice(request, response);
//        }else if (requestURI.endsWith("/displayUser")){
//            url = "/cart/user.jsp";
//        }else if (requestURI.endsWith("/displayCreditCard")){
//            url = "/cart/creditCard.jsp";
//        }else if (requestURI.endsWith("/completeOrder")){
//            url = completeOrder(request, response);
//        }

        getServletContext()
                .getRequestDispatcher(url)
                .forward(request,response);

    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException, ServletException {

        String requestURI = request.getRequestURI();
        String url = defaultURL;

        if (requestURI.endsWith("/showCart")){
            url = showCart(request, response);
        }else if (requestURI.endsWith("/requestUser")){
//            url = requestUser(request, response);
        }

        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);

    }

    private String showCart(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        Cart cart =(Cart)session.getAttribute("cart");

        if (cart == null || cart.getCount() == 0){
            request.setAttribute("emptyCart", "Your cart is empty.");
        }else {
            request.getSession().setAttribute("cart", cart);
        }

        return defaultURL;

    }

    private String addItem(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        Cart cart =(Cart)session.getAttribute("cart");

        if (cart == null)
            cart = new Cart();

        String productCode = request.getParameter("productCode");
//        Product product = ProductDB.selectProduct(productCode);
//
//        if (product != null){
//            LineItem lineItem = new LineItem();
//            lineItem.setProduct(product);
//            cart.addLineItem(lineItem);
//        }

        session.setAttribute("cart", cart);

        return defaultURL;
    }

    private String updateItem(HttpServletRequest request, HttpServletResponse response){
        String productQuantity = request.getParameter("productQuantity");
        String productCode = request.getParameter("productCode");

        HttpSession session = request.getSession();
        Cart cart =(Cart)session.getAttribute("cart");

        int cartQuantity;
        try{
            cartQuantity = Integer.parseInt(productQuantity);
            if (cartQuantity < 0)
                cartQuantity = 1;
        }catch (NumberFormatException ex){
            cartQuantity = 1;
        }

//        Product product = ProductDB.selectProduct(productCode);
//        if (product != null && cart != null){
//            LineItem lineItem = new LineItem();
//            lineItem.setProduct(product);
//            lineItem.setQuantity(cartQuantity);
//
//            if (cartQuantity > 0)
//                cart.addLineItem(lineItem);
//            else
//                cart.removeLineItem(lineItem);
//
//        }
        return defaultURL;
    }

}
