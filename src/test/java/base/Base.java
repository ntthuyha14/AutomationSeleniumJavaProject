package base;

import Utils.CommonUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;
import java.util.Properties;

public class Base {

    WebDriver driver;

    public WebDriver openBrowserAndApplication(){
        Properties prop = CommonUtils.loadProperties();
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

    public String getPageURL(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    public String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }
}
