package farmfresh.controllers;

import farmfresh.business.Product;
import farmfresh.business.ProductType;
import farmfresh.business.User;
import farmfresh.data.ProductDB;
import farmfresh.data.ProductTypeDB;
import farmfresh.data.UserDB;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

/**
 * Created by Mom and Dad on 11/9/2016.
 */
public class CatalogController extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)throws IOException, ServletException{

        String requestURI = request.getRequestURI();
        String url = "/catalog";

        if (requestURI.endsWith("/displayProducts")) {
            url = displayProducts(request, response);
        }else if (requestURI.endsWith("/displayProduct")) {
            url = displayProduct(request, response);
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
            url = registerUser(request, response);
        }

        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }

    private String displayProducts(HttpServletRequest request, HttpServletResponse response) {

        // New Logic
        String productTypeId = request.getParameter("ProductTypeID");

        // getPathInfo - takes the path info after the servlet path, but before the query string
        // servlet path is known because of the mapping the the xml file - correct?
        // ex)  FFE.com/cart/display --- known servlet is cart/  display is extra path information...
        //Should never be null
//        String productType = request.getPathInfo();
        if (productTypeId != null) {
//            productType = productType.substring(1); //skip first character returned from PathInfo - it's a '/'
            List<Product> products = ProductDB.selectAllProducts(productTypeId);
            HttpSession session = request.getSession();
            session.setAttribute("products", products);
        }
        return "/catalog/products.jsp";
    }

    private String displayProduct(HttpServletRequest request, HttpServletResponse response){

        String productId = request.getParameter("ProductID");

        if (productId != null){
            Product product = ProductDB.selectProduct(productId);
            HttpSession session = request.getSession();
            session.setAttribute("product", product);
        }

//        return "/catalog/" + productCode + "/index.jsp";
        return "/catalog/product.jsp";
    }

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
