package services.outputCase;

import services.response.ProductListResponseModel;

public class ConditionProductList implements ProductListOutputCase {

    @Override
    public ProductListResponseModel parse(ProductListResponseModel response, String error) {
        if (error != null) {
            response.setError(error);
            response.setSuccess(false);
        } else {
            response.setSuccess(true);
        }
        return response;
    }
}
