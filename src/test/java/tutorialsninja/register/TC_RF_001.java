package tutorialsninja.register;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Date;

public class TC_RF_001 {

    @Test
    public void verifyRegisteringWithMandatoryFields(){
        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.manage().window().maximize();
        driver.get("https://tutorialsninja.com/demo/");

        driver.findElement(By.xpath("//span[text()='My Account']")).click();
        driver.findElement(By.linkText("Register")).click();

        driver.findElement(By.id("input-firstname")).sendKeys("Arun");
        driver.findElement(By.id("input-lastname")).sendKeys("Motoori");
        driver.findElement(By.id("input-email")).sendKeys(generateNewEmail());
        driver.findElement(By.id("input-telephone")).sendKeys("1234567890");
        driver.findElement(By.id("input-password")).sendKeys("12345");
        driver.findElement(By.id("input-confirm")).sendKeys("12345");
        driver.findElement(By.name("agree")).click();
        driver.findElement(By.xpath("//input[@value='Continue']")).click();

        Assert.assertTrue(driver.findElement(By.linkText("Logout")).isDisplayed());

        String expectedHeading = "Your Account Has Been Created!";

        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='common-success']//h1")).getText(),expectedHeading);

        String actualProperDetailOne = "Congratulations! Your new account has been successfully created!";
        String actualProperDetaiTwo = "You can now take advantage of member privileges to enhance your online shopping experience with us.";
        String actualProperDetaiThree = "If you have ANY questions about the operation of this online shop, please e-mail the store owner.";
        String actualProperDetaiFour = "contact us";

        String expectedProperDetail = driver.findElement(By.id("content")).getText();
        Assert.assertTrue(expectedProperDetail.contains(actualProperDetailOne));
        Assert.assertTrue(expectedProperDetail.contains(actualProperDetaiTwo));
        Assert.assertTrue(expectedProperDetail.contains(actualProperDetaiThree));
        Assert.assertTrue(expectedProperDetail.contains(actualProperDetaiFour));

        driver.findElement(By.xpath("//a[text()='Continue']")).click();

        Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());

        driver.quit();

    }

    public String generateNewEmail(){
        String emailWithTimeStamp = new Date().toString().replaceAll("\\s","").replaceAll("\\:","") + "@gmail.com";
        return emailWithTimeStamp;
    }

}
