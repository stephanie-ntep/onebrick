package services.inputCase;

import services.request.ProductListRequestModel;
import services.response.ProductListResponseModel;

public interface ProductListInputCase {
    ProductListResponseModel execute(ProductListRequestModel request) throws Exception;
}
