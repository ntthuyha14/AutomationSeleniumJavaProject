package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.text.Element;
import java.time.Duration;

public class RegisterPage {
    WebDriver driver;

    public RegisterPage (WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy (xpath = "//div[@id='content']/h1")
    private WebElement titleRegisterAccount;
    public String getTitleRegisterAccount(){
        return titleRegisterAccount.getText();
    }

    @FindBy (xpath = "//ul[@class='breadcrumb']//a[text()='Register']")
    private WebElement registerBreadcrumb;
    public boolean didWeNavigatetoRegisterPage(){
        return registerBreadcrumb.isDisplayed();
    }

    @FindBy(id = "input-firstname")
    private WebElement firstNameField;
    public void enterFirstName(String firstNameText){
        firstNameField.sendKeys(firstNameText);
    }

    @FindBy(id = "input-lastname")
    private WebElement lastNameField;
    public void enterLastName(String lastNameText){
        lastNameField.sendKeys(lastNameText);
    }


    @FindBy(id = "input-email")
    private WebElement emailField;
    public void enterEmail(String emailText){
        emailField.sendKeys(emailText);
    }

    @FindBy(id = "input-telephone")
    private WebElement telephoneField;
    public void enterTelephone(String telephoneText){
        telephoneField.sendKeys(telephoneText);
    }

    @FindBy(id = "input-password")
    private WebElement passwordField;
    public  void enterPassword(String passwordText){
        passwordField.sendKeys(passwordText);
    }

    @FindBy(id = "input-confirm")
    private WebElement passwordConfirmField;
    public void enterConfirmPassword(String passwordConfirmText){
        passwordConfirmField.sendKeys(passwordConfirmText);
    }

    @FindBy(name = "agree")
    private WebElement privacyPolicyField;
    public void selectPrivacyPolicy(){
        privacyPolicyField.click();
    }

    @FindBy(xpath = "//input[@value='Continue']")
    private WebElement continueButton;
    public AccountPageSuccessful clickOnContinueButton(){
        continueButton.click();
        return new AccountPageSuccessful(driver);
    }

    @FindBy(xpath = "//input[@name='newsletter'][@value='1']")
    private WebElement yesNewsletterOption;
    public void selectYesNewsletterOption(){
        yesNewsletterOption.click();
    }

    @FindBy(xpath = "//input[@name='newsletter'][@value='0']")
    private WebElement noNewsletterOption;
    public void selectNoNewsletterOption(){
        noNewsletterOption.click();
    }

    @FindBy (xpath = "//input[@id='input-firstname']/following-sibling::div")
    private WebElement firstNameWarning;
    public String getfirstNameWarning(){
        return  firstNameWarning.getText();
    }

    @FindBy (xpath= "//input[@id='input-lastname']/following-sibling::div")
    private WebElement lastNameWarning;
    public String getLastNameWarning(){
        return lastNameWarning.getText();
    }

    @FindBy (xpath = "//input[@id='input-email']/following-sibling::div")
    private WebElement emailWarning;
    public String getEmailWarning(){
        return emailWarning.getText();
    }

    @FindBy (xpath = "//input[@id='input-telephone']/following-sibling::div")
    private WebElement telephoneWarning;
    public String getTelephoneWarning(){
        return telephoneWarning.getText();
    }

    @FindBy (xpath = "//input[@id='input-password']/following-sibling::div")
    private WebElement passwordWarning;
    public String getPasswordWarning(){
        return passwordWarning.getText();
    }

    @FindBy (xpath= "//div[@class='alert alert-danger alert-dismissible']")
    private  WebElement policyWarning;
    public String getPolicyWarning(){
        return policyWarning.getText();
    }

    @FindBy (xpath = "//div[@class='text-danger']")
    private WebElement confirmPasscodeWarning;
    public String getConfirmPasscodeWarning(){
        return confirmPasscodeWarning.getText();
    }

    @FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
    private WebElement existingEmailWarning;
    public String getExistingEmailWarning(){
        return confirmPasscodeWarning.getText();
    }

    public String getTextFromHolderFirstNameField(){
        return firstNameField.getDomAttribute("placeholder");
    }

    public String getTextFromHolderLastNameField(){
        return lastNameField.getDomAttribute("placeholder");
    }

    public String getTextFromHolderEmailField(){
        return emailField.getDomAttribute("placeholder");
    }

    public String getTextFromHolderTelephoneField(){
        return telephoneField.getAttribute("placeholder");
    }

    public String getTextFromHolderPasswordField(){
        return passwordField.getDomAttribute("placeholder");
    }

    public String getTextFromHolderConfirmField(){
        return passwordConfirmField.getDomAttribute("placeholder");
    }

    @FindBy (css = "label[for='input-firstname']")
    private WebElement firstNameLabel;
    public String getFirstNameLabelContent(WebDriver driver){
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        //FirstName
        String fnContent = (String) jse.executeScript("return window.getComputedStyle(arguments[0], '::before').getPropertyValue('content');", firstNameLabel);
        return fnContent;
    }

    public String getFirstNameLabelColor(WebDriver driver){
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        String fnColor = (String) jse.executeScript("return window.getComputedStyle(arguments[0], '::before').getPropertyValue('color');", firstNameLabel);
        return fnColor;
    }

    @FindBy (css ="label[for='input-lastname']")
    private WebElement lastNameLabel;
    public String getLastNameLabelContent(WebDriver driver){
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        String lnContent = (String) jse.executeScript("return window.getComputedStyle(arguments[0], '::before').getPropertyValue('content');", lastNameLabel);
        return lnContent;
    }

    public String getLastNameLabelColor(WebDriver driver){
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        String lnColor = (String) jse.executeScript("return window.getComputedStyle(arguments[0], '::before').getPropertyValue('color');", lastNameLabel);
        return lnColor;
    }

    @FindBy (css = "label[for='input-email']")
    private WebElement emailLabel;
    public String getEmailLabelContent(WebDriver driver){
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        String emailContent = (String) jse.executeScript("return window.getComputedStyle(arguments[0], '::before').getPropertyValue('content');", emailLabel);
        return emailContent;
    }

    public String getEmailLabelColor(WebDriver driver){
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        String emailColor = (String) jse.executeScript("return window.getComputedStyle(arguments[0], '::before').getPropertyValue('color');", emailLabel);
        return emailColor;
    }

    @FindBy (css = "label[for='input-telephone']")
    private WebElement telephoneLabel;
    public String getTelephoneLabelContent(WebDriver driver){
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        String telephoneContent = (String) jse.executeScript("return window.getComputedStyle(arguments[0], '::before').getPropertyValue('content');", telephoneLabel);
        return  telephoneContent;
    }

    public String getTelephoneLabelColor(WebDriver driver){
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        String telephoneColor = (String) jse.executeScript("return window.getComputedStyle(arguments[0], '::before').getPropertyValue('color');", telephoneLabel);
        return  telephoneColor;
    }

    @FindBy (css = "label[for='input-password']")
    private WebElement passwordLabel;
    public String getPasswordLabelContent(WebDriver driver){
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        String passwordContent = (String) jse.executeScript("return window.getComputedStyle(arguments[0], '::before').getPropertyValue('content');", passwordLabel);
        return passwordContent;
    }

    public String getPasswordLabelColor(WebDriver driver){
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        String passwordColor = (String) jse.executeScript("return window.getComputedStyle(arguments[0], '::before').getPropertyValue('color');", passwordLabel);
        return passwordColor;
    }

    @FindBy (css ="label[for='input-confirm']")
    private WebElement confirmPasswordLabel;
    public String getConfirmPasswordLabelContent(WebDriver driver){
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        String confirmPasswordContent = (String) jse.executeScript("return window.getComputedStyle(arguments[0], '::before').getPropertyValue('content');", confirmPasswordLabel);
        return confirmPasswordContent;
    }

    public String getConfirmPasswordLabelColor(WebDriver driver){
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        String confirmPasswordColor = (String) jse.executeScript("return window.getComputedStyle(arguments[0], '::before').getPropertyValue('color');", confirmPasswordLabel);
        return confirmPasswordColor;
    }

    public String  getFirstNameFieldHeight(){
        return firstNameField.getCssValue("height");
    }

    public String getFirstNameFieldWidth(){
        return firstNameField.getCssValue("width");
    }

    public void clearFirstNameField(){
        firstNameField.clear();
    }

    public boolean isFirstNameWarningDisplayed(){
        boolean status = false;
        try {
            firstNameWarning.isDisplayed();
        } catch (NoSuchElementException e) {
             status = false;
        }
        return status;
    }

    public String getLastNameFieldHeight(){
        return lastNameField.getCssValue("height");
    }

    public String getLastNameFieldWidth(){
        return lastNameField.getCssValue("width");
    }

    public void clearLastNameField(){
         lastNameField.clear();
    }

    public boolean isLastNameWarningDisplayed(){
        boolean status = false;
        try {
            lastNameWarning.isDisplayed();
        } catch (NoSuchElementException e) {
            status = false;
        }
        return status;
    }

    public String getEmailFieldHeight(){
        return emailField.getCssValue("height");
    }

    public String getEmailFieldWidth(){
        return emailField.getCssValue("width");
    }

    public void clearEmailField(){
        emailField.clear();
    }

    public boolean isEmailWarningDisplayed(){
        boolean status = false;
        try {
            emailWarning.isDisplayed();
        } catch (NoSuchElementException e) {
            status = false;
        }
        return status;
    }

    public String getTelephoneFieldHeight(){
        return telephoneField.getCssValue("height");
    }

    public String getTelephoneFieldWidth(){
        return telephoneField.getCssValue("width");
    }

    public void clearTelephoneField(){
        telephoneField.clear();
    }

    public boolean isTelephoneWarningDisplayed(){
        boolean status = false;
        try {
            telephoneWarning.isDisplayed();
        } catch (NoSuchElementException e) {
            status = false;
        }
        return status;
    }

    public String getPasswordFieldHeight(){
        return passwordField.getCssValue("height");
    }

    public String getPasswordFieldWidth(){
        return passwordField.getCssValue("width");
    }

    public void clearPasswordField(){
        passwordField.clear();
    }

    public boolean isPasswordWarningDisplayed(){
        boolean status = false;
        try {
            passwordWarning.isDisplayed();
        } catch (NoSuchElementException e) {
            status = false;
        }
        return status;
    }

    public String getConfirmPasswordFieldHeight(){
        return passwordConfirmField.getCssValue("height");
    }

    public String getConfirmPasswordFieldWidth(){
        return passwordConfirmField.getCssValue("width");
    }

    @FindBy (xpath = "//input[@name='agree']")
    private WebElement agreeSubscribe;
    public String getValueAgreeSubscribe(){
        return agreeSubscribe.getAttribute("value");
    }

    public String getTypePasswordField(){
        return passwordField.getAttribute("type");
    }

    public String getTypePasswordConfirmField(){
        return passwordConfirmField.getAttribute("type");
    }

    @FindBy (xpath = "//a/i[@class='fa fa-phone']")
    private WebElement phoneIconOption;
    public ContactUsPage clickOnphoneIconOption(){
        phoneIconOption.click();
        return new ContactUsPage(driver);
    }

    @FindBy (xpath = "//a[@id='wishlist-total']")
    private WebElement heartIconOption;
    public LoginPage clickOnHeartIconOption(){
        heartIconOption.click();
        return new LoginPage(driver);
    }

    @FindBy (xpath = "//a/i[@class='fa fa-shopping-cart']")
    private WebElement shoppingcartIcon;
    public ShoppingCartPage clickOnShoppingCartIconOption(){
        shoppingcartIcon.click();
        return new ShoppingCartPage(driver);
    }

    @FindBy (xpath = "//a/i[@class='fa fa-share']")
    private WebElement shareIcon;
    public ShoppingCartPage clickOnShareIconOption(){
        shareIcon.click();
        return new ShoppingCartPage(driver);
    }

    @FindBy (linkText = "Qafox.com")
    private WebElement btnQafox;
    public LandingPage clickOnButtonQafox(){
        btnQafox.click();
        return new LandingPage(driver);
    }

    @FindBy (xpath = "//button[@class='btn btn-default btn-lg']")
    private WebElement searchIconOption;
    public SearchPage clickOnSearchIconOption(){
        searchIconOption.click();
        return new SearchPage(driver);
    }

    public void clickOnRegisterBreadcrumbOption(){
        registerBreadcrumb.click();
    }

    @FindBy (xpath = "//ul[@class='breadcrumb']//a[text()='Account']")
    private WebElement accountBreadcrumb;
    public LoginPage clickOnAccountBreadcrumb(){
        accountBreadcrumb.click();
        return new LoginPage(driver);
    }

    @FindBy (linkText = "login page")
    private WebElement btnLoginPage;
    public LoginPage clickBtnLoginPage(){
        btnLoginPage.click();
        return new LoginPage(driver);
    }

    @FindBy (xpath = "//a[@class='agree']/b[text()='Privacy Policy']")
    private WebElement btnPrivacyPolicy;

    @FindBy (xpath = "//ul[@class='breadcrumb']//i[@class='fa fa-home']")
    private WebElement btnHome;
    public LandingPage clickOnButtonHome(){
        btnHome.click();
        return new LandingPage(driver);
    }

    @FindBy (xpath = "//button[text()='×']")
    private WebElement closePrivacyPolicyOption;

    @FindBy (xpath = "//button[text()='×']")
    private WebElement xOption;

    private By xOptionPrivacyPolicy = By.xpath("//button[text()='×']");

    public boolean waitAndCheckDisplayStatusOfClosePrivacyPolicyOption(WebDriver driver, int seconds){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
         wait.until(ExpectedConditions.visibilityOfElementLocated(xOptionPrivacyPolicy));
         return xOption.isDisplayed();
    }

    public void closePrivacyPolicyDialog(){
        xOption.click();
    }

    public void clickOnPrivacyPolicyOption(){
        btnPrivacyPolicy.click();
    }

    @FindBy (xpath = "//div[@class='list-group']//a[text()='Login']")
    private WebElement rightSideLoginOption;
    public LoginPage clickOnRightSideLoginOption(){
        rightSideLoginOption.click();
        return new LoginPage(driver);
    }

    @FindBy(xpath = "//div[@class='list-group']//a[text()='Register']")
    private WebElement rightSideRegisterOption;
    public void clickOnRightSideRegisterOption(){
        rightSideRegisterOption.click();
    }

    @FindBy (xpath = "//div[@class='list-group']//a[text()='Forgotten Password']")
    private WebElement rightSideForgottenPasswordOption;
    public ForgotPasswordPage clickOnRightSideForgottenPasswordOption(){
        rightSideForgottenPasswordOption.click();
        return new ForgotPasswordPage(driver);
    }

    @FindBy (xpath = "//div[@class='list-group']//a[text()='My Account']")
    private WebElement rightSideMyAccountOption;
    public LoginPage clickOnRightSideMyAccountOption(){
        rightSideMyAccountOption.click();
        return new LoginPage(driver);
    }

    @FindBy (xpath = "//div[@class='list-group']//a[text()='Address Book']")
    private WebElement rightSideAddressBookOption;
    public LoginPage clickOnSideAddressBookOption(){
        rightSideAddressBookOption.click();
        return new LoginPage(driver);
    }

    @FindBy (xpath = "//div[@class='list-group']//a[text()='Wish List']")
    private WebElement rightSideWishListOption;
    public LoginPage clickOnRightSideWishListOption(){
        rightSideWishListOption.click();
        return new LoginPage(driver);
    }

    @FindBy (xpath = "//div[@class='list-group']//a[text()='Order History']")
    private WebElement rightSideOrderHistoryOption;
    public LoginPage clickOnRightSideOrderHistoryOption(){
        rightSideOrderHistoryOption.click();
        return new LoginPage(driver);
    }

    @FindBy (xpath = "//div[@class='list-group']//a[text()='Downloads']")
    private WebElement rightSideDownloadOption;
    public LoginPage clickOnRightSideDownloadOption(){
        rightSideDownloadOption.click();
        return new LoginPage(driver);
    }

    @FindBy (xpath = "//div[@class='list-group']//a[text()='Recurring payments']")
    private WebElement rightSideRecurringPaymentOption;
    public LoginPage clickOnRightSideRecurringPaymentOption(){
        rightSideRecurringPaymentOption.click();
        return new LoginPage(driver);
    }

    @FindBy (xpath = "//ul[@class='list-unstyled']//a[text()='About Us']")
    private WebElement rightSideAboutUsOption;
    public AboutUsPage clickOnRightSideAboutUsOption(){
        rightSideAboutUsOption.click();
        return new AboutUsPage(driver);
    }

    @FindBy (xpath = "//div[@class='col-sm-10']//div[@class='text-danger']")
    private WebElement warningPasscodeConfirm;
    public String warningPasscodeConfirmNotMatch(){
       return warningPasscodeConfirm.getText();
    }

    @FindBy (xpath = "//div[@id='content']//h1")
    private WebElement contentH1RegisterPage;
    public String getContentH1RegisterPage(){
        return contentH1RegisterPage.getText();
    }


































}
