package farmfresh.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Database Utility Object - used to close Database Statements,
 * Result Sets and Prepared Statements
 *
 * @author Benard Pacho developed the logic
 *         Amy Radtke commented the code and added the RuntimeException logic.
 */
public class DBUtil {

    /**
     * Closes the given SQL Statement
     * @param s {@link Statement}
     */
      public static void closeStatement(Statement s) {

          try {
              if (s != null) {
                  s.close();
              }

          } catch (SQLException e) {
              System.err.println(e);
              throw new RuntimeException(e);
          }
      }

    /**
     * Closes the given SQL Result Set
     * @param rs {@link ResultSet}
     */
    public static void closeResultSet(ResultSet rs) {

        try {
            if (rs != null) {
                rs.close();
            }

        } catch (SQLException e) {
            System.err.println(e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Closes the given SQL Prepared Statement
     * @param ps {@link Statement}
     */
    public static void closePreparedStatement(Statement ps){

          try {
              if (ps != null) {
                  ps.close();
              }

          } catch (SQLException e) {
              System.err.println(e);
              throw new RuntimeException(e);
          }
      }

}//End DBUtil.java
