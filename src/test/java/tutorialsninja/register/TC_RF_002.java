package tutorialsninja.register;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.FlagTerm;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

public class TC_RF_002 {

    @Test
    public void verifyConfirmationEmail() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.get("https://www.amazon.in/");

        // Kiểm tra xem có button "Continue shopping" hay không
        List<WebElement> continueBtns = driver.findElements(By.xpath("//button[contains(text(),'Continue shopping')]"));

        if (!continueBtns.isEmpty()) {
            // Nếu có thì click vào
            continueBtns.get(0).click();
        }

// Sau đó thực hiện Sign in
        driver.findElement(By.xpath("//span[text()='Hello, sign in']")).click();


        String email = "thuyhainner@gmail.com";
        String appPassCode = "fswf ctdl qpzd xgra";
        String link = null;

        driver.findElement(By.id("ap_email_login")).sendKeys("thuyhainner@gmail.com");
        driver.findElement(By.id("continue")).click();
        driver.findElement(By.id("auth-fpp-link-bottom")).click();
        driver.findElement(By.id("continue")).click();

        System.out.println("Halting the program intentionally for 10 seconds");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        // Gmail IMAP settings
        String host = "imap.gmail.com";
        String port = "993";
        String username = email;
        String password = appPassCode;  // Not recommended, use OAuth2 for better security
        String expectedSubject = "amazon.in: Password recovery";
        String expectedFromEmail = "\"amazon.in\" <account-update@amazon.in>";
        String expectedBodyEmail = "Someone is attempting to reset the password of your account.";

        try {
            // Mail server connection properties
            Properties properties = new Properties();
            properties.put("mail.store.protocol", "imaps");
            properties.put("mail.imap.host", host);
            properties.put("mail.imap.port", port);
            properties.put("mail.imap.ssl.enable", "true");

            // Connect to the mail server
            Session emailSession = Session.getDefaultInstance(properties);
            Store store = emailSession.getStore("imaps");
            store.connect("imap.gmail.com", username, password); // replace email password with App password

            // Open the inbox folder
            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);

            // Search for unread emails
            Message[] messages = inbox.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));

            boolean found = false;
            for (int i = messages.length - 1; i >= 0; i--) {

                Message message = messages[i];

                if (message.getSubject().contains(expectedSubject)) {
                    found = true;
                    Assert.assertEquals(message.getSubject(), expectedSubject);
                    Assert.assertEquals(message.getFrom()[0].toString(), expectedFromEmail);
                    String ActualEmailBody = getTextFromMessage(message);
                    Assert.assertTrue(ActualEmailBody.contains(expectedBodyEmail));

                    String[] ar = ActualEmailBody.split("it: <a href=\"");
                    String linkPart = ar[1];
                    String[] arr = linkPart.split("\" rel=\"nofollow\">");
                    link = arr[0].trim();

                    break;
                }
            }

            if (!found) {
                System.out.println("No confirmation email found.");
            }

            // Close the store and folder objects
            inbox.close(false);
            store.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        driver.navigate().to(link);
        Assert.assertTrue(driver.findElement(By.name("customerResponseDenyButton")).isDisplayed());

        driver.quit();

    }

    public static String getTextFromMessage(Message message) throws Exception {
        String result = "";
        if (message.isMimeType("text/plain")) {
            result = message.getContent().toString();
        } else if (message.isMimeType("text/html")) {
            result = message.getContent().toString();
        } else if (message.isMimeType("multipart/*")) {
            MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
            result = getTextFromMimeMultipart(mimeMultipart);
        }
        return result;
    }

    public static String getTextFromMimeMultipart(MimeMultipart mimeMultipart) throws Exception {
        StringBuilder result = new StringBuilder();
        int count = mimeMultipart.getCount();
        for (int i = 0; i < count; i++) {
            BodyPart bodyPart = mimeMultipart.getBodyPart(i);
            if (bodyPart.isMimeType("text/plain")) {
                result.append(bodyPart.getContent());
            } else if (bodyPart.isMimeType("text/html")) {
                result.append(bodyPart.getContent());
            } else if (bodyPart.getContent() instanceof MimeMultipart) {
                result.append(getTextFromMimeMultipart((MimeMultipart) bodyPart.getContent()));
            }
        }
        return result.toString();
    }


}
