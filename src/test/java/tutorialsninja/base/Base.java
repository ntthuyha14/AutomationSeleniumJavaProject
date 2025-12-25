package tutorialsninja.base;

import Utils.CommonUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;
import java.util.Properties;

public class Base {

    WebDriver driver;
    Properties prop;

    public WebDriver openBrowserAndApplication(){
         prop = CommonUtils.loadProperties();
        String browserName = prop.getProperty("browserName");
        if (browserName.equals("chrome")){
            driver = new ChromeDriver();
        } else if (browserName.equals("firefox")){
            driver = new FirefoxDriver();
        } else if (browserName.equals("safari")){
            driver = new SafariDriver();
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.manage().window().maximize();

        driver.get(prop.getProperty("appURL"));

        return driver;
    }

    public WebDriver openBrowserAndApplicationAmazon(){
         prop = CommonUtils.loadProperties();
        String browserName = prop.getProperty("browserName");
        if (browserName.equals("chrome")){
            driver = new ChromeDriver();
        } else if (browserName.equals("firefox")){
            driver = new FirefoxDriver();
        } else if (browserName.equals("safari")){
            driver = new SafariDriver();
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.manage().window().maximize();

        driver.get(prop.getProperty(""));

        return driver;
    }

    public WebDriver navigateToRegisterPage(WebDriver driver, String URL){
        driver.navigate().to(URL);
        return driver;
    }

    public WebDriver navigateBack (WebDriver driver){
        driver.navigate().back();
        return driver;
    }
    
    public WebDriver presskeyMultipleTimes(WebDriver driver, Keys keyName, int count){
        Actions actions = new Actions(driver);
        for (int i = 1; i<=count; i++){
            actions.sendKeys(keyName).perform();
        }
        return driver;
    }
    
    public WebDriver enterDetailsIntoRegisterAccountPageFieldS(){
        prop = CommonUtils.loadProperties();
        Actions actions = new Actions(driver);
        actions.sendKeys(prop.getProperty("firstName")).pause(Duration.ofSeconds(1))
                .sendKeys(Keys.TAB).pause(Duration.ofSeconds(1))
                .sendKeys(prop.getProperty("lastName")).pause(Duration.ofSeconds(1))
                .sendKeys(Keys.TAB).pause(Duration.ofSeconds(1))
                .sendKeys(CommonUtils.generateBrandNewEmail()).pause(Duration.ofSeconds(1))
                .sendKeys(Keys.TAB).pause(Duration.ofSeconds(1))
                .sendKeys(prop.getProperty("phoneNumber")).pause(Duration.ofSeconds(1))
                .sendKeys(Keys.TAB).pause(Duration.ofSeconds(1))
                .sendKeys(prop.getProperty("passWord")).pause(Duration.ofSeconds(1))
                .sendKeys(Keys.TAB).pause(Duration.ofSeconds(1))
                .sendKeys(prop.getProperty("confirmPassword")).pause(Duration.ofSeconds(1))
                //mặc định là Option No nên cần Keys.Left để chọn Yes
                .sendKeys(Keys.TAB).pause(Duration.ofSeconds(1))
                .sendKeys(Keys.LEFT).pause(Duration.ofSeconds(1))
                //Keys.TAB: Privacy Policy
                .sendKeys(Keys.TAB).pause(Duration.ofSeconds(1))
                //Keys.TAB: Check Box của Privacy Policy
                .sendKeys(Keys.TAB).pause(Duration.ofSeconds(1))
                //Keys.SPACE: select vào CheckBox
                .sendKeys(Keys.SPACE).pause(Duration.ofSeconds(1))
                //Keys.TAB và Keys.ENTER : button Continue
                .sendKeys(Keys.TAB).pause(Duration.ofSeconds(1))
                .sendKeys(Keys.ENTER).build().perform();
        return driver;
    }
    
    public WebDriver enterDetailsIntoLoginAccountPageFields(){
        prop = CommonUtils.loadProperties();
        Actions actions = new Actions(driver);
        actions.sendKeys(prop.getProperty("emailLogin")).pause(Duration.ofSeconds(1))
                .sendKeys(Keys.TAB).pause(Duration.ofSeconds(1))
                .sendKeys(prop.getProperty("passwordLogin")).pause(Duration.ofSeconds(1))
                .sendKeys(Keys.TAB)
                .sendKeys(Keys.TAB).pause(Duration.ofSeconds(1))
                .sendKeys(Keys.ENTER).build().perform();
        return driver;
    }
}
