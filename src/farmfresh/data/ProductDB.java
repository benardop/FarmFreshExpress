package farmfresh.data;

import farmfresh.business.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Purpose: To  provide all CRUD - Create, Read(Select), Update and Delete
 * functionality involving the 'product' Table.
 *
 * @author Amy Radtke
 * @version 1.0  07/01/2017
 */
public class ProductDB {

    /**
     * Return the List of Products matching the given Product Type that are
     * Available to be sold
     * @param productTypeId  Unique ID for a Product Type such as fruits, veggies
     * @return List of Products of a given Product Type that are available to be sold
     */
    public static List<Product> selectAvailableProducts(String productTypeId) {

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query =  "SELECT product.ProductID," +
                " product.ProductTypeID," +
                " product.ProductCode," +
                " product.Name," +
                " product.Description," +
                " product.ImageID," +
                " product.Price," +
                " product.IsAvailable," +
                " producttype.ProductTypeName" +
                " FROM producttype, product" +
                " WHERE producttype.ProductTypeID = product.ProductTypeID" +
                " AND product.ProductTypeID = ?" +
                " AND product.IsAvailable = TRUE" +
                " ORDER BY product.Name;";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, productTypeId);
            rs = ps.executeQuery();

            List<Product> products = new ArrayList<Product>();

            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getLong("ProductID"));
                product.setProductTypeId(rs.getLong("ProductTypeID"));
                product.setProductCode(rs.getString("ProductCode"));
                product.setName(rs.getString("Name"));
                product.setDescription(rs.getString("Description"));
                product.setImageId(rs.getString("ImageID"));
                product.setPrice(rs.getDouble("Price"));
                product.setIsAvailable(rs.getBoolean("IsAvailable"));
                product.setProductTypeName(rs.getString("ProductTypeName"));
                products.add(product);
            }
            return products;

        } catch (SQLException e) {
            System.err.println(e);
            throw new RuntimeException(e);
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }

    }//End - selectAvailableProducts

    /**
     * Return a Product given the Product's Unique ID
     * @param productId  A Product's Unique ID
     * @return {@link Product}
     */
    public static Product selectProduct(String productId) {

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query =  "SELECT product.ProductID," +
                        " product.ProductTypeID," +
                        " product.ProductCode," +
                        " product.Name," +
                        " product.Description," +
                        " product.ImageID," +
                        " product.Price," +
                        " product.IsAvailable," +
                        " producttype.ProductTypeName" +
                        " FROM producttype, product" +
                        " WHERE producttype.ProductTypeID = product.ProductTypeID" +
                        " AND product.ProductID = ?;";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, productId);
            rs = ps.executeQuery();

            if (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getLong("ProductID"));
                product.setProductTypeId(rs.getLong("ProductTypeID"));
                product.setProductCode(rs.getString("ProductCode"));
                product.setName(rs.getString("Name"));
                product.setDescription(rs.getString("Description"));
                product.setImageId(rs.getString("ImageID"));
                product.setPrice(rs.getDouble("Price"));
                product.setIsAvailable(rs.getBoolean("IsAvailable"));
                product.setProductTypeName(rs.getString("ProductTypeName"));
                return product;
            } else {
                return null;
            }

        } catch (SQLException e) {
            System.err.println(e);
            throw new RuntimeException(e);
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }

    }//End - selectProduct()

// METHODS I PROGRAMMED - I DON'T NEED THEM NOW - BUT I MAY WANT THEM FOR A LATER VERSION...
//    public static void insert(Product product) {
//        ConnectionPool pool = ConnectionPool.getInstance();
//        Connection connection = pool.getConnection();
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//
//        String query
//                = "INSERT INTO product(ProductID, Description, Price) "
//                + "VALUES (?, ?, ?)";
//
//        try {
//            ps = connection.prepareStatement(query);
//            ps.setString(1, product.getProductCode());
//            ps.setString(2, product.getDescription());
//            ps.setString(3, Double.toString(product.getPrice()));
//
//            ps.executeUpdate();
//
//              //Get the ID from the last insert statement
//            String identityQuery = "SELECT @@IDENTITY AS IDENTITY";
//            Statement identityStatement = connection.createStatement();
//            ResultSet identityResultSet = identityStatement.executeQuery(identityQuery);
//
//            identityResultSet.next();
//
//            long productId = identityResultSet.getLong("IDENTITY");
//            identityResultSet.close();
//            identityStatement.close();
//
//            // set user id in the user object
//            product.setProductId(productId);
//
//        } catch (SQLException e) {
//            System.err.println(e);
//            throw new RuntimeException(e);
//        } finally {
//            DBUtil.closeResultSet(rs);
//            DBUtil.closePreparedStatement(ps);
//            pool.freeConnection(connection);
//
//        }
//
//    }
//
//    public static void update(Product product) {
//        ConnectionPool pool = ConnectionPool.getInstance();
//        Connection connection = pool.getConnection();
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//
//        String query = "UPDATE product SET "
//                + "product_code = ?, "
//                + "description = ?,  "
//                + "price = ? "
//                + "WHERE product_code = ?";
//
//        try {
//            ps = connection.prepareStatement(query);
//            ps.setString(1, product.getProductCode());
//            ps.setString(2, product.getDescription());
//            ps.setString(3, Double.toString(product.getPrice()));
//            ps.setString(4, product.getProductCode());
//
//            ps.executeUpdate();
//
//        } catch (SQLException e) {
//            System.err.println(e);
//            throw new RuntimeException(e);
//        } finally {
//            DBUtil.closeResultSet(rs);
//            DBUtil.closePreparedStatement(ps);
//            pool.freeConnection(connection);
//
//        }
//
//    }

}//End - ProductDB.java
