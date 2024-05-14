import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

import static helper.Printer.*;

import java.time.LocalDateTime;

public class A_LocalDateTime {

    public static void main(String[] args) {
        /** 
         * Notice how there is no "new" keyword and a lot of method chaining like Streams API.
         * 
         * I also forgot to mention "LocalDate" and "LocalTime" classes which are standalones of 
         * the date and time representation. We can initialise the LocalDate class without setting
         * midnight as default time if we simply want to manipulate date, which is an improvement on
         * the original Date class which requires both date and time information.
         * 
         * But please be reminded that LocalDate, LocalTime, and LocalDateTime do not account for 
         * Daylight Saving! Its use case will be quite limited.
         * 
         * There are no ZonedDate or ZonedTime or OffsetDate or OffsetTime classes.
         * 
         */

        // date
        LocalDate date = LocalDate.of(2023, 9, 4);
        print(date); // 2023-09-04

        // time
        LocalTime time = LocalTime.of(10, 23, 23);
        print(time); // 10:23:23

        // date & time
        LocalDateTime datetime = LocalDateTime.of(2023, Month.SEPTEMBER, 24, 10, 23, 23);
        print(datetime); // 2023-09-24T10:23:23

        // format
        String prettyDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("ccc, L MMM yyyy hh:mm:ss a"));
        print(prettyDateTime); // Wed, 9 Sep 2023 12:34:49 PM
        String prettyDateTime2 = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);  // built-in formatter, mostly ISO formats
        print(prettyDateTime2); // 2023-09-13T12:34:49.712
  
        // Useful methods that do not account for DST or timezone!
        // If you wish to add smaller time units such as Minutes or Seconds, Instant is more suitable
        boolean isAfter = LocalDateTime.now().isAfter(LocalDateTime.now().plusMinutes(30));
        boolean isBefore = LocalDateTime.now().isBefore(LocalDateTime.now().plusMinutes(30));
        boolean isEqual = LocalDateTime.now().isEqual(LocalDateTime.now().plusMinutes(30));

        // Note that you can still use compareTo() but you will have to validate positive or negative result,
        // although it may be useful for the statement such as "before or equal to"
        int compareTo = LocalDateTime.now().compareTo(LocalDateTime.now().plusMinutes(30));

        // The isAfter, isBefore, isEqual, compareTo or plusMinutes, plusDays, etc. methods 
        // are also applicable to ZonedDateTime as well as OffsetDateTime
        

        /*****************************************************/
        // 1. Does LocalDateTime take into account leap year?
        /*****************************************************/

        // We will simply check whether 29th February exists or the date skips to 1st March
        LocalDateTime ldt = LocalDateTime.of(2023, 2, 28, 10,0,0).plusDays(1);
        print(ldt); // 2023-03-01T10:00
        LocalDateTime ldt2 = LocalDateTime.of(2024, 2, 28, 10,0,0).plusDays(1);
        print(ldt2); // 2024-02-29T10:00

        // We can also individually check the day and the month like
        boolean isLeap = ldt.getDayOfMonth() == 29 && ldt.getMonthValue() == 2; // or ldt.getMonth() == Month.FEBRUARY
        print(isLeap); // false
        boolean isLeap2 = ldt2.getDayOfMonth() == 29 && ldt2.getMonthValue() == 2; // or ldt2.getMonth() == Month.FEBRUARY
        print(isLeap2); // true
    }
    


}
