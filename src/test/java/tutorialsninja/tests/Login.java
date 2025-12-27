package tutorialsninja.tests;

import Utils.CommonUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;
import tutorialsninja.base.Base;

import java.time.Duration;
import java.util.Properties;

public class Login extends Base {
    WebDriver driver; 
    Properties prop;
    LandingPage landingPage;
    LoginPage loginPage;
    AccountPage accountPage;
    ForgotPasswordPage forgotPasswordPage;
    AccountLogoutPage accountLogoutPage;
    
    @BeforeMethod
    public void setup(){
        driver = openBrowserAndApplication();
        prop = CommonUtils.loadProperties();
        landingPage = new LandingPage(driver);
        landingPage.clickOnMyAccount();
        loginPage = landingPage.selectLoginOption();
    }
    
    @AfterMethod
    public void tearDown(){
        if (driver != null){
            driver.quit();
        }
    }

    @Test(priority = 1)
    public void verifyLoginWithValidCredentails(){
        loginPage.enterInputEmailField(prop.getProperty("emailLogin"));
        loginPage.enterInputPassWordField(prop.getProperty("passwordLogin"));
        accountPage = loginPage.clickOnButtonLogin();
        Assert.assertTrue(accountPage.didWeNavigateToAccountPage());
        Assert.assertTrue(accountPage.isUserLoggedIn());
    }
    
    @Test (priority = 2)
    public void verifyLoginWithInValidCredentails(){
        loginPage.enterInputEmailField(CommonUtils.generateBrandNewEmail());
        loginPage.enterInputPassWordField(prop.getProperty("passwordLoginInvalid"));
        accountPage = loginPage.clickOnButtonLogin();
        Assert.assertEquals(loginPage.getWarningMessage(), prop.getProperty("warningMessage"));
    }
    
    @Test (priority = 3)
    public void verifyLoginWithInvalidEmailAndValidPassword(){
        loginPage.enterInputEmailField(CommonUtils.generateBrandNewEmail());
        loginPage.enterInputPassWordField(prop.getProperty("passwordLogin"));
        accountPage = loginPage.clickOnButtonLogin();
        Assert.assertEquals(loginPage.getWarningMessage(), prop.getProperty("warningMessage"));
    }
    
    @Test (priority = 4)
    public void verifyLoginWithValidEmailAndInvalidPassword(){
        loginPage.enterInputEmailField(CommonUtils.validEmailRandomGenerator());
        loginPage.enterInputPassWordField(prop.getProperty("passwordLoginInvalid"));
        accountPage = loginPage.clickOnButtonLogin();
        Assert.assertEquals(loginPage.getWarningMessage(), prop.getProperty("warningMessage"));
    }
    
    @Test (priority = 5)
    public void verifyLoginWithoutAnyCredentails(){
        accountPage = loginPage.clickOnButtonLogin();
        Assert.assertEquals(loginPage.getWarningMessage(), prop.getProperty("warningMessage"));
    }
    
    @Test (priority = 6)
    public void verifyForgottenPasswordLinkOnLoginPage(){
        accountPage = loginPage.clickOnButtonLogin();
        loginPage.isForgottenPasswordAvailable();
        forgotPasswordPage = loginPage.clickOnForgottenPasswordOption();
        Assert.assertTrue(forgotPasswordPage.didWeNavigateToForgotPasswordPage());
        Assert.assertEquals(forgotPasswordPage.getContentForgottenPassword(),"Forgot Your Password?");
    }
    
    @Test (priority = 7)
    public void verifyLoginAccountUsingKeyboardKeys(){
        driver = presskeyMultipleTimes(driver,Keys.TAB, 23);
        driver = enterDetailsIntoLoginAccountPageFields();
        accountPage = new AccountPage(driver);
        Assert.assertTrue(accountPage.didWeNavigateToAccountPage());
        Assert.assertTrue(accountPage.isUserLoggedIn());
    }
    
    @Test (priority = 8)
    public void verifyLoginFieldsPlaceHolders(){
        String expectedPlaceHolderEmail = "E-Mail Address";
        Assert.assertEquals(loginPage.getPlaceHolderEmailField(), expectedPlaceHolderEmail);
        String expectedPlaceHolderPassword = "Password";
        Assert.assertEquals(loginPage.getPlaceHolderPasswordField(), expectedPlaceHolderPassword);
    }
    
    @Test (priority = 9)
    public void verifyBrowserBackAfterLogin(){
        loginPage.enterInputEmailField(prop.getProperty("emailLogin"));
        loginPage.enterInputPassWordField(prop.getProperty("passwordLogin"));
        loginPage.clickOnButtonLogin();
        driver = navigateBack(driver);
        loginPage = new LoginPage(driver);
        accountPage = loginPage.clickOnMyAccountRightColumnOption();
        Assert.assertTrue(accountPage.isUserLoggedIn());
    }
    
    @Test (priority = 10)
    public void verifyLogoutAfterBackLoginFunction(){
        loginPage.enterInputEmailField(prop.getProperty("emailLogin"));
        loginPage.enterInputPassWordField(prop.getProperty("passwordLogin"));
        accountPage =loginPage.clickOnButtonLogin();
        accountPage.clickOnLogoutRightColumnOption();
        driver = navigateBack(driver);
        accountPage = new AccountPage(driver);
        accountPage.clickEditAccountInformation();
        loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.didWeNavigateToLoginPage());
    }

    @Test (priority = 11)
    public void verifyLoginWithInactiveCredentials(){
        loginPage.enterInputEmailField(prop.getProperty("inActiveEmail"));
        loginPage.enterInputPassWordField(prop.getProperty("inActivePassword"));
        accountPage = loginPage.clickOnButtonLogin();
        Assert.assertEquals(loginPage.getWarningMessage(), prop.getProperty("warningMessage"));

    }

    @Test (priority = 12)
    public void verifyNumberOfUnsuccessfulLoginAttempts() throws InterruptedException {
        loginPage.enterInputEmailField(CommonUtils.generateBrandNewEmail());
        loginPage.enterInputPassWordField(prop.getProperty("passwordLogin"));
        loginPage.clickOnButtonLogin();
        Assert.assertEquals(loginPage.getWarningMessage(), prop.getProperty("warningMessage"));
        loginPage.clickOnButtonLogin();
        Assert.assertEquals(loginPage.getWarningMessage(), prop.getProperty("warningMessage"));
        loginPage.clickOnButtonLogin();
        Assert.assertEquals(loginPage.getWarningMessage(), prop.getProperty("warningMessage"));
        loginPage.clickOnButtonLogin();
        Assert.assertEquals(loginPage.getWarningMessage(), prop.getProperty("warningMessage"));
        loginPage.clickOnButtonLogin();
        Assert.assertEquals(loginPage.getWarningMessage(), prop.getProperty("warningMessage"));
        loginPage.clickOnButtonLogin();
        String expectedWarningLoginAttempts = "Warning: Your account has exceeded allowed number of login attempts." +
                " Please try again in 1 hour.";
        Assert.assertEquals(loginPage.getWarningMessage(), expectedWarningLoginAttempts);
    }

    @Test (priority = 13)
    public void verifyTextEnteredIntoPasswordFieldIsToggledToHideItsVisibility(){
        Assert.assertEquals(loginPage.getTypePasswordField(), "password");
    }
}
