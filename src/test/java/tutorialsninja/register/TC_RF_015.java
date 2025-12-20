package tutorialsninja.register;

import Utils.CommonUtilsEmail;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.sql.*;
import java.time.Duration;
import java.util.Locale;

import static com.mysql.cj.conf.PropertyKey.PASSWORD;

public class TC_RF_015 {
    WebDriver driver;

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    private CommonUtilsEmail CommonUtils;
    String url = "jdbc:mysql://localhost:3306/opencart_db";
    String user = "root";
    String password = null;

    @Test
    public void verifyDataTestingOfRegisteringAccount() {

        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        driver.manage().window().maximize();
        driver.get("http://localhost/opencart/");

        driver.findElement(By.xpath("//span[text()='My Account']")).click();
        driver.findElement(By.linkText("Register")).click();

        String firstNameInputData = "Arun";
        driver.findElement(By.id("input-firstname")).sendKeys(firstNameInputData);

        String lastNameInputData = "Motoori";
        driver.findElement(By.id("input-lastname")).sendKeys(lastNameInputData);

        String emailInputData = CommonUtilsEmail.generateBrandNewEmail().toLowerCase(Locale.ROOT);
        driver.findElement(By.id("input-email")).sendKeys(emailInputData);

        String passwordInputData = "123456";
        driver.findElement(By.id("input-password")).sendKeys(passwordInputData);

        driver.findElement(By.id("input-newsletter")).click();

        driver.findElement(By.name("agree")).click();

        driver.findElement(By.xpath("//button[text()='Continue']")).click();

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        String firstNameStoredInDatabase = null;
        String lastNameStoredInDatabase = null;
        String emailStoredInDatabase = null;


        try {
            //Step1: Establish the connection
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the database!");

            //Step2: Create a statement
            statement = connection.createStatement();

            //Step3: Execute a query
            String sql = "SELECT * FROM oc_customer";
            resultSet = statement.executeQuery(sql);


            //Step4: Process the result set
            while (resultSet.next()) {
                firstNameStoredInDatabase = resultSet.getString("firstname");
                lastNameStoredInDatabase = resultSet.getString("lastname");
                emailStoredInDatabase = resultSet.getString("email");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //Clean up the resources
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) resultSet.close();
                if (connection != null) resultSet.close();
            } catch (SQLException e) {

            }
        }


//        Assert.assertEquals(firstNameStoredInDatabase, firstNameInputData);
//        Assert.assertEquals(lastNameStoredInDatabase, lastNameInputData);
        Assert.assertEquals(emailStoredInDatabase, emailInputData);

    }


}
