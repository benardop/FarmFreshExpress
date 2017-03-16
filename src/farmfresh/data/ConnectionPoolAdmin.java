package farmfresh.data;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by benard on 11/15/2016.
 */
public class ConnectionPoolAdmin {
    private static ConnectionPoolAdmin pool = null;
    private static DataSource dataSource = null;

    public synchronized static ConnectionPoolAdmin getInstance() {
        if (pool == null) {
            pool = new ConnectionPoolAdmin();
        }
        return pool;
    }

    private ConnectionPoolAdmin() {
        try {
            InitialContext ic = new InitialContext();
            dataSource = (DataSource) ic.lookup("java:/comp/env/jdbc/farmDB");
        } catch (NamingException e) {
            System.err.println(e);
        }
    }

    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException sqle) {
            System.err.println(sqle);
            return null;
        }
    }

    public void freeConnection(Connection c) {
        try {
            c.close();
        } catch (SQLException sqle) {
            System.err.println(sqle);
        }
    }
}
