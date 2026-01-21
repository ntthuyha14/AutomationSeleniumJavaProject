package tutorialsninja.tests;

import Utils.CommonUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;
import tutorialsninja.base.Base;

import java.time.Duration;
import java.util.Properties;

public class Login extends Base {
    public WebDriver driver;
    Properties prop;
    LandingPage landingPage;
    LoginPage loginPage;
    AccountPage accountPage;
    ForgotPasswordPage forgotPasswordPage;
    AccountLogoutPage accountLogoutPage;
    ChangePasswordPage changePasswordPage;
    LogoutPage logoutPage;
    RegisterPage registerPage;
    ContactUsPage contactUsPage;
    ShoppingCartPage shoppingCartPage;
    SearchPage searchPage;
    AboutUsPage aboutUsPage;
    HeaderOptions headerOptions;
    RightColumnOptions rightColumnOptions;
    
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
        closeBrowser(driver);
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

    @Test (priority = 14)
    public void verifyCopyingOfTextEnteredIntoPasswordField(){
        String passwordText = prop.getProperty("passwordLogin");
        loginPage.enterInputPassWordField(passwordText);
        driver = loginPage.selectPasswordFieldTextAndCopy(driver);
        driver = loginPage.pastCopiedTextIntoEmailField(driver);
        Assert.assertNotEquals(loginPage.getTextCopiedIntoEnteredPasswordField(), passwordText);
    }

    //TestCase Failed => Trong PageSource có thông tin passcode
    @Test (priority = 15)
    public void verifyPasswordFieldIsStoreInHTMLCodeOfThePage(){
        String passwordText = prop.getProperty("passwordLogin");
        loginPage.enterInputPassWordField(passwordText);
        Assert.assertFalse(getHTMLCodeOfThePage().contains(passwordText));
        loginPage.clickOnButtonLogin();
        Assert.assertFalse(getHTMLCodeOfThePage().contains(passwordText));
    }

    @Test (priority = 16)
    public void verifyLoggingAfterChangingPassword(){
        loginPage.enterInputEmailField(prop.getProperty("existingSampleEmailTwo"));
        loginPage.enterInputPassWordField(prop.getProperty("validPasswordTwo"));
        accountPage = loginPage.clickOnButtonLogin();
        changePasswordPage = accountPage.clickChangPassword();
        changePasswordPage.enterNewPasswordField(prop.getProperty("samplePasswordTwo"));
        changePasswordPage.enterConfirmNewPasswordField(prop.getProperty("samplePasswordTwo"));
        accountPage = changePasswordPage.clickOnButtonContinue();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.body.style.zoom='70%'");
        logoutPage = accountPage.clickOnLogoutRightColumnOption();
        logoutPage.clickOnMyAccount();
        logoutPage.selectLoginOption();
        loginPage.enterInputEmailField(prop.getProperty("existingSampleEmailTwo"));
        loginPage.enterInputPassWordField(prop.getProperty("validPasswordTwo"));
        accountPage = loginPage.clickOnButtonLogin();
        Assert.assertEquals(loginPage.getWarningMessage(), prop.getProperty("warningMessage"));
        loginPage.clearPasswordValue();
        loginPage.enterInputPassWordField(prop.getProperty("samplePasswordTwo"));
        accountPage = loginPage.clickOnButtonLogin();
        Assert.assertTrue(accountPage.didWeNavigateToAccountPage());
        Assert.assertTrue(accountPage.isUserLoggedIn());

        //Edit Old Password
        changePasswordPage = accountPage.clickChangPassword();
        changePasswordPage.enterNewPasswordField(prop.getProperty("validPasswordTwo"));
        changePasswordPage.enterConfirmNewPasswordField(prop.getProperty("validPasswordTwo"));
        accountPage = changePasswordPage.clickOnButtonContinue();
        String expectedAlertUpdatePasswordSuccessful = "Success: Your password has been successfully updated.";
        Assert.assertEquals(accountPage.getAlertUpdatedPasswordSuccessful(), expectedAlertUpdatePasswordSuccessful);
    }

