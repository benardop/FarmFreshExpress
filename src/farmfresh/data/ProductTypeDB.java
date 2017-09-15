package farmfresh.data;

import farmfresh.business.ProductType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Purpose: To  provide all CRUD - Create, Read(Select), Update and Delete
 * functionality involving the 'producttype' Table.
 *
 * @author Amy Radtke
 * @version 1.0  07/01/2017
 */
public class ProductTypeDB {

    /**
     * @return Return the List of All Product Types
     */
    public static List<ProductType> selectProductTypes() {

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM producttype ORDER BY SortOrder";

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
            throw new RuntimeException(e);
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }

    }//End - selectProductTypes()

}//End - ProductTypeDB.java