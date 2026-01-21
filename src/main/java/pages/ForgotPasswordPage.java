package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.root.RootPage;

public class ForgotPasswordPage extends RootPage {
    public ForgotPasswordPage(WebDriver driver){
        super(driver);
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

