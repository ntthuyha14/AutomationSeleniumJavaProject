package Utils;

import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CommonUtils {
    public static boolean compareTwoScreenshots(String actualImagePath, String expectedImagePath) throws IOException {
        // Đọc 2 ảnh từ đường dẫn
        BufferedImage actualImg   = ImageIO.read(new File(actualImagePath));
        BufferedImage expectedImg = ImageIO.read(new File(expectedImagePath));

        // So sánh ảnh bằng ImageDiffer
        ImageDiffer imageDiffer = new ImageDiffer();
        ImageDiff diff = imageDiffer.makeDiff(expectedImg, actualImg);

        // Trả về true nếu có sự khác biệt
        return diff.hasDiff();
    }

}
