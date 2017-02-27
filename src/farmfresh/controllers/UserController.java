package farmfresh.controllers;

import farmfresh.business.User;
import farmfresh.data.UserDB;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

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
         }

        getServletContext()
           .getRequestDispatcher(url)
           .forward(request,response);

     }// End - doPost()

    private String deleteCookies(HttpServletRequest request, HttpServletResponse response){

        Cookie[] cookies = request.getCookies();
        for (Cookie cookie: cookies) {
            cookie.setMaxAge(0);   //delete the cookie
            cookie.setPath("/");   // / means the entire application  e.g. FarmFreshExpress
            //            response.addCookie(cookie);  //TODO do we need this line?
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
        url = "/connect/thanks.jsp";
        return url;

    }// End - eNewsletterSubscribe()

    private String unsubscribeFromNewsletter(HttpServletRequest request, HttpServletResponse response){
        String email = request.getParameter("email");

        String url;
        String message;

        if (UserDB.emailExists(email)) {
            UserDB.unsubscribeFromNewsletter(email);
            message = "Sorry to see you go!<br>"
                    + email + " has been unsubscribed.";
        }else{
            message = "Unknown Email!<br>"
                    + "We were not able to unsubscribe " + email + " because it does not exist.";
        }

        request.setAttribute("message", message);
        url = "/connect/thanks.jsp";
        return url;

    }// End - eNewsletterUnsubscribe()

}// End - UserController class

