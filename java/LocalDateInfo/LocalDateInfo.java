import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.TextStyle;
import java.util.Locale;

public class LocalDateInfo {
    // ====================================================
    // Variables
    static ZoneId systemTZ = ZoneId.systemDefault();
    static String TZDisplayName = systemTZ.getDisplayName(TextStyle.FULL, Locale.ENGLISH);
    static LocalDateTime myDateObj = LocalDateTime.now();
    static ZoneOffset offset = systemTZ.getRules().getOffset(myDateObj);

    // ====================================================
    // Methods - LocalDateTime
    static void printLocalDateTime(ZoneId systemTZ, String TZDisplayName, LocalDateTime myDateObj, ZoneOffset offset) {
        String description = addPaddingRight("System date and time", 40);
        System.out.println("\n" + description + myDateObj);

        description = addPaddingRight("  ZoneId.systemDefault()", 40);
        System.out.println(description + systemTZ);

        description = addPaddingRight("  ZoneId.getDisplayName()", 40);
        System.out.println(description + TZDisplayName);

        description = addPaddingRight("  System timezone offset", 40);
        System.out.println(description + offset + "\n");
    }

    // ====================================================
    // Methods - Utility
    static String addPaddingRight(String myDescription, int stringLength) {
        String paddedString = myDescription + " ";
        int currentStringLenth = paddedString.length();

        while ( currentStringLenth < stringLength ) {
            paddedString = paddedString + ".";
            currentStringLenth = paddedString.length();
        }
        return paddedString + ": ";
    }

    // ====================================================
    // Main
    public static void main(String[] args) {
        printLocalDateTime(systemTZ, TZDisplayName, myDateObj, offset);
    }

}