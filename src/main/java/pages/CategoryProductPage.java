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

public class CategoryProductPage extends RootPage {
    
    public  CategoryProductPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    
    @FindBy (xpath = "//button[@id='list-view']")
    private WebElement buttonListView;
    public void clickOnButtonListView(){
        buttonListView.click();
    }

    @FindBy (xpath = "//button[@id='grid-view']")
    private WebElement buttonGridView;
    public void clickOnButtonGridView(){
        buttonGridView.click();
    }

    @FindBy(xpath = "(//button[@data-original-title='Compare this Product'])[1]")
    private WebElement firstCompareButton;
    public void clickOnButtonCompareProduct(){
        firstCompareButton.click();
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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement element = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.linkText("product comparison"))
        );

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", element);

        return new ProductComparisonPage(driver);
    }

    @FindBy (id = "compare-total")
    private WebElement productCompareLink;
    public ProductComparisonPage selectProductCompareLink(){
        productCompareLink.click();
        return new ProductComparisonPage(driver);
    }
    
    
}
