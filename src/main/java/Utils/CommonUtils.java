package Utils;

import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class CommonUtils {
    public static boolean compareTwoScreenshots(String actualImagePath, String expectedImagePath) throws IOException {
        BufferedImage acutualBImg = null;
        BufferedImage expectedBImg = null;
        try {
            acutualBImg = ImageIO.read(new File(System.getProperty("user.dir")+actualImagePath));
            expectedBImg = ImageIO.read(new File(System.getProperty("user.dir")+expectedImagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        ImageDiffer imgDiffer = new ImageDiffer();
        ImageDiff imgDifference = imgDiffer.makeDiff(expectedBImg, acutualBImg);

        return imgDifference.hasDiff();
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

}
