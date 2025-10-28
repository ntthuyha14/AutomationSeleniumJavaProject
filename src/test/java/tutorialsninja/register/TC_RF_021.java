package tutorialsninja.register;

import base.Base;
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

public class TC_RF_021 extends Base {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = openBrowserAndApplication();
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
    public void verifyDefaultCheckPrivacyPolicy() {

        Assert.assertEquals(driver.findElement(By.xpath("//input[@name='agree']")).getAttribute("value"), "1");

        driver.quit();

    }
}
