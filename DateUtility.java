import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * Provides date utility methods for converting dates from string to 
 * Java 8 date formats, and vice versa.
 * 
 * @Lakshmi Saketh
 * @04262018
 */

public class DateUtility
{
    private static final String PATTERN_Date = "dd-MM-yyyy";
    private static final String PATTERN_Date2 = "dd-MMM-yyyy";

    
    public static LocalDate convertDate(String dateInput) 
    {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(PATTERN_Date);
        return LocalDate.parse(dateInput, dateFormat);

        
    }
    
   
    public static LocalTime convertTime(String timeInput) 
    {
        DateTimeFormatter parseFormat = new DateTimeFormatterBuilder().appendPattern("H:m").toFormatter();
        return LocalTime.parse(timeInput, parseFormat);

    }
    
  
    
    /**
     * This gives the local date of system
     *
     * @return Local Sytem date or server date.
     */
    public static LocalDate getCurrentDate() {
        return LocalDate.now();
    }

    /**
     * This gives the local system time
     *
     * @return local system time
     */
    public static LocalTime getCurrentTime() {
        return LocalTime.now();
    }

    /**
     * This gives both the date and time
     *
     * @ retuns the date and time of local system.
     */

    public static LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now();
    }

    /**
     * generates localised date from provided string Date
     *
     * @param inputDate provided string date
     * @return Localised date as per the requirements
     */
    public static String getLocalisedDate(LocalDate inputDate) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(PATTERN_Date2, Locale.getDefault());
        return df.format(inputDate);
    }

    /**
     * Returns the days between provided dates
     *
     * @param date for comparison
     * @return difference between dates
     * <p><p>
     * See <a href ="https://stackoverflow.com/a/29812532/3796452">https://stackoverflow.com/a/29812532/3796452</a> for reference
     */
    public static long daysBetweenDates(LocalDate firstDate, LocalDate secondDate) {
        return ChronoUnit.DAYS.between(firstDate, secondDate);
    }

    /**
     * Returns the days between provided dates
     *
     * @param date for comparison
     * @return difference between times
     * <p><p>
     * See <a href ="https://stackoverflow.com/a/29812532/3796452">https://stackoverflow.com/a/29812532/3796452</a> for reference
     */
    public static long timeBetweenDateTimes(LocalTime firstTime, LocalTime secondTime) {
        return ChronoUnit.MINUTES.between(firstTime, secondTime);
    }


    /**
     * Returns the days between provided dates
     *
     * @param date for comparison
     * @return difference between dates and times
     * <p><p>
     * See <a href ="https://stackoverflow.com/a/29812532/3796452">https://stackoverflow.com/a/29812532/3796452</a> for reference
     */
    public static long hoursBetweenDateTimes(LocalDateTime firstTime, LocalDateTime secondTime) {
        return ChronoUnit.HOURS.between(firstTime, secondTime);
    }
}

        

