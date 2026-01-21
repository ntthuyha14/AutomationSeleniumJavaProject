package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.root.RootPage;

public class LogoutPage extends RootPage {
    public LogoutPage (WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy (xpath = "//span[text()='My Account']")
    private WebElement myAccountDropMenu;
    public void clickOnMyAccount(){
        myAccountDropMenu.click();
    }

    @FindBy (linkText = "Login")
    private WebElement loginoption;
    public LoginPage selectLoginOption() {
        loginoption.click();
        return new LoginPage(driver);
    }
}
