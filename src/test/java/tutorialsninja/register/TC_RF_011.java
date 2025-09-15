package tutorialsninja.register;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Date;

public class TC_RF_011 {
    WebDriver driver;

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
    @Test
    public void verifyRegisterAccountByProvidingInvalidTelephone(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.get("https://tutorialsninja.com/demo/");

        driver.findElement(By.xpath("//span[text()='My Account']")).click();
        driver.findElement(By.linkText("Register")).click();

        driver.findElement(By.id("input-firstname")).sendKeys("Arun");
        driver.findElement(By.id("input-lastname")).sendKeys("Motoori");
        driver.findElement(By.id("input-email")).sendKeys(generateBrandNewEmail());
        driver.findElement(By.id("input-telephone")).sendKeys("abcde");
        driver.findElement(By.id("input-password")).sendKeys("12345");
        driver.findElement(By.id("input-confirm")).sendKeys("12345");
        driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).isSelected();
        driver.findElement(By.xpath("//input[@name='agree']")).click();
        driver.findElement(By.xpath("//input[@value='Continue']")).click();

        String expectedWarningMessage = "Telephone number does not appear to be valid";

        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='input-telephone']/following-sibling::div")).getText(), expectedWarningMessage);

    }

    public static String generateBrandNewEmail(){
        Date date = new Date();
        String dateString = date.toString();
        String dateStringWithoutSpaces = dateString.replaceAll("\\s", "");
        String dateStringWithoutSpacesAndColons = dateStringWithoutSpaces.replaceAll("\\:", "");
        String brandNewEmail = dateStringWithoutSpacesAndColons + "@gmail.com";
        return brandNewEmail;
    }

}

