package farmfresh.business;

/**
 * Created by Mom and Dad on 12/14/2016.
 */
public class ProductType {
    //PRIVATE INSTANCE VARIABLES
    private long productTypeId;
    private String productTypeName;

    //PUBLIC METHODS:  GETTERS AND SETTERS
    public long getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(long productTypeId) { this.productTypeId = productTypeId; }

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) { this.productTypeName = productTypeName;}

}
