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

public class LandingPage extends RootPage {

    public LandingPage(WebDriver driver){
        super(driver);
        this.driver = driver ;
        PageFactory.initElements(driver, this);
    }

    @FindBy (xpath = "//span[text()='My Account']")
    private WebElement myAccountDropMenu;
    public void clickOnMyAccount(){
        myAccountDropMenu.click();
    }

    @FindBy (linkText = "Register")
    private WebElement registerOption;
    public RegisterPage selectRegisterOption() {
        registerOption.click();
        return new RegisterPage(driver);
    }

    @FindBy (linkText = "Login")
    private WebElement loginOption;
    public LoginPage selectLoginOption() {
        loginOption.click();
        return new LoginPage(driver);
    }
    
    @FindBy (xpath = "//input[@class='form-control input-lg']")
    private WebElement inputSearch;
    public void  enterProductNameInSearch(String productName){
         inputSearch.sendKeys(productName);
    }
    
   
    public String getPlaceHolderInputSearchField(){
        return inputSearch.getDomProperty("placeholder");
    }

    @FindBy (xpath = "//button[@class='btn btn-default btn-lg']")
    private WebElement searchIconOption;
    public SearchPage clickOnSearchIconOption(){
        searchIconOption.click();
        return new SearchPage(driver);
    }
    
    public void clearInputSearch(){
        inputSearch.clear();
    }
    
    @FindBy (linkText = "Site Map")
    private WebElement siteMap;
    public SiteMapPage clickOnSiteMap(){
        siteMap.click();
        return new SiteMapPage(driver);
    }

    @FindBy (linkText = "Desktops")
    private WebElement DesktopsOption;
    public void clickOnDesktopsOption(){
        DesktopsOption.click();
    }

    @FindBy (linkText = "Show AllDesktops")
    private WebElement showAllDesktopsOption;
    public CategoryProductPage clickOnShowAllDesktopsOption(){
        showAllDesktopsOption.click();
        return new CategoryProductPage(driver);
    }

    @FindBy (xpath = "(//button[@data-original-title = 'Compare this Product'])[1]")
    private WebElement buttonCompareProduct;
    public void clickOnButtonCompareProductLandingPage(){
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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement element = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.linkText("product comparison"))
        );

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", element);

        return new ProductComparisonPage(driver);
    }
    


}