package services.outputCase;

import services.response.ProductListResponseModel;

public interface ProductListOutputCase {
    ProductListResponseModel parse(ProductListResponseModel response, String error);

}
