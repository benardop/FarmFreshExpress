package farmfresh.data;

import farmfresh.business.Invoice;
import farmfresh.business.LineItem;
import farmfresh.business.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mom and Dad on 11/11/2016.
 */
public class InvoiceDB {

    // Invoice is written to database at point that Order is complete and paid for
    public static void insert(Invoice invoice) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query
                = "INSERT INTO Invoice(UserID, InvoiceDate, TotalAmount, IsProcessed) "
                + "VALUES (?, NOW(), ?, 'n')";

        try{
            ps = connection.prepareStatement(query);
            ps.setLong(1, invoice.getUser().getUserId());
            ps.setDouble(2, invoice.getInvoiceTotal());

            ps.executeUpdate();

            //Get the ID from the last insert statement
            String identityQuery = "SELECT @@IDENTITY AS IDENTITY";
            Statement identityStatement = connection.createStatement();
            ResultSet identityResultSet = identityStatement.executeQuery(identityQuery);

            identityResultSet.next();

            long invoiceId = identityResultSet.getLong("IDENTITY");
            identityResultSet.close();
            identityStatement.close();

            //write line items to the line items table
            List<LineItem> lineItems = invoice.getLineItemList();
            for (LineItem item: lineItems){
                LineItemDB.insert(invoiceId, item);
            }

        }catch(SQLException e){
            System.err.println(e);

        }finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);

        }

    }

    public static void update(Invoice invoice) {
        ConnectionPool pool = ConnectionPool.getInstance();  //TODO fix this
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "UPDATE Invoice SET "
                + "TotalAmount = ?, "
                + "IsProcessed = ?,  "
                + "WHERE UserID = ?, InvoiceDate = ?";

        try{
            ps = connection.prepareStatement(query);
            ps.setDouble(1, invoice.getInvoiceTotal());
            ps.setString(2, "y");
            ps.setLong(2, invoice.getUser().getUserId());
            //ps.setDate(3, invoice.getInvoiceDate());

            ps.executeUpdate();

            //TODO  HOW DO YOU UPDATE PRODUCT ITEMS

        }catch(SQLException e){
            System.err.println(e);

        }finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }

    }

    public static List<Invoice> selectUnprocessedInvoices(){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query =  "SELECT * FROM Invoice " +
                        "WHERE IsProcessed = 'n'";

        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();

            List<Invoice> unprocessedInvoices = new ArrayList<Invoice>();

            while (rs.next()){
                Invoice invoice = new Invoice();
                long userId = rs.getLong("UserID");
                User user = UserDB.selectUser(userId);
                invoice.setUser(user);
                List<LineItem> lineItems = LineItemDB.selectLineItems(rs.getLong("InvoiceID"));
                invoice.setInvoiceDate(rs.getDate("InvoiceDate"));
                //TODO total???

                unprocessedInvoices.add(invoice);
            }
            return unprocessedInvoices;

        } catch (SQLException e) {
            System.err.println(e);
            return null;
        }finally{
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }


// NOT USED ---- YET --- KEEPING JUST IN CASE!
//    public static Product selectProduct(String productCode) {
//
//        ConnectionPool pool = ConnectionPool.getInstance();  //TODO fix this
//        Connection connection = pool.getConnection();
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//
//        String query = "SELECT * FROM Products"
//                + "WHERE product_code = ?";
//
//        try {
//            ps = connnection.prepareStatement(query);
//            ps.setString(1, productCode);
//            rs = ps.executeQuery();
//            Product product = null;
//
//            if (rs.next()) {
//                product = new Product();
//                product.setProductCode(rs.getString("ProductCode"));
//                product.setDescription(rs.getString("Description"));
//                product.setPrice(rs.getDouble("Price"));
//            }
//            return product;
//
//        } catch (SQLException e) {
//            System.err.println(e);
//            return null;
//        }finally{
//            DBUtil.closeResultSet(rs);
//            DBUtil.closePreparedStatement(ps);
//            //pool.freeConnection(connection);  //TODO  later
//        }
//        return null;  //TODO Remove after uncommenting
//    }
//
//    public static List<Product> selectAllProducts(){
//        ConnectionPool pool = ConnectionPool.getInstance();  //TODO fix this
//        Connection connection = pool.getConnection();
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//
//        String query = "SELECT * FROM Users";
//
//        try {
//            ps = connnection.prepareStatement(query);
//            rs = ps.executeQuery();
//
//            List<Product> products = new ArrayList<Product>();
//
//            while (rs.next()){
//                Product product = new Product();
//                product.setProductCode(rs.getString("ProductCode"));
//                product.setDescription(rs.getString("Description"));
//                product.setPrice(rs.getDouble("Price"));
//                products.add(product);
//            }
//            return products;
//
//        } catch (SQLException e) {
//            System.err.println(e);
//            return false;
//        }finally{
//            DBUtil.closeResultSet(rs);
//            DBUtil.closePreparedStatement(ps);
//            //pool.freeConnection(connection);  //TODO  later
//        }
//        return false;  //TODO remove this line...
//    }

}



