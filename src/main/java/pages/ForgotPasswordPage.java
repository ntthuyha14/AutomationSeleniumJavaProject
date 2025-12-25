package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ForgotPasswordPage {
    WebDriver driver;
    public ForgotPasswordPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Forgotten Password']")
    private WebElement forgotPasswordBreadcrumb;
    public boolean didWeNavigateToForgotPasswordPage(){
        return forgotPasswordBreadcrumb.isDisplayed();
    }

    @FindBy (xpath = "//div[@id='content']//h1")
    private WebElement contentForgottentPassword;
    public String getContentForgottenPassword(){
        return contentForgottentPassword.getText();
    }
}

