package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.http.HttpClient;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HeaderOptions {
    WebDriver driver;

    public HeaderOptions(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a/i[@class='fa fa-phone']")
    private WebElement phoneIconOption;
    public ContactUsPage clickOnphoneIconOption(){
        phoneIconOption.click();
        return new ContactUsPage(driver);
    }

    @FindBy (xpath = "//span[text()='My Account']")
    private WebElement myAccountDropMenu;
    public void clickOnMyAccount(){
        myAccountDropMenu.click();
    }

    @FindBy (linkText = "Register")
    private WebElement registeroption;
    public RegisterPage selectRegisterOption() {
        registeroption.click();
        return new RegisterPage(driver);
    }

    @FindBy (linkText = "Login")
    private WebElement loginoption;
    public LoginPage selectLoginOption() {
        loginoption.click();
        return new LoginPage(driver);
    }

    @FindBy (xpath = "//a[@id='wishlist-total']")
    private WebElement heartIconOption;
    public void  clickOnHeartIconOption(){
        heartIconOption.click();
    }

    @FindBy (xpath = "//a/i[@class='fa fa-shopping-cart']")
    private WebElement shoppingcartIcon;
    public ShoppingCartPage clickOnShoppingCartIconOption(){
        shoppingcartIcon.click();
        return new ShoppingCartPage(driver);
    }

    @FindBy (xpath = "//a/i[@class='fa fa-share']")
    private WebElement shareIcon;
    public ShoppingCartPage clickOnShareIconOption(){
        shareIcon.click();
        return new ShoppingCartPage(driver);
    }

    @FindBy (linkText = "Qafox.com")
    private WebElement btnQafox;
    public LandingPage clickOnButtonQafox(){
        btnQafox.click();
        return new LandingPage(driver);
    }

    @FindBy (xpath = "//button[@class='btn btn-default btn-lg']")
    private WebElement searchIconOption;
    public SearchPage clickOnSearchIconOption(){
        searchIconOption.click();
        return new SearchPage(driver);
    }
}
