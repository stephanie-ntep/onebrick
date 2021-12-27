package services.inputCase;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import model.ProductEntity;
import services.request.ProductListRequestModel;
import services.response.ProductListResponseModel;

import java.io.FileWriter;
import java.io.Writer;
import java.nio.file.Path;

public class WriteProductListUseCase implements ProductListInputCase {

    @Override
    public ProductListResponseModel execute(ProductListRequestModel request) throws Exception {
        ProductListResponseModel responseModel = new ProductListResponseModel();
        if (request.getProductEntities() != null && request.getProductEntities().size() > 0) {
            Path path = Path.of("TokopediaScrapperOutput.csv");
            try(Writer writer = new FileWriter(path.toString())) {
                StatefulBeanToCsv<ProductEntity> beanToCsv = new StatefulBeanToCsvBuilder<ProductEntity>(writer)
                        .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                        .build();
                beanToCsv.write(request.getProductEntities());
            }
        } else {
            throw new UnsupportedOperationException("Product entities with size 0. Please check your code!");
        }

        return responseModel;
    }
}
