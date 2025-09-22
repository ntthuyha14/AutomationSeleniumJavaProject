package tutorialsninja.register;

import Utils.CommonUtilsEmail;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

public class TC_RF_017 {
    //Testcase Check Password Complexity Standard:
    // at least 8 characters, >1 upcase, >1 lowcase, >1 number, >1 character special
    WebDriver driver;

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    @Test(dataProvider = "passwordSupplier")
    public void verifyCheckingPasswordComplexityStandards(String passwordText){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.get("https://tutorialsninja.com/demo/");

        driver.findElement(By.xpath("//span[text()='My Account']")).click();
        driver.findElement(By.linkText("Register")).click();

        driver.findElement(By.id("input-firstname")).sendKeys("Arun");
        driver.findElement(By.id("input-lastname")).sendKeys("Motoori");
        driver.findElement(By.id("input-email")).sendKeys(CommonUtilsEmail.generateBrandNewEmail());
        driver.findElement(By.id("input-telephone")).sendKeys("1234567890");
        driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
        driver.findElement(By.name("agree")).click();
        driver.findElement(By.xpath("//input[@value='Continue']")).click();

        driver.findElement(By.id("input-password")).sendKeys(passwordText);
        driver.findElement(By.id("input-confirm")).sendKeys(passwordText);

        String waringMessage = "Password entered is not matching the Complexity standards";

        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::div")).getText(), waringMessage);
        Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Success']")).isDisplayed());

    }

    @DataProvider(name = "passwordSupplier")
    public Object[][] supplyPasswords(){
        Object[][] data = {{"12345"}, {"abcdeghi"}, {"abcd1234"}, {"abcd123$"}, {"ABCD456#"} };
        return data;
    }

}
