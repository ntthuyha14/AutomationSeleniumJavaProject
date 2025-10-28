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

public class TC_RF_013 extends Base {
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
    public void verifyPlaceHolderOfTextFieldInRegisterAccountPage() {

        String expectedFirstNamePlaceHolderText = "First Name";
        String expectedLastNamePlaceHolderText = "Last Name";
        String expectedEmailPlaceholderText = "E-Mail";
        String expectedTelephonePlaceHolderText = "Telephone";
        String expectedPassWordPlaceHolderText = "Password";
        String expectedConfirmPassWordPlaceHolderText = "Password Confirm";


        Assert.assertEquals(driver.findElement(By.id("input-firstname")).getAttribute("placeholder"), expectedFirstNamePlaceHolderText);
        Assert.assertEquals(driver.findElement(By.id("input-lastname")).getAttribute("placeholder"), expectedLastNamePlaceHolderText);
        Assert.assertEquals(driver.findElement(By.id("input-email")).getAttribute("placeholder"), expectedEmailPlaceholderText);
        Assert.assertEquals(driver.findElement(By.id("input-telephone")).getAttribute("placeholder"), expectedTelephonePlaceHolderText);
        Assert.assertEquals(driver.findElement(By.id("input-password")).getAttribute("placeholder"), expectedPassWordPlaceHolderText);
        Assert.assertEquals(driver.findElement(By.id("input-confirm")).getAttribute("placeholder"), expectedConfirmPassWordPlaceHolderText);

        driver.quit();

    }
}
