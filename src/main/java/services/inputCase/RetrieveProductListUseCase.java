package services.inputCase;

import constant.ProductType;
import model.ProductEntity;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import services.request.ProductListRequestModel;
import services.response.ProductListResponseModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RetrieveProductListUseCase implements ProductListInputCase {

    @Override
    public ProductListResponseModel execute(ProductListRequestModel request) throws Exception {
        ProductListResponseModel result = new ProductListResponseModel();
        if (request.getProductType().equals(ProductType.PHONE)) {
            List<WebElement> webElements = new ArrayList<>();
            List<ProductEntity> productEntities = new ArrayList<>();
            ChromeDriver driver = new ChromeDriver();
            if (request.getNumberOfPage() > 0) {
                webElements = retrieveElements(driver, request.getNumberOfPage());
                productEntities = parseProductEntities(driver, webElements);
            } else if (request.getLimit() > 0) {
                int page = 1;
                while (webElements.size() < request.getLimit()) {
                    List<WebElement> temp = retrieveElements(driver, page);
                    synchronized (temp) {
                        webElements.addAll(temp);
                        List<ProductEntity> tempEntities = parseProductEntities(driver, temp);
                        productEntities.addAll(tempEntities);
                        page++;
                    }
                }
                productEntities = productEntities.subList(0, request.getLimit());
            }
            result.setProducts(productEntities);
            driver.close();
            driver.quit();
        } else {
            throw new UnsupportedOperationException("The web scraping method for the selected product is not yet implemented");
        }
        return result;
    }

    private List<WebElement> retrieveElements(ChromeDriver driver, int page) throws Exception {
        List<WebElement> elements = new ArrayList<>();
        try {
            String url = "https://www.tokopedia.com/p/handphone-tablet/handphone";
            ChromeOptions options = new ChromeOptions();
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            driver.manage().window().maximize();
            driver.get("https://www.tokopedia.com/p/handphone-tablet/handphone?page=" + page);
            executor.executeScript("return document.getElementById('zeus-header').remove()");
            WebElement categoryListContainer = driver.findElementByXPath("//*[@data-testid='lstCL2ProductList']");
            List<WebElement> temp = categoryListContainer.findElements(By.xpath(".//*[@data-testid='lnkProductContainer']"));
            int current = temp.size() - 1;
            Actions actions = new Actions(driver);
            do {
//                actions.moveToElement(temp.get(temp.size() - 1));
                executor.executeScript("arguments[0].scrollIntoView(true);", temp.get(current));
                elements = temp;
                temp = categoryListContainer.findElements(By.xpath(".//*[@data-testid='lnkProductContainer']"));
                current += 10;
            } while (current < temp.size());
        } catch (Exception e) {
            driver.close();
            e.printStackTrace();
        }

        return elements;
    }

    private List<ProductEntity> parseProductEntities(ChromeDriver driver, List<WebElement> elements) throws IOException {
        List<ProductEntity> productEntities = new ArrayList<>();
        for (WebElement element : elements) {
            ProductEntity entity = new ProductEntity();
            WebElement imageUrl = element.findElement(By.xpath(".//img"));
            WebElement productName = element.findElement(By.xpath(".//span[@class='css-1bjwylw']"));
            WebElement storeName = element.findElements(By.xpath(".//span[@class='css-1kr22w3']")).get(1);
            WebElement price = element.findElement(By.xpath(".//span[@class='css-o5uqvq']"));
            entity.setimageLink(imageUrl.getAttribute("src"));
            entity.setProductName(productName.getText());
            entity.setStoreName(storeName.getAttribute("innerHTML"));
            entity.setPrice(Integer.parseInt(price.getText().replaceAll("[A-Za-z. ]", "")));
//            getDescriptionAndRating(driver, element, entity);
            productEntities.add(entity);
        }
        return productEntities;
    }

    // this method is not yet used, since there is a blocker found which cannot navigate to the product detail page
    private void getDescriptionAndRating(ChromeDriver driver, WebElement element, ProductEntity entity) throws IOException {
        element.click();
        System.out.println(driver.manage().getCookies());
        System.out.println(driver.getPageSource());
        String description = driver.findElementByXPath("//*[@data-testid='lblPDPDescriptionProduk']").getAttribute("innerHTML");
        String rating = driver.findElementByXPath("//*[@data-testid='lblPDPDetailProductRatingNumber']").getAttribute("innerHTML");
        System.out.println(description);
        System.out.println(rating);
        //entity.setDescription(description);
        //entity.setRating(Float.parseFloat(rating));
    }
}
