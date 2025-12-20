package Utils;

import java.util.Date;

public class CommonUtilsEmail {
    public static String generateBrandNewEmail(){
        Date date = new Date();
        String dateString = date.toString();
        String dateStringWithoutSpaces = dateString.replaceAll("\\s", "");
        String dateStringWithoutSpacesAndColons = dateStringWithoutSpaces.replaceAll("\\:", "");
        String brandNewEmail = dateStringWithoutSpacesAndColons + "@gmail.com";
        return brandNewEmail;
    }
}
