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
 * return it to the pool by closing it.<br>
 *
 * It connects to the farmFreshDB that contains all non-security related
 * data such as User, Cart, Invoice, etc. See the context.xml file for
 * additional details
 *
 * @author Benard Pacho developed the logic  Amy Radtke commented the code.
 */
public class ConnectionPool {

    /**
     * DB Connection pool used by the Farm Fresh application
     * One set up statically for this Application
     */
    private static ConnectionPool pool = null;

    /**
     * The factory for connections to the physical datasource: farmFreshDB
     * that is connected to the non-security related Farm Fresh Express Data
     * such as Users, Cart, Invoices, etc.
     * One set up statically for this Application
     */
    private static DataSource dataSource = null;

    /**
     * @return a New ConnectionPool if one has not been instantiated yet.
     */
    public synchronized static ConnectionPool getInstance() {
        if (pool == null) {
            pool = new ConnectionPool();
        }
        return pool;
    }

    /**
     * ConnectionPool Constructor that initializes the Datasource
     * associated with the farmFreshDB used by this application
     */
    private ConnectionPool() {
        try {
            InitialContext ic = new InitialContext();
            dataSource = (DataSource) ic.lookup("java:/comp/env/jdbc/farmFreshDB");
        } catch (NamingException e) {
            System.err.println(e);
        }
    }

    /**
     * @return Connection returned from the farmFreshDB Datasource (non-security tables)
     */
    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException sqle) {
            System.err.println(sqle);
            return null;
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
        }
    }

}//End - ConnectionPool.java
