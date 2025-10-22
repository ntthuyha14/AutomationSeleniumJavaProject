package Utils;

import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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

}
