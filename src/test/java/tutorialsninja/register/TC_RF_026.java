package tutorialsninja.register;

import Utils.CommonUtils;
import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import static Utils.CommonUtils.compareTwoScreenshots;

public class TC_RF_026 extends Base {
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
    public void verifyUIRegisterByScreenShot() throws IOException {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        TakesScreenshot ts = (TakesScreenshot) driver;
        File scrScreenshot = ts.getScreenshotAs(OutputType.FILE);
        try {
            FileHandler.copy(scrScreenshot, new File(System.getProperty("user.dir") + "\\Screenshots\\actualRegisterUI.png"));
        } catch (IOException e) {
            e.printStackTrace();


            Assert.assertTrue(CommonUtils.compareTwoScreenshots(System.getProperty("user.dir") + "\\Screenshots\\actualRegisterUI.png", System.getProperty("user.dir") + "\\Screenshots\\expectedRegisterUI.png"));
        }
    }
}
