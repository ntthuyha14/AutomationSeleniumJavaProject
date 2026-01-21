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

}
