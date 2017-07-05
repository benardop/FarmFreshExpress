package farmfresh.controllers;

import farmfresh.business.User;
import farmfresh.business.UserPass;
import farmfresh.business.UserRole;
import farmfresh.data.UserDB;
import farmfresh.data.UserPassDB;
import farmfresh.data.UserRoleDB;
import farmfresh.util.CookieUtil;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.Objects;

/**
 * Purpose: To manage all User specific activities
 * These activities include Logging In, Logging Out, Registering, subscribing to and
 * un-subscribing from the eNewsletter.
 * Logging In is managed by the Security Layer of this application.
 * (/user/login is setup as a secure area (requiring login) in the web.xml file
 * Deleting Cookies is also available - but only to the Super User for Development purposes.
 *
 * @author Amy Radtke
 * @version 1.0  07/01/2017
 */
public class UserController extends HttpServlet {

    /**
     *  Handles any URL ending in /register, /logout or /deleteCookies
     */
    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException, ServletException{

        String requestURI = request.getRequestURI();
        String url = "";

        if (requestURI.endsWith("/register")){
            url = register(request, response);
        }else if (requestURI.endsWith("/login")) {
            url = login(request, response);
        }else if (requestURI.endsWith("/logout")){
            url = logout(request, response);
        }else if (requestURI.endsWith("/deleteCookies")){
             url = deleteCookies(request, response);
         }

        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);

    }// End - doGet()

    /**
     *  Handles any URL ending in /subscribeToNewsletter or /unsubscribeFromNewsletter
     */
    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws IOException, ServletException{

         String requestURI = request.getRequestURI();
         String url = "";

         if (requestURI.endsWith("/subscribeToNewsletter")){
             url = subscribeToNewsletter(request, response);
         }else if (requestURI.endsWith("/unsubscribeFromNewsletter")) {
             url = unsubscribeFromNewsletter(request, response);
         }else if (requestURI.endsWith("/register")){
             url = register(request, response);
         }

        getServletContext()
           .getRequestDispatcher(url)
           .forward(request,response);

     }// End - doPost()


    /**
     * <br>
     * Registers the User - saving their information (including Login and Password) in the database.
     * <br><br>
     * Retrieves all User info from the incoming request and stores it in a User object.
     * Verifies all user information:<br>
     * - Passwords must match. Registration window is redisplayed with Error Msg if not.<br>
     * - If User subscribed to the eNewsletter, but has not yet Registered - all User's
     *   information will be updated in the User table.<br>
     * - If full User information exists (User has been registered) and Email already exists
     *   in the User table - then the user cannot re-register.  They are directed to login instead. <br>
     *   Else --- <br>
     *   A row is inserted into the user table containing all User information entered <br>
     *   A row is inserted in the userpass table containing the User's password <br>
     *   A row is inserted in the userrole table containing the role of "user' <br>
     *   A userEmail cookie is created containing the User's Email <br>
     *   The User is Logged In <br>
     *   The URL for the homepage is returned - initiating display of that window.
     * <br><br>
     * Request object Parameters:<br>
     * Parameters used are those linked to the form fields of the register_user.jsp
     * <br><br>
     * Session object Attributes:<br>
     * "user" - User object
     *
     * @return  URL to /index.jsp which displays the homepage
     * @throws UnsupportedEncodingException
     */
    private String register(HttpServletRequest request,
                            HttpServletResponse response) throws UnsupportedEncodingException {

        // Pull all user information from the request
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");
        String companyName = request.getParameter("companyName");
        String address1 = request.getParameter("address1");
        String address2 = request.getParameter("address2");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String zip = request.getParameter("zip");

        // Create User object if it does not already exist in the Session
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            user = new User();
        }

        // Save user information to the User object
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setCompanyName(companyName);
        user.setAddress1(address1);
        user.setAddress2(address2);
        user.setCity(city);
        user.setState(state);
        user.setZip(zip);
        user.setIsSubscribedToNewsletter(false);

        // Verify Information Entered
        // - Passwords must match. Registration window is redisplayed with Error Msg if not.
        // - If User subscribed to the eNewsletter, but has not yet Registered - all User's
        //    information will be updated in the User table.
        // - If full User information exists (User has been registered) and Email already exists
        //    in the User table - then the user cannot re-register.  They are directed to login instead
        // - Else --- A row is inserted into the User DB containing all User information entered
        if (password1.equals(password2) == false){
            // The two passwords entered do not match.
            String message = "The two passwords entered do not match.  ";
            message += "Please re-enter the passwords.";
            request.setAttribute("message", message);
            return "/user/register_user.jsp";
        }else if (UserDB.userIsOnlySubcribedToNewsletterAndNotRegistered(email)){
            UserDB.update(user);
        }else if (UserDB.emailExists(email)) {
            String message = "A User has already registered with email address " + email + ".<br>";
            message += "Please Log In instead.";
            request.setAttribute("message", message);
            return "/user/register_user.jsp";
        }else {
            UserDB.insert(user);
        }

        // Save user to Session object
        session.setAttribute("user", user);

        // Insert Password information into userpass table
        UserPass userPass = new UserPass();
        userPass.setUsername(email);
        userPass.setPassword(password1);
        UserPassDB.insert(userPass);

        // Insert row into userrole table with a role of "user"
        UserRole userRole = new UserRole();
        userRole.setUsername(email);
        userRole.setRolename("user");
        UserRoleDB.insert(userRole);

        // Create a User Email cookie
        Cookie emailCookie = new Cookie("emailCookie", email);
        emailCookie.setMaxAge(60 * 60 * 365 * 2); // 2 years
        emailCookie.setPath("/");  //root which is FarmFreshExpress
        response.addCookie(emailCookie);

        // Login User
        try {
            request.login(email, password1);
        } catch(ServletException e) {
            this.log("Login Failed with a ServletException.."
                    + e.getMessage());
        }

        // Initiate display of Home Page
        return "/index.jsp";

    }//End - register()

    /**
     * Initiates User Login:  /user/login is a secured method - which initiates System Login.
     * Functionality.  Once the user is Logged In and Authenticated, the User information is
     * saved to the Session object and the email is saved to the userEmail Cookie.
     *
     * @param request
     * @param response
     * @return  URL to
     * @return  URL / is returned to initiate display of the homepage
     * the homepage
     */
    private String login(HttpServletRequest request, HttpServletResponse response) {

        // The only way for a user to be able to proceed with the Login functionality is
        // they've already done a System Login and been authenticated...
        // getRemoteUser() - Returns the login of the user making this request.

        String email = request.getRemoteUser();

        //  ** User has Authenticated **
        // Pull user from User DB and save User to Session
        User user = UserDB.selectUser(email);
        HttpSession session = request.getSession();
        session.setAttribute("user", user);

        // Set the email cookie in the user's browser
        Cookie emailCookie = new Cookie("emailCookie", user.getEmail());
        emailCookie.setMaxAge(60 * 60 * 24 * 365 * 2);  //TODO fix other setMax
        emailCookie.setPath("/");  // for the entire application
        response.addCookie(emailCookie);

        // Initiate display of the Homepage
        return "/";

    }// End - login()

    /**
     * <br>
     * Initiates the User Logout and returns to the Home Screen.
     * Also removes the User from the Session object and removes the User Email Cookie
     *
     * User's identity information should be cleared from the request.
     * User should be cleared from the session object.  userEmail Cookie should be cleared.
     * BUT, the cart should NOT be cleared from the session object.
     * To support the scenario where the user added items to the cart, then realized they
     * were logged in under the wrong account.  They should be able to logout of the wrong account
     * and login with the right account - without losing the items that were stored in their cart.
     *
     * NOTE:  logout() clears the identity information in the request but doesn't affect the session.
     * invalidate() invalidates the session but doesn't affect the identity information in the request.
     * I want to maintain the cart information in the Session object.
     * Therefore, I did not call Session.invalidate
     *
     * <br><br>
     * Request object Parameters:<br>
     * "emailCookie" - Email Cookie <br>
     * <br>
     * Response object Parameters:<br>
     * "emailCookie" - Email Cookie (deleted) <br>
     * <br>
     * Session object Attributes:<br>
     * "user" - User object
     *
     * @return  URL to / which displays the Home Page window
     */
    private String logout(HttpServletRequest request, HttpServletResponse response){

        // Call request.logout() to clear the identity information from the request
        try{
            request.logout();

        }catch (javax.servlet.ServletException e){
            this.log("Unable to logout  \n" +
                    "Please check your system settings");
            return "/";
        }

        // Remove user from session object
        request.getSession().setAttribute("user", null);

        // Delete the email cookie
        Cookie cookie = CookieUtil.getCookie(request.getCookies(), "emailCookie");
        if (cookie != null) {
            cookie.setMaxAge(0);
            cookie.setPath("/");
            response.addCookie(cookie);
        }

        return "/";

    }//  End logout()

    /**
     *  Delete all cookies at the Application Level <br>
     *
     * @return  URL to /delete_cookies.jsp which displays window indicating
     * the Cookies Have Been Deleted
     */
    private String deleteCookies(HttpServletRequest request, HttpServletResponse response){

        Cookie[] cookies = request.getCookies();
        for (Cookie cookie: cookies) {
            cookie.setMaxAge(0);   //delete the cookie
            cookie.setPath("/");   // / means the entire application  e.g. FarmFreshExpress
            response.addCookie(cookie);
        }

        return "/delete_cookies.jsp";

    }//  End - deleteCookies()

    /**
     * <br>
     * Subscribes User to eNewsletter
     * <br><br>
     * If there exists a row in the User table for this email address, update the subscribedToNewsletter Flag.
     * If no row exists, create one containing the email address and subscribedToNewsletter Flag=TRUE.
     * <br><br>
     * Request object Parameters:<br>
     * "email" - the User's email<br>
     * <br>
     *
     * @return  URL to /eNewsletter/thanks.jsp which displays a message to the user
     */
    private String subscribeToNewsletter(HttpServletRequest request, HttpServletResponse response){

        // retrieve email from request
        String email = request.getParameter("email");

        // If email already exists in User table - set the subscribedToNewsletter Flag to TRUE
        // Else Insert a new row in the User table - setting email and subscribedToNewsletter to TRUE
       if (UserDB.emailExists(email)) {
           UserDB.subscribeToNewsletter(email);
        }else{
           User user = new User();
           user.setEmail(email);
           user.setIsSubscribedToNewsletter(true);
           UserDB.insert(user);
        }

        // Prepare message to be displayed - which will indicated email address subscribed.
        String message;
        message = "Glad you have joined us!<br><br>"
                + email + " is now subscribed to our eNewsLetter.";

        request.setAttribute("message", message);

        return "/eNewsletter/thanks.jsp";

    }// End - SubscribeToNewsletter()

    /**
     * <br>
     * Un-Subscribes User from eNewsletter
     * <br><br>
     * If the information stored on the UserDB indicates they have only subscribed to
     * the newsletter and they have not registered.  Delete the row.<br>
     * Else Update the row indicating that the user no longer wants the newsletter. <br>
     * Else if User has never subscribed to the Newsletter or Registered - Let the user know.
     * <br><br>
     * Request object Parameters:<br>
     * "email" - the User's email<br>
     * <br>
     *
     * @return  URL to /eNewsletter/thanks.jsp which displays a message to the user
     */
    private String unsubscribeFromNewsletter(HttpServletRequest request, HttpServletResponse response){

        // retrieve email from request
        String email = request.getParameter("email");

        // If the information stored on the UserDB indicates they have only subscribed to
        // the newsletter and they have not registered.  Delete the row.
        // Else Update the row indicating that the user no longer wants the newsletter.
        // Else if User has never subscribed to the Newsletter or Registered - Let the user know
        String message;
        if (UserDB.userIsOnlySubcribedToNewsletterAndNotRegistered(email)) {
            UserDB.delete(email);
            message = "Sorry to see you go!<br><br>"
                    + email + " has been unsubscribed.";
        } else if (UserDB.emailExists(email)) {
            UserDB.unsubscribeFromNewsletter(email);
            message = "Sorry to see you go!<br><br>"
                    + email + " has been unsubscribed.";
        }else{
            message = "Unknown Email!<br><br>"
                    + "We were unable to unsubscribe " + email + " because it does not exist.";
        }

        request.setAttribute("message", message);

        return "/eNewsletter/thanks.jsp";

    }// End - unsubscribeFromNewsletter()



}// End - UserController class