package farmfresh.controllers;

import farmfresh.business.Product;
import farmfresh.business.User;
import farmfresh.data.ProductDB;
import farmfresh.data.UserDB;

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
        url = showProduct(request, response);

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
            url = registerUser(request, response);
        }

        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    // Pending doubts
    // Listen() was not called - no need to make sure we have a user object connected to request.session
    // Ar request to show the product was directly requested in the URL
    private String showProduct(HttpServletRequest request, HttpServletResponse response){
        String productCode = request.getPathInfo();  //what might an example url look like?
        //getPathInfo - takes the path info after the servlet path, but before the query string
        //servlet path is known because of the mapping the the xml file - correct?
        // ex)  FFE.com/cart/display --- known servlet is cart/  display is extra path information...


        //Should never be null
        if (productCode != null){
            productCode = productCode.substring(1);//skip first character returned from PathInfo it's a '/' - correct?
            Product product = ProductDB.selectProduct(productCode);
            HttpSession session = request.getSession();
            session.setAttribute("product", product);
        }
        //what if product code was null?  Are we just assuming it will always be populated?

        return "/catalog" + productCode + "/index.jsp";  //Will this URL be different than the URL passed as the last
                                                         //line in the method above? How are the pages displayed diff?
    }

    //Users are unique based on their e'mail - that their unique attribute?
    private String registerUser(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");

        User user;
        if (UserDB.emailExists(email)){
            user = UserDB.selectUser(email);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            UserDB.update(user);
        }else{
            user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            UserDB.insert(user);
        }

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
