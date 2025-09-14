package tutorialsninja.register;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Date;

public class TC_RF_006 {
    @Test
    public void verifyRegisteingAccountBySubscribingToNoNewsletter(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.get("https://tutorialsninja.com/demo/");

        driver.findElement(By.xpath("//span[text()='My Account']")).click();
        driver.findElement(By.linkText("Register")).click();

        driver.findElement(By.id("input-firstname")).sendKeys("Arun");
        driver.findElement(By.id("input-lastname")).sendKeys("Motoori");
        driver.findElement(By.id("input-email")).sendKeys(generateEmail());
        driver.findElement(By.id("input-telephone")).sendKeys("1234567890");
        driver.findElement(By.id("input-password")).sendKeys("12345");
        driver.findElement(By.id("input-confirm")).sendKeys("12345");
        driver.findElement(By.xpath("//input[@name='newsletter'][@value='0']")).isSelected();
        driver.findElement(By.xpath("//input[@name='agree']")).click();
        driver.findElement(By.xpath("//input[@value='Continue']")).click();

        driver.findElement(By.linkText("Continue")).click();

        Assert.assertTrue(driver.findElement(By.linkText("Subscribe / unsubscribe to newsletter")).isDisplayed());
        driver.findElement(By.linkText("Subscribe / unsubscribe to newsletter")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//input[@name='newsletter'][@value='0']")).isSelected());

    }

    public String generateEmail(){
        Date date = new Date();
        String dateString = date.toString();
        String noSpaceDateString = dateString.replaceAll("\\s", "");
        String noSpaceAndColonstateString = noSpaceDateString.replaceAll("\\:", "");
        String emailWithTimeStamp = noSpaceAndColonstateString + "@gmail.com";
        System.out.println(emailWithTimeStamp);
        return emailWithTimeStamp;
    }
}
