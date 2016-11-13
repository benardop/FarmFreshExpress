package farmfresh.controllers;

import farmfresh.business.User;

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

        String requestURI = request.getRequestURI();    //get URL
        // System.out.println("UserController.doGet RequestURI: " + requestURI);
        String url = "";    //BEN - if not deleteCookies - the url is "" - what does that mean? - control forwards to what?
        if (requestURI.endsWith("/deleteCookies")){     // if the URL ends with deleteCookies
            url = deleteCookies(request, response);     // then call deleteCookies - returning /delete_cookies.jsp
        }
        getServletContext()                          //server Context for entire web application - shared across servlets and jsps
                .getRequestDispatcher(url)          //The dispatcher serves as the mechanism for the 'controller' (servlet)
                .forward(request, response);        //to pass the user to the 'view' (jsp) ??? true???

    }

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws IOException, ServletException{

         String requestURI = request.getRequestURI();
         String url = "";
         if (requestURI.endsWith("/subscribeToEmail")){
             url = subscribeToEmail(request, response);
         }

        getServletContext()
           .getRequestDispatcher(url)
           .forward(request,response);

     }

    private String deleteCookies(HttpServletRequest request, HttpServletResponse response){

        Cookie[] cookies = request.getCookies();
        for (Cookie cookie: cookies) {
            cookie.setMaxAge(0);   //delete the cookie
                                   // making sure we delete all of user cookies when they leave our site
                                   // no cookies are saved on our end
            cookie.setPath("/");    // / means the entire application  e.g. FarmFreshExpress
            //            response.addCookie(cookie);
        }

        return "/delete_cookies.jsp";

    }

    private String subscribeToEmail(HttpServletRequest request, HttpServletResponse response){
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");

        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        request.setAttribute("user",user);  //BEN?  why in request and not response

        String url;
        String message;
        //TODO uncomment this
        // Implementing the missing UserDB Class
        // check that e'mail address doesn't already exist
//       if (UserDB.emailExists(email)) {
//            message = "This email already exists.<br>"
//                    + "Please enter another email address.";
//            request.setAttribute("message", message);
//            url = "/email/index.jsp";
//        }else{
//            UserDB.insert(user);
//            message = "";  //setting it to "" is equivalent to calling RemoveAttribute("message");
//            request.setAttribute("message", message);
            url = "/email/thanks.jsp";
//        }
        return url;
    }
}
