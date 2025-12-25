package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver driver;
    public  LoginPage(WebDriver driver){
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
    



}
