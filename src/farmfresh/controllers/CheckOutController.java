package farmfresh.controllers;

import farmfresh.business.*;
import farmfresh.data.InvoiceDB;
import farmfresh.data.UserDB;
import farmfresh.util.CookieUtil;
import farmfresh.util.MailUtil;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Date;

/**
 * Created by Mom and Dad on 3/13/2017.
 */
public class CheckOutController extends HttpServlet {

    private static final String defaultURL = "/cart/cart.jsp";
//        private static final String defaultURL = "";

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws IOException, ServletException {

        String requestURI = request.getRequestURI();
        String url = "";
        if (requestURI.endsWith("/checkOut")) {
            url = checkOut(request, response);
        } else if (requestURI.endsWith("/processUser")) {
            url = processUser(request, response);
        } else if (requestURI.endsWith("/displayInvoice")) {
            url = displayInvoice(request, response);
        } else if (requestURI.endsWith("/displayUser")) {
            url = "/cart/user.jsp";
        } else if (requestURI.endsWith("/displayCreditCard")) {
            url = "/cart/credit_card.jsp";
        } else if (requestURI.endsWith("/completeOrder")) {
            url = completeOrder(request, response);
        }

        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException, ServletException {

        String requestURI = request.getRequestURI();
        String url = defaultURL;

        if (requestURI.endsWith("/checkUser")) {
            url = checkOut(request, response);
        }else if (requestURI.endsWith("/completeOrder")) {
            url = completeOrder(request, response);
        }

        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);

    }

    /**
     * @param request
     * @param response
     * @return
     */
    private String checkOut(HttpServletRequest request, HttpServletResponse response) {

        // The only way for a user to get to checkOut() is if they have been authenticated...
        // So getRemoteUser() should always return something...
        // But if it does not --- force use to redo this method() - which will force them to login
        String email = request.getRemoteUser();

//        // if there is no RemoteUser/email - then noone has logged in - they cannot move forward
//        if (email == null)
//            return "/checkOut/checkOut";

        // if there is no information in the UserDB for this logged in user - the user may be an admin or super_user
        // they need to register to be a user as well...
        if (email == null) {
            return "/login_error.jsp";
        }

        User user = UserDB.selectUser(email);
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        return "/checkOut/displayInvoice";

//        String url = "/cart/user.jsp";
//
//        //If user has registered in the past (user exists in Session and DB)
//        if (user != null && !user.getAddress1().equals("")) {
//            url = "/checkOut/displayInvoice";
//        } else {
//            //If the email cookie exists - the user has registered - pull user from the DB
//            Cookie[] cookies = request.getCookies();
//            String email = CookieUtil.getCookieValue(cookies, "emailCookie"); //why not from params
//
//            //if emailCookie does not exist - you need to get user to enter user information
//            if (email.equals("")) {
//                user = new User();
//                url = "/register_user.jsp";
//            } else {
//                user = UserDB.selectUser(email);
//                session.setAttribute("user", user);
//
//                //if user exists on the DB and user address1  is populated
//                if (user != null && !user.getAddress1().equals("")) {
//                    url = "/checkOut/displayInvoice";
//                }
//            }
//        }

//        return url;
    }

    /**
     * @param request
     * @param response
     * @return
     */
    private String processUser(HttpServletRequest request, HttpServletResponse response) {

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String companyName = request.getParameter("companyName");
        String address1 = request.getParameter("address1");
        String address2 = request.getParameter("address2");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String zip = request.getParameter("zip");
        String country = request.getParameter("country");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            user = new User();
        }

        if (UserDB.emailExists(email)) {
            user = UserDB.selectUser(email);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setCompanyName(companyName);
            user.setAddress1(address1);
            user.setAddress2(address2);
            user.setCity(city);
            user.setState(state);
            user.setZip(zip);
            user.setCountry(country);
            UserDB.update(user);
        } else {
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setCompanyName(companyName);
            user.setAddress1(address1);
            user.setAddress2(address2);
            user.setCity(city);
            user.setState(state);
            user.setZip(zip);
            user.setCountry(country);
            UserDB.insert(user);
        }

        session.setAttribute("user", user);

        return "/order/displayInvoice";
    }

    private String displayInvoice(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Cart cart = (Cart) session.getAttribute("cart");

        Date today = new Date();

        Invoice invoice = new Invoice();
        invoice.setUser(user);
        invoice.setLineItems(cart.getLineItems());
        invoice.setInvoiceDate(today);

        session.setAttribute("invoice", invoice);
        return "/cart/invoice.jsp";
    }

    //Check Out Processing
    private String completeOrder(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        Invoice invoice = (Invoice) session.getAttribute("invoice");

        String creditCardType = request.getParameter("creditCardType");
        String creditCardNumber = request.getParameter("creditCardNumber");
        String creditCardExpMonth = request.getParameter("creditCardExpMonth");
        String creditCardExpYear = request.getParameter("creditCardExpYear");

        User user = invoice.getUser();
        user.setCreditCardType(creditCardType);
        user.setCreditCardNumber(creditCardNumber);
        user.setCreditCardExpMonth(creditCardExpMonth);
        user.setCreditCardExpYear(creditCardExpYear);

        if (UserDB.emailExists(user.getEmail())) {
            UserDB.update(user);
        } else {
//          ERROR  UserDB.insert(user);
        }

        invoice.setUser(user);
        InvoiceDB.insert(invoice);

//        //set the email cookie in the user's browser
//        Cookie emailCookie = new Cookie("emailCookie", user.getEmail());
//        emailCookie.setMaxAge(60 * 60 * 24 * 365 * 2);  //TODO fix other setMax
//        emailCookie.setPath("/");  // for the entire application
//
//        response.addCookie(emailCookie);

        // remove all items from the user's cart
        session.setAttribute("cart", null);

        // send email to user to confirm the order
        String to = user.getEmail();
//        String from = "FarmFreshExpress@gmail.com";
        String from = "amy.freelance.dev@gmail.com";
        String subject = "Farm Fresh Express Order ";  //TODO fix + invoiceNumber;
        String body = "Dear " + user.getFirstName() + "\n\n" +
                "Thank you for shopping Farm Fresh Express.\n" +
                "Your order should be arriving (2-3) days.";

        boolean isBodyHTML = false;

        try {
            MailUtil.sendMail(to, from, subject, body, isBodyHTML);
        } catch (javax.mail.MessagingException e) {
            this.log("Unable to send email  \n" +
                    "Please check your system settings");
        }

        return "/cart/complete.jsp";
    }

    private String showCart(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null || cart.getCount() == 0) {
            request.setAttribute("emptyCart", "Your cart is empty.");
        } else {
            request.getSession().setAttribute("cart", cart);
        }
        return defaultURL;  //defaultURL = "/cart/cart.jsp"
    }

}//End - CheckOutController.java
