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
 * Purpose: Acts like a Product Catalog.  Manages the display of all products of a given Product Type
 * (Products window) or Product Detail for a single Product (Product window)
 *
 * @author Amy Radtke
 * @version 1.0  07/01/2017
 */
public class CatalogController extends HttpServlet {

    /**
     *  Handles any URL ending in /displayProduct or /displayAvailableProducts
     */
    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)throws IOException, ServletException{

        String requestURI = request.getRequestURI();
        String url = "/";

        if (requestURI.endsWith("/displayProduct")) {
            url = displayProduct(request, response);
        }else if (requestURI.endsWith("/displayAvailableProducts")) {
            url = displayAvailableProducts(request, response);
        }

        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);

    }//End - doGet()

    /**
     *  Handles no URLs at this time...
     */
    @Override
    public void doPost(HttpServletRequest request,
                      HttpServletResponse response)throws IOException, ServletException{

        String requestURI = request.getRequestURI();
        String url = "/";

        getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);

    }//End - doPost()

     /**
     * <br>
     * Initiates the display of a given Product in the Product Detail window.
     * <br><br>
     * Retrieves a Product from the Database based on the Product ID pulled from the incoming request.
     * The Product retrieved is then stored on the Session object and
     * the URL for the Product Detail window is returned - initiating display of that window.
     * <br><br>
     * Request object Parameters:<br>
     * "productId" - the Product ID that uniquely identifies a Product<br>
     * <br>
     * Session object Attributes:<br>
     * "product" - a Product Object<br>
     *
     * @return  URL to /catalog/product.jsp which displays the Product Detail window
     */
    private String displayProduct(HttpServletRequest request, HttpServletResponse response){

        String productId = request.getParameter("productId");

        if (productId != null){
            Product product = ProductDB.selectProduct(productId);
            HttpSession session = request.getSession();
            session.setAttribute("product", product);
        }

        return "/catalog/product.jsp";

    }//End - displayProduct()

    /**
     * <br>
     * Initiates the display of all Available Products of a particular type
     * in the Products window, as well as the Type of Product being displayed.
     * <br><br>
     * Retrieves the Name of the Product Type (such as Fruits, Vegetables, etc) from
     * the Request and stores it in the Session object - to be used by future windows.
     * Retrieves all Products that are available and are of a particular Product Type
     * from the Product Database.  The List of Available Products is then stored on the
     * Session object and the URL for the Products window is returned - initiating
     * display of that window.
     * <br><br>
     * Request object Parameters:<br>
     * "productTypeId" - the Product Type ID that uniquely identifies the Type of Product<br>
     * "productTypeName" - the Name corresponding to the Product Type ID
     * such as Fruits, Vegetables, etc<br>
     *
     * <br>
     * Session object Attributes:<br>
     * "productTypeName" - the Name corresponding to the Product Type ID
     * such as Fruits, Vegetables, etc<br>
     * "availableProducts" - Products that are available to be sold of a given Product Type
     *
     * @return  URL to /catalog/products.jsp which displays the Products window
     */
    private String displayAvailableProducts(HttpServletRequest request, HttpServletResponse response) {

        String productTypeId = request.getParameter("productTypeId");
        String productTypeName = request.getParameter("productTypeName");
        HttpSession session = request.getSession();
        session.setAttribute("productTypeName", productTypeName);

        if (productTypeId != null) {
            List<Product> availableProducts = ProductDB.selectAvailableProducts(productTypeId);
            session.setAttribute("availableProducts", availableProducts);
        }
        return "/catalog/products.jsp";

    }//End - displayProducts()

}//End - CatalogController.java
