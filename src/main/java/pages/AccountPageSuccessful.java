package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.root.RootPage;

public class AccountPageSuccessful extends RootPage {

    public AccountPageSuccessful(WebDriver driver){
        super(driver);
        this.driver = driver ;
        PageFactory.initElements(driver, this);
    }

    @FindBy (linkText = "Logout")
    private WebElement logoutOption;
    public boolean logoutOptionIsDisplay(){
        return logoutOption.isDisplayed();
    }

    @FindBy (xpath = "//div[@id='common-success']//h1" )
    private WebElement pageHeading;
    public String getPageHeading(){
        return pageHeading.getText();
    }

    @FindBy (id = "content")
    private WebElement pageContent;
    public String getContent(){
        return pageContent.getText();
    }

    @FindBy (xpath = "//a[text()='Continue']")
    private WebElement getContinueButton;
    public AccountPage clickOnContinueButton(){
        getContinueButton.click();
        return new AccountPage(driver);
    }

    @FindBy (xpath = "//a[@class='list-group-item'][text()='Logout']")
    private WebElement rightSideLogoutOption;
    public boolean rightSideLogoutOptionDisplay(){
        return rightSideLogoutOption.isDisplayed();
    }

    @FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Success']")
    private WebElement accountSuccessPageBreadcrumb;
    public boolean didWeNavigateToAccountSuccessPage(){
        return accountSuccessPageBreadcrumb.isDisplayed();
    }
    




}
