package tutorialsninja.register;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class TC_RF_018 {

    @Test
    public void verifyRegisteringAccountFieldHeighWidthAligment(){
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.manage().window().maximize();
        driver.get("https://tutorialsninja.com/demo/");

        driver.findElement(By.xpath("//span[text()='My Account']")).click();
        driver.findElement(By.linkText("Register")).click();

        // Using JavascriptExecutor

        WebElement firstNameInput = driver.findElement(By.cssSelector("#input-firstname"));

        String expectedValueHeight = "34px";
        String expectedValueWidth = "701.25px";

        JavascriptExecutor jse = (JavascriptExecutor) driver;

        String fnHeight = (String)jse.executeScript("return window.getComputedStyle(arguments[0]).getPropertyValue('height');", firstNameInput).toString();
        System.out.println(fnHeight);

        String fnWidth = (String)jse.executeScript("return window.getComputedStyle(arguments[0]).getPropertyValue('width');", firstNameInput);
        System.out.println(fnWidth);

        Assert.assertEquals(fnHeight, expectedValueHeight);
        Assert.assertEquals(fnWidth, expectedValueWidth);

        // Using GetCssValue
        //LastName
        String actualLastNameHeight = driver.findElement(By.id("input-lastname")).getCssValue("height");
        String actualLastNameWidth = driver.findElement(By.id("input-lastname")).getCssValue("width");

        Assert.assertEquals(actualLastNameHeight, expectedValueHeight);
        Assert.assertEquals(actualLastNameWidth, expectedValueWidth);

        //Email
        String actualEmailHeight = driver.findElement(By.id("input-email")).getCssValue("height");
        String actualEmailWidth = driver.findElement(By.id("input-email")).getCssValue("width");

        Assert.assertEquals(actualEmailHeight, expectedValueHeight);
        Assert.assertEquals(actualEmailWidth, expectedValueWidth);

        //Telephone
        String actualTelephoneHeight = driver.findElement(By.id("input-telephone")).getCssValue("height");
        String actualTelephoneWidth = driver.findElement(By.id("input-telephone")).getCssValue("width");

        Assert.assertEquals(actualTelephoneHeight, expectedValueHeight);
        Assert.assertEquals(actualTelephoneWidth, expectedValueWidth);

        //Password
        WebElement actualPassword = driver.findElement(By.cssSelector("#input-password"));

        String actualPasswordHeight= (String)jse.executeScript("return window.getComputedStyle(arguments[0]).getPropertyValue('height');", actualPassword).toString();
        String actualPasswordWidth = (String)jse.executeScript("return window.getComputedStyle(arguments[0]).getPropertyValue('width');", actualPassword);

        Assert.assertEquals(actualPasswordHeight, expectedValueHeight);
        Assert.assertEquals(actualPasswordWidth, expectedValueWidth);

//        //ConfirmPassword
        WebElement actualConfirmPassword = driver.findElement(By.cssSelector("#input-confirm"));


        String  actualConfirmPasswordHeight= (String)jse.executeScript("return window.getComputedStyle(arguments[0]).getPropertyValue('height');", actualConfirmPassword).toString();
        String  actualConfirmPasswordWidth = (String)jse.executeScript("return window.getComputedStyle(arguments[0]).getPropertyValue('width');", actualConfirmPassword);

        Assert.assertEquals(actualConfirmPasswordHeight, expectedValueHeight);
        Assert.assertEquals(actualConfirmPasswordWidth, expectedValueWidth);

        driver.quit();


    }
}
