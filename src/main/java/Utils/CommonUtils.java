package Utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

import javax.imageio.ImageIO;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.internet.MimeMultipart;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class CommonUtils {
    public static boolean compareTwoScreenshots(String actualImagePath, String expectedImagePath) {

        BufferedImage actualBImg;
        BufferedImage expectedBImg;

        try {
            actualBImg = ImageIO.read(new File(actualImagePath));
            expectedBImg = ImageIO.read(new File(expectedImagePath));
        } catch (IOException e) {
            throw new RuntimeException("Cannot read image file", e);
        }

        ImageDiffer imgDiffer = new ImageDiffer();
        ImageDiff diff = imgDiffer.makeDiff(expectedBImg, actualBImg);

        return diff.hasDiff();
    }


    public static Properties loadProperties(){
        Properties  prop = new Properties();
        try {
            FileReader fr = new FileReader(System.getProperty("user.dir") + ("\\src\\test\\resources\\projectdata.properties"));
            prop.load(fr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
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

    public static WebDriver takeScreenshot(WebDriver driver, String pathToBCopied) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File srcScreenshot = ts.getScreenshotAs(OutputType.FILE);
        try {
            FileHandler.copy(srcScreenshot, new File(System.getProperty("user.dir") + pathToBCopied));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return driver;
    }

}
