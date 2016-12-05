package farmfresh.data;

import farmfresh.business.LineItem;
import farmfresh.business.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mom and Dad on 11/15/2016.
 */
public class LineItemDB {

    public static void insert(long invoiceId, LineItem lineItem) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query
                = "INSERT INTO LineItem (InvoiceID, ProductID, Quantity) "
                + "VALUES (?, ?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setLong(1, invoiceId);
            ps.setLong(2, lineItem.getProduct().getProductId());
            ps.setInt(3, lineItem.getQuantity());

            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static List<LineItem> selectLineItems(long invoiceID){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM LineItem WHERE InvoiceID = ?";

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
            return null;
        }finally{
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }

    }

    // NOT USED - BUT KEEPING LOGIC ANYWAY
//    public static void update(long invoiceId, LineItem lineItem) {
//        ConnectionPool pool = ConnectionPool.getInstance();
//        Connection connection = pool.getConnection();
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//
//        String query = "UPDATE LineItem SET Quantity = ? "
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
//        String query = "DELETE LineItem "
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
//        } finally {
//            DBUtil.closeResultSet(rs);
//            DBUtil.closePreparedStatement(ps);
//            pool.freeConnection(connection);
//        }
//    }

}
