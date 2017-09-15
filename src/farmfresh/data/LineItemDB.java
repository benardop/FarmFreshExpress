package farmfresh.data;

import farmfresh.business.LineItem;
import farmfresh.business.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Purpose: To  provide all CRUD - Create, Read(Select), Update and Delete
 * functionality involving the 'lineitem' Table.
 *
 * @author Amy Radtke
 * @version 1.0  07/01/2017
 */
public class LineItemDB {

    /**
     * Insert a LineItem into the 'lineitem' table associated with the
     * given Invoice ID.
     * @param invoiceId an Invoice's Unique ID
     * @param lineItem {@link LineItem}
     */
    public static void insert(long invoiceId, LineItem lineItem) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query
                = "INSERT INTO lineitem (InvoiceID, ProductID, Quantity) "
                + "VALUES (?, ?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setLong(1, invoiceId);
            ps.setLong(2, lineItem.getProduct().getProductId());
            ps.setInt(3, lineItem.getQuantity());

            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println(e);
            throw new RuntimeException(e);
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }

    }//End - insert()

    /**
     * Select all Line Items that belong to the Invoice with the given Invoice ID
     * @param invoiceID Unique Identifier for an Invoice
     * @return List of Line Items belonging to the Invoice with the given Invoice ID
     */
    public static List<LineItem> selectLineItems(long invoiceID){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM lineitem WHERE InvoiceID = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setLong(1, invoiceID);
            rs = ps.executeQuery();

            List<LineItem> lineItems = new ArrayList<LineItem>();

            while (rs.next()){
                LineItem lineItem = new LineItem();
                Product product =  ProductDB.selectProduct(rs.getString("ProductID"));
                lineItem.setProduct(product);
                lineItem.setQuantity(rs.getInt("Quantity"));
                lineItems.add(lineItem);
            }
            return lineItems;

        } catch (SQLException e) {
            System.err.println(e);
            throw new RuntimeException(e);
        }finally{
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }

    }//End - selectLineItems()

    // NOT USED - BUT KEEPING LOGIC ANYWAY
//    public static void update(long invoiceId, LineItem lineItem) {
//        ConnectionPool pool = ConnectionPool.getInstance();
//        Connection connection = pool.getConnection();
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//
//        String query = "UPDATE lineitem SET Quantity = ? "
//                     + "WHERE InvoiceId = ? AND ProductId = ?";
//        try {
//            ps = connection.prepareStatement(query);
//            ps.setInt(1, lineItem.getQuantity());
//            ps.setLong(2, invoiceId);
//            ps.setLong(3, lineItem.getProduct().getProductId());
//
//
//            ps.executeUpdate();
//        } catch (SQLException e) {
//            System.err.println(e);
//            throw new RuntimeException(e);
//        } finally {
//            DBUtil.closeResultSet(rs);
//            DBUtil.closePreparedStatement(ps);
//            pool.freeConnection(connection);
//        }
//    }
//
//    public static void delete(long invoiceId, long productId) {
//        ConnectionPool pool = ConnectionPool.getInstance();
//        Connection connection = pool.getConnection();
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//
//        String query = "DELETE lineitem "
//                + "WHERE InvoiceId = ? AND ProductId = ?";
//        try {
//            ps = connection.prepareStatement(query);
//            ps.setLong(1, invoiceId);
//            ps.setLong(2, productId);
//
//            ps.executeUpdate();
//
//        } catch (SQLException e) {
//            System.err.println(e);
//                throw new RuntimeException(e);
//        } finally {
//            DBUtil.closeResultSet(rs);
//            DBUtil.closePreparedStatement(ps);
//            pool.freeConnection(connection);
//        }
//    }

}//End - LineItemDB.java
