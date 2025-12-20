package tutorialsninja.register;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class TC_RF_18_Test {

    private WebDriver driver;

    // ====== Locators dùng chung ======
    private final By FIRSTNAME = By.id("input-firstname");
    private final By LASTNAME  = By.id("input-lastname");
    private final By EMAIL     = By.id("input-email");
    private final By TEL       = By.id("input-telephone");
    private final By PASS      = By.id("input-password");
    private final By CONFIRM   = By.id("input-confirm");
    private final By BTN_CONT  = By.xpath("//input[@value='Continue']");

    private final By FN_ERR  = By.xpath("//input[@id='input-firstname']/following-sibling::div");
    private final By LN_ERR  = By.xpath("//input[@id='input-lastname']/following-sibling::div");
    private final By TEL_ERR = By.xpath("//input[@id='input-telephone']/following-sibling::div");
    private final By PW_ERR  = By.xpath("//input[@id='input-password']/following-sibling::div");

    // ====== Messages ======
    private static final String MSG_FN  = "First Name must be between 1 and 32 characters!";
    private static final String MSG_LN  = "Last Name must be between 1 and 32 characters!";
    private static final String MSG_TEL = "Telephone must be between 3 and 32 characters!";
    private static final String MSG_PW  = "Password must be between 4 and 20 characters!";

    // ====== Expected size ======
    private static final String H = "34px";
    private static final String W = "701.25px";

    @BeforeMethod
    public void openRegister() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        // Tránh implicit wait dài gây khó kiểm soát; dùng locate lại + pause
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));

        driver.get("https://tutorialsninja.com/demo");
        driver.findElement(By.xpath("//span[text()='My Account']")).click();
        driver.findElement(By.linkText("Register")).click();
    }

    @AfterMethod
    public void quit(ITestResult result) {
        // Dừng 3s cho bạn quan sát trước khi đóng
        pause(3);
        if (driver != null) driver.quit();
    }

    // ===== Helpers =====
    private void pause(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private boolean isShown(By by) {
        var els = driver.findElements(by);
        if (els.isEmpty()) return false;
        try { return els.get(0).isDisplayed(); }
        catch (StaleElementReferenceException e) { return false; }
    }

    private String textOf(By by) {
        var els = driver.findElements(by);
        return els.isEmpty() ? "" : els.get(0).getText();
    }

    private void clickContinue() {
        driver.findElement(BTN_CONT).click();
    }

    private void assertSize(By by, String expectedH, String expectedW) {
        WebElement f = driver.findElement(by);
        Assert.assertEquals(f.getCssValue("height"), expectedH);
        Assert.assertEquals(f.getCssValue("width"),  expectedW);
    }

    private void type(By by, String value) {
        WebElement el = driver.findElement(by);
        el.clear();
        el.sendKeys(value);
    }

    // =========================
    //        SIZE TESTS
    // =========================
    @Test public void size_firstname() { assertSize(FIRSTNAME, H, W); pause(3); }
    @Test public void size_lastname()  { assertSize(LASTNAME,  H, W); pause(3); }
    @Test public void size_email()     { assertSize(EMAIL,     H, W); pause(3); }
    @Test public void size_telephone() { assertSize(TEL,       H, W); pause(3); }
    @Test public void size_password()  { assertSize(PASS,      H, W); pause(3); }
    @Test public void size_confirm()   { assertSize(CONFIRM,   H, W); pause(3); }

    // =========================
    //   FIRSTNAME VALIDATION
    // =========================
    @Test
    public void firstname_invalid_empty() {
        type(FIRSTNAME, "");
        clickContinue();
        Assert.assertEquals(textOf(FN_ERR), MSG_FN);
        pause(3);
    }

    @Test
    public void firstname_valid_short() {
        type(FIRSTNAME, "ab"); // hợp lệ
        clickContinue();
        Assert.assertFalse(isShown(FN_ERR));
        pause(3);
    }

    @Test
    public void firstname_invalid_33chars() {
        type(FIRSTNAME, "abcdefghijklmnopabcdefghijklmnopq"); // 33
        clickContinue();
        Assert.assertEquals(textOf(FN_ERR), MSG_FN);
        pause(3);
    }

    // =========================
    //   LASTNAME VALIDATION
    // =========================
    @Test
    public void lastname_invalid_empty() {
        type(LASTNAME, "");
        clickContinue();
        Assert.assertEquals(textOf(LN_ERR), MSG_LN);
        pause(3);
    }

    @Test
    public void lastname_valid_short() {
        type(LASTNAME, "ab"); // hợp lệ
        clickContinue();
        Assert.assertFalse(isShown(LN_ERR));
        pause(3);
    }

    @Test
    public void lastname_invalid_33chars() {
        type(LASTNAME, "abcdefghijklmnopabcdefghijklmnopq"); // 33
        clickContinue();
        Assert.assertEquals(textOf(LN_ERR), MSG_LN);
        pause(3);
    }

    // =========================
    //   TELEPHONE VALIDATION
    // =========================
    @Test
    public void telephone_invalid_short() {
        type(TEL, "a"); // < 3
        clickContinue();
        Assert.assertEquals(textOf(TEL_ERR), MSG_TEL);
        pause(3);
    }

    @Test
    public void telephone_valid_mid() {
        type(TEL, "abcdefghijklmnop"); // 16 (<=32)
        clickContinue();
        Assert.assertFalse(isShown(TEL_ERR));
        pause(3);
    }

    @Test
    public void telephone_invalid_over32() {
        type(TEL, "abcdefghijklmnopabcdefghijklmnopq"); // 33
        clickContinue();
        Assert.assertEquals(textOf(TEL_ERR), MSG_TEL);
        pause(3);
    }

    // =========================
    //   PASSWORD VALIDATION
    // =========================
    @Test
    public void password_invalid_short() {
        type(PASS, "abc"); // < 4
        clickContinue();
        Assert.assertEquals(textOf(PW_ERR), MSG_PW);
        pause(3);
    }

    @Test
    public void password_valid_10() {
        type(PASS, "abcdefghij"); // 10 (<=20)
        clickContinue();
        Assert.assertFalse(isShown(PW_ERR));
        pause(3);
    }

    @Test
    public void password_invalid_21() {
        type(PASS, "abcdefghijabcdefghijk"); // 21
        clickContinue();
        Assert.assertEquals(textOf(PW_ERR), MSG_PW);
        pause(3);
    }

    // =========================
    //  (Tuỳ chọn) Screenshot alignment
    // =========================
    @Test(enabled = false)
    public void register_page_alignment_matches_baseline() throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
        String actual = System.getProperty("user.dir") + "\\Screenshots\\registerPageActualAligment.png";
        FileHandler.copy(src, new File(actual));

        String expected = System.getProperty("user.dir") + "\\Screenshots\\registerPageExpectedAligment.png";

        BufferedImage actualImg   = ImageIO.read(new File(actual));
        BufferedImage expectedImg = ImageIO.read(new File(expected));

        ru.yandex.qatools.ashot.comparison.ImageDiffer differ =
                new ru.yandex.qatools.ashot.comparison.ImageDiffer();
        ru.yandex.qatools.ashot.comparison.ImageDiff diff =
                differ.makeDiff(expectedImg, actualImg);

        Assert.assertFalse(diff.hasDiff(), "Screenshots are different from baseline!");
        pause(3);
    }
}