    @Test (priority = 17)
    public void verifyNavigatingToDifferentPagesFromLoginPage(){

        loginPage = new LoginPage(driver);
        registerPage = loginPage.clickOnButtonContinue();
        Assert.assertTrue(registerPage.didWeNavigatetoRegisterPage());
        navigateBack(driver);

        loginPage = new LoginPage(driver);
        contactUsPage = loginPage.clickOnphoneIconOption();
        Assert.assertTrue(contactUsPage.didWeNavigateToContactUsPage());
        navigateBack(driver);

        loginPage = new LoginPage(driver);
        loginPage.clickOnHeartIconOption();
        Assert.assertTrue(loginPage.didWeNavigateToLoginPage());

        loginPage = new LoginPage(driver);
        shoppingCartPage = loginPage.clickOnShoppingCartIconOption();
        Assert.assertTrue(shoppingCartPage.didWeNavigateToShoppingCartPage());
        navigateBack(driver);

        loginPage = new LoginPage(driver);
        shoppingCartPage = loginPage.clickOnShareIconOption();
        Assert.assertTrue(shoppingCartPage.didWeNavigateToShoppingCartPage());
        navigateBack(driver);

        loginPage = new LoginPage(driver);
        landingPage = loginPage.clickOnButtonQafox();
        Assert.assertEquals(driver.getCurrentUrl(), prop.getProperty("landingPageURL"));
        navigateBack(driver);

        loginPage = new LoginPage(driver);
        searchPage = loginPage.clickOnSearchIconOption();
        Assert.assertTrue(searchPage.didWeNavigateToSearchPage());
        navigateBack(driver);

        loginPage = new LoginPage(driver);
        loginPage.clickOnLoginBreadcrumbOption();
        Assert.assertTrue(loginPage.didWeNavigateToLoginPage());

        loginPage = new LoginPage(driver);
        loginPage.clickOnAccountBreadcrumb();
        Assert.assertTrue(loginPage.didWeNavigateToLoginPage());




        loginPage = new LoginPage(driver);
        forgotPasswordPage = loginPage.clickOnForgottenPasswordOption();
        Assert.assertTrue(forgotPasswordPage.didWeNavigateToForgotPasswordPage());
        navigateBack(driver);

        loginPage = new LoginPage(driver);
        loginPage.clickOnButtonLogin();
        Assert.assertTrue(loginPage.didWeNavigateToLoginPage());

        loginPage = new LoginPage(driver);
        loginPage.clickOnRightSideLoginOption();
        Assert.assertTrue(loginPage.didWeNavigateToLoginPage());

        loginPage = new LoginPage(driver);
        registerPage = loginPage.clickOnRightSideRegisterOption();
        Assert.assertTrue(registerPage.didWeNavigatetoRegisterPage());
        driver = navigateBack(driver);

        loginPage = new LoginPage(driver);
        forgotPasswordPage = loginPage.clickOnRightSideForgottenPasswordOption();
        Assert.assertTrue(forgotPasswordPage.didWeNavigateToForgotPasswordPage());
        navigateBack(driver);

        loginPage = new LoginPage(driver);
        loginPage.clickOnRightSideMyAccountOption();
        Assert.assertTrue(loginPage.didWeNavigateToLoginPage());

        loginPage = new LoginPage(driver);
        loginPage.clickOnSideAddressBookOption();
        Assert.assertTrue(loginPage.didWeNavigateToLoginPage());

        loginPage = new LoginPage(driver);
        loginPage.clickOnRightSideWishListOption();
        Assert.assertTrue(loginPage.didWeNavigateToLoginPage());

        loginPage = new LoginPage(driver);
        loginPage.clickOnRightSideOrderHistoryOption();
        Assert.assertTrue(loginPage.didWeNavigateToLoginPage());

        loginPage = new LoginPage(driver);
        loginPage.clickOnRightSideDownloadOption();
        Assert.assertTrue(loginPage.didWeNavigateToLoginPage());

        loginPage = new LoginPage(driver);
        loginPage.clickOnRightSideRecurringPaymentOption();
        Assert.assertTrue(loginPage.didWeNavigateToLoginPage());

        loginPage = new LoginPage(driver);
        aboutUsPage = loginPage.clickOnRightSideAboutUsOption();
        Assert.assertTrue(aboutUsPage.didWeNavigateToAboutUsPage());
        navigateBack(driver);
    }

    @Test(priority = 18)
    public void verifyDifferentWaysOfNavigatingToLoginPage() {

        registerPage = loginPage.clickButtonContinue();
        loginPage = registerPage.clickBtnLoginPage();
        Assert.assertTrue(loginPage.didWeNavigateToLoginPage());
        rightColumnOptions= new RightColumnOptions(driver);
        loginPage = rightColumnOptions.clickOnRightSideLoginOption();
        Assert.assertTrue(loginPage.didWeNavigateToLoginPage());
        headerOptions = new HeaderOptions(driver);
        headerOptions.clickOnMyAccountDropMenu();
        loginPage = headerOptions.selectLoginOption();
        Assert.assertTrue(loginPage.didWeNavigateToLoginPage());

    }

    @Test (priority = 19)
    public void verifyBreakcrumbHeadingTitleAndPageLogin(){
        headerOptions = new HeaderOptions(driver);
        headerOptions.clickOnMyAccountDropMenu();
        loginPage = headerOptions.selectLoginOption();
        Assert.assertTrue(loginPage.didWeNavigateToLoginPage());

    }

    @Test (priority = 20)
    public void verifyBreadCrumbPageHeadingTitleAndPageURLOfLoginPage(){
        Assert.assertTrue(loginPage.didWeNavigateToLoginPage());
        Assert.assertEquals(getPageTitle(driver), prop.getProperty("loginPageTitle"));
        Assert.assertEquals(getURLPage(driver), prop.getProperty("loginPageURL"));
        Assert.assertEquals(loginPage.getLoginHeadingOne(), prop.getProperty("loginHeadingOne"));
        Assert.assertEquals(loginPage.getLoginHeadingTwo(), prop.getProperty("loginHeadingTwo"));
    }

    @Test (priority = 21)
    public void verifyUIOfLoginPage(){
        CommonUtils.takeScreenshot(driver,  "\\Screenshots\\actualLoginPageUI.png");
        Assert.assertFalse(CommonUtils.compareTwoScreenshots(
                System.getProperty("user.dir") +"\\Screenshots\\actualLoginPageUI.png",
                System.getProperty("user.dir") +"\\Screenshots\\expectedLoginPageUI.png" ));
    }

}
