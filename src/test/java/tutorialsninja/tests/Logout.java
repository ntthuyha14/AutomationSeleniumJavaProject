package tutorialsninja.tests;

import Utils.CommonUtils;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;
import tutorialsninja.base.Base;

import java.util.Properties;

public class Logout extends Base{

    public WebDriver driver;
    LandingPage landingPage;
    LoginPage loginPage;
    Properties prop;
    AccountPage accountPage;
    HeaderOptions headerOptions;
    AccountLogoutPage accountLogoutPage;
    RightColumnOptions rightColumnOptions;

    @BeforeMethod
    public void setup(){
        driver = openBrowserAndApplication();
        prop = CommonUtils.loadProperties();
        landingPage = new LandingPage(driver);


    }

    @AfterMethod
    public void tearDown(){
       closeBrowser(driver);
    }

    @Test (priority = 1)
    public void verifyLoggingOutUsingMyAccountDropMenu(){
        landingPage.clickOnMyAccount();
        loginPage = landingPage.selectLoginOption();
        loginPage.enterInputEmailField(prop.getProperty("emailLogin"));
        loginPage.enterInputPassWordField(prop.getProperty("passwordLogin"));
        accountPage = loginPage.clickOnButtonLogin();
        driver =  accountPage.getDriver();
        headerOptions = new HeaderOptions(driver);
        headerOptions.clickOnMyAccountDropMenu();
        headerOptions.isDisplayButtonLogoutOnDropDown();
        accountLogoutPage = headerOptions.selectLogoutOption();
        accountLogoutPage.didWeNavigateToAccountLogoutPage();
        driver = accountLogoutPage.getDriver();
        headerOptions = new HeaderOptions(driver);
        headerOptions.clickOnMyAccountDropMenu();
        Assert.assertTrue(headerOptions.isLoginOptionAvailable());
        driver = headerOptions.getDriver();
        accountLogoutPage = new AccountLogoutPage(driver);
        landingPage = accountLogoutPage.clickOnButtonContinue();
        Assert.assertEquals(getURLPage(landingPage.getDriver()),prop.getProperty("landingPageURL"));

    }
    
    @Test (priority = 2)
    public void verifyLoggingOutUsingRightColumn(){
        landingPage.clickOnMyAccount();
        loginPage = landingPage.selectLoginOption();
        loginPage.enterInputEmailField(prop.getProperty("emailLogin"));
        loginPage.enterInputPassWordField(prop.getProperty("passwordLogin"));
        accountPage = loginPage.clickOnButtonLogin();
        driver =  accountPage.getDriver();
        rightColumnOptions = new RightColumnOptions(driver);
        accountLogoutPage = rightColumnOptions.clickOnRightSideLogoutOption();
        accountLogoutPage.didWeNavigateToAccountLogoutPage();
        driver = accountLogoutPage.getDriver();
        headerOptions = new HeaderOptions(driver);
        headerOptions.clickOnMyAccountDropMenu();
        Assert.assertTrue(headerOptions.isLoginOptionAvailable());
        driver = headerOptions.getDriver();
        accountLogoutPage = new AccountLogoutPage(driver);
        landingPage = accountLogoutPage.clickOnButtonContinue();
        Assert.assertEquals(getURLPage(landingPage.getDriver()),prop.getProperty("landingPageURL"));
        
    }
    
    @Test (priority = 4)
    public void verifyLoggingOutAndBrowingBack() throws InterruptedException {
        landingPage.clickOnMyAccount();
        loginPage = landingPage.selectLoginOption();
        loginPage.enterInputEmailField(prop.getProperty("emailLogin"));
        loginPage.enterInputPassWordField(prop.getProperty("passwordLogin"));
        accountPage = loginPage.clickOnButtonLogin();
        driver =  accountPage.getDriver();
        headerOptions = new HeaderOptions(driver);
        headerOptions.clickOnMyAccountDropMenu();
        headerOptions.isDisplayButtonLogoutOnDropDown();
        accountLogoutPage = headerOptions.selectLogoutOption();
        driver = accountLogoutPage.getDriver();
        driver = navigateBack(driver);
        driver = refreshPage(driver);
        loginPage = new LoginPage(driver);
        Assert.assertFalse(loginPage.didWeNavigateToLoginPage());
    }
    
    @Test (priority = 5)
    public void verfifyLogoutOptionNotDisplayBeforeLoggin(){
        headerOptions = new HeaderOptions(driver);
        headerOptions.clickOnMyAccountDropMenu();
        Assert.assertFalse(headerOptions.isDisplayButtonLogoutOnDropDown());
    }
    
    @Test (priority = 6)
    public void verfifyLogoutOptionOnRightColumnNotDisplayBeforeLoggin(){
        landingPage.clickOnMyAccount();
        loginPage = landingPage.selectLoginOption();
        driver = loginPage.getDriver();
        rightColumnOptions = new RightColumnOptions(driver);
        Assert.assertFalse(rightColumnOptions.isLogoutRightColumnOptionAvailable());
    }
    
    @Test (priority = 7)
    public void verifyLoginAfterLogout(){
        landingPage.clickOnMyAccount();
        loginPage = landingPage.selectLoginOption();
        loginPage.enterInputEmailField(prop.getProperty("emailLogin"));
        loginPage.enterInputPassWordField(prop.getProperty("passwordLogin"));
        accountPage = loginPage.clickOnButtonLogin();
        driver = accountPage.getDriver();
        headerOptions = new HeaderOptions(driver);
        headerOptions.clickOnMyAccountDropMenu();
        accountLogoutPage = headerOptions.selectLogoutOption();
        headerOptions = new HeaderOptions(driver);
        driver = accountLogoutPage.getDriver();
        landingPage.clickOnMyAccount();
        loginPage = landingPage.selectLoginOption();
        loginPage.enterInputEmailField(prop.getProperty("emailLogin"));
        loginPage.enterInputPassWordField(prop.getProperty("passwordLogin"));
        accountPage = loginPage.clickOnButtonLogin();
        Assert.assertTrue(accountPage.didWeNavigateToAccountPage());
        Assert.assertTrue(accountPage.isUserLoggedIn());
        
    }
    
    @Test (priority = 8)
    public void verifyPageHeadingPageTitlePageURLAndPageBreadcrumbDisplay(){
        landingPage.clickOnMyAccount();
        loginPage = landingPage.selectLoginOption();
        loginPage.enterInputEmailField(prop.getProperty("emailLogin"));
        loginPage.enterInputPassWordField(prop.getProperty("passwordLogin"));
        accountPage = loginPage.clickOnButtonLogin();
        driver = accountPage.getDriver();
        headerOptions = new HeaderOptions(driver);
        headerOptions.clickOnMyAccountDropMenu();
        accountLogoutPage = headerOptions.selectLogoutOption();
        Assert.assertTrue(accountLogoutPage.didWeNavigateToAccountLogoutPage());
        
        Assert.assertEquals(accountLogoutPage.getHeadingLogoutPage(), prop.getProperty("accountLogoutPageHeading"));
        Assert.assertEquals(getPageTitle(accountLogoutPage.getDriver()), prop.getProperty("accountLogoutPageTitle"));
        Assert.assertEquals(getURLPage(accountLogoutPage.getDriver()), prop.getProperty("accountLogoutPageURL"));
        
    }
    
}
