package tutorialsninja.register;

import tutorialsninja.base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class TC_RF_022 extends Base {
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
    public void verifyHideVisiblilityPassword() {

        Assert.assertEquals(driver.findElement(By.id("input-password")).getAttribute("type"), "password");
        Assert.assertEquals(driver.findElement(By.id("input-confirm")).getAttribute("type"), "password");

        driver.quit();

    }

}
