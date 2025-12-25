package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountLogoutPage {
    WebDriver driver;
    public  AccountLogoutPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    @FindBy (xpath = "//a[@class='btn btn-primary'][text()='Continue']")
    private WebElement buttonContinue;
    public void clickOnButtonContinue(){
        buttonContinue.click();
    }
}
