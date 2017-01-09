package farmfresh.data;

import farmfresh.business.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mom and Dad on 11/15/2016.
 */
public class ProductDB {

    public static List<Product> selectAllProducts(String productTypeId) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query =  "SELECT Product.ProductID," +
                " Product.ProductTypeID," +
                " Product.ProductCode," +
                " Product.Name," +
                " Product.Description," +
                " Product.ImageID," +
                " Product.Price," +
                " Product.InSeason," +
                " ProductType.ProductTypeName" +
                " FROM ProductType, Product" +
                " WHERE ProductType.ProductTypeID = Product.ProductTypeID" +
                " AND Product.ProductTypeID = ?;";


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
                product.setInSeason(rs.getBoolean("InSeason"));
                product.setProductTypeName(rs.getString("ProductTypeName"));
                products.add(product);
            }
            return products;

        } catch (SQLException e) {
            System.err.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }

    }

//    public static Product selectProduct(String productID) {
//
//        ConnectionPool pool = ConnectionPool.getInstance();
//        Connection connection = pool.getConnection();
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//
//        String query = "SELECT * FROM Product "
//                + "WHERE ProductID = ?";
//
//        try {
//            ps = connection.prepareStatement(query);
//            ps.setString(1, productID);
//            rs = ps.executeQuery();
//
//            if (rs.next()) {
//                Product product = new Product();
//                product.setProductId(rs.getLong("ProductID"));
//                product.setProductCode(rs.getString("ProductCode"));
//                product.setDescription(rs.getString("Description"));
//                product.setPrice(rs.getDouble("Price"));
//                return product;
//            } else {
//                return null;
//            }
//
//        } catch (SQLException e) {
//            System.err.println(e);
//            return null;
//        } finally {
//            DBUtil.closeResultSet(rs);
//            DBUtil.closePreparedStatement(ps);
//            pool.freeConnection(connection);
//        }
//    }

    public static Product selectProduct(String productId) {

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;


        String query =  "SELECT Product.ProductID," +
                        " Product.ProductTypeID," +
                        " Product.ProductCode," +
                        " Product.Name," +
                        " Product.Description," +
                        " Product.ImageID," +
                        " Product.Price," +
                        " Product.InSeason," +
                        " ProductType.ProductTypeName" +
                        " FROM ProductType, Product" +
                        " WHERE ProductType.ProductTypeID = Product.ProductTypeID" +
                        " AND Product.ProductID = ?;";

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
                product.setInSeason(rs.getBoolean("InSeason"));
                product.setProductTypeName(rs.getString("ProductTypeName"));
                return product;
            } else {
                return null;
            }

        } catch (SQLException e) {
            System.err.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

// METHODS I PROGRAMMED - I DON'T NEED THEM NOW - BUT I MAY WANT THEM LATER...
//    public static void insert(Product product) {
//        ConnectionPool pool = ConnectionPool.getInstance();
//        Connection connection = pool.getConnection();
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//
//        String query
//                = "INSERT INTO Product(ProductID, Description, Price) "
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
//
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
//        String query = "UPDATE Products SET "
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
//
//        } finally {
//            DBUtil.closeResultSet(rs);
//            DBUtil.closePreparedStatement(ps);
//            pool.freeConnection(connection);
//
//        }
//
//    }

}
