package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.root.RootPage;

public class ChangePasswordPage extends RootPage {
    public ChangePasswordPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy (id = "input-password")
    private WebElement inputNewPasswordField;
    public void enterNewPasswordField(String newPassword){
        inputNewPasswordField.sendKeys(newPassword);
    }

    @FindBy (id = "input-confirm")
    private WebElement inputConfirmNewPasswordField;
    public void enterConfirmNewPasswordField(String confirmNewPassword){
        inputConfirmNewPasswordField.sendKeys(confirmNewPassword);
    }

    @FindBy(xpath = "//input[@class='btn btn-primary']")
    private WebElement buttonContinue;
    public AccountPage clickOnButtonContinue(){
        buttonContinue.click();
        return new AccountPage(driver);
    }
}
