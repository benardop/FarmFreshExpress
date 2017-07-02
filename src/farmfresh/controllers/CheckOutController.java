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
 * Purpose: To manage all activities the User may perform as they Checkout.
 * Activities include:  Making sure User is Logged In before Checking Out,
 * Displaying the Invoice, Updating the User's Address Information (optional),
 * Collecting Credit Card information, Processing Credit Card Information (In Development),
 * Saving the Invoice information to the Database thus Completing the Order.
 * Note:  When the Invoice is saved, the Cart information is removed from the Session object.
 *
 * @author Amy Radtke
 * @version 1.0  07/01/2017
 */
public class CheckOutController extends HttpServlet {

    private static final String defaultURL = "/cart/cart.jsp";

    /**
     *  Handles any URL ending in /checkUser, /displayInvoice, /editUser,
     *  /processUserUpdate, /displayCreditCard or /submitOrder
     */
    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws IOException, ServletException {

        String requestURI = request.getRequestURI();
        String url = "";

        if (requestURI.endsWith("/checkUser")) {
            url = checkUser(request, response);
        } else if (requestURI.endsWith("/displayInvoice")) {
            url = displayInvoice(request, response);
        } else if (requestURI.endsWith("/editUser")) {
            url = "/cart/user.jsp";
        }else if (requestURI.endsWith("/processUserUpdate")) {
            url = processUserUpdate(request, response);
        } else if (requestURI.endsWith("/displayCreditCard")) {
            url = "/cart/credit_card.jsp";
        } else if (requestURI.endsWith("/submitOrder")) {
            url = submitOrder(request, response);
        }

        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);

    }// End - doPost()

    /**
     *  Handles no URLs at this time
     */
    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException, ServletException {

        String requestURI = request.getRequestURI();
        String url = defaultURL;

//        if (requestURI.endsWith("/processUserUpdate")) {
//            url = processUserUpdate(request, response);
//        }
//        if (requestURI.endsWith("/checkUser")) {
//            url = checkOut(request, response);
//        }else if (requestURI.endsWith("/completeOrder")) {
//            url = completeOrder(request, response);
//        }

        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);

    }//End - doGet()

    /**
     * Verifies the user has authenticated.  If they haven not, it initiates Log In.
     * If they have - it initiates the display of the User's Invoice (prior to submission).
     *
     * @param request
     * @param response
     * @return
     */
    private String checkUser(HttpServletRequest request, HttpServletResponse response) {

        // The only way for a user to be able to proceed with checkOut() is if they have Logged In...
        // getRemoteUser() - Returns the login of the user making this request, if the user has
        // been authenticated, or null if the user has not been authenticated.
        // If null, force the user to login.  Else display Invoice.
        String email = request.getRemoteUser();
        if (email == null) {
            return "/login_error.jsp";
        }

        // User has authenticated - display their invoice (Invoice not saved to DB Yet).
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

    /**
     * Takes updated User information (populated in the User Update window)
     * and saves it to the User DB and "user" session object.
     *
     * Assumptions:  User already exists in DB, because User would first need to be registered
     * and saved in the User DB before User would be allowed to Checkout.
     *
     * @param request
     * @param response
     * @return  URL to /checkout/displayInvoice.jsp which displays the Invoice window at Checkout
     */
    private String processUserUpdate(HttpServletRequest request, HttpServletResponse response) {

        // Pull user information from request
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String companyName = request.getParameter("companyName");
        String address1 = request.getParameter("address1");
        String address2 = request.getParameter("address2");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String zip = request.getParameter("zip");

        // Assumption:  User is in Session object - Stored there during CheckUser functionality
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        // If User is not in Session object, pull user from DB.
//        if (user == null){
  //          user = UserDB.selectUser(email);
    //    }

        // Update user information based on User input and Save it to the User DB
        // The one piece of information that is never changed is the User Email
        user.setFirstName(firstName);
        user.setLastName(lastName);
        //user.setEmail(email);
        user.setCompanyName(companyName);
        user.setAddress1(address1);
        user.setAddress2(address2);
        user.setCity(city);
        user.setState(state);
        user.setZip(zip);
        UserDB.update(user);

        //Save Updated User to Session object
        session.setAttribute("user", user);

        //Initiate display of the Invoice window
        return "/checkOut/displayInvoice";

    }//End - userUpdate();

    /**
     * Submit Order - Charge Credit Card, Save Invoice to Database and Clear out Cart
     * NOTE:  Credit Card functionality is under development and not available
     *
     * @param request
     * @param response
     * @return
     */
    private String submitOrder(HttpServletRequest request, HttpServletResponse response) {

        // Retrieve invoice from session object
        HttpSession session = request.getSession();
        Invoice invoice = (Invoice) session.getAttribute("invoice");

        // Save Invoice to Invoice Database
        InvoiceDB.insert(invoice);

        // Clear out user's cart
        session.setAttribute("cart", null);

//        **************************************************************************************
//        * IN DEVELOPMENT - Credit Card Processing Logic
//        **************************************************************************************
//        NEED TO DETERMINE HOW INVOICE KNOWS WHAT CREDIT CARD WAS USED TO PAY FOR IT
//        At this time - an Invoice knows the User making the purchase
//        In the future a User will have multiple Credit Cards and an Invoice will be linked
//        to One Credit Card associated with that User
//        NEED TO DETERMINE WHAT PROCESS IS IF CREDIT CARD PAYMENT CANNOT GO THROUGH
//        **************************************************************************************
//        String creditCardType = request.getParameter("creditCardType");
//        String creditCardNumber = request.getParameter("creditCardNumber");
//        String creditCardExpMonth = request.getParameter("creditCardExpMonth");
//        String creditCardExpYear = request.getParameter("creditCardExpYear");
//
//        user.setCreditCardType(creditCardType);
//        user.setCreditCardNumber(creditCardNumber);
//        user.setCreditCardExpMonth(creditCardExpMonth);
//        user.setCreditCardExpYear(creditCardExpYear);
//
//        if (UserDB.emailExists(user.getEmail())) {
//            UserDB.update(user);
//        } else {
//          ERROR  UserDB.insert(user);
//        }
//        **************************************************************************************

//        //set the email cookie in the user's browser - DO I NEED THIS?
//        Cookie emailCookie = new Cookie("emailCookie", user.getEmail());
//        emailCookie.setMaxAge(60 * 60 * 24 * 365 * 2);  //TODO fix other setMax
//        emailCookie.setPath("/");  // for the entire application
//
//        response.addCookie(emailCookie);

//        **************************************************************************************
//        * IN DEVELOPMENT - Send email to user to confirm the order
//        **************************************************************************************
//        User user = invoice.getUser();
//        String emailTo = user.getEmail();
//        String emailFrom = "amy@roofreelancing.com";
//        String emailSubject = "Farm Fresh Express Order ";  //TODO fix + invoiceNumber;
//        String emailBody =  "Dear " + user.getFirstName() + "\n\n" +
//                            "Thank you for shopping Farm Fresh Express.\n" +
//                            "Your order should be arriving within 2 to 3 business days.";
//
//        boolean isBodyHTML = false;
//
//        try {
//            MailUtil.sendMail(emailTo, emailFrom, emailSubject, emailBody, isBodyHTML);
//        } catch (javax.mail.MessagingException e) {
//            this.log("Unable to send email  \n" +
//                    "Please check your system settings");
//        }
//        **************************************************************************************

        return "/cart/complete.jsp";
    }

    //    private String checkOut(HttpServletRequest request, HttpServletResponse response) {
//
//        HttpSession session = request.getSession();
//        User user = (User) session.getAttribute("user");
//
//        // TODO Check to make sure a User has logged in...
//        // Once Logged in if user exists and has a populated Address1 field - skip the user input page
//        // and display Invoice right away...
//        String url = "/cart/user.jsp";
//        if (user != null && !user.getAddress1().equals("")) {
//            url = "/order/displayInvoice";
//        } else {
//            //If the email cookie exists - pull the user from the DB
//            Cookie[] cookies = request.getCookies();
//            String email = CookieUtil.getCookieValue(cookies, "emailCookie"); //why not from params
//
//            //if emailCookie does not exist - you need to get user to enter user information
//            if (email.equals("")) {
//                user = new User();
//                url = "/register_user.jsp";
////                url = "/cart/user.jsp";
//            } else {
//                user = UserDB.selectUser(email);
//                session.setAttribute("user", user);
//
//                //if user exists on the DB and user address1  is populated
//                if (user != null && !user.getAddress1().equals("")) {
//                    url = "/order/displayInvoice";
//                }
//
//            }
//        }
//
//        return url;
//    }

}//End - CheckOutController.java
