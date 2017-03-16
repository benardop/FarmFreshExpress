package farmfresh.data;

import farmfresh.business.UserRole;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Mom and Dad on 11/15/2016.
 */
public class UserRoleDB {

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
