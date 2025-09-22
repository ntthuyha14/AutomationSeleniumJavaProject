package tutorialsninja.register;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TC_RF_016 {

    @Test
    public void verifyOnlySpacesMandatoryFields(){
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://tutorialsninja.com/demo/");

        driver.findElement(By.xpath("//span[text()='My Account']")).click();
        driver.findElement(By.linkText("Register")).click();

        driver.findElement(By.id("input-firstname")).sendKeys(" ");
        driver.findElement(By.id("input-lastname")).sendKeys(" ");
        driver.findElement(By.id("input-email")).sendKeys(" ");
        driver.findElement(By.id("input-telephone")).sendKeys(" ");
        driver.findElement(By.id("input-password")).sendKeys(" ");
        driver.findElement(By.id("input-confirm")).sendKeys(" ");
        driver.findElement(By.xpath("//input[@name='newsletter' and @value='1']")).click();
        driver.findElement(By.xpath("//input[@name='agree']")).isSelected();
        driver.findElement(By.xpath("//input[@value='Continue']")).click();

        String expectedFirstName = "First Name must be between 1 and 32 characters!";
        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='input-firstname']/following-sibling::div")).getText(), expectedFirstName);

        String expectedLastName = "Last Name must be between 1 and 32 characters!";
        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='input-lastname']/following-sibling::div")).getText(), expectedLastName);

        String expectedEmail = "E-Mail Address does not appear to be valid!";
        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='input-email']/following-sibling::div")).getText(), expectedEmail);

        String expectedTelephone = "Telephone must be between 3 and 32 characters!";
        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='input-telephone']/following-sibling::div")).getText(), expectedTelephone);

        String expectedPassword = "Password must be between 4 and 20 characters!";
        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::div")).getText(), expectedPassword);

        driver.quit();

    }
}
