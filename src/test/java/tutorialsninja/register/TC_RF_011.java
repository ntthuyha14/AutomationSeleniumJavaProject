package tutorialsninja.register;

import Utils.CommonUtils;
import tutorialsninja.base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Properties;

public class TC_RF_011 extends Base {
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
    public void verifyRegisterAccountByProvidingInvalidTelephone() {
        driver.findElement(By.id("input-firstname")).sendKeys(prop.getProperty("firstName"));
        driver.findElement(By.id("input-lastname")).sendKeys(prop.getProperty("lastName"));
        driver.findElement(By.id("input-email")).sendKeys(CommonUtils.generateBrandNewEmail());
        driver.findElement(By.id("input-telephone")).sendKeys(prop.getProperty("phoneNumber"));
        driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("passWord"));
        driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("passWord"));

        driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).isSelected();
        driver.findElement(By.xpath("//input[@name='agree']")).click();
        driver.findElement(By.xpath("//input[@value='Continue']")).click();

        //Test Fail
        String expectedWarningMessage = "Telephone number does not appear to be valid";
        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='input-telephone']/following-sibling::div")).getText(), expectedWarningMessage);

    }


}

