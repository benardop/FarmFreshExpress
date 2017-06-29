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
import java.util.Objects;

/**
 * Created by Mom and Dad on 11/6/2016.
 */
public class UserController extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException, ServletException{

        String requestURI = request.getRequestURI();
        String url = "";

        if (requestURI.endsWith("/deleteCookies")){
            url = deleteCookies(request, response);
        }else if (requestURI.endsWith("/logout")){
            url = logout(request, response);
        }else if (requestURI.endsWith("/register")){
            url = register(request, response);
        }

        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);

    }// End - doGet()

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

//         else if (requestURI.endsWith("/login")){
//             url = login(request, response);
//         }

        getServletContext()
           .getRequestDispatcher(url)
           .forward(request,response);

     }// End - doPost()

//    private String login(HttpServletRequest request, HttpServletResponse response){
////        try {
////            boolean b = request.authenticate(response);
////
////            this.log("authenticate returned" + b);
////        } catch (IOException e) {
////            this.log("Authentication Failed with a IOException."
////                    + e.getMessage());
////        }catch (ServletException e) {
////            this.log("Authentication Failed with a IOException."
////                    + e.getMessage());
////        }
//
//
//        String userName = request.getParameter("username");
//        String password = request.getParameter("password");
//
//        try {
//            request.login(userName, password);
//        } catch(ServletException e) {
//            this.log("Login Failed with a ServletException.."
//                    + e.getMessage());
//        }
//        return "/";
//
//    }//  End login()

    private String logout(HttpServletRequest request, HttpServletResponse response){

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

    private String deleteCookies(HttpServletRequest request, HttpServletResponse response){

        Cookie[] cookies = request.getCookies();
        for (Cookie cookie: cookies) {
            cookie.setMaxAge(0);   //delete the cookie
            cookie.setPath("/");   // / means the entire application  e.g. FarmFreshExpress
            response.addCookie(cookie);
        }

        return "/delete_cookies.jsp";

    }//  End deleteCookies()

    private String subscribeToNewsletter(HttpServletRequest request, HttpServletResponse response){
        String email = request.getParameter("email");

        String url;
        String message;

       if (UserDB.emailExists(email)) {
           UserDB.subscribeToNewsletter(email);
        }else{
           User user = new User();
           user.setEmail(email);
           user.setSubscribedToNewsletter(true);
           request.setAttribute("user",user);
           UserDB.insert(user);
        }

        message = "Glad you have joined us!<br>"
                + email + " is now subscribed to our eNewsLetter.";
        request.setAttribute("message", message);

        url = "/eNewsletter/thanks.jsp";
        return url;

    }// End - eNewsletterSubscribe()

    private String unsubscribeFromNewsletter(HttpServletRequest request, HttpServletResponse response){
        String email = request.getParameter("email");

        String url;
        String message;
        if (UserDB.emailExistsUserDataDoesNot(email)) {
            UserDB.delete(email);
            message = "Sorry to see you go!<br>"
                    + email + " has been unsubscribed.";
        } else if (UserDB.emailExists(email)) {
            UserDB.unsubscribeFromNewsletter(email);
            message = "Sorry to see you go!<br>"
                    + email + " has been unsubscribed.";
        }else{
            message = "Unknown Email!<br>"
                    + "We were not able to unsubscribe " + email + " because it does not exist.";
        }

        request.setAttribute("message", message);

        url = "/eNewsletter/thanks.jsp";
        return url;

    }// End - eNewsletterUnsubscribe()

    private String register(HttpServletRequest request,
                            HttpServletResponse response) throws UnsupportedEncodingException{

        HttpSession session = request.getSession();
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

        User user = (User) session.getAttribute("user");
        if (user == null) {
            user = new User();
        }

        //set user data
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setCompanyName(companyName);
        user.setAddress1(address1);
        user.setAddress2(address2);
        user.setCity(city);
        user.setState(state);
        user.setZip(zip);

        // save  user to session
        session.setAttribute("user", user);

        if (password1.equals(password2) == false){
            // The two passwords entered do not match.
            String message = "The two passwords entered do not match.  ";
            message += "Please re-enter the passwords.";
            request.setAttribute("message", message);
            return "/register_user.jsp";
        }else if (UserDB.emailExistsUserDataDoesNot(user.getEmail())) {
            // User has subscribed to get an eNewsletter, but hasn't Registered yet
            UserDB.update(user);
      } else if (UserDB.emailExists(user.getEmail())) {
            //User has already registered or is an admin or super_user
            String message = "A User has already registered with email address " + email + ".<br>";
            message += "Please Log In instead.";
            request.setAttribute("message", message);
            return "/register_user.jsp";
        }else{
            // row in User table doesn't exist yet for this email address
            UserDB.insert(user);
        }

        // insert row into userpass table
        UserPass userPass = new UserPass();
        userPass.setUsername(email);
        userPass.setPassword(password1);
        UserPassDB.insert(userPass);

        // insert row into userrole table
        UserRole userRole = new UserRole();
        userRole.setUsername(email);
        userRole.setRolename("user");
        UserRoleDB.insert(userRole);

        // create a cookie to save the User's Email Address
        Cookie emailCookie = new Cookie("emailCookie", email);
        emailCookie.setMaxAge(60 * 60 * 365 * 2); // 2 years
        emailCookie.setPath("/");  //root which is FarmFreshExpress
        response.addCookie(emailCookie);

        // Log this user in
        try {
            request.login(email, password1);
        } catch(ServletException e) {
            this.log("Login Failed with a ServletException.."
                    + e.getMessage());
        }

        return "/index.jsp";

    }//End - register()

}// End - UserController class