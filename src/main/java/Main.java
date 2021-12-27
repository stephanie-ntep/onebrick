import constant.ProductType;
import services.request.ProductListRequestModel;
import services.response.ProductListResponseModel;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        Controller controller = new Controller();
        ProductListResponseModel responseModel = controller.getProductList(new ProductListRequestModel(0, 100, ProductType.PHONE));
        if (responseModel.getError() != null) {
            System.out.println(responseModel.getError());
        } else {
            ProductListRequestModel writeRequest = new ProductListRequestModel();
            writeRequest.setProductEntities(responseModel.getProducts());
            ProductListResponseModel writeResponse = controller.writeProductList(writeRequest);
        }
    }
}
