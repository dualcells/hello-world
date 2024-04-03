import java.time.ZonedDateTime;
import java.time.ZoneOffset;

public class ZonedDateInfo {
    // ====================================================
    // Variables
    static ZonedDateTime currentDateTime = ZonedDateTime.now();
    static ZonedDateTime ztd_utc = currentDateTime.withZoneSameInstant(ZoneOffset.UTC);

    // ====================================================
    // Methods - ZonedDateTime
    static void printInfo(ZonedDateTime currentDateTime) {
        String description = addPaddingRight("System date and time", 40);
        System.out.println("\n" + description + currentDateTime);

        description = addPaddingRight("  ZonedDateTime.getZone()", 40);
        System.out.println(description + currentDateTime.getZone());

        description = addPaddingRight("  ZonedDateTime.getOffset()", 40);
        System.out.println(description + currentDateTime.getOffset());

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
        printInfo(currentDateTime);
        printInfo(ztd_utc);
    }

}