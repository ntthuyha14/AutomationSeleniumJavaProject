package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.root.RootPage;

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

    


}