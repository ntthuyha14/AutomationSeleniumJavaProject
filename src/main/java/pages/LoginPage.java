package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.root.RootPage;

import java.time.Duration;

public class LoginPage extends RootPage {
    public  LoginPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy (linkText = "Continue")
    private WebElement btnContinue;
    public RegisterPage clickButtonContinue(){
        btnContinue.click();
        return new RegisterPage(driver);
    }

    @FindBy(xpath="//a[@class='list-group-item'][text()='Register']")
    private WebElement rightSideRegisterOption;
    public RegisterPage clickOnRightSideRegisterOption(){
        rightSideRegisterOption.click();
        return new RegisterPage(driver);
    }

    @FindBy (xpath = "//ul[@class='breadcrumb']//a[text()='Login']")
    private WebElement loginBreadcrumb;
    public boolean didWeNavigateToLoginPage(){
        return loginBreadcrumb.isDisplayed();
    }

    @FindBy (id = "input-email")
    private WebElement inputEmailField;
    public void enterInputEmailField(String emailFieldText){
        inputEmailField.sendKeys(emailFieldText);
    }

    @FindBy (id = "input-password")
    private WebElement inputPasswordField;
    public void enterInputPassWordField(String passwordFieldText){
        inputPasswordField.sendKeys(passwordFieldText);
    }
    
    @FindBy (xpath = "//input[@class='btn btn-primary']")
    private WebElement buttonLogin;
    public AccountPage clickOnButtonLogin(){
        buttonLogin.click();
        return new AccountPage(driver);
    }
    
    @FindBy (xpath = "//div[@class='alert alert-danger alert-dismissible']")
    private WebElement warningMessage;
    public String getWarningMessage(){
        return warningMessage.getText();
    }

    @FindBy (xpath = "//div[@class='form-group']//a[text()='Forgotten Password']")
    private WebElement forgottenPasswordOption;
    public ForgotPasswordPage clickOnForgottenPasswordOption(){
        forgottenPasswordOption.click();
        return new ForgotPasswordPage(driver);
    }
    
    public boolean isForgottenPasswordAvailable(){
        return forgottenPasswordOption.isDisplayed();
    }
    
    public String getPlaceHolderEmailField(){
        return inputEmailField.getAttribute("placeholder");
    }
    
    public String getPlaceHolderPasswordField(){
        return inputPasswordField.getAttribute("placeholder");
    }
    
    @FindBy (xpath = "//a[@class='list-group-item'][text()='My Account']")
    private WebElement myAccountRightColumnOption;
    public AccountPage clickOnMyAccountRightColumnOption(){
        myAccountRightColumnOption.click();
        return new AccountPage(driver);
    }

    public String getTypePasswordField(){
        return inputPasswordField.getDomAttribute("type");
    }

    public WebDriver selectPasswordFieldTextAndCopy(WebDriver driver){
        Actions actions = new Actions(driver);
        actions.doubleClick(inputPasswordField).keyDown(Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL);
        return driver;
    }

    public WebDriver pastCopiedTextIntoEmailField(WebDriver driver){
        Actions actions = new Actions(driver);
        actions.click(inputEmailField).keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL);
        return driver;
    }

    public String getTextCopiedIntoEnteredPasswordField(){
        return inputEmailField.getDomAttribute("value");
    }

    public void clearPasswordValue(){
        inputPasswordField.clear();
    }

    @FindBy (xpath = "//a[text()='Continue']")
    private WebElement buttonContinue;
    public RegisterPage clickOnButtonContinue(){
        buttonContinue.click();
        return new RegisterPage(driver);
    }

    @FindBy (xpath = "//a/i[@class='fa fa-phone']")
    private WebElement phoneIconOption;
    public ContactUsPage clickOnphoneIconOption(){
        phoneIconOption.click();
        return new ContactUsPage(driver);
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

    public void clickOnLoginBreadcrumbOption(){
        loginBreadcrumb.click();
    }


    @FindBy (xpath = "//ul[@class='breadcrumb']//a[text()='Account']")
    private WebElement accountBreadcrumb;
    public void clickOnAccountBreadcrumb(){
        accountBreadcrumb.click();
    }

    @FindBy(xpath = "//ul[@class='breadcrumb']//a[i[contains(@class,'fa-home')]]")
    private WebElement btnHome;
    public LandingPage clickOnButtonHome() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOf(btnHome));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", btnHome);

        wait.until(ExpectedConditions.elementToBeClickable(btnHome)).click();

        return new LandingPage(driver);
    }
    @FindBy (xpath = "//div[@class='list-group']//a[text()='Login']")
    private WebElement rightSideLoginOption;
    public void clickOnRightSideLoginOption(){
        rightSideLoginOption.click();
    }

    @FindBy(xpath = "//div[@class='list-group']//a[text()='Forgotten Password']")
    private WebElement rightSideForgottenPasswordOption;
    public ForgotPasswordPage clickOnRightSideForgottenPasswordOption(){
        rightSideForgottenPasswordOption.click();
        return new ForgotPasswordPage(driver);
    }

    @FindBy (xpath = "//div[@class='list-group']//a[text()='My Account']")
    private WebElement rightSideMyAccountOption;
    public void clickOnRightSideMyAccountOption(){
        rightSideMyAccountOption.click();
    }

    @FindBy (xpath = "//div[@class='list-group']//a[text()='Address Book']")
    private WebElement rightSideAddressBookOption;
    public void clickOnSideAddressBookOption(){
        rightSideAddressBookOption.click();
    }

    @FindBy (xpath = "//div[@class='list-group']//a[text()='Wish List']")
    private WebElement rightSideWishListOption;
    public void clickOnRightSideWishListOption(){
        rightSideWishListOption.click();
    }

    @FindBy (xpath = "//div[@class='list-group']//a[text()='Order History']")
    private WebElement rightSideOrderHistoryOption;
    public void clickOnRightSideOrderHistoryOption() {
        rightSideOrderHistoryOption.click();
    }

    @FindBy (xpath = "//div[@class='list-group']//a[text()='Downloads']")
    private WebElement rightSideDownloadOption;
    public void clickOnRightSideDownloadOption(){
        rightSideDownloadOption.click();
    }

    @FindBy (xpath = "//div[@class='list-group']//a[text()='Recurring payments']")
    private WebElement rightSideRecurringPaymentOption;
    public void clickOnRightSideRecurringPaymentOption(){
        rightSideRecurringPaymentOption.click();
    }

    @FindBy (xpath = "//ul[@class='list-unstyled']//a[text()='About Us']")
    private WebElement rightSideAboutUsOption;
    public AboutUsPage clickOnRightSideAboutUsOption(){
        rightSideAboutUsOption.click();
        return new AboutUsPage(driver);
    }

    @FindBy(xpath = "//div[@id='content']//h2[text()='New Customer']")
    private WebElement loginHeadingOne;
    public String getLoginHeadingOne(){
        return loginHeadingOne.getText();
    }

    @FindBy(xpath = "//div[@id='content']//h2[text()='Returning Customer']")
    private WebElement loginHeadingTwo;
    public String getLoginHeadingTwo(){
        return loginHeadingTwo.getText();
    }








}
