package tutorialsninja.register;

import Utils.CommonUtils;
import Utils.CommonUtilsEmail;
import base.Base;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Properties;

public class TC_RF_009 extends Base {
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
    public void verifyRegiterUsingExistingEmailAddress() {
        driver.findElement(By.id("input-firstname")).sendKeys(prop.getProperty("firstName"));
        driver.findElement(By.id("input-lastname")).sendKeys(prop.getProperty("lastName"));
        driver.findElement(By.id("input-telephone")).sendKeys(prop.getProperty("phoneNumber"));
        driver.findElement(By.id("input-email")).sendKeys("thuyha@gmail.com");
        driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("passWord"));
        driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("passWord"));
        driver.findElement(By.xpath("//input[@name='newsletter'][@value='0']")).isSelected();
        driver.findElement(By.xpath("//input[@name='agree']")).click();
        driver.findElement(By.xpath("//input[@value='Continue']")).click();
        String expectedEmailWarning = "Warning: E-Mail Address is already registered!";

        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText(), expectedEmailWarning);
        Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Register']")).isDisplayed());

    }
}
