package farmfresh.business;

/**
 * Purpose: Product Type represents the Types/Categories Products are grouped
 * under for sale (e.g.  Fruit, Vegetables, Floral, etc)
 *
 * @author Amy Radtke
 * @version 1.0  07/01/2017
 */
public class ProductType {

    /**
     * Identifies the Product Type the Product belongs to
     * (e.g. Fuji Apples belong to the Fruit Product Type)
     */
    private long productTypeId;

    /**
     * Name of the Product Type (e.g. Fruits, Veggies)
     * Product Type Name is stored on the Product Object,
     * but is pulled in from the producttype table
     */
    private String productTypeName;

    /**
     * @param productTypeId  The Unique Identifier of the Product's Product Type
     */
    public void setProductTypeId(long productTypeId) {
        this.productTypeId = productTypeId;
    }

    /**
     * @return  The Unique Identifier of the Product's Product Type
     */
    public long getProductTypeId() {
        return productTypeId;
    }

    /**
     * @param productTypeName  The Name of the Product's Product Type (e.g. Fruits, Veggies).
     */
    public void setProductTypeName(String productTypeName) { this.productTypeName = productTypeName;}
    /**
     *
     * @return  The Name of the Product's Product Type (e.g. Fruits, Veggies).
     */
    public String getProductTypeName() {
        return productTypeName;
    }

}//End - ProductType.java
