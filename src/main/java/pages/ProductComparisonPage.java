package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.root.RootPage;

public class ProductComparisonPage  extends RootPage {
    
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

}
