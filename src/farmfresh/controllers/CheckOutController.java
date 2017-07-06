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
 * Activities include:  Initiate Checking Out, Displaying the Order,
 * Updating the User's Address Information (optional/but available),
 * Collecting Credit Card information, Processing Credit Card Information (In Development),
 * Saving the Invoice information to the Database thus Completing the Order.<br>
 * Note:  When the Invoice is saved, the Cart information is removed from the Session object.<br>
 * Note:  /checkout (aka the CheckoutController) is secure and requires user login to
 * access any of it's methods
 * <br><br>
 * Future Development:  At this time - information saved in the User and Product Tables
 * are used when displaying a completed Order Invoice.  In future enhancements -
 * A snapshot of relevant user information will be saved to the Invoice - because
 * User's address and email As well as a Product's price may change after the Invoice
 * has been saved.
 *
 * @author Amy Radtke
 * @version 1.0  07/01/2017
 */
public class CheckOutController extends HttpServlet {

    private static final String defaultURL = "/cart/cart.jsp";

    /**
     *  Handles any URL ending in /checkUser, /displayOrder, /editUser,
     *  /processUserUpdate, /displayCreditCard or /submitOrder
     */
    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws IOException, ServletException {

        String requestURI = request.getRequestURI();
        String url = "";

        if (requestURI.endsWith("/checkUser")) {
            url = checkUser(request, response);
        } else if (requestURI.endsWith("/displayOrder")) {
            url = displayOrder(request, response);
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

        if (requestURI.endsWith("/processUserUpdate")) {
            url = processUserUpdate(request, response);
        }

        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);

    }//End - doGet()

    /**
     * The only way for a user to be able to proceed with checkOut() is if they
     * have Logged In and been authenticated...
     * Once the user is Logged In and Authenticated, the User and Invoice information is
     * saved to the Session object and the email is saved to the userEmail Cookie.
     * Then, initiates the display of the User Invoice window (prior to Submission).
     * <br><br>
     * Request Object Information:
     * email - is returned by calling RemoteUser() on the request object
     * <br><br>
     * Session object Attributes:<br>
     * "user" - User object
     *
     * @return  URL /checkOut/displayOrder is returned to initiate displaying of
     * the order for verification before payment
     */
    private String checkUser(HttpServletRequest request, HttpServletResponse response) {

        // getRemoteUser() - Returns the email of the person who has logged in and been authenticated
        String email = request.getRemoteUser();

        //  Pull user from User DB based on the email and save User to Session
        User user = UserDB.selectUser(email);
        HttpSession session = request.getSession();
        session.setAttribute("user", user);

        // Set the email cookie in the user's browser
        Cookie emailCookie = new Cookie("emailCookie", user.getEmail());
        emailCookie.setMaxAge(60 * 60 * 24 * 365 * 2);  //TODO fix other setMax
        emailCookie.setPath("/");  // for the entire application
        response.addCookie(emailCookie);

        // Initiate display of Order window
        return "/checkOut/displayOrder";

    }// End - checkUser()

    /**
     * Takes a snapshot of the Cart and stores it in an Invoice pending Order
     * Completion.  The Invoice is saved to the session object and display
     * of the Order window is initiated (displaying the Invoice)
     * <br><br>
     * Session object Attributes:<br>
     * "user" - User object
     * "cart" - User's Cart
     * "invoice" - User's Invoice after Cart is converted to an Invoice.
     *
     * @return URL /cart/order.jsp is returned to initiate displaying the Order Window
     */
    private String displayOrder(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Cart cart = (Cart) session.getAttribute("cart");

        // Convert Cart into an Invoice and save it to the Session object
        // Order will display Invoice prior to being Saved.
        if (user != null && cart !=null) {
            Invoice invoice = new Invoice();
            invoice.setUser(user);
            invoice.setLineItems(cart.getLineItems());
            session.setAttribute("invoice", invoice);
        }

        // Initiate the display of the invoice(pending completion) in the Order window
        return "/cart/order.jsp";

    }//End - displayOrder()

    /**
     * Takes updated User information (populated in the User Update window)
     * and saves it to the User DB and "user" session object.
     *
     * Assumption:  User already exists in the DB, because User would first need
     * to be registered and saved in the User DB before User would be allowed to Checkout.
     * <br><br>
     * Request Object Information:
     * all User attributes - pulled from request and used to update User object and table
     * <br><br>
     * Session object Attributes:<br>
     * "user" - User object
     *
     * @return  URL to /checkout/displayInvoice.jsp which displays the Invoice window again
     */
    private String processUserUpdate(HttpServletRequest request, HttpServletResponse response) {

        // Pull user information from request
        // Note:  Email is never changed - it Uniquely identifies the user upon Insert
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String companyName = request.getParameter("companyName");
        String address1 = request.getParameter("address1");
        String address2 = request.getParameter("address2");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String zip = request.getParameter("zip");

        // Assumption:  User is in Session object - Stored there during CheckUser functionality
        // Which also means, if user can Login, they also exist in the User DB
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        // Update user information based on User input and Save it to the User DB
        user.setFirstName(firstName);
        user.setLastName(lastName);
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
        return "/checkOut/displayOrder";

    }//End - processUserUpdate();

    /**
     * Submit Order - Charge Credit Card (In Development - Not Yet Available),
     * Save Invoice to Database, Send User an Order Confirmation Email (In Development -
     * Not Yet Available) and Clear out the Cart from the Session object
     * <br><br>
     * Request Object Information:
     * all User attributes - pulled from request and used to update User object and table
     * <br><br>
     * Session object Attributes:<br>
     * "user" - User object
     * @return URL to /cart/complete.jsp is returned to initiate the displaying
     * of the "Your Order is Complete" message window.
     */
    private String submitOrder(HttpServletRequest request, HttpServletResponse response) {

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
//                  return "/error_info.jsp";
//        }
//        **************************************************************************************

        // Retrieve invoice from session object
        HttpSession session = request.getSession();
        Invoice invoice = (Invoice) session.getAttribute("invoice");

        if (invoice != null) {
            // Save Invoice to Invoice Database
            InvoiceDB.insert(invoice);

//        **************************************************************************************
//        * IN DEVELOPMENT - Send email to user to confirm the order
//        **************************************************************************************
//        User user = invoice.getUser();
//        String emailTo = user.getEmail();
//        String emailFrom = "amy@roofreelancing.com";
//        String emailSubject = "Farm Fresh Express Order";
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

            // Clear out user's cart and invoice
            session.setAttribute("cart", null);
            session.setAttribute("invoice", null);

            // Initiate the display of the Order Complete window
            return "/cart/complete.jsp";
        }else {
            // Return error window
            return "/error_info.jsp";
        }

    }//End - submitOrder()

}//End - CheckOutController.java
