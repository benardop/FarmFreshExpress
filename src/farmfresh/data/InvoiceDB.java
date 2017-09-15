package farmfresh.data;

import farmfresh.business.Invoice;
import farmfresh.business.LineItem;
import farmfresh.business.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Purpose: To  provide all CRUD - Create, Read(Select), Update and Delete
 * functionality involving the 'invoice' Table.
 *
 * @author Amy Radtke
 * @version 1.0  07/01/2017
 */
public class InvoiceDB {

    /**
     * Insert Invoice into the 'invoice' table.  It is inserted at the point
     * that the Order is submitted and paid for.  (Note:  payment does not exist
     * in V1 - it will be an enhancements for future builds)<br>
     * Invoices are uniquely identified by InvoiceID which also serves as the
     * Invoice Number used when displaying the Invoice to the user.
     * @param invoice  An Invoice that has not yet been saved to the database
     */
    public static void insert(Invoice invoice) {

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query
                = "INSERT INTO invoice(UserID, InvoiceDate, TotalAmount, IsProcessed) "
                + "VALUES (?, NOW(), ?, FALSE)";

        try{
            ps = connection.prepareStatement(query);
            ps.setLong(1, invoice.getUser().getUserId());
            ps.setDouble(2, invoice.getTotalCost());

            ps.executeUpdate();

            // Get the Invoice ID from the last insert statement
            String identityQuery = "SELECT @@IDENTITY AS IDENTITY";
            Statement identityStatement = connection.createStatement();
            ResultSet identityResultSet = identityStatement.executeQuery(identityQuery);
            identityResultSet.next();
            long invoiceId = identityResultSet.getLong("IDENTITY");
            identityResultSet.close();
            identityStatement.close();

            // Write the Invoice's Line Items to the database (with the InvoiceID)
            List<LineItem> lineItems = invoice.getLineItems();
            for (LineItem item: lineItems){
                LineItemDB.insert(invoiceId, item);
            }

        }catch(SQLException e){
            System.err.println(e);
            throw new RuntimeException(e);
        }finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }

    }//End - insert()

    /**
     * Set the isProcessed Flag to TRUE for the given Invoice.
     * @param invoice {@link Invoice}
     */
    public static void markAsProcessed(Invoice invoice) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "UPDATE invoice SET"
                     + " IsProcessed = TRUE  "
                     + "WHERE InvoiceID = ?";

        try{
            ps = connection.prepareStatement(query);
            ps.setLong(1, invoice.getInvoiceNumber());
            ps.executeUpdate();

        }catch(SQLException e){
            System.err.println(e);
            throw new RuntimeException(e);
        }finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }

    }//End - markAsProcessed()

    /**
     * Select all Rows from the 'invoice' table where the isUnprocessed = FALSE.
     * An Invoice has not been processed until it has shipped.
     * @return List of Invoices
     */
    public static List<Invoice> selectUnprocessedInvoices(){

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query =  "SELECT * FROM invoice " +
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
            throw new RuntimeException(e);
        }finally{
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }

    }//End - selectUnprocessedInvoices()

}//End - InvoiceDB.java