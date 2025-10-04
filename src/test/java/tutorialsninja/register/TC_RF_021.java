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

        Assert.assertEquals(driver.findElement(By.id("input-password")).getAttribute("type"), "password");
        Assert.assertEquals(driver.findElement(By.id("input-confirm")).getAttribute("type"), "password");

        driver.quit();

    }
}
