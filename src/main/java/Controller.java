import services.request.ProductListRequestModel;
import services.response.ProductListResponseModel;
import services.inputCase.ProductListInputCase;
import services.inputCase.RetrieveProductListUseCase;
import services.inputCase.WriteProductListUseCase;
import services.outputCase.ProductListOutputCase;
import services.outputCase.ConditionProductList;

public class Controller {
    ProductListInputCase retrieveProductListUseCase = new RetrieveProductListUseCase();
    ProductListInputCase writeProductListUseCase = new WriteProductListUseCase();

    public ProductListResponseModel getProductList(ProductListRequestModel request) {
        ProductListResponseModel response = new ProductListResponseModel();
        ProductListOutputCase presenter = new ConditionProductList();
        try {
            response = retrieveProductListUseCase.execute(request);
            presenter.parse(response, null);
        } catch (Exception e) {
            e.printStackTrace();
            presenter.parse(response, e.getMessage());
        }

        return response;
    }

    public ProductListResponseModel writeProductList(ProductListRequestModel request) {
        ProductListResponseModel response = new ProductListResponseModel();
        try {
            response = writeProductListUseCase.execute(request);
        } catch (Exception e) {
            e.printStackTrace();
            ProductListOutputCase presenter = new ConditionProductList();
            presenter.parse(response, e.getMessage());
        }

        return response;
    }
}
