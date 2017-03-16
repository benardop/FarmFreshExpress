package farmfresh.data;

import farmfresh.business.UserPass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Mom and Dad on 11/15/2016.
 */
public class UserPassDB {

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
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }//End - insert()

}//End - UserPassDB.java
