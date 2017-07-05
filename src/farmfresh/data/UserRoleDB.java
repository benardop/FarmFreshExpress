package farmfresh.data;

import farmfresh.business.UserRole;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Purpose: To  provide all CRUD - Create, Read(Select), Update and Delete
 * functionality involving the 'userrole' Table.
 *
 * @author Amy Radtke
 * @version 1.0  07/01/2017
 */
public class UserRoleDB {

    /**
     * Insert a row into the 'userrole' table
     * @param userRole contains the User's User/Login Name and Role
     * Roles Such As:  User, Super User and Admin
     */
    public static void insert(UserRole userRole) {

        ConnectionPoolAdmin pool = ConnectionPoolAdmin.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query
                = "INSERT INTO userrole (Username, Rolename) "
                + "VALUES (?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, userRole.getUsername());
            ps.setString(2, userRole.getRolename());

            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }//End - insert()

}//End - UserRoleDB.java
