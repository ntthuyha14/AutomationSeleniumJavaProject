package tutorialsninja.register;

import Utils.CommonUtilsEmail;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TC_RF_014 {

    @Test
    public void verifyMandatoryFieldsSymbolAndColorInRegister(){
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.get("https://tutorialsninja.com/demo/");

        driver.findElement(By.xpath("//span[text()='My Account']")).click();
        driver.findElement(By.linkText("Register")).click();

        WebElement firstNameLabel = driver.findElement(By.cssSelector("label[for='input-firstname']"));

        String expectedContent = "\"* \"";
        String expectedColor ="rgb(255, 0, 0)";

        JavascriptExecutor jse = (JavascriptExecutor)driver;

        //FirstName
        String fnContent = (String)jse.executeScript("return window.getComputedStyle(arguments[0], '::before').getPropertyValue('content');", firstNameLabel);
        System.out.println(fnContent);

        String fnColor = (String)jse.executeScript("return window.getComputedStyle(arguments[0], '::before').getPropertyValue('color');", firstNameLabel);
        System.out.println(fnColor);

        Assert.assertEquals(fnContent, expectedContent);
        Assert.assertEquals(fnColor, expectedColor);

        //LastName
        WebElement lastNameLabel = driver.findElement(By.cssSelector("label[for='input-lastname']"));

        String lnContent = (String)jse.executeScript("return window.getComputedStyle(arguments[0], '::before').getPropertyValue('content');", lastNameLabel);
        System.out.println(lnContent);
        String lnColor = (String)jse.executeScript("return window.getComputedStyle(arguments[0], '::before').getPropertyValue('color');", lastNameLabel);
        System.out.println(lnColor);

        Assert.assertEquals(lnContent, expectedContent);
        Assert.assertEquals(lnColor, expectedColor);

        //Email
        WebElement emailLabel = driver.findElement(By.cssSelector("label[for='input-email']"));

        String emailContent = (String)jse.executeScript("return window.getComputedStyle(arguments[0], '::before').getPropertyValue('content');", emailLabel);
        System.out.println(emailContent);
        String emailColor = (String)jse.executeScript("return window.getComputedStyle(arguments[0], '::before').getPropertyValue('color');", emailLabel);
        System.out.println(emailColor);

        Assert.assertEquals(emailContent, expectedContent);
        Assert.assertEquals(emailColor, expectedColor);

        //TelePhone
        WebElement telephoneLabel = driver.findElement(By.cssSelector("label[for='input-telephone']"));

        String telephoneContent = (String)jse.executeScript("return window.getComputedStyle(arguments[0], '::before').getPropertyValue('content');", telephoneLabel);
        System.out.println(telephoneContent);
        String telephoneColor = (String)jse.executeScript("return window.getComputedStyle(arguments[0], '::before').getPropertyValue('color');", telephoneLabel);
        System.out.println(telephoneColor);

        Assert.assertEquals(telephoneContent, expectedContent);
        Assert.assertEquals(telephoneColor, expectedColor);

        //Password
        WebElement passwordLabel = driver.findElement(By.cssSelector("label[for='input-password']"));

        String passwordContent = (String)jse.executeScript("return window.getComputedStyle(arguments[0], '::before').getPropertyValue('content');", passwordLabel);
        System.out.println(passwordContent);
        String passwordColor = (String)jse.executeScript("return window.getComputedStyle(arguments[0], '::before').getPropertyValue('color');", passwordLabel);
        System.out.println(passwordColor);

        Assert.assertEquals(passwordContent, expectedContent);
        Assert.assertEquals(passwordColor, expectedColor);

        //Confirm Password
        WebElement confirmPasswordLabel = driver.findElement(By.cssSelector("label[for='input-confirm']"));

        String confirmPasswordContent = (String)jse.executeScript("return window.getComputedStyle(arguments[0], '::before').getPropertyValue('content');", confirmPasswordLabel);
        System.out.println(confirmPasswordContent);
        String confirmPasswordColor = (String)jse.executeScript("return window.getComputedStyle(arguments[0], '::before').getPropertyValue('color');", confirmPasswordLabel);
        System.out.println(confirmPasswordColor);

        Assert.assertEquals(confirmPasswordContent, expectedContent);
        Assert.assertEquals(confirmPasswordColor, expectedColor);

        driver.quit();
    }
}
