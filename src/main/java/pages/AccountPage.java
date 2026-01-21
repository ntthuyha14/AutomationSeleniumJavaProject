package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.root.RootPage;

public class AccountPage extends RootPage {


    public AccountPage (WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(linkText = "Edit your account information")
    private WebElement editYourAccountInformation;
    public boolean editYourAccountInformationDisplay(){
        
        return editYourAccountInformation.isDisplayed();
    }

    public void clickEditAccountInformation(){
        
        editYourAccountInformation.click();
    }

    @FindBy (linkText = "Subscribe / unsubscribe to newsletter")
    private WebElement subcribeUnsubcribeNewsletterOption;
    public NewsLetterPage selectSubcribeUnsubcribeNewsletterOption(){
        subcribeUnsubcribeNewsletterOption.click();
        return new NewsLetterPage(driver);
    }

    public boolean didWeNavigateToNewletterPage(){
        return subcribeUnsubcribeNewsletterOption.isDisplayed();
    }

    @FindBy (id = "input-firstname" )
    private WebElement firstnameField;
    public String getValueFirstName(){
        return firstnameField.getAttribute("value");
    }

    @FindBy (id = "input-lastname" )
    private WebElement lastnameField;
    public String getValueLastName(){
        return lastnameField.getAttribute("value");
    }

    @FindBy (id = "input-email" )
    private WebElement emailField;
    public String getValueEmail(){
        return emailField.getAttribute("value");
    }

    @FindBy (id = "input-telephone")
    private WebElement telephoneField;
    public String getValueTelephone(){
        return telephoneField.getAttribute("value");
    }

    @FindBy (xpath = "//ul[@class='breadcrumb']//a[text()='Account']")
    private WebElement accountBreadcrumb;
    public boolean didWeNavigateToAccountPage(){
        return accountBreadcrumb.isDisplayed();
    }
    
    @FindBy (xpath = "//div[@class='list-group']//a[text()='Logout']")
    private WebElement logoutRightColumnOption;
    public boolean isUserLoggedIn(){
        return logoutRightColumnOption.isDisplayed();
    }
    
    public LogoutPage clickOnLogoutRightColumnOption(){
        logoutRightColumnOption.click();
        return new LogoutPage(driver);
    }

    @FindBy (xpath = "//a[text()='Change your password']")
    private WebElement changePassWord;
    public ChangePasswordPage clickChangPassword(){
        changePassWord.click();
        return new ChangePasswordPage(driver);
    }

    @FindBy (xpath = "//div[@class='alert alert-success alert-dismissible']")
    private WebElement alertUpdatedPassword;
    public String getAlertUpdatedPasswordSuccessful(){
        return alertUpdatedPassword.getText();
    }

    

}
