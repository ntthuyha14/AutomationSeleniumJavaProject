package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage{
    WebDriver driver;

    public LandingPage(WebDriver driver){
        this.driver = driver ;
        PageFactory.initElements(driver, this);
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
}