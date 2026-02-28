package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.root.RootPage;

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

    

}
