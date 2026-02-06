package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.root.RootPage;

public class HeaderOptions extends RootPage {

    public HeaderOptions(WebDriver driver){
        super(driver);
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
    public void clickOnMyAccountDropMenu(){
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

    public boolean isLoginOptionAvailable(){
        return loginOption.isDisplayed();
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

    @FindBy (xpath = "//ul[@class='dropdown-menu dropdown-menu-right']//a[text()='Logout']")
    private WebElement buttonLogoutOnDropDown;
    public boolean isDisplayButtonLogoutOnDropDown(){
        try {
            return buttonLogoutOnDropDown.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public AccountLogoutPage selectLogoutOption(){
        buttonLogoutOnDropDown.click();
        return new AccountLogoutPage(driver);
    }
}
