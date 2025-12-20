package tutorialsninja.register;

import Utils.CommonUtils;
import Utils.CommonUtilsEmail;
import tutorialsninja.base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.LandingPage;

import java.util.Properties;

public class TC_RF_001 extends Base {
    WebDriver driver;
    Properties prop;

    @BeforeMethod
    public void setup() {
        driver = openBrowserAndApplication();
        prop = CommonUtils.loadProperties();
        LandingPage landingPage = new LandingPage(driver);
        landingPage.clickOnMyAccount();
        landingPage.selectRegisterOption();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test(priority = 1)
    public void verifyRegisteringWithMandatoryFields() {

        driver.findElement(By.id("input-firstname")).sendKeys(prop.getProperty("firstName"));
        driver.findElement(By.id("input-lastname")).sendKeys(prop.getProperty("lastName"));
        driver.findElement(By.id("input-email")).sendKeys(CommonUtilsEmail.generateBrandNewEmail());
        driver.findElement(By.id("input-telephone")).sendKeys(prop.getProperty("phoneNumber"));
        driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("passWord"));
        driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("passWord"));
        driver.findElement(By.name("agree")).click();
        driver.findElement(By.xpath("//input[@value='Continue']")).click();

        Assert.assertTrue(driver.findElement(By.linkText("Logout")).isDisplayed());

        String expectedHeading = "Your Account Has Been Created!";

        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='common-success']//h1")).getText(), expectedHeading);

        String actualProperDetailOne = "Congratulations! Your new account has been successfully created!";
        String actualProperDetaiTwo = "You can now take advantage of member privileges to enhance your online shopping experience with us.";
        String actualProperDetaiThree = "If you have ANY questions about the operation of this online shop, please e-mail the store owner.";
        String actualProperDetaiFour = "contact us";

        String expectedProperDetail = driver.findElement(By.id("content")).getText();
        Assert.assertTrue(expectedProperDetail.contains(actualProperDetailOne));
        Assert.assertTrue(expectedProperDetail.contains(actualProperDetaiTwo));
        Assert.assertTrue(expectedProperDetail.contains(actualProperDetaiThree));
        Assert.assertTrue(expectedProperDetail.contains(actualProperDetaiFour));

        driver.findElement(By.xpath("//a[text()='Continue']")).click();

        Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());


    }


}
