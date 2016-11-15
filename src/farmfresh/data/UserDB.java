package farmfresh.data;

import farmfresh.business.User;

import java.sql.*;
//import java.sql.*

/**
 * Created by Mom and Dad on 11/6/2016.
 */
public class UserDB {

    public static void insert(User user) {
//        ConnectionPool pool = ConnectionPool.getInstance();  //TODO fix this
//        Connection connection = pool.getConnection();
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//
//        String query
//                = "INSERT INTO Users(first_name, last_name, email, company_name, "
//                + "address1, address2, city, state, zip, country, "
//                + "credit_card_type, credit_card_nbr, credit_card_exp_dt)"
//                + "VALUES (?, ?, ?, ?, "
//                + "?, ?, ?, ?, ?, ?, "
//                + "?, ?, ?)";
//
//        try{
//            ps = connection.prepareStatement(query);
//            ps.setString(1, user.getFirstName());
//            ps.setString(2, user.getLastName());
//            ps.setString(3, user.getEmail());
//            ps.setString(4, user.getCompanyName());
//            ps.setString(5, user.getAddress1());
//            ps.setString(6, user.getAddress2());
//            ps.setString(7, user.getCity());
//            ps.setString(8, user.getState());
//            ps.setString(9, user.getZip());
//            ps.setString(10, user.getCountry());
//            ps.setString(11, user.getCreditCardType());
//            ps.setString(12, user.getCreditCardNumber());
//            ps.setString(13, user.getCreditCardExpirationDate());
//
//            ps.executeUpdate();
//
//            //Get the ID from the last insert statement
//            String identityQuery = "SELECT @@IDENTITY AS IDENTITY";
//            Statement identityStatement = connection.createStatement();
//            ResultSet identityResultSet = identityStatement.executeQuery(identityQuery);
//
//            identityResultSet.next();
//
//            long userID = identityResultSet.getLong("IDENTITY");
//            identityResultSet.close();
//            identityStatement.close();
//
//            // set user id in the user object
//            user.setUserId(userID);
//
//        }catch(SQLException e){
//            System.err.println(e);
//
//        }finally {
//            DBUtil.closeResultSet(rs);
//            DBUtil.closePreparedStatement(ps);
//            //pool.freeConnection(connection);  //TODO  later
//
//        }

    }

    public static void update(User user) {
//        ConnectionPool pool = ConnectionPool.getInstance();  //TODO fix this
//        Connection connection = pool.getConnection();
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//
//        String query = "UPDATE Users SET "
//                + "first_name = ?, "
//                + "last_name = ?,  "
//                + "email = ?, "
//                + "company_name = ?, "
//                + "address1 = ?, "
//                + "address2 = ?,  "
//                + "city = ?, "
//                + "state = ?, "
//                + "zip = ?,  "
//                + "country = ?, "
//                + "credit_card_type = ?, "
//                + "credit_card_nbr = ?,  "
//                + "credit_card_exp_dt = ? "
//                + "WHERE email = ?";
//
//        try{
//            ps = connection.prepareStatement(query);
//            ps.setString(1, user.getFirstName());
//            ps.setString(2, user.getLastName());
//            ps.setString(3, user.getEmail());
//            ps.setString(4, user.getCompanyName());
//            ps.setString(5, user.getAddress1());
//            ps.setString(6, user.getAddress2());
//            ps.setString(7, user.getCity());
//            ps.setString(8, user.getState());
//            ps.setString(9, user.getZip());
//            ps.setString(10, user.getCountry());
//            ps.setString(11, user.getCreditCardType());
//            ps.setString(12, user.getCreditCardNumber());
//            ps.setString(13, user.getCreditCardExpirationDate());
//            ps.setString(14, user.getEmail());
//
//            ps.executeUpdate();
//
//        }catch(SQLException e){
//            System.err.println(e);
//
//        }finally {
//            DBUtil.closeResultSet(rs);
//            DBUtil.closePreparedStatement(ps);
//            //pool.freeConnection(connection);  //TODO  later
//
//        }

    }

    public static User selectUser(String email) {

//        ConnectionPool pool = ConnectionPool.getInstance();  //TODO fix this
//        Connection connection = pool.getConnection();
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//
//        String query = "SELECT * FROM Users"
//                     + "WHERE Email = ?";
//
//        try {
//            ps = connnection.prepareStatement(query);
//            ps.setString(1, email);
//            rs = ps.executeQuery();
//            User user = null;
//
//            if (rs.next()) {
//                user = new User();
//                user.setUserId(rs.getLong("UserID"));
//                user.setFirstName(rs.getString("FirstName"));
//                user.setLastName(rs.getString("LastName"));
//                user.setEmail(rs.getString("Email"));
//                user.setCompanyName(rs.getString("CompanyName"));
//                user.setAddress1(rs.getString("Address1"));
//                user.setAddress2(rs.getString("Address2"));
//                user.setCity(rs.getString("City"));
//                user.setState(rs.getString("State"));
//                user.setZip(rs.getString("Zip"));
//                user.setCountry(rs.getString("Country"));
//                user.setCreditCardType(rs.getString("CreditCardType"));
//                user.setCreditCardNumber(rs.getString("CreditCardNbr"));
//                user.setCreditCardExpirationDate(rs.getString("CreditCardExpDt"));
//            }
//            return user;
//
//        } catch (SQLException e) {
//            System.err.println(e);
//            return null;
//        }finally{
//            DBUtil.closeResultSet(rs);
//            DBUtil.closePreparedStatement(ps);
//            //pool.freeConnection(connection);  //TODO  later
//        }
        return null;  //TODO Remove after uncommenting
    }

    public static boolean emailExists(String email){
//        ConnectionPool pool = ConnectionPool.getInstance();  //TODO fix this
//        Connection connection = pool.getConnection();
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//
//        String query = "SELECT Email FROM Users"
//                     + "WHERE Email = ?";
//
//        try {
//            ps = connnection.prepareStatement(query);
//            ps.setString(1, email);
//            rs = ps.executeQuery();
//            return rs.next();
//
//        } catch (SQLException e) {
//            System.err.println(e);
//            return false;
//        }finally{
//            DBUtil.closeResultSet(rs);
//            DBUtil.closePreparedStatement(ps);
//            //pool.freeConnection(connection);  //TODO  later
//        }
        return false;  //TODO remove this line...
    }

}
