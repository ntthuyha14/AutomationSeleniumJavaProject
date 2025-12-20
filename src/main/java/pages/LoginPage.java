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



}
