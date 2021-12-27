package services.response;

import model.ProductEntity;

import java.util.List;

public class ProductListResponseModel {
    private List<ProductEntity> products;
    private String error;
    private boolean success;

    public ProductListResponseModel() {}

    public List<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
