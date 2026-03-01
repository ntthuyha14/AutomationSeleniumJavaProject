package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.root.RootPage;

import java.time.Duration;

public class ProductDisplayPage extends RootPage {
    public ProductDisplayPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    @FindBy (xpath = "//button[@data-original-title = 'Compare this Product']")
    private WebElement buttonCompareProduct;
    public void clickOnButtonCompareProduct(){
        buttonCompareProduct.click();
    }
    
    @FindBy (xpath = "//div[@class='alert alert-success alert-dismissible']")
    private WebElement warningMessageCompareProduct;
    public String getWarningMessageCompareProduct(){
        String fullText = warningMessageCompareProduct.getText();
        return fullText.substring(0, fullText.indexOf("!") + 1);
    }

    @FindBy (linkText = "product comparison")
    private WebElement buttonProductComparison;
    public ProductComparisonPage clickOnProductComparison(){
        buttonProductComparison.click();
        return new ProductComparisonPage(driver);
    }

    public ProductComparisonPage clickOnProductRelatedComparison() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement element = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.linkText("product comparison"))
        );

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", element);

        return new ProductComparisonPage(driver);
    }

    @FindBy (xpath = "(//button[@data-original-title = 'Compare this Product'])[2]")
    private WebElement buttonCompareRelatedProduct;
    public void clickOnButtonCompareRelatedProduct(){
        buttonCompareRelatedProduct.click();
    }
    
    @FindBy (linkText = "iMac")
    private WebElement productName;
    public ProductPage clickOnProductNameFromProductSearchDetailPage(){
            productName.click();
            return new ProductPage(driver);
    }


}
