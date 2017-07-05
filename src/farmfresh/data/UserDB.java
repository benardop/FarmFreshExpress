/**
 * Created by Mom and Dad on 11/6/2016.
 */

package farmfresh.data;

import farmfresh.business.User;

import java.sql.*;

/**
 * Purpose: To  provide all CRUD - Create, Read(Select), Update and Delete Functionality
 * involving the 'user' Table.
 *
 * @author Amy Radtke
 * @version 1.0  07/01/2017
 */
public class UserDB {

    /**
     * Insert a row into the 'user' table
     *
     * @param user {@link User}
     */
    public static void insert(User user) {

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query
                = "INSERT INTO user (FirstName, LastName, Email, CompanyName, "
                + "Address1, Address2, City, State, Zip, IsSubscribedToNewsletter) "
                + "VALUES (?, ?, ?, ?, ?, "
                + "?, ?, ?, ?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getCompanyName());
            ps.setString(5, user.getAddress1());
            ps.setString(6, user.getAddress2());
            ps.setString(7, user.getCity());
            ps.setString(8, user.getState());
            ps.setString(9, user.getZip());
            ps.setBoolean(10, user.isSubscribedToNewsletter());

            ps.executeUpdate();

            // Get the Unique ID from the last INSERT statement.
            // And use it to set the UserID of the User object.
            String identityQuery = "SELECT @@IDENTITY AS IDENTITY";
            Statement identityStatement = connection.createStatement();
            ResultSet identityResultSet = identityStatement.executeQuery(identityQuery);
            identityResultSet.next();
            long userID = identityResultSet.getLong("IDENTITY");
            identityResultSet.close();
            identityStatement.close();
            user.setUserId(userID);

        } catch (SQLException e) {
            System.err.println(e);
            throw new RuntimeException(e);
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }

    }//End - insert()

    /**
     * Update a row in the 'user' table for the given User
     *
     * @param user {@link User}
     */
    public static void update(User user) {

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "UPDATE User SET "
                + "FirstName = ?, "
                + "LastName = ?, "
                + "CompanyName = ?, "
                + "Address1 = ?, "
                + "Address2 = ?, "
                + "City = ?, "
                + "State = ?, "
                + "Zip = ? "
                + "WHERE Email = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getCompanyName());
            ps.setString(4, user.getAddress1());
            ps.setString(5, user.getAddress2());
            ps.setString(6, user.getCity());
            ps.setString(7, user.getState());
            ps.setString(8, user.getZip());
            ps.setString(9, user.getEmail());

            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println(e);
            throw new RuntimeException(e);
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }

    }//End - update()

    /**
     * Set the isSubscribedToNewsletter Flag to TRUE for the User
     * with the given Email Address.
     *
     * @param email User's Email
     */
    public static void subscribeToNewsletter(String email) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "UPDATE User SET "
                + "IsSubscribedToNewsletter = TRUE "
                + "WHERE Email = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
            throw new RuntimeException(e);
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }

    }//End - subscribeToNewsletter()

    /**
     * Set the isSubscribedToNewsletter Flag to FALSE for the User
     * with the given Email Address.
     *
     * @param email User's Email
     */
    public static void unsubscribeFromNewsletter(String email) {

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "UPDATE User SET "
                + "IsSubscribedToNewsletter = FALSE "
                + "WHERE Email = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
            throw new RuntimeException(e);
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }

    }//End - UnsubscribeFromNewsletter

    /**
     * Delete a row from the 'user' table given a User object
     *
     * @param user {@link User}
     */
    public static void delete(User user) {

        delete(user.getEmail());

    }//End - delete(User user)

    /**
     * Delete a row from the 'user' table given the User's Email
     *
     * @param email User's Email (Unique field in User table)
     */
    public static void delete(String email) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "DELETE FROM User WHERE Email = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println(e);
            throw new RuntimeException(e);
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }//End - Delete(String email)

    /**
     * Select a User from the 'user' table given the User's
     * UserID (Primary Key)
     *
     * @param userId Primary Key in User Table
     * @return {@link User} with given UserID (Primary Key)
     */
    public static User selectUser(Long userId) {

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM User "
                + "WHERE UserID = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setLong(1, userId);
            rs = ps.executeQuery();
            User user = null;
            if (rs.next()) {
                user = buildUser(rs);
            }
            return user;
        } catch (SQLException e) {
            System.err.println(e);
            throw new RuntimeException(e);
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }

    }//End - selectUser(Long userId)

    /**
     * Select a User given the 'user' table given the User's Email
     *
     * @param email User's Email
     *              with given email (which is a
     *              Unique Field in the User Table)
     * @return {@link User}
     */
    public static User selectUser(String email) {

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM User "
                + "WHERE Email = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            User user = null;
            if (rs.next()) {
                user = buildUser(rs);
            }
            return user;
        } catch (SQLException e) {
            System.err.println(e);
            throw new RuntimeException(e);
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }

    }//End - selectUser(String email)

    /**
     * Build User Object given User Information containted in the ResultSet
     *
     * @param rs ResultSet containing a row from the User table
     * @return {@link User} User Information
     * @
     */
    private static User buildUser(ResultSet rs) {

        try {
            User user = new User();
            user.setUserId(rs.getLong("UserID"));
            user.setFirstName(rs.getString("FirstName"));
            user.setLastName(rs.getString("LastName"));
            user.setEmail(rs.getString("Email"));
            user.setCompanyName(rs.getString("CompanyName"));
            user.setAddress1(rs.getString("Address1"));
            user.setAddress2(rs.getString("Address2"));
            user.setCity(rs.getString("City"));
            user.setState(rs.getString("State"));
            user.setZip(rs.getString("Zip"));
            user.setIsSubscribedToNewsletter(rs.getBoolean("IsSubscribedToNewsletter"));
            return user;
        } catch (SQLException e) {
            System.err.println(e);
            throw new RuntimeException(e);
        }

    }//End - buildUser()

    /**
     * @return TRUE if a User exists in the user Table with the given Email.
     * FALSE otherwise.
     */
    public static boolean emailExists(String email) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT Email FROM User "
                + "WHERE Email = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.err.println(e);
            throw new RuntimeException(e);
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }

    }//End - emailExists()

    /**
     * @return TRUE if the User that exists in the user Table with the given Email
     * has only subscribed to the eNewsletter and has not Registered.
     * FALSE otherwise.  NOTE:  When registering, a User is required to enter a
     * Last Name.
     */
    public static boolean userIsOnlySubcribedToNewsletterAndNotRegistered(String email) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT Email FROM User "
                + "WHERE Email = ? "
                + "AND LastName IS NULL "
                + "AND IsSubscribedToNewsletter IS TRUE";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.err.println(e);
            throw new RuntimeException(e);
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }

    }//End - userIsOnlySubscribedToNewsletterAndNotRegistered()

}//End - UserDB.java