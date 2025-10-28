package tutorialsninja.register;

import Utils.CommonUtilsEmail;
import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Date;

public class TC_RF_012 extends Base {
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
    public void verifyRegisteringAccountUsingKeyboardKeys() {

        Actions action = new Actions(driver);
        for (int i = 1; i <= 23; i++) {
            action.sendKeys(Keys.TAB).perform();
        }

        action.sendKeys("Arun").pause(Duration.ofSeconds(1))
                .sendKeys(Keys.TAB).pause(Duration.ofSeconds(1))
                .sendKeys("Motoori").pause(Duration.ofSeconds(1))
                .sendKeys(Keys.TAB).pause(Duration.ofSeconds(1))
                .sendKeys(CommonUtilsEmail.generateBrandNewEmail()).pause(Duration.ofSeconds(1))
                .sendKeys(Keys.TAB).pause(Duration.ofSeconds(1))
                .sendKeys("123456789").pause(Duration.ofSeconds(1))
                .sendKeys(Keys.TAB).pause(Duration.ofSeconds(1))
                .sendKeys("12345").pause(Duration.ofSeconds(1))
                .sendKeys(Keys.TAB).pause(Duration.ofSeconds(1))
                .sendKeys("12345").pause(Duration.ofSeconds(1))
                //mặc định là Option No nên cần Keys.Left để chọn Yes
                .sendKeys(Keys.TAB).pause(Duration.ofSeconds(1))
                .sendKeys(Keys.LEFT).pause(Duration.ofSeconds(1))
                //Keys.TAB: Privacy Policy
                .sendKeys(Keys.TAB).pause(Duration.ofSeconds(1))
                //Keys.TAB: Check Box của Privacy Policy
                .sendKeys(Keys.TAB).pause(Duration.ofSeconds(1))
                //Keys.SPACE: select vào CheckBox
                .sendKeys(Keys.SPACE).pause(Duration.ofSeconds(1))
                //Keys.TAB và Keys.ENTER : button Continue
                .sendKeys(Keys.TAB).pause(Duration.ofSeconds(1))
                .sendKeys(Keys.ENTER).build().perform();

        Assert.assertTrue(driver.findElement(By.xpath("//*[@id='column-right']//a[text()='Logout']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Success']")).isDisplayed());

        driver.quit();

    }

}
