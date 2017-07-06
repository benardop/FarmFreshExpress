package farmfresh.data;

import farmfresh.business.UserPass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Purpose: To  provide all CRUD - Create, Read(Select), Update and Delete
 * functionality involving the 'userpass' Table.
 *
 * @author Amy Radtke
 * @version 1.0  07/01/2017
 */
public class UserPassDB {

    /**
     * Insert a row into the userpass table
     * @param userPass contains the User's User/Login Name and Password
     */
    public static void insert(UserPass userPass) {

        ConnectionPoolAdmin pool = ConnectionPoolAdmin.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query
                = "INSERT INTO userpass (Username, Password) "
                + "VALUES (?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, userPass.getUsername());
            ps.setString(2, userPass.getPassword());

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

}//End - UserPassDB.java
