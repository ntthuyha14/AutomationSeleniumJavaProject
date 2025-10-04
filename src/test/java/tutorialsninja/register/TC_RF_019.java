package tutorialsninja.register;

import Utils.CommonUtilsEmail;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class TC_RF_019 {
    WebDriver driver;

    @AfterTest
    public void CloseChrome(){
        driver.quit();
    }

    @Test
    public void verifyLeadingAndTrailingSpaceWhileRegisteringAccount(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.manage().window().maximize();
        driver.get("https://tutorialsninja.com/demo/");

        driver.findElement(By.xpath("//span[text()='My Account']")).click();
        driver.findElement(By.linkText("Register")).click();

        String enteredFirstName = "     Arun     ";
        driver.findElement(By.id("input-firstname")).sendKeys(enteredFirstName);
        String enteredLastName = "     Motoori     ";
        driver.findElement(By.id("input-lastname")).sendKeys(enteredLastName);
        String enteredEmail = "     " + CommonUtilsEmail.generateBrandNewEmail() + "     ";
        driver.findElement(By.id("input-email")).sendKeys(enteredEmail);
        String enteredTelephone = "     1234567890     ";
        driver.findElement(By.id("input-telephone")).sendKeys(enteredTelephone);
        driver.findElement(By.id("input-password")).sendKeys("12345");
        driver.findElement(By.id("input-confirm")).sendKeys("12345");

        driver.findElement(By.xpath("//input[@name='agree']")).click();
        driver.findElement(By.xpath("//input[@value='Continue']")).click();

        driver.findElement(By.linkText("Continue")).click();

        driver.findElement(By.linkText("Edit your account information")).click();

        Assert.assertEquals(driver.findElement(By.id("input-firstname")).getAttribute("value"), enteredFirstName.trim());
        Assert.assertEquals(driver.findElement(By.id("input-lastname")).getAttribute("value"), enteredLastName.trim());
        Assert.assertEquals(driver.findElement(By.id("input-email")).getAttribute("value"), enteredEmail.trim());
        Assert.assertEquals(driver.findElement(By.id("input-telephone")).getAttribute("value"), enteredTelephone.trim());
    }
}
