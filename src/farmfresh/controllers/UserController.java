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

    }

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws IOException, ServletException{

         String requestURI = request.getRequestURI();
         String url = "";
         if (requestURI.endsWith("/eNewsLetterSignUp")){
             url = eNewsLetterSignUp(request, response);
         }

        getServletContext()
           .getRequestDispatcher(url)
           .forward(request,response);

     }

    private String deleteCookies(HttpServletRequest request, HttpServletResponse response){

        Cookie[] cookies = request.getCookies();
        for (Cookie cookie: cookies) {
            cookie.setMaxAge(0);   //delete the cookie
            cookie.setPath("/");   // / means the entire application  e.g. FarmFreshExpress
            //            response.addCookie(cookie);  //TODO do we need this line?
        }

        return "/delete_cookies.jsp";

    }

    private String eNewsLetterSignUp(HttpServletRequest request, HttpServletResponse response){
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
//        String address1 = request.getParameter("address1");
//        String address2 = request.getParameter("address2");
//        String city = request.getParameter("city");
//        String state = request.getParameter("state");
//        String zip = request.getParameter("zip");
//        String creditCardType = request.getParameter("creditCardType");
//        String creditCardNumber = request.getParameter("creditCardNumber");
//        String creditCardExpirationDate = request.getParameter("creditCardExpirationDate");


        User user = new User();
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
//        user.setAddress1(address1);
//        user.setAddress2(address2);
//        user.setCity(city);
//        user.setState(state);
//        user.setZip(zip);
//        user.setCreditCardType(creditCardType);
//        user.setCreditCardNumber(creditCardNumber);
//        user.setCreditCardExpirationDate(creditCardExpirationDate);
        request.setAttribute("user",user);  //TODO - BEN?  why in request and not response

        String url;
        String message;

       if (UserDB.emailExists(email)) {
            message = "Email " + email + " is already signed up.<br>"
                    + "Glad you have joined us!";
            request.setAttribute("message", message);
            url = "/connect/index.jsp";
        }else{
            UserDB.insert(user);
            message = "";  //setting it to "" is equivalent to calling RemoveAttribute("message");
            request.setAttribute("message", message);
            url = "/connect/thanks.jsp";
        }
        return url;
    }
}
