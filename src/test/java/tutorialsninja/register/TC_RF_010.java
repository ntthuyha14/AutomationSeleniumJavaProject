package tutorialsninja.register;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.io.FileHandler;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;


public class TC_RF_010 {
    @Test
    public void verifyRegiterUsingInvalidEmailAddress() throws InterruptedException, IOException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.get("https://tutorialsninja.com/demo/");

        driver.findElement(By.xpath("//span[text()='My Account']")).click();
        driver.findElement(By.linkText("Register")).click();

        driver.findElement(By.id("input-firstname")).sendKeys("Arun");
        driver.findElement(By.id("input-lastname")).sendKeys("Motoori");
        driver.findElement(By.id("input-email")).sendKeys("amotoori1");
        driver.findElement(By.id("input-telephone")).sendKeys("1234567890");
        driver.findElement(By.id("input-password")).sendKeys("12345");
        driver.findElement(By.id("input-confirm")).sendKeys("12345");
        driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).isSelected();
        driver.findElement(By.xpath("//input[@name='agree']")).click();
        driver.findElement(By.xpath("//input[@value='Continue']")).click();

        Thread.sleep(3000);

        //Case: Email = amotoori1
        File srcScreenshot1 = driver.findElement(By.xpath("//form[@class='form-horizontal']")).getScreenshotAs(OutputType.FILE);
        FileHandler.copy(srcScreenshot1, new File(System.getProperty("user.dir") + "\\Screenshots\\sc1Actual.png"));

        //Luu y hinh chup phai cung man hinh, cung kich co de so sanh
        BufferedImage actualBImg = ImageIO.read(new File(System.getProperty("user.dir") + "\\Screenshots\\sc1Actual.png"));
        BufferedImage expectedBImg = ImageIO.read(new File(System.getProperty("user.dir") + "\\Screenshots\\sc1Expected.png"));

        ImageDiffer imgDiffer1 = new ImageDiffer();
        ImageDiff imgDifference = imgDiffer1.makeDiff(expectedBImg, actualBImg);

        Assert.assertFalse(imgDifference.hasDiff());

        //Case: Email = amotoori1@
        driver.findElement(By.id("input-email")).clear();
        driver.findElement(By.id("input-email")).sendKeys("amotoori1@");
        driver.findElement(By.xpath("//input[@value='Continue']")).click();

        Thread.sleep(3000);

        File srcScreenshot2 = driver.findElement(By.xpath("//form[@class='form-horizontal']")).getScreenshotAs(OutputType.FILE);
        FileHandler.copy(srcScreenshot2, new File(System.getProperty("user.dir") + "\\Screenshots\\sc2Actual.png"));

        BufferedImage actualBImg2 = ImageIO.read(new File(System.getProperty("user.dir") + "\\Screenshots\\sc2Expected.png"));
        BufferedImage expectedBImg2 = ImageIO.read(new File(System.getProperty("user.dir")+ "\\Screenshots\\sc2Expected.png"));

        ImageDiffer imageDiffer2 = new ImageDiffer();
        ImageDiff imgDifference2 = imageDiffer2.makeDiff(expectedBImg2,actualBImg2);

        Assert.assertFalse(imgDifference.hasDiff());

        //Case Email = amotoori1@gmail

        driver.findElement(By.id("input-email")).clear();
        driver.findElement(By.id("input-email")).sendKeys("amotoori1@gmail");
        driver.findElement(By.xpath("//input[@value='Continue']")).click();

        String expectedEmailWarning = "E-Mail Address does not appear to be valid!";

        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='input-email']/following-sibling::div")).getText(), expectedEmailWarning);

        //Case: Email = amotoori1@gmail.
        driver.findElement(By.id("input-email")).clear();
        driver.findElement(By.id("input-email")).sendKeys("amotoori1@gmail.");
        driver.findElement(By.xpath("//input[@value='Continue']")).click();

        Thread.sleep(3000);

        File srcScreenshot3 = driver.findElement(By.xpath("//form[@class='form-horizontal']")).getScreenshotAs(OutputType.FILE);
        FileHandler.copy(srcScreenshot3, new File(System.getProperty("user.dir") + "\\Screenshots\\sc3Actual.png"));

        BufferedImage actualBImg3 = ImageIO.read(new File(System.getProperty("user.dir") + "\\Screenshots\\sc3Expected.png"));
        BufferedImage expectedBImg3 = ImageIO.read(new File(System.getProperty("user.dir")+ "\\Screenshots\\sc3Expected.png"));

        ImageDiffer imageDiffer3 = new ImageDiffer();
        ImageDiff imgDifference3 = imageDiffer3.makeDiff(expectedBImg3,actualBImg3);

        Assert.assertFalse(imgDifference3.hasDiff());

        driver.quit();


    }

}

