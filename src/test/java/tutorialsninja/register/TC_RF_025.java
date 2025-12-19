package tutorialsninja.register;

import tutorialsninja.base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TC_RF_025 extends Base {
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
    public void verifyBreadcrumbURLHeadingTitleOfRegisterAccountPage() {

        Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Register']")).isDisplayed());

        String expectedTitle = "Register Account";
        Assert.assertEquals(driver.getTitle(), expectedTitle);

        String expectedUrlLink = "https://tutorialsninja.com/demo/index.php?route=account/register";
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrlLink);

        String expectedHeading = "Register Account";
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='content']//h1")).getText(), expectedHeading);

        driver.quit();

    }
}
