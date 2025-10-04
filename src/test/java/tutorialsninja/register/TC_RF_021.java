package tutorialsninja.register;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TC_RF_021 {

    @Test
    public void verifyRegisteringAccountWithoutPrivacyPolicySelection(){
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.manage().window().maximize();
        driver.get("https://tutorialsninja.com/demo/");

        driver.findElement(By.xpath("//span[text()='My Account']")).click();
        driver.findElement(By.linkText("Register")).click();

//        driver.findElement(By.id("input-firstname")).sendKeys("Arun");
//        driver.findElement(By.id("input-lastname")).sendKeys("Motoori");
//        driver.findElement(By.id("input-email")).sendKeys(CommonUtilsEmail.generateBrandNewEmail());
//        driver.findElement(By.id("input-telephone")).sendKeys("1234567890");
//        driver.findElement(By.id("input-password")).sendKeys("12345");
//        driver.findElement(By.id("input-confirm")).sendKeys("12345");

        Assert.assertEquals(driver.findElement(By.id("input-password")).getAttribute("type"), "password");
        Assert.assertEquals(driver.findElement(By.id("input-confirm")).getAttribute("type"), "password");

        driver.quit();

    }
}
