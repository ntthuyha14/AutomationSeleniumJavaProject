package tutorialsninja.register;

import Utils.CommonUtils;
import tutorialsninja.base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Properties;

public class TC_RF_019 extends Base {
    WebDriver driver;
    Properties prop ;

    @BeforeMethod
    public void setup() {
        driver = openBrowserAndApplication();
        prop = CommonUtils.loadProperties();
        driver.findElement(By.xpath("//span[text()='My Account']")).click();
        driver.findElement(By.linkText("Register")).click();
    }

//    @AfterMethod
//    public void tearDown() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }

    @Test
    public void verifyLeadingAndTrailingSpaceWhileRegisteringAccount() {

        String enteredFirstName = "     " + prop.getProperty("firstName") + "     ";
        driver.findElement(By.id("input-firstname")).sendKeys(enteredFirstName);
        String enteredLastName = "     " + prop.getProperty("lastName") + "     ";
        driver.findElement(By.id("input-lastname")).sendKeys(enteredLastName);
        String enteredEmail = "     " + CommonUtils.generateBrandNewEmail() + "     ";
        driver.findElement(By.id("input-email")).sendKeys(enteredEmail);
        String enteredTelephone = "     " + prop.getProperty("phoneNumber") + "     ";
        driver.findElement(By.id("input-telephone")).sendKeys(enteredTelephone);
        driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("passWord"));
        driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("passWord"));

        driver.findElement(By.xpath("//input[@name='agree']")).click();
        driver.findElement(By.xpath("//input[@value='Continue']")).click();

        driver.findElement(By.linkText("Continue")).click();

        driver.findElement(By.linkText("Edit your account information")).click();

        //Testcase Fail
        Assert.assertEquals(driver.findElement(By.id("input-firstname")).getAttribute("value"), enteredFirstName.trim());
        Assert.assertEquals(driver.findElement(By.id("input-lastname")).getAttribute("value"), enteredLastName.trim());
        Assert.assertEquals(driver.findElement(By.id("input-email")).getAttribute("value"), enteredEmail.trim());
        Assert.assertEquals(driver.findElement(By.id("input-telephone")).getAttribute("value"), enteredTelephone.trim());
    }
}
