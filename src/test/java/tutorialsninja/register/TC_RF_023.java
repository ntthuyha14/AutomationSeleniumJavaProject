package tutorialsninja.register;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.openqa.selenium.By.*;

public class TC_RF_023 {
    @Test
    public void verifyNavigatingToOtherPages(){
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get("https://tutorialsninja.com/demo/");

        driver.findElement(xpath("//span[text()='My Account']")).click();
        driver.findElement(linkText("Register")).click();

        driver.findElement(xpath("//a/i[@class='fa fa-phone']")).click();
        Assert.assertTrue(driver.findElement(xpath("//ul[@class='breadcrumb']//a[text()='Contact Us']")).isDisplayed());
        driver.navigate().back();

        driver.findElement(xpath("//a[@id='wishlist-total']")).click();
        Assert.assertTrue(driver.findElement(xpath("//ul[@class='breadcrumb']//a[text()='Login']")).isDisplayed());
        driver.navigate().back();

        driver.findElement(xpath("//a/i[@class='fa fa-shopping-cart']")).click();
        Assert.assertTrue(driver.findElement(xpath("//ul[@class='breadcrumb']//a[text()='Shopping Cart']")).isDisplayed());
        driver.navigate().back();

        driver.findElement(xpath("//a/i[@class='fa fa-share']")).click();
        Assert.assertTrue(driver.findElement(xpath("//ul[@class='breadcrumb']//a[text()='Shopping Cart']")).isDisplayed());
        driver.navigate().back();

        driver.findElement(linkText("Qafox.com")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://tutorialsninja.com/demo/index.php?route=common/home");
        driver.navigate().back();



        driver.findElement(xpath("//button[@class='btn btn-default btn-lg']")).click();
        Assert.assertTrue(driver.findElement(xpath("//ul[@class='breadcrumb']//a[text()='Search']")).isDisplayed());
        driver.navigate().back();


        driver.findElement(xpath("//ul[@class='breadcrumb']//a[text()='Register']")).click();
        Assert.assertTrue(driver.findElement(xpath("//ul[@class='breadcrumb']//a[text()='Register']")).isDisplayed());

        driver.findElement(xpath("//ul[@class='breadcrumb']//a[text()='Account']")).click();
        Assert.assertTrue(driver.findElement(xpath("//ul[@class='breadcrumb']//a[text()='Login']")).isDisplayed());
        driver.navigate().back();

        driver.findElement(By.xpath("//a[@href='https://tutorialsninja.com/demo/index.php?route=common/home']")).click();
        Assert.assertEquals(driver.getCurrentUrl(),"https://tutorialsninja.com/demo/index.php?route=common/home");
        driver.navigate().back();

        driver.findElement(By.linkText("login page")).click();
        Assert.assertTrue(driver.findElement(xpath("//ul[@class='breadcrumb']//a[text()='Login']")).isDisplayed());
        driver.navigate().back();

        driver.findElement(By.xpath("//a[@class='agree']/b[text()='Privacy Policy']")).click();
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        WebElement xOption = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='×']")));
        Assert.assertTrue(xOption.isDisplayed());
        xOption.click();

        driver.findElement(By.xpath("//div[@class='list-group']//a[text()='Login']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Login']")).isDisplayed());
        driver.navigate().back();

        driver.findElement(By.xpath("//div[@class='list-group']//a[text()='Register']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Register']")).isDisplayed());


        driver.findElement(By.xpath("//div[@class='list-group']//a[text()='Forgotten Password']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Forgotten Password']")).isDisplayed());
        driver.navigate().back();

        driver.findElement(By.xpath("//div[@class='list-group']//a[text()='My Account']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Login']")).isDisplayed());
        driver.navigate().back();

        driver.findElement(By.xpath("//div[@class='list-group']//a[text()='Address Book']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Login']")).isDisplayed());
        driver.navigate().back();

        driver.findElement(By.xpath("//div[@class='list-group']//a[text()='Wish List']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Login']")).isDisplayed());
        driver.navigate().back();

        driver.findElement(By.xpath("//div[@class='list-group']//a[text()='Order History']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Login']")).isDisplayed());
        driver.navigate().back();

        driver.findElement(By.xpath("//div[@class='list-group']//a[text()='Downloads']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Login']")).isDisplayed());
        driver.navigate().back();

        driver.findElement(By.xpath("//div[@class='list-group']//a[text()='Recurring payments']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Login']")).isDisplayed());
        driver.navigate().back();

        driver.findElement(By.xpath("//div[@class='list-group']//a[text()='Recurring payments']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='Login']")).isDisplayed());
        driver.navigate().back();

        driver.findElement(By.xpath("//ul[@class='list-unstyled']//a[text()='About Us']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumb']//a[text()='About Us']")).isDisplayed());
        driver.navigate().back();

        driver.quit();


    }
}
