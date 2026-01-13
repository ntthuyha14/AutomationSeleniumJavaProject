package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import Utils.CommonUtils;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MyListeners implements ITestListener {
    ExtentReports extentReports;
    ExtentTest extentTest;
    WebDriver driver;
    @Override
    public void onStart(ITestContext context) {
        extentReports = CommonUtils.getExtentReport();
    }

    @Override
    public void onTestStart(ITestResult result) {
        extentTest= extentReports.createTest(result.getName());
        extentTest.log(Status.INFO, result.getName() + "test excution started");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTest.log(Status.PASS, result.getName() + "test got passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentTest.log(Status.FAIL, result.getName() + "test got failed");
        try {
//            driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstanceName());
            driver = (WebDriver) result.getTestClass().getRealClass()
                    .getDeclaredField("driver")
                    .get(result.getInstance());

            if (driver != null) {
                String screenshotPath = CommonUtils.takeScreenshotAndReturnPath(driver, result.getName());
                extentTest.addScreenCaptureFromPath(screenshotPath);
            } else {
                System.out.println("Không thể chụp ảnh vì Driver đang bị NULL.");
            }
        } catch (Exception e) {
            System.err.println("Lỗi xảy ra trong Listener: " + e.getMessage());
            e.printStackTrace();
        }

//        String screenshotPath = CommonUtils.takeScreenshotAndReturnPath(driver,"\\Screenshots\\" +result.getName() + ".png");
//        extentTest.addScreenCaptureFromPath(screenshotPath);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.log(Status.SKIP, result.getName() + "test got skipped");

    }

    @Override
    public void onFinish(ITestContext context) {
        extentReports.flush();
        File extentReportFile = new File(System.getProperty("user.dir") + "\\reports\\TNExtentReport.html");
        try {
            Desktop.getDesktop().browse(extentReportFile.toURI());
        } catch (IOException e) {
          e.printStackTrace();
        }
    }


}
