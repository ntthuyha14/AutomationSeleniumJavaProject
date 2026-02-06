package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.root.RootPage;

public class AccountLogoutPage extends RootPage {
    public  AccountLogoutPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    @FindBy (xpath = "//a[@class='btn btn-primary'][text()='Continue']")
    private WebElement buttonContinue;
    public LandingPage clickOnButtonContinue(){

        buttonContinue.click();
        return new LandingPage(driver);
    }


    @FindBy (xpath = "//ul[@class='breadcrumb']//a[text()='Logout']")
    private WebElement logoutBreadcrumb;
    public boolean didWeNavigateToAccountLogoutPage(){
        return logoutBreadcrumb.isDisplayed();
    }

    @FindBy (xpath = "//div[@id='content']//h1")
    private WebElement getHeadingPage;
    public String getHeadingLogoutPage(){
        return getHeadingPage.getText();
    }
}
