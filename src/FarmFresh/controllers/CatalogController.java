package FarmFresh.controllers;

import FarmFresh.business.Product;
import FarmFresh.business.User;
import FarmFresh.data.UserDB;
import FarmFresh.util.CookieUtil;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Created by Mom and Dad on 11/9/2016.
 */
public class CatalogController extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)throws IOException, ServletException{

        String requestURI = request.getRequestURI();
        String url;

        if (requestURI.endsWith("/listen")){
            url = listen(request, response);
        }else{
            url = showProduct(request, response);
        }

        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request,
                      HttpServletResponse response)throws IOException, ServletException{

        String requestURI = request.getRequestURI();
        String url = "/catalog";
        if (requestURI.endsWith("/register")){
//            url = userRegister(request, response);
        }
        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);

    }
// Pending doubts
    private String showProduct(HttpServletRequest request, HttpServletResponse response){
        String productCode = request.getPathInfo();
        if (productCode != null){   //Should never be null
            productCode = productCode.substring(1);
//            Product product = ProductDB.selectProduct(productCode);
//            HttpSession session = request.getSession();
//            session.setAttribute("product", product);

        }
        return "/catalog" + productCode + "/index.jsp";
    }

    private String listen(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        // if the user object doesn't exist, check for the e'mail cookie
        if (user == null) {
            Cookie[] cookies = request.getCookies();
            String emailAddress = CookieUtil.getCookieValue(cookies, "emailCookie");

            //if email cookie doesn't exist, go to registration page
            if (emailAddress == null || emailAddress.equals("")) {
                return "/catalog/register.jsp";
            } else {
//                user = UserDB.selectUser(emailAddress);
                //If the user doesn't exist in the database, go to registration page
                if (user == null) {
                    return "/catalog/register.jsp";
                }

                session.setAttribute("user", user);
            }


        }

        Product product = (Product)session.getAttribute("product");
        return "/catalog" + product + "index.jsp";

    }// end method

    private String registerUser(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");

        User user = null;  // remove after uncommenting
//        User user;
//        if (UserDB.emailExists(email)){
//            user = UserDB.selectUser(email);
//            user.setFirstName(firstName);
//            user.setLastName(lastName);
//            user.setEmail(email);
//            UserDB.update(user);
//        }else{
//            user = new User();
//            user.setFirstName(firstName);
//            user.setLastName(lastName);
//            user.setEmail(email);
//            UserDB.add(user);
//        }

        session.setAttribute("user", user);
        Cookie emailCookie = new Cookie("emailCookie", email);
        emailCookie.setMaxAge(60 * 60 * 365 * 2); // 2 years
        emailCookie.setPath("/");  //root which is FarmFreshExpress
        response.addCookie(emailCookie);

        Product product = (Product)session.getAttribute("product");
        String url = "/catalog" + product + "index.jsp";
        return url;
    }


}
