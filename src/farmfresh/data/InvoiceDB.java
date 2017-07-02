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

        // Get a connection from the Connection Pool
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();

        PreparedStatement ps = null;
        ResultSet rs = null;

        String query
                = "INSERT INTO Invoice(UserID, InvoiceDate, TotalAmount, IsProcessed) "
                + "VALUES (?, NOW(), ?, FALSE)";

        try{
            ps = connection.prepareStatement(query);
            ps.setLong(1, invoice.getUser().getUserId());
            ps.setDouble(2, invoice.getInvoiceTotal());

            ps.executeUpdate();

            // Get the Invoice ID from the last insert statement
            String identityQuery = "SELECT @@IDENTITY AS IDENTITY";
            Statement identityStatement = connection.createStatement();
            ResultSet identityResultSet = identityStatement.executeQuery(identityQuery);
            identityResultSet.next();
            long invoiceId = identityResultSet.getLong("IDENTITY");
            identityResultSet.close();
            identityStatement.close();

            // Write line items to the database (with the InvoiceID
            List<LineItem> lineItems = invoice.getLineItems();
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

    public static void markAsProcessed(Invoice invoice) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "UPDATE Invoice SET"
                     + " IsProcessed = TRUE  "
                     + "WHERE InvoiceID = ?";

        try{
            ps = connection.prepareStatement(query);
            ps.setLong(1, invoice.getInvoiceNumber());
            ps.executeUpdate();

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
                        "WHERE IsProcessed = FALSE ";

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
                invoice.setLineItems(lineItems);
                invoice.setInvoiceDate(rs.getTimestamp("InvoiceDate"));
                invoice.setInvoiceNumber(rs.getLong("InvoiceID"));

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

    }//End - selectUnprocessedInvoices()

}//End - InvoiceDB.java