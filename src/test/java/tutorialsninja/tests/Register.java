package tutorialsninja.tests;

import Utils.CommonUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.*;
import tutorialsninja.base.Base;

import javax.mail.*;
import javax.mail.search.FlagTerm;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.time.Duration;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Properties;


public class Register extends Base {
    private static final Logger log = LoggerFactory.getLogger(Register.class);
    WebDriver driver;
    Properties prop;
    LandingPage landingPage;
    RegisterPage registerPage;
    AccountPageSuccessful accountPageSuccessful;
    AccountPage accountPage;
    NewsLetterPage newsLetterPage;
    LoginPage loginPage;
    RightColumnOptions rightColumnOptions;
    ContactUsPage contactUsPage;
    ShoppingCartPage shoppingCartPage;
    SearchPage searchPage;
    ForgotPasswordPage forgotPasswordPage;
    AboutUsPage aboutUsPage;

    @BeforeMethod
    public void setup() {
        driver = openBrowserAndApplication();
        prop = CommonUtils.loadProperties();
        landingPage = new LandingPage(driver);
        landingPage.clickOnMyAccount();
        registerPage = landingPage.selectRegisterOption();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test(priority = 1)
    public void verifyRegisteringWithMandatoryFields() {

        registerPage.enterFirstName(prop.getProperty("firstName"));
        registerPage.enterLastName(prop.getProperty("lastName"));
        registerPage.enterEmail(CommonUtils.generateBrandNewEmail());
        registerPage.enterTelephone(prop.getProperty("phoneNumber"));
        registerPage.enterPassword(prop.getProperty("passWord"));
        registerPage.enterConfirmPassword(prop.getProperty("confirmPassword"));
        registerPage.selectPrivacyPolicy();
        accountPageSuccessful = registerPage.clickOnContinueButton();

        Assert.assertTrue(accountPageSuccessful.logoutOptionIsDisplay());

        String expectedHeading = "Your Account Has Been Created!";

        Assert.assertEquals(accountPageSuccessful.getPageHeading(), expectedHeading);

        String expectedProperDetailOne = "Congratulations! Your new account has been successfully created!";
        String expectedDetaiTwo = "You can now take advantage of member privileges to enhance your online shopping experience with us.";
        String expectedDetaiThree = "If you have ANY questions about the operation of this online shop, please e-mail the store owner.";
        String expectedDetaiFour = "contact us";

        String actualProperDetail = accountPageSuccessful.getContent();

        Assert.assertTrue(actualProperDetail.contains(expectedProperDetailOne));
        Assert.assertTrue(actualProperDetail.contains(expectedDetaiTwo));
        Assert.assertTrue(actualProperDetail.contains(expectedDetaiThree));
        Assert.assertTrue(actualProperDetail.contains(expectedDetaiFour));

        accountPage = accountPageSuccessful.clickOnContinueButton();

        Assert.assertTrue(accountPage.editYourAccountInformationDisplay());

    }

    //Testcase 2
    @Test (priority = 2)
    public void verifyConfirmationEmail() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get("https://www.amazon.in/");

        // Kiểm tra xem có button "Continue shopping" hay không
        List<WebElement> continueBtns = driver.findElements(By.xpath("//button[contains(text(),'Continue shopping')]"));

        if (!continueBtns.isEmpty()) {
            // Nếu có thì click vào
            continueBtns.get(0).click();
        }

// Sau đó thực hiện Sign in
        driver.findElement(By.xpath("//span[text()='Hello, sign in']")).click();


        String email = "thuyhainner@gmail.com";
        String appPassCode = "fswf ctdl qpzd xgra";
        String link = null;

        driver.findElement(By.id("ap_email_login")).sendKeys("thuyhainner@gmail.com");
        driver.findElement(By.id("continue")).click();
        driver.findElement(By.id("auth-fpp-link-bottom")).click();
        driver.findElement(By.id("continue")).click();

        System.out.println("Halting the program intentionally for 10 seconds");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        // Gmail IMAP settings
        String host = "imap.gmail.com";
        String port = "993";
        String username = email;
        String password = appPassCode;  // Not recommended, use OAuth2 for better security
        String expectedSubject = "amazon.in: Password recovery";
        String expectedFromEmail = "\"amazon.in\" <account-update@amazon.in>";
        String expectedBodyEmail = "Someone is attempting to reset the password of your account.";

        try {
            // Mail server connection properties
            Properties properties = new Properties();
            properties.put("mail.store.protocol", "imaps");
            properties.put("mail.imap.host", host);
            properties.put("mail.imap.port", port);
            properties.put("mail.imap.ssl.enable", "true");

            // Connect to the mail server
            Session emailSession = Session.getDefaultInstance(properties);
            Store store = emailSession.getStore("imaps");
            store.connect("imap.gmail.com", username, password); // replace email password with App password

            // Open the inbox folder
            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);

            // Search for unread emails
            Message[] messages = inbox.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));

            boolean found = false;
            for (int i = messages.length - 1; i >= 0; i--) {

                Message message = messages[i];

                if (message.getSubject().contains(expectedSubject)) {
                    found = true;
                    Assert.assertEquals(message.getSubject(), expectedSubject);
                    Assert.assertEquals(message.getFrom()[0].toString(), expectedFromEmail);
                    String ActualEmailBody = CommonUtils.getTextFromMessage(message);
                    Assert.assertTrue(ActualEmailBody.contains(expectedBodyEmail));

                    String[] ar = ActualEmailBody.split("it: <a href=\"");
                    String linkPart = ar[1];
                    String[] arr = linkPart.split("\" rel=\"nofollow\">");
                    link = arr[0].trim();

                    break;
                }
            }

            if (!found) {
                System.out.println("No confirmation email found.");
            }

            // Close the store and folder objects
            inbox.close(false);
            store.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        driver.navigate().to(link);
        Assert.assertTrue(driver.findElement(By.name("customerResponseDenyButton")).isDisplayed());

    }

    @Test (priority = 3)
    public void verifyRegisterAccountWithAllFields() {

        registerPage.enterFirstName(prop.getProperty("firstName"));
        registerPage.enterLastName(prop.getProperty("lastName"));
        registerPage.enterEmail(CommonUtils.generateBrandNewEmail());
        registerPage.enterTelephone(prop.getProperty("phoneNumber"));
        registerPage.enterPassword(prop.getProperty("passWord"));
        registerPage.enterConfirmPassword(prop.getProperty("confirmPassword"));
        registerPage.selectYesNewsletterOption();
        registerPage.selectPrivacyPolicy();
        accountPageSuccessful = registerPage.clickOnContinueButton();

        accountPageSuccessful.logoutOptionIsDisplay();
        accountPageSuccessful.didWeNavigateToAccountSuccessPage();

        String expectedProperDetailsOne = "Your Account Has Been Created!";
        String expectedProperDetailsTwo = "Congratulations! Your new account has been successfully created!";
        String expectedProperDetailsThree = "You can now take advantage of member privileges to enhance your online shopping experience with us.";
        String expectedProperDetailsFour = "If you have ANY questions about the operation of this online shop, please e-mail the store owner.";
        String expectedProperDetailsFive = "A confirmation has been sent to the provided e-mail address. If you have not received it within the hour, please ";
        String expectedProperDetailsSix = "contact us";

        String actualProperDetails = accountPageSuccessful.getContent();

        Assert.assertTrue(actualProperDetails.contains(expectedProperDetailsOne));
        Assert.assertTrue(actualProperDetails.contains(expectedProperDetailsTwo));
        Assert.assertTrue(actualProperDetails.contains(expectedProperDetailsThree));
        Assert.assertTrue(actualProperDetails.contains(expectedProperDetailsFour));
        Assert.assertTrue(actualProperDetails.contains(expectedProperDetailsFive));
        Assert.assertTrue(actualProperDetails.contains(expectedProperDetailsSix));

        accountPage = accountPageSuccessful.clickOnContinueButton();
        Assert.assertTrue(accountPage.editYourAccountInformationDisplay());

    }

    @Test (priority = 4)
    public void verifyRegistringAccountWithoutFillFields() {

        registerPage.clickOnContinueButton();

        String expectedPolicyWarning = "Warning: You must agree to the Privacy Policy!";
        String expectedFirstNameWarning = "First Name must be between 1 and 32 characters!";
        String expectedLastNameWarning = "Last Name must be between 1 and 32 characters!";
        String expectedEmailWarning = "E-Mail Address does not appear to be valid!";
        String expectedPhoneWarning = "Telephone must be between 3 and 32 characters!";
        String expectedPasswordWarning = "Password must be between 4 and 20 characters!";

        Assert.assertEquals(registerPage.getfirstNameWarning(), expectedFirstNameWarning);
        Assert.assertEquals(registerPage.getLastNameWarning(), expectedLastNameWarning);
        Assert.assertEquals(registerPage.getEmailWarning(), expectedEmailWarning);
        Assert.assertEquals(registerPage.getTelephoneWarning(), expectedPhoneWarning);
        Assert.assertEquals(registerPage.getPasswordWarning(), expectedPasswordWarning);
        Assert.assertEquals(registerPage.getPolicyWarning(), expectedPolicyWarning);

    }

    @Test (priority = 5)
    public void verifyRegisteingAccountBySubscribingToNewsletter() {
        registerPage.enterFirstName(prop.getProperty("firstName"));
        registerPage.enterLastName(prop.getProperty("lastName"));
        registerPage.enterEmail(CommonUtils.generateBrandNewEmail());
        registerPage.enterTelephone(prop.getProperty("phoneNumber"));
        registerPage.enterPassword(prop.getProperty("passWord"));
        registerPage.enterConfirmPassword(prop.getProperty("confirmPassword"));
        registerPage.selectYesNewsletterOption();
        registerPage.selectPrivacyPolicy();
        accountPageSuccessful = registerPage.clickOnContinueButton();

        accountPage = accountPageSuccessful.clickOnContinueButton();
        newsLetterPage = accountPage.selectSubcribeUnsubcribeNewsletterOption();

        Assert.assertTrue(newsLetterPage.didWeNavigateToNewsletterPage());
        Assert.assertTrue(newsLetterPage.isYesNewsletterOptionSelected());

    }

    @Test (priority = 6)
    public void verifyRegisteingAccountBySubscribingToNoNewsletter() {

        registerPage.enterFirstName(prop.getProperty("firstName"));
        registerPage.enterLastName(prop.getProperty("lastName"));
        registerPage.enterEmail(CommonUtils.generateBrandNewEmail());
        registerPage.enterTelephone(prop.getProperty("phoneNumber"));
        registerPage.enterPassword(prop.getProperty("passWord"));
        registerPage.enterConfirmPassword(prop.getProperty("confirmPassword"));

        registerPage.selectNoNewsletterOption();
        registerPage.selectPrivacyPolicy();
        accountPageSuccessful = registerPage.clickOnContinueButton();

        accountPage = accountPageSuccessful.clickOnContinueButton();
        Assert.assertTrue(accountPage.didWeNavigateToNewletterPage());
        newsLetterPage = accountPage.selectSubcribeUnsubcribeNewsletterOption();
        Assert.assertTrue(newsLetterPage.isNoNewsletterOptionSelected());

    }

    @Test (priority = 7 )
    public void verifyDifferentWaysToRegisterPage() {

        String expectedRegisterText = "Register Account";
        Assert.assertEquals(registerPage.getTitleRegisterAccount(), expectedRegisterText);
        Assert.assertTrue(registerPage.didWeNavigatetoRegisterPage());

        landingPage.clickOnMyAccount();
        loginPage =  landingPage.selectLoginOption();
        registerPage = loginPage.clickButtonContinue();
        Assert.assertEquals(registerPage.getTitleRegisterAccount(), expectedRegisterText);
        Assert.assertTrue(registerPage.didWeNavigatetoRegisterPage());

        landingPage.clickOnMyAccount();
        loginPage =  landingPage.selectLoginOption();
        registerPage = loginPage.clickOnRightSideRegisterOption();
        Assert.assertEquals(registerPage.getTitleRegisterAccount(), expectedRegisterText);
        Assert.assertTrue(registerPage.didWeNavigatetoRegisterPage());
    }

    @Test (priority = 8)
    public void verifyDifferentPasscodeConfirm() {
        registerPage.enterFirstName(prop.getProperty("firstName"));
        registerPage.enterLastName(prop.getProperty("lastName"));
        registerPage.enterEmail(CommonUtils.generateBrandNewEmail());
        registerPage.enterTelephone(prop.getProperty("phoneNumber"));
        registerPage.enterPassword(prop.getProperty("passWord"));
        registerPage.enterConfirmPassword("abcdf");
        registerPage.selectNoNewsletterOption();
        registerPage.selectPrivacyPolicy();
        registerPage.clickOnContinueButton();

        String expectedConfirmPasscodeWarning = "Password confirmation does not match password!";

        Assert.assertEquals(registerPage.getConfirmPasscodeWarning(), expectedConfirmPasscodeWarning);
        Assert.assertTrue(registerPage.didWeNavigatetoRegisterPage());

    }

    @Test(priority = 9)
    public void verifyRegiterUsingExistingEmailAddress() {
        registerPage.enterFirstName(prop.getProperty("firstName"));
        registerPage.enterLastName(prop.getProperty("lastName"));
        registerPage.enterTelephone(prop.getProperty("phoneNumber"));
        registerPage.enterEmail("thuyha@gmail.com");
        registerPage.enterPassword(prop.getProperty("passWord"));
        registerPage.enterPassword(prop.getProperty("confirmPassword"));;
        registerPage.selectNoNewsletterOption();
        registerPage.selectPrivacyPolicy();
        registerPage.clickOnContinueButton();

        String expectedEmailWarning = "Warning: E-Mail Address is already registered!";

        Assert.assertEquals(registerPage.getExistingEmailWarning(), expectedEmailWarning);
        Assert.assertTrue(registerPage.didWeNavigatetoRegisterPage());
    }

    @Test (priority = 10)
    public void verifyRegiterUsingInvalidEmailAddress() throws InterruptedException, IOException {

        registerPage.enterFirstName(prop.getProperty("firstName"));
        registerPage.enterLastName(prop.getProperty("lastName"));
        registerPage.enterTelephone(prop.getProperty("phoneNumber"));
        registerPage.enterPassword(prop.getProperty("passWord"));
        registerPage.enterConfirmPassword(prop.getProperty("confirmPassword"));
        registerPage.selectYesNewsletterOption();
        registerPage.selectPrivacyPolicy();
        registerPage.clickOnContinueButton();

        Thread.sleep(3000);

        File srcScreenshot1 = driver.findElement(By.xpath("//form[@class='form-horizontal']")).getScreenshotAs(OutputType.FILE);
        FileHandler.copy(srcScreenshot1,new File(System.getProperty("user.dir")+"\\Screenshots\\sc1Actual.png"));

        Thread.sleep(3000);

//        Assert.assertFalse(CommonUtils.compareTwoScreenshots(System.getProperty("user.dir")+"\\Screenshots\\sc1Actual.png",System.getProperty("user.dir")+"\\Screenshots\\sc1Expected.png"));

        driver.findElement(By.id("input-email")).clear();
        driver.findElement(By.id("input-email")).sendKeys("amotoori@");
        driver.findElement(By.xpath("//input[@value='Continue']")).click();

        Thread.sleep(2000);

        File srcScreenshot2 = driver.findElement(By.xpath("//form[@class='form-horizontal']")).getScreenshotAs(OutputType.FILE);
        FileHandler.copy(srcScreenshot2,new File(System.getProperty("user.dir")+"\\Screenshots\\sc2Actual.png"));

        Thread.sleep(2000);

        Assert.assertFalse(CommonUtils.compareTwoScreenshots(System.getProperty("user.dir")+"\\Screenshots\\sc2Actual.png",System.getProperty("user.dir")+"\\Screenshots\\sc2Expected.png"));

        driver.findElement(By.id("input-email")).clear();
        driver.findElement(By.id("input-email")).sendKeys("amotoori@gmail");
        driver.findElement(By.xpath("//input[@value='Continue']")).click();

        Thread.sleep(2000);

        String expectedWarningMessage = "E-Mail Address does not appear to be valid!";
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='input-email']/following-sibling::div")).getText(), expectedWarningMessage);

        driver.findElement(By.id("input-email")).clear();
        driver.findElement(By.id("input-email")).sendKeys("amotoori@gmail.");
        driver.findElement(By.xpath("//input[@value='Continue']")).click();

        Thread.sleep(3000);

        File srcScreenshot3 = driver.findElement(By.xpath("//form[@class='form-horizontal']")).getScreenshotAs(OutputType.FILE);
        FileHandler.copy(srcScreenshot3,new File(System.getProperty("user.dir")+"\\Screenshots\\sc3Actual.png"));

        Thread.sleep(3000);

        Assert.assertFalse(CommonUtils.compareTwoScreenshots(System.getProperty("user.dir")+"\\Screenshots\\sc3Actual.png",System.getProperty("user.dir")+"\\Screenshots\\sc3Expected.png"));



    }

    @Test (priority = 11)
    public void verifyRegisterAccountByProvidingInvalidTelephone() {
        registerPage.enterFirstName(prop.getProperty("firstName"));
        registerPage.enterLastName(prop.getProperty("lastName"));
        registerPage.enterEmail(CommonUtils.generateBrandNewEmail());
        registerPage.enterTelephone(prop.getProperty("invalidPhoneNumber"));
        registerPage.enterPassword(prop.getProperty("passWord"));
        registerPage.enterConfirmPassword(prop.getProperty("confirmPassword"));

        registerPage.selectYesNewsletterOption();
        registerPage.selectPrivacyPolicy();
        registerPage.clickOnContinueButton();

        //Test Fail
        String expectedWarningMessage = "Telephone number does not appear to be valid";

        boolean state = false;

        try {
            String actualWarningMessage = registerPage.getTelephoneWarning();
            if (actualWarningMessage.equals(expectedWarningMessage)) {
                state = true;
            }
        } catch (NoSuchElementException e) {
            state = false;
        }

        Assert.assertTrue(state);
//        Assert.assertEquals(registerPage.getTelephoneWarning(), expectedWarningMessage);

    }

    @Test (priority = 12)
    public void verifyRegisteringAccountUsingKeyboardKeys() {
        driver= presskeyMultipleTimes(driver, Keys.TAB, 23);
        driver= enterDetailsIntoRegisterAccountPageFieldS();
        accountPageSuccessful = new AccountPageSuccessful(driver);
        Assert.assertTrue(accountPageSuccessful.rightSideLogoutOptionDisplay());
        Assert.assertTrue(accountPageSuccessful.didWeNavigateToAccountSuccessPage());

    }

    @Test (priority = 13)
    public void verifyPlaceHolderOfTextFieldInRegisterAccountPage() {

        String expectedFirstNamePlaceHolderText = "First Name";
        String expectedLastNamePlaceHolderText = "Last Name";
        String expectedEmailPlaceholderText = "E-Mail";
        String expectedTelephonePlaceHolderText = "Telephone";
        String expectedPassWordPlaceHolderText = "Password";
        String expectedConfirmPassWordPlaceHolderText = "Password Confirm";


        Assert.assertEquals(registerPage.getTextFromHolderFirstNameField(), expectedFirstNamePlaceHolderText);
        Assert.assertEquals(registerPage.getTextFromHolderLastNameField(), expectedLastNamePlaceHolderText);
        Assert.assertEquals(registerPage.getTextFromHolderEmailField(), expectedEmailPlaceholderText);
        Assert.assertEquals(registerPage.getTextFromHolderTelephoneField(), expectedTelephonePlaceHolderText);
        Assert.assertEquals(registerPage.getTextFromHolderPasswordField(), expectedPassWordPlaceHolderText);
        Assert.assertEquals(registerPage.getTextFromHolderConfirmField(), expectedConfirmPassWordPlaceHolderText);

    }

    @Test (priority = 14)
    public void verifyMandatoryFieldsSymbolAndColorInRegister() {

        String expectedContent = "\"* \"";
        String expectedColor = "rgb(255, 0, 0)";

        //FirstName
        Assert.assertEquals(registerPage.getFirstNameLabelContent(driver), expectedContent);
        Assert.assertEquals(registerPage.getFirstNameLabelColor(driver), expectedColor);

        //LastName
        Assert.assertEquals(registerPage.getLastNameLabelContent(driver), expectedContent);
        Assert.assertEquals(registerPage.getLastNameLabelColor(driver), expectedColor);

        //Email
        Assert.assertEquals(registerPage.getEmailLabelContent(driver), expectedContent);
        Assert.assertEquals(registerPage.getEmailLabelColor(driver), expectedColor);

        //TelePhone
        Assert.assertEquals(registerPage.getTelephoneLabelContent(driver), expectedContent);
        Assert.assertEquals(registerPage.getTelephoneLabelColor(driver), expectedColor);

        //Password
        Assert.assertEquals(registerPage.getPasswordLabelContent(driver), expectedContent);
        Assert.assertEquals(registerPage.getPasswordLabelColor(driver), expectedColor);

        //Confirm Password
        Assert.assertEquals(registerPage.getConfirmPasswordLabelContent(driver), expectedContent);
        Assert.assertEquals(registerPage.getConfirmPasswordLabelColor(driver), expectedColor);

    }


    private static final String url = "jdbc:mysql://localhost:3306/opencart_db";
    private static final String user = "root";
    private static final String password = null;

    @Test (priority = 15)
    public void verifyDataTestingOfRegisteringAccount() {

        String firstNameInputData = "Arun";
        registerPage.enterFirstName(firstNameInputData);
        String lastNameInputData = "Motoori";
        registerPage.enterLastName(lastNameInputData);
        String emailInputData = CommonUtils.generateBrandNewEmail().toLowerCase(Locale.ROOT);
        registerPage.enterEmail(emailInputData);
        String passwordInputData = "123456";
        registerPage.enterPassword(passwordInputData);
        registerPage.selectPrivacyPolicy();
        registerPage.clickOnContinueButton();
        driver.findElement(By.xpath("//button[text()='Continue']")).click();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        String firstNameStoredInDatabase = null;
        String lastNameStoredInDatabase = null;
        String emailStoredInDatabase = null;


        try {
            //Step1: Establish the connection
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the database!");

            //Step2: Create a statement
            statement = connection.createStatement();

            //Step3: Execute a query
            String sql = "SELECT * FROM oc_customer";
            resultSet = statement.executeQuery(sql);


            //Step4: Process the result set
            while (resultSet.next()) {
                firstNameStoredInDatabase = resultSet.getString("firstname");
                lastNameStoredInDatabase = resultSet.getString("lastname");
                emailStoredInDatabase = resultSet.getString("email");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //Clean up the resources
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) resultSet.close();
                if (connection != null) resultSet.close();
            } catch (SQLException e) {

            }
        }

        Assert.assertEquals(emailStoredInDatabase, emailInputData);

    }

    @Test (priority = 16)
    public void verifyOnlySpacesMandatoryFields() {

        registerPage.enterFirstName(" ");
        registerPage.enterLastName(" ");
        registerPage.enterEmail(" ");
        registerPage.enterTelephone(" ");
        registerPage.enterPassword(" ");
        registerPage.enterConfirmPassword(" ");
        registerPage.selectYesNewsletterOption();
        registerPage.selectPrivacyPolicy();
        registerPage.clickOnContinueButton();

        String expectedFirstName = "First Name must be between 1 and 32 characters!";
        Assert.assertEquals(registerPage.getfirstNameWarning(), expectedFirstName);

        String expectedLastName = "Last Name must be between 1 and 32 characters!";
        Assert.assertEquals(registerPage.getLastNameWarning(), expectedLastName);

        String expectedEmail = "E-Mail Address does not appear to be valid!";
        Assert.assertEquals(registerPage.getEmailWarning(), expectedEmail);

        String expectedTelephone = "Telephone must be between 3 and 32 characters!";
        Assert.assertEquals(registerPage.getTelephoneWarning(), expectedTelephone);

        String expectedPassword = "Password must be between 4 and 20 characters!";
        Assert.assertEquals(registerPage.getPasswordWarning(), expectedPassword);
    }

    @Test(priority = 17, dataProvider="passwordSupplier")
    public void verifyRegisteringAccountAndCheckingPasswordComplexityStandards(String passwordText) throws InterruptedException {

        registerPage.enterFirstName("Arun");
        registerPage.enterLastName("Motoori");
        registerPage.enterEmail(CommonUtils.generateBrandNewEmail());
        registerPage.enterTelephone("1234567890");
        registerPage.selectYesNewsletterOption();
        registerPage.selectPrivacyPolicy();
        registerPage.enterPassword(passwordText);
        registerPage.enterConfirmPassword(passwordText);
        registerPage.clickOnContinueButton();

        // This TestCase is Fail

        String warningMessage = "Password entered is not matching the Complexity standards";

        boolean state = false;
        try {
            String acutalWarningMessage = registerPage.getPasswordWarning();
            if (acutalWarningMessage.equals(warningMessage)){
                state = true;
            }
        } catch (NoSuchElementException e){
            state = false;
        }

        Assert.assertTrue(state);
        Assert.assertFalse(registerPage.didWeNavigatetoRegisterPage());

        Thread.sleep(3000);

    }

    @DataProvider(name="passwordSupplier")
    public Object[][] supplyPasswords() {
        Object[][] data = {{"12345"},{"abcdefghi"},{"abcd1234"},{"abcd123$"},{"ABCD456#"}};
        return data;
    }

    @Test (priority = 18)
    public void verifyRegisteringAccountFieldsHeightWidthAligment() throws IOException {

        String expectedHeight = "34px";
        String expectedWidth = "701.25px";

        String actualFirstNameFieldHeight = registerPage.getFirstNameFieldHeight();
        String expectedFirstNameFieldWidth = registerPage.getFirstNameFieldWidth();

        Assert.assertEquals(actualFirstNameFieldHeight, expectedHeight);
        Assert.assertEquals(expectedFirstNameFieldWidth, expectedWidth);

        registerPage.enterFirstName("");
        registerPage.clickOnContinueButton();

        String expectedWarning = "First Name must be between 1 and 32 characters!";
        Assert.assertEquals(registerPage.getfirstNameWarning(), expectedWarning);

        registerPage = new RegisterPage(driver);
        registerPage.clearFirstNameField();
        registerPage.enterFirstName("a");
        registerPage.clickOnContinueButton();

        try {
            Assert.assertFalse(registerPage.isFirstNameWarningDisplayed());
        } catch (NoSuchElementException e) {
            Assert.assertTrue(true);
        }

        registerPage = new RegisterPage(driver);
        registerPage = new RegisterPage(driver);
        registerPage.clearFirstNameField();
        registerPage.enterFirstName("ab");

        try {
            Assert.assertFalse(registerPage.isFirstNameWarningDisplayed());
        } catch (NoSuchElementException e) {
            Assert.assertTrue(true);
        }

        registerPage = new RegisterPage(driver);
        registerPage.clearFirstNameField();
        registerPage.enterFirstName("abcdefghijklmnopabcdefghijklmnop");
        registerPage.clickOnContinueButton();
        try {
            Assert.assertFalse(registerPage.isFirstNameWarningDisplayed());
        } catch (NoSuchElementException e) {
            Assert.assertTrue(true);
        }

        registerPage = new RegisterPage(driver);
        registerPage.clearFirstNameField();
        registerPage.enterFirstName("abcdefghijklmnopabcdefghijklmnopq");
        registerPage.clickOnContinueButton();
        try {
            Assert.assertFalse(registerPage.isFirstNameWarningDisplayed());
        } catch (NoSuchElementException e) {
            Assert.assertTrue(true);
        }

        //---------------------



        registerPage = new RegisterPage(driver);
        Assert.assertEquals(registerPage.getLastNameFieldHeight(), expectedHeight);
        Assert.assertEquals(registerPage.getLastNameFieldWidth(), expectedWidth);

        expectedWarning = "Last Name must be between 1 and 32 characters!";
        registerPage.clickOnContinueButton();
        registerPage.clearLastNameField();
        registerPage.enterLastName("");
        registerPage.clickOnContinueButton();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        Assert.assertEquals(registerPage.getLastNameWarning(), expectedWarning);


        registerPage = new RegisterPage(driver);
        registerPage.clearLastNameField();
        registerPage.enterLastName("a");
        registerPage.clickOnContinueButton();
        try {
            Assert.assertFalse(registerPage.isLastNameWarningDisplayed());
        } catch (NoSuchElementException e) {
            Assert.assertTrue(true);
        }

        registerPage = new RegisterPage(driver);
        registerPage.clearLastNameField();
        registerPage.enterLastName("ab");
        registerPage.clickOnContinueButton();
        try {
            Assert.assertFalse(registerPage.isLastNameWarningDisplayed());
        } catch (NoSuchElementException e) {
            Assert.assertTrue(true);
        }

        registerPage = new RegisterPage(driver);
        registerPage.clearLastNameField();
        registerPage.enterLastName("abcdefghijklmnopq");
        registerPage.clickOnContinueButton();
        try {
            Assert.assertFalse(registerPage.isLastNameWarningDisplayed());
        } catch (NoSuchElementException e) {
            Assert.assertTrue(true);
        }

        registerPage = new RegisterPage(driver);
        registerPage.clearLastNameField();
        registerPage.enterLastName("abcdefghijklmnopabcdefghijklmnop");
        registerPage.clickOnContinueButton();
        try {
            Assert.assertFalse(registerPage.isLastNameWarningDisplayed());
        } catch (NoSuchElementException e) {
            Assert.assertTrue(true);
        }

        registerPage = new RegisterPage(driver);
        registerPage.clearLastNameField();
        registerPage.enterLastName("abcdefghijklmnopabcdefghijklmnopq");
        registerPage.clickOnContinueButton();
        Assert.assertEquals(registerPage.getLastNameWarning(), expectedWarning);

        //---------------------

        registerPage = new RegisterPage(driver);
        Assert.assertEquals(registerPage.getEmailFieldHeight(), expectedHeight);
        Assert.assertEquals(registerPage.getEmailFieldWidth(), expectedWidth);

        registerPage = new RegisterPage(driver);
        registerPage.clearEmailField();
        registerPage.enterEmail("abcdefghijklmnopabcdefghijklmnopqabcdefghijklmnopabcdefghijklmno");
        registerPage.clickOnContinueButton();
        try {
            Assert.assertFalse(registerPage.isEmailWarningDisplayed());
        } catch (NoSuchElementException e) {
            Assert.assertTrue(true);
        }

//        ----------------------------------------
        WebDriverWait waitPhone = new WebDriverWait(driver, Duration.ofSeconds(6));
        registerPage = new RegisterPage(driver);
        Assert.assertEquals(registerPage.getTelephoneFieldHeight(), expectedHeight);
        Assert.assertEquals(registerPage.getTelephoneFieldWidth(), expectedWidth);

        expectedWarning = "Telephone must be between 3 and 32 characters!";

        registerPage = new RegisterPage(driver);
        registerPage.clearTelephoneField();
        registerPage.enterTelephone("");
        registerPage.clickOnContinueButton();
        Assert.assertEquals(registerPage.getTelephoneWarning(), expectedWarning);

        registerPage = new RegisterPage(driver);
        registerPage.clearTelephoneField();
        registerPage.enterTelephone("a");
        registerPage.clickOnContinueButton();
        Assert.assertEquals(registerPage.getTelephoneWarning(), expectedWarning);

        registerPage = new RegisterPage(driver);
        registerPage.clearTelephoneField();
        registerPage.enterTelephone("ab");
        registerPage.clickOnContinueButton();
        Assert.assertEquals(registerPage.getTelephoneWarning(), expectedWarning);

        registerPage = new RegisterPage(driver);
        registerPage.clearTelephoneField();
        registerPage.enterTelephone("abc");
        registerPage.clickOnContinueButton();
        try {
            Assert.assertFalse(registerPage.isTelephoneWarningDisplayed());
        } catch (NoSuchElementException e) {
            Assert.assertTrue(true);
        }

        registerPage = new RegisterPage(driver);
        registerPage.clearTelephoneField();
        registerPage.enterTelephone("abcd");
        registerPage.clickOnContinueButton();
        try {
            Assert.assertFalse(registerPage.isTelephoneWarningDisplayed());
        } catch (NoSuchElementException e) {
            Assert.assertTrue(true);
        }

        registerPage = new RegisterPage(driver);
        registerPage.clearTelephoneField();
        registerPage.enterTelephone("abcdefghijklmnop");
        registerPage.clickOnContinueButton();
        try {
            Assert.assertFalse(registerPage.isTelephoneWarningDisplayed());
        } catch (NoSuchElementException e) {
            Assert.assertTrue(true);
        }

        registerPage = new RegisterPage(driver);
        registerPage.clearTelephoneField();
        registerPage.enterTelephone("abcdefghijklmnopabcdefghijklmnop");
        registerPage.clickOnContinueButton();
        try {
            Assert.assertFalse(registerPage.isTelephoneWarningDisplayed());
        } catch (NoSuchElementException e) {
            Assert.assertTrue(true);
        }

        registerPage = new RegisterPage(driver);
        registerPage.clearTelephoneField();
        registerPage.enterTelephone("abcdefghijklmnopabcdefghijklmnopq");
        registerPage.clickOnContinueButton();
        Assert.assertEquals(registerPage.getTelephoneWarning(), expectedWarning);

        //-----------------------
        registerPage = new RegisterPage(driver);
        Assert.assertEquals(registerPage.getPasswordFieldHeight(), expectedHeight);
        Assert.assertEquals(registerPage.getPasswordFieldWidth(), expectedWidth);

        expectedWarning = "Password must be between 4 and 20 characters!";

        registerPage = new RegisterPage(driver);
        registerPage.clearPasswordField();
        registerPage.enterPassword("");
        registerPage.clickOnContinueButton();
        Assert.assertEquals(registerPage.getPasswordWarning(), expectedWarning);

        registerPage = new RegisterPage(driver);
        registerPage.clearPasswordField();
        registerPage.enterPassword("a");
        registerPage.clickOnContinueButton();
        Assert.assertEquals(registerPage.getPasswordWarning(), expectedWarning);

        registerPage = new RegisterPage(driver);
        registerPage.clearPasswordField();
        registerPage.enterPassword("ab");
        registerPage.clickOnContinueButton();
        Assert.assertEquals(registerPage.getPasswordWarning(), expectedWarning);

        registerPage = new RegisterPage(driver);
        registerPage.clearPasswordField();
        registerPage.enterPassword("abc");
        registerPage.clickOnContinueButton();
        Assert.assertEquals(registerPage.getPasswordWarning(), expectedWarning);

        registerPage = new RegisterPage(driver);
        registerPage.clearPasswordField();
        registerPage.enterPassword("abcd");
        registerPage.clickOnContinueButton();
        Assert.assertEquals(registerPage.getPasswordWarning(), expectedWarning);
        try {
            Assert.assertFalse(registerPage.isPasswordWarningDisplayed());
        } catch (NoSuchElementException e) {
            Assert.assertTrue(true);
        }

        registerPage = new RegisterPage(driver);
        registerPage.clearPasswordField();
        registerPage.enterPassword("abcde");
        registerPage.clickOnContinueButton();
        Assert.assertEquals(registerPage.getPasswordWarning(), expectedWarning);
        try {
            Assert.assertFalse(registerPage.isPasswordWarningDisplayed());
        } catch (NoSuchElementException e) {
            Assert.assertTrue(true);
        }

        registerPage = new RegisterPage(driver);
        registerPage.clearPasswordField();
        registerPage.enterPassword("abcdefghij");
        registerPage.clickOnContinueButton();
        Assert.assertEquals(registerPage.getPasswordWarning(), expectedWarning);
        try {
            Assert.assertFalse(registerPage.isPasswordWarningDisplayed());
        } catch (NoSuchElementException e) {
            Assert.assertTrue(true);
        }

        registerPage = new RegisterPage(driver);
        registerPage.clearPasswordField();
        registerPage.enterPassword("abcdefghijabcdefghi");
        registerPage.clickOnContinueButton();
        Assert.assertEquals(registerPage.getPasswordWarning(), expectedWarning);
        try {
            Assert.assertFalse(registerPage.isPasswordWarningDisplayed());
        } catch (NoSuchElementException e) {
            Assert.assertTrue(true);
        }

        registerPage = new RegisterPage(driver);
        registerPage.clearPasswordField();
        registerPage.enterPassword("abcdefghijabcdefghij");
        registerPage.clickOnContinueButton();
        Assert.assertEquals(registerPage.getPasswordWarning(), expectedWarning);
        try {
            Assert.assertFalse(registerPage.isPasswordWarningDisplayed());
        } catch (NoSuchElementException e) {
            Assert.assertTrue(true);
        }

        registerPage = new RegisterPage(driver);
        registerPage.clearPasswordField();
        registerPage.enterPassword("abcdefghijabcdefghijk");
        registerPage.clickOnContinueButton();
        Assert.assertEquals(registerPage.getPasswordWarning(), expectedWarning);

//        =================================

        registerPage = new RegisterPage(driver);

        Assert.assertEquals(registerPage.getConfirmPasswordFieldHeight(), expectedHeight);
        Assert.assertEquals(registerPage.getConfirmPasswordFieldWidth(), expectedWidth);

        driver = navigateToRegisterPage(driver, prop.getProperty("registerPageURL"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.body.style.zoom='70%'");

        driver = CommonUtils.takeScreenshot(driver, "\\Screenshots\\registerPageActualAligment.png");

        Assert.assertFalse(CommonUtils.compareTwoScreenshots(
                System.getProperty("user.dir") + "\\Screenshots\\registerPageActualAligment.png",
                System.getProperty("user.dir") + "\\Screenshots\\registerPageExpectedAligment.png"
        ));
        driver.quit();

    }

    @Test (priority = 19)
    public void verifyLeadingAndTrailingSpaceWhileRegisteringAccount() {

        String enteredFirstName = "     " + prop.getProperty("firstName") + "     ";
        registerPage.enterFirstName(enteredFirstName);
        String enteredLastName = "     " + prop.getProperty("lastName") + "     ";
        registerPage.enterLastName(enteredLastName);
        String enteredEmail = "     " + CommonUtils.generateBrandNewEmail() + "     ";
        registerPage.enterEmail(enteredEmail);
        String enteredTelephone = "     " + prop.getProperty("phoneNumber") + "     ";
        registerPage.enterTelephone(enteredTelephone);
        registerPage.enterPassword(prop.getProperty("passWord"));
        registerPage.enterConfirmPassword(prop.getProperty("passWord"));

        registerPage.selectPrivacyPolicy();
        accountPageSuccessful = registerPage.clickOnContinueButton();

        accountPage = accountPageSuccessful.clickOnContinueButton();

        accountPage.clickEditAccountInformation();

        //Testcase Fail
        Assert.assertEquals(accountPage.getValueFirstName(), enteredFirstName.trim());
        Assert.assertEquals(accountPage.getValueLastName(), enteredLastName.trim());
        Assert.assertEquals(accountPage.getValueEmail(), enteredEmail.trim());
        Assert.assertEquals(accountPage.getValueTelephone(), enteredTelephone.trim());
    }

    @Test (priority = 20)
    public void verifyRegisteringAccountWithoutPrivacyPolicySelection() {

        registerPage.enterFirstName(prop.getProperty("firstName"));
        registerPage.enterLastName(prop.getProperty("lastName"));
        registerPage.enterEmail(CommonUtils.generateBrandNewEmail());
        registerPage.enterTelephone(prop.getProperty("phoneNumber"));
        registerPage.enterPassword(prop.getProperty("passWord"));
        registerPage.enterConfirmPassword(prop.getProperty("passWord"));

        registerPage.clickOnContinueButton();

        String expectedWarning = "Warning: You must agree to the Privacy Policy!";
        Assert.assertEquals(registerPage.getPolicyWarning(), expectedWarning);
    }

    @Test (priority = 21)
    public void verifyDefaultCheckPrivacyPolicy() {
        Assert.assertEquals(registerPage.getValueAgreeSubscribe(), "1");

    }

    @Test (priority = 22)
    public void verifyHideVisiblilityPassword() {

        Assert.assertEquals(registerPage.getTypePasswordField(), "password");
        Assert.assertEquals(registerPage.getTypePasswordConfirmField(), "password");

    }

    @Test (priority = 23)
    public void verifyNavigatingToOtherPages() {

        ContactUsPage contactUsPage = registerPage.clickOnphoneIconOption();
        Assert.assertTrue(contactUsPage.didWeNavigateToContactUsPage());
        navigateBack(driver);

        registerPage = new RegisterPage(driver);
        loginPage = registerPage.clickOnHeartIconOption();
        Assert.assertTrue(loginPage.didWeNavigateToLoginPage());
        navigateBack(driver);

        registerPage = new RegisterPage(driver);
        shoppingCartPage = registerPage.clickOnShoppingCartIconOption();
        Assert.assertTrue(shoppingCartPage.didWeNavigateToShoppingCartPage());
        navigateBack(driver);

        registerPage = new RegisterPage(driver);
        shoppingCartPage = new ShoppingCartPage(driver);
        shoppingCartPage= registerPage.clickOnShareIconOption();
        Assert.assertTrue(shoppingCartPage.didWeNavigateToShoppingCartPage());
        navigateBack(driver);

        registerPage = new RegisterPage(driver);
        landingPage = registerPage.clickOnButtonQafox();
        Assert.assertEquals(driver.getCurrentUrl(), prop.getProperty("landingPageURL"));
        navigateBack(driver);


        registerPage = new RegisterPage(driver);
        searchPage = registerPage.clickOnSearchIconOption();
        Assert.assertTrue(searchPage.didWeNavigateToSearchPage());
        navigateBack(driver);


        registerPage = new RegisterPage(driver);
        registerPage.clickOnRegisterBreadcrumbOption();
        Assert.assertTrue(registerPage.didWeNavigatetoRegisterPage());

        registerPage = new RegisterPage(driver);
        loginPage = registerPage.clickOnAccountBreadcrumb();
        Assert.assertTrue(loginPage.didWeNavigateToLoginPage());
        navigateBack(driver);

        registerPage = new RegisterPage(driver);
        loginPage = registerPage.clickBtnLoginPage();
        Assert.assertTrue(loginPage.didWeNavigateToLoginPage());
        navigateBack(driver);

        registerPage = new RegisterPage(driver);
        landingPage = registerPage.clickOnButtonHome();
        Assert.assertEquals(driver.getCurrentUrl(), prop.getProperty("landingPageURL"));
        driver = navigateBack(driver);

        registerPage = new RegisterPage(driver);
        registerPage.clickOnPrivacyPolicyOption();
        registerPage.waitAndCheckDisplayStatusOfClosePrivacyPolicyOption(driver, 20);
        registerPage.closePrivacyPolicyDialog();

        registerPage = new RegisterPage(driver);
        registerPage.clickOnContinueButton();
        Assert.assertTrue(registerPage.didWeNavigatetoRegisterPage());

        registerPage = new RegisterPage(driver);
        loginPage = registerPage.clickOnRightSideLoginOption();
        Assert.assertTrue(loginPage.didWeNavigateToLoginPage());
        navigateBack(driver);

        registerPage = new RegisterPage(driver);
        registerPage.clickOnRightSideRegisterOption();
        Assert.assertTrue(registerPage.didWeNavigatetoRegisterPage());


        registerPage = new RegisterPage(driver);
        forgotPasswordPage = registerPage.clickOnRightSideForgottenPasswordOption();
        Assert.assertTrue(forgotPasswordPage.didWeNavigateToForgotPasswordPage());
        navigateBack(driver);

        registerPage = new RegisterPage(driver);
        loginPage = registerPage.clickOnRightSideMyAccountOption();
        Assert.assertTrue(loginPage.didWeNavigateToLoginPage());
        navigateBack(driver);

        registerPage = new RegisterPage(driver);
        loginPage = registerPage.clickOnSideAddressBookOption();
        Assert.assertTrue(loginPage.didWeNavigateToLoginPage());
        navigateBack(driver);

        registerPage = new RegisterPage(driver);
        loginPage = registerPage.clickOnRightSideWishListOption();
        Assert.assertTrue(loginPage.didWeNavigateToLoginPage());
        navigateBack(driver);

        registerPage = new RegisterPage(driver);
        loginPage = registerPage.clickOnRightSideOrderHistoryOption();
        Assert.assertTrue(loginPage.didWeNavigateToLoginPage());
        navigateBack(driver);

        registerPage = new RegisterPage(driver);
        loginPage = registerPage.clickOnRightSideDownloadOption();
        Assert.assertTrue(loginPage.didWeNavigateToLoginPage());
        navigateBack(driver);


        registerPage = new RegisterPage(driver);
        loginPage = registerPage.clickOnRightSideRecurringPaymentOption();
        Assert.assertTrue(loginPage.didWeNavigateToLoginPage());
        navigateBack(driver);

        registerPage = new RegisterPage(driver);
        aboutUsPage = registerPage.clickOnRightSideAboutUsOption();
        Assert.assertTrue(aboutUsPage.didWeNavigateToAboutUsPage());
        navigateBack(driver);

    }

    @Test (priority = 24)
    public void verifyNotFillConfirmField() {
        registerPage.enterFirstName(prop.getProperty("firstName"));
        registerPage.enterLastName(prop.getProperty("lastName"));
        registerPage.enterEmail(CommonUtils.generateBrandNewEmail());
        registerPage.enterTelephone(prop.getProperty("phoneNumber"));
        registerPage.enterPassword(prop.getProperty("passWord"));
        registerPage.selectPrivacyPolicy();
        registerPage.selectPrivacyPolicy();
        registerPage.clickOnContinueButton();

        String expectedWarningConfirm = "Password confirmation does not match password!";
        Assert.assertEquals(registerPage.warningPasscodeConfirmNotMatch(), expectedWarningConfirm);
    }

    @Test (priority = 25)
    public void verifyBreadcrumbURLHeadingTitleOfRegisterAccountPage() {

        Assert.assertTrue(registerPage.didWeNavigatetoRegisterPage());
        Assert.assertEquals(driver.getTitle(), prop.getProperty("registerPageTitle"));
        Assert.assertEquals(driver.getCurrentUrl(), prop.getProperty("registerPageURL"));
        Assert.assertEquals(registerPage.getContentH1RegisterPage(), prop.getProperty("registerPageHeading"));

    }

    @Test (priority = 26)
    public void verifyUIRegisterByScreenShot() throws IOException {

        CommonUtils.takeScreenshot(driver, "\\Screenshots\\actualRegisterPageUI.png");

        Assert.assertFalse(CommonUtils.compareTwoScreenshots(
                System.getProperty("user.dir") + "\\Screenshots\\actualRegisterPageUI.png",
                System.getProperty("user.dir") + "\\Screenshots\\expectedRegisterPageUI.png"
        ));
    }

    @Test(priority = 27, dataProvider = "environmentsSuplier")
    public void verifySupportFullEnvironment(String env) {

        registerPage.enterFirstName(prop.getProperty("firstName"));
        registerPage.enterLastName(prop.getProperty("lastName"));
        registerPage.enterEmail(CommonUtils.generateBrandNewEmail());
        registerPage.enterTelephone(prop.getProperty("phoneNumber"));
        registerPage.enterPassword(prop.getProperty("passWord"));
        registerPage.enterConfirmPassword(prop.getProperty("passWord"));
        registerPage.selectYesNewsletterOption();
        registerPage.selectPrivacyPolicy();
        accountPageSuccessful = registerPage.clickOnContinueButton();

        Assert.assertTrue(accountPageSuccessful.rightSideLogoutOptionDisplay());
        Assert.assertTrue(accountPageSuccessful.didWeNavigateToAccountSuccessPage());
        accountPage = accountPageSuccessful.clickOnContinueButton();
        Assert.assertEquals(driver.getTitle(), prop.getProperty("accountPageTitle"));

        driver.quit();


    }

    @DataProvider(name = "environmentsSuplier")
    public Object[][] passTestEnvironment() {
        Object[][] envs = {{"chrome"}, {"edge"}};
        return envs;
    }

}
