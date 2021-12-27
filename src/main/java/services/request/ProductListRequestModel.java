package services.request;

import constant.ProductType;
import model.ProductEntity;

import java.util.List;

public class ProductListRequestModel {
    private List<ProductEntity> productEntities;
    private int numberOfPage;
    private int limit;
    private ProductType productType;

    public ProductListRequestModel(int numberOfPage, int limit, ProductType productType) {
        this.numberOfPage = numberOfPage;
        this.limit = limit;
        this.productType = productType;
    }

    public ProductListRequestModel() {
    }

    public int getNumberOfPage() {
        return numberOfPage;
    }

    public void setNumberOfPage(int numberOfPage) {
        this.numberOfPage = numberOfPage;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public List<ProductEntity> getProductEntities() {
        return productEntities;
    }

    public void setProductEntities(List<ProductEntity> productEntities) {
        this.productEntities = productEntities;
    }
}
