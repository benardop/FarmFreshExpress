package farmfresh.data;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * A connection pool contains a group of JDBC connections that are created when the
 * connection pool is registered. Connection pools use a JDBC driver
 * to create physical database connections. This class provides the ability
 * for the application to borrow a connection from the pool, use it, then
 * return it to the pool by closing it.  <br>
 *
 * It connects to the farmDB that contains all security related
 * data such as User Logins, Passwords and Roles
 * See the context.xml file for additional details
 *
 * @author Benard Pacho developed the logic
 *         Amy Radtke commented the code and added RunTimeException logic
 */
public class ConnectionPoolAdmin {
    /**
     * DB Connection pool used by the Farm Fresh application
     * One set up statically for this Application
     */
    private static ConnectionPoolAdmin pool = null;

    /**
     * The factory for connections to the physical datasource: farmDB
     * that is connected to the security related Farm Fresh Express Data
     * such as User Logins, Passwords and Roles.
     * One set up statically for this Application
     */
    private static DataSource dataSource = null;

    /**
     * @return a New ConnectionPoolAdmin if one has not been instantiated yet.
     */
    public synchronized static ConnectionPoolAdmin getInstance() {
        if (pool == null) {
            pool = new ConnectionPoolAdmin();
        }
        return pool;
    }

    /**
     * ConnectionPool Constructor that initializes the Datasource
     * associated with the farmDB used by this application
     */
    private ConnectionPoolAdmin() {
        try {
            InitialContext ic = new InitialContext();
            dataSource = (DataSource) ic.lookup("java:/comp/env/jdbc/farmDB");
        } catch (NamingException e) {
            System.err.println(e);
            throw new RuntimeException(e);
        }
    }

    /**
     * @return Connection returned from the farmFreshDB Datasource (security tables)
     */
    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException sqle) {
            System.err.println(sqle);
            throw new RuntimeException(sqle);
        }
    }

    /**
     * Free the given Database Connection by closing it
     * @param c {@link Connection}
     */
    public void freeConnection(Connection c) {
        try {
            c.close();
        } catch (SQLException sqle) {
            System.err.println(sqle);
            throw new RuntimeException(sqle);
        }
    }

}//End - ConnectionPoolAdmin()