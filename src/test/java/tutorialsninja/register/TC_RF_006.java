package tutorialsninja.register;

import Utils.CommonUtils;
import tutorialsninja.base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.Properties;

public class TC_RF_006 extends Base {
    WebDriver driver;
    Properties prop;

    @BeforeMethod
    public void setup() {
        driver = openBrowserAndApplication();
        prop = CommonUtils.loadProperties();
        driver.findElement(By.xpath("//span[text()='My Account']")).click();
        driver.findElement(By.linkText("Register")).click();


    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void verifyRegisteingAccountBySubscribingToNoNewsletter() {

        driver.findElement(By.id("input-firstname")).sendKeys(prop.getProperty("firstName"));
        driver.findElement(By.id("input-lastname")).sendKeys(prop.getProperty("lastName"));
        driver.findElement(By.id("input-email")).sendKeys(CommonUtils.generateBrandNewEmail());
        driver.findElement(By.id("input-telephone")).sendKeys(prop.getProperty("phoneNumber"));
        driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("passWord"));
        driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("passWord"));
        driver.findElement(By.xpath("//input[@name='newsletter'][@value='0']")).isSelected();
        driver.findElement(By.xpath("//input[@name='agree']")).click();
        driver.findElement(By.xpath("//input[@value='Continue']")).click();

        driver.findElement(By.linkText("Continue")).click();
        Assert.assertTrue(driver.findElement(By.linkText("Subscribe / unsubscribe to newsletter")).isDisplayed());
        driver.findElement(By.linkText("Subscribe / unsubscribe to newsletter")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//input[@name='newsletter'][@value='0']")).isSelected());

    }

    public String generateEmail() {
        Date date = new Date();
        String dateString = date.toString();
        String noSpaceDateString = dateString.replaceAll("\\s", "");
        String noSpaceAndColonstateString = noSpaceDateString.replaceAll("\\:", "");
        String emailWithTimeStamp = noSpaceAndColonstateString + "@gmail.com";
        System.out.println(emailWithTimeStamp);
        return emailWithTimeStamp;
    }
}
