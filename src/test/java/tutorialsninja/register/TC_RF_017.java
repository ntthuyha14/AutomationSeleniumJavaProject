package tutorialsninja.register;

import Utils.CommonUtilsEmail;
import tutorialsninja.base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TC_RF_017 extends Base {
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

    @Test(dataProvider="passwordSupplier")
    public void verifyRegisteringAccountAndCheckingPasswordComplexityStandards(String passwordText) throws InterruptedException {

        driver.findElement(By.xpath("//span[text()='My Account']")).click();
        driver.findElement(By.linkText("Register")).click();

        driver.findElement(By.id("input-firstname")).sendKeys("Arun");
        driver.findElement(By.id("input-lastname")).sendKeys("Motoori");
        driver.findElement(By.id("input-email")).sendKeys(CommonUtilsEmail.generateBrandNewEmail());
        driver.findElement(By.id("input-telephone")).sendKeys("1234567890");
        driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
        driver.findElement(By.name("agree")).click();
        driver.findElement(By.id("input-password")).sendKeys(passwordText);
        driver.findElement(By.id("input-confirm")).sendKeys(passwordText);
        driver.findElement(By.xpath("//input[@value='Continue']")).click();

        // This TestCase is Fail

        String warningMessage = "Password entered is not matching the Complexity standards";

        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::div")).getText(), warningMessage);
        Assert.assertFalse(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Success']")).isDisplayed());

        Thread.sleep(3000);

    }

    @DataProvider(name="passwordSupplier")
    public Object[][] supplyPasswords() {
        Object[][] data = {{"12345"},{"abcdefghi"},{"abcd1234"},{"abcd123$"},{"ABCD456#"}};
        return data;
    }

}
