package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.root.RootPage;

import java.util.List;

public class ProductComparisonPage extends RootPage {
    
    public ProductComparisonPage (WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(xpath= "//ul[@class='breadcrumb']//a[text()='Product Comparison']")
    private WebElement breadcrumbCompareProduct;
    public boolean didWeNavigatingToCompareProductPage(){
        return breadcrumbCompareProduct.isDisplayed();
    }

    @FindBy (xpath = "//a//strong")
    private WebElement productName;
    public String getProductNameOnComparePage(){
        return productName.getText();
    }

    @FindBy (xpath = "//td[@class='description']")
    private WebElement productDetail;
    public String getProductDetail(){
        return productDetail.getText();
    }

    @FindBy (xpath = "//td[text()='Price']/following-sibling::td")
    private WebElement PriceProduct;
    public String getPriceProduct(){
        return PriceProduct.getText();
    }

    @FindBy (xpath = "//td[text()='Model']/following-sibling::td")
    private WebElement modelProduct;
    public String getModelProduct(){
        return modelProduct.getText();
    }

    @FindBy (xpath = "//td[text()='Brand']/following-sibling::td")
    private WebElement brandProduct;
    public String getBrandProduct(){
        return brandProduct.getText();
    }

    @FindBy (xpath = "//td[text()='Availability']/following-sibling::td")
    private WebElement statusProduct;
    public String getStatusProduct(){
        return statusProduct.getText();
    }

    @FindBy(xpath = "//td[@class='rating']/span")
    private List<WebElement> quantityRating;
    public int getQuantityRating() {
        return quantityRating.size();
    }

    @FindBy (xpath = "//td[text()='Weight']/following-sibling::td")
    private WebElement weightProduct;
    public String getWeightProduct(){
        return weightProduct.getText();
    }

    @FindBy (xpath = "//td[text()='Dimensions (L x W x H)']/following-sibling::td")
    private WebElement dimensionProduct;
    public String getDemensionProduct(){
        return dimensionProduct.getText();
    }
    
    @FindBy (xpath = "//input[@value='Add to Cart']")
    private WebElement buttonAddToCart;
    public boolean didButtonAddToCartDisplay(){
        try {
            return buttonAddToCart.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @FindBy (linkText = "Remove")
    private WebElement buttonRemove;
    public boolean didButtonRemoveDisplay(){
        return buttonRemove.isDisplayed();
    }

    @FindBy (xpath = "//div[@id='content']//p")
    private WebElement contentNoProductMessage;
    public String getContentNoProductChoose(){
        return contentNoProductMessage.getText();
    }

    @FindBy (linkText = "Continue")
    private WebElement buttonContinue;
    public LandingPage clickOnButtonContinue(){
        buttonContinue.click();
        return new LandingPage(driver);
    }
    
    @FindBy (xpath = "//td[text()='Product']/following-sibling::td")
    private List<WebElement> quantityProductCompare;
    public int getQuantityProductCompare(){
        return quantityProductCompare.size();
    }
}
