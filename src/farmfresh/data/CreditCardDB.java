/**
 * Created by Mom and Dad on 11/6/2016.
 *
 * IN DEVELOPMENT - FIRST DRAFT - NEED TO LOOK AT DATATYPES USED...
 * Not tested...
 */
package farmfresh.data;

import farmfresh.business.CreditCard;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Purpose: To  provide all CRUD - Create, Read(Select), Update and Delete
 * functionality involving the creditcard DB Table.
 * NOT YET IMPLEMENTED - THIS IS ONLY A SHELL
 *
 * @author Amy Radtke
 * @version 1.0  07/01/2017
 */
public class CreditCardDB {

    public static void insert(CreditCard creditCard) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query
                = "INSERT INTO CreditCard (UserID, CreditCardType, CreditCardNumber, " +
                    "CreditCardExpMonth, CreditCardExpYear, Primary)"
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setLong(1, creditCard.getUserId());
            ps.setString(2, creditCard.getCreditCardType());
            ps.setString(3, creditCard.getCreditCardNumber());
            ps.setString(4, creditCard.getCreditCardExpMonth());
            ps.setString(5, creditCard.getCreditCardExpYear());
            ps.setBoolean(6, creditCard.isPrimary());

            ps.executeUpdate();

            //Get the user ID from the last INSERT statement.
            String identityQuery = "SELECT @@IDENTITY AS IDENTITY";
            Statement identityStatement = connection.createStatement();
            ResultSet identityResultSet = identityStatement.executeQuery(identityQuery);
            identityResultSet.next();
            long creditCardId = identityResultSet.getLong("IDENTITY");
            identityResultSet.close();
            identityStatement.close();

            // Set the CreditCardID in the creditCard object
            creditCard.setCreditCardId(creditCardId);
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static void update(CreditCard creditCard) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "UPDATE CreditCard SET "
                + "CreditCardType = ?, "
                + "CreditCardNumber = ?, "
                + "CreditCardExpMonth = ?, "
                + "CreditCardExpYear = ? "
                + "Primary = ?"
                + "WHERE CreditCardID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, creditCard.getCreditCardType());
            ps.setString(2, creditCard.getCreditCardNumber());
            ps.setString(3, creditCard.getCreditCardExpMonth());
            ps.setString(4, creditCard.getCreditCardExpYear());
            ps.setBoolean(5, creditCard.isPrimary());
            ps.setLong(6, creditCard.getCreditCardId());

            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static void delete(Long creditCardId) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "DELETE FROM CreditCard WHERE CreditCardID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setLong(1, creditCardId);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static List<CreditCard> selectAllCreditCards(String userId) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query =  "SELECT * FROM CreditCard " +
                        "WHERE UserID = ? " +
                        " ORDER BY Primary DESC;";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, userId);
            rs = ps.executeQuery();

            List<CreditCard> creditCards = new ArrayList<CreditCard>();

            while (rs.next()) {
                CreditCard creditCard = buildCreditCard(rs);
                creditCards.add(creditCard);
            }
            return creditCards;

        } catch (SQLException e) {
            System.err.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }

    }

    public static CreditCard selectCreditCard(Long creditCardId) {

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query =  "SELECT * FROM CreditCard" +
                " WHERE CreditCardID = ?;";

        try {
            ps = connection.prepareStatement(query);
            ps.setLong(1, creditCardId);
            rs = ps.executeQuery();

            if (rs.next()) {
                return buildCreditCard(rs);
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

    public static CreditCard buildCreditCard(ResultSet rs) throws SQLException {

        try {
            CreditCard creditCard = new CreditCard();
            creditCard.setCreditCardId(rs.getLong("CreditCardID"));
            creditCard.setUserId(rs.getLong("UserID"));
            creditCard.setCreditCardType(rs.getString("CreditCardType"));
            creditCard.setCreditCardNumber(rs.getString("CreditCardNumber"));
            creditCard.setCreditCardExpMonth(rs.getString("CreditCardExpMonth"));
            creditCard.setCreditCardExpYear(rs.getString("CreditCardExpYear"));
            creditCard.setPrimary(rs.getBoolean("Primary"));
            return creditCard;
        } catch (SQLException e) {
            System.err.println(e);
            throw e;
        }
    }

}