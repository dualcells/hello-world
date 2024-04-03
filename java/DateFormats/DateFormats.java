/** 
 * Prints instances of .now() in varied formats.
 * 
 * @author  Spencer Gagne 
 * @version 0.4
 * @since   2024-03-29
 * 
 * @see https://docs.oracle.com/en/java/javase/22/docs/api/java.base/java/time/LocalDateTime.html
 * @see https://docs.oracle.com/en/java/javase/22/docs/api/java.base/java/time/ZonedDateTime.html
 * @see https://docs.oracle.com/en/java/javase/22/docs/api/java.base/java/time/Instant.html
 * @see https://docs.oracle.com/en/java/javase/22/docs/api/java.base/java/time/OffsetDateTime.html
 * 
 */

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.Clock;

public class DateFormats {

    // ====================================================
    // Variables
    static ZoneId systemTZ = ZoneId.systemDefault();
    static ZoneId UTC_TZ = ZoneId.of("UTC");
    static LocalDateTime myDateObj = LocalDateTime.now();
    static String TZDisplayName = systemTZ.getDisplayName(TextStyle.FULL, Locale.ENGLISH);
    static ZonedDateTime myDateZonedObj = ZonedDateTime.of(myDateObj, systemTZ);
    static ZonedDateTime myDateZonedUTC = myDateZonedObj.withZoneSameInstant(ZoneOffset.UTC);
    static Clock myUTC = Clock.system(UTC_TZ);
    static Instant myInstantObj = Instant.now(); 


    static DateTimeFormatter ISO_LOCAL_DATE_TIME_format = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    static DateTimeFormatter ISO_LOCAL_DATE_format = DateTimeFormatter.ISO_LOCAL_DATE;
    static DateTimeFormatter ISO_ZONED_DATE_TIME_format = DateTimeFormatter.ISO_ZONED_DATE_TIME;
    static DateTimeFormatter ISO_OFFSET_DATE_TIME_format = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
    static DateTimeFormatter RFC_1123_DATE_TIME_format = DateTimeFormatter.RFC_1123_DATE_TIME;

    // ====================================================
    // Methods - LocalDateTime
    static void printLocalDateTime(LocalDateTime myDateObj) {
        String description = addPaddingRight("LocalDateTime.now()", 40);
        System.out.println("\n" + description + myDateObj);
    }

    static void printDateTime_withFormat(LocalDateTime myDateObj, DateTimeFormatter myFormatObj, String predefinedFormatterName) {
        String description = addPaddingRight(predefinedFormatterName, 40);
        String formattedDate = myDateObj.format(myFormatObj);
        System.out.println(description + formattedDate);
    }

    // ====================================================
    // Methods - ZonedDateTime
    static void printZonedDateTime(ZonedDateTime myDateZonedObj) {
        String description = addPaddingRight("ZonedDateTime.now()", 40);
        System.out.println("\n" + description + myDateZonedObj);

    }

    static void printDateTime_withFormat(ZonedDateTime myDateObj, DateTimeFormatter myFormatObj, String predefinedFormatterName) {
        String description = addPaddingRight(predefinedFormatterName, 40);
        String formattedDate = myDateObj.format(myFormatObj);
        System.out.println(description + formattedDate);
    }

    // ====================================================
    // Methods - Instant
    static void printInstant(Instant myInstantObj) {
        String description = addPaddingRight("Instant.now()", 40);
        System.out.println("\n" + description + myDateZonedObj);
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

    static void printDefaultTimeZone(ZoneId systemTZ, String TZDisplayName) {
        String description = addPaddingRight("ZoneId.systemDefault()", 40);
        System.out.println(description + systemTZ);

        description = addPaddingRight("ZoneId.getDisplayName()", 40);
        System.out.println(description + TZDisplayName);  
    }

    // ====================================================
    // Main
    public static void main(String[] args) {
        printDefaultTimeZone(systemTZ, TZDisplayName);

        printLocalDateTime(myDateObj);
        printDateTime_withFormat(myDateObj, ISO_LOCAL_DATE_TIME_format, "  ISO_LOCAL_DATE_TIME");
        printDateTime_withFormat(myDateObj, ISO_LOCAL_DATE_format, "  ISO_LOCAL_DATE");

        printZonedDateTime(myDateZonedObj);
        printDateTime_withFormat(myDateZonedObj, ISO_ZONED_DATE_TIME_format, "  ISO_ZONED_DATE_TIME");
        printDateTime_withFormat(myDateZonedObj, ISO_OFFSET_DATE_TIME_format, "  ISO_OFFSET_DATE_TIME");
        printDateTime_withFormat(myDateZonedObj, RFC_1123_DATE_TIME_format, "  RFC_1123_DATE_TIME");

        printInstant(myInstantObj);

        printZonedDateTime(myDateZonedUTC);
        printDateTime_withFormat(myDateZonedUTC, ISO_ZONED_DATE_TIME_format, "  ISO_ZONED_DATE_TIME");
        printDateTime_withFormat(myDateZonedUTC, ISO_OFFSET_DATE_TIME_format, "  ISO_OFFSET_DATE_TIME");
        printDateTime_withFormat(myDateZonedUTC, RFC_1123_DATE_TIME_format, "  RFC_1123_DATE_TIME");

    }
}