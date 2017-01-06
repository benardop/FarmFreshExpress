package farmfresh.data;

import farmfresh.business.ProductType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mom and Dad on 12/14/2016.
 */
public class ProductTypeDB {

    public static List<ProductType> selectProductTypes() {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM ProductType ORDER BY OrderNumber";

        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();

            List<ProductType> productTypes = new ArrayList<ProductType>();

            while (rs.next()) {
                ProductType productType = new ProductType();
                productType.setProductTypeId(rs.getLong("ProductTypeID"));
                productType.setProductTypeName(rs.getString("ProductTypeName"));
                productTypes.add(productType);
            }
            return productTypes;

        } catch (SQLException e) {
            System.err.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }

    }

}
