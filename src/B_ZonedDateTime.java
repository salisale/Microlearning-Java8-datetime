import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import static helper.Printer.*;


public class B_ZonedDateTime {
    public static void main(String[] args) {

        // try showing ZoneIds
        print(ZoneId.getAvailableZoneIds());
        // [Asia/Aden, America/Cuiaba, Etc/GMT+9, Etc/GMT+8, Africa/Nairobi, America/Marigot, Asia/Aqtau, 
        //  Pacific/Kwajalein, America/El_Salvador, Asia/Pontianak, Africa/Cairo, Pacific/Pago_Pago.. ]

        // The "Continent/City" format has most of the world's major cities. There is "Europe/Vienna" but not 
        // "Europe/Dornbirn". Note that there are still generic timezones like "Etc/GMT+9" for minor cities, 
        // but does it account for Daylight Saving?
        

        // create ZonedDateTime by specific place or ZoneId
        ZonedDateTime viennaZDT = ZonedDateTime.now(ZoneId.of("Europe/Vienna"));
        // create ZonedDateTime by fixed offset, but be careful of using this! No Daylight Saving!
        ZonedDateTime offsetZDT = ZonedDateTime.now(ZoneOffset.ofHours(2));


        // create ZonedDateTime by conversion from LocalDateTime class
        // Not that you can convert to and from between all 3 DateTime classes
        ZonedDateTime localObjZDT = ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("Europe/Vienna"));
        // if you create ZonedDateTime without any zone data, it will represent local time instead!
        ZonedDateTime localZDT = ZonedDateTime.now(); // this will output Thailand time
        
        /*************************************************************************************************/
        // 1. If I watch the Ring tape on 28 Oct 2023 in Vienna and I shall die in 7 days, when will I die
        //    if 7 days is considered in term of days and in term of hours
        /*************************************************************************************************/
        // create ZonedDateTime of 28th Oct 2023 at, say, 8 am, in Vienna
        ZonedDateTime watchDate = ZonedDateTime.of(2023, 10, 28, 8, 0, 0, 0, ZoneId.of("Europe/Vienna"));

        // We will simply try to add days and hours to it and see what it outputs

        print(watchDate.plusDays(7),                        watchDate.plusHours(24*7));
        // 2023-11-04T08:00+01:00[Europe/Vienna]            2023-11-04T07:00+01:00[Europe/Vienna]

        // Can we also try to add weeks and minutes as well?

        print(watchDate.plusWeeks(1),                       watchDate.plusMinutes(60*24*7));
        // 2023-11-04T08:00+01:00[Europe/Vienna]            2023-11-04T07:00+01:00[Europe/Vienna]

        // Notice that the first value on each line outputs 8 AM whereas the second value each outputs 7 AM.
        // I am now confused by Daylight Saving Time too! But it looks like the plusDays and plusWeeks account for
        // Daylight Saving, whereas plusHours and plusMinutes do not?

        // So not only do you have to be cafeful which DateTime classes to use to account for Daylight Saving
        // (you should use ZonedDateTime with complete region information e.g. Europe/Vienna, and NOT 
        // LocalDateTime or OffsetDateTime, or ZonedDateTime with hour offset), but you have to be careful
        // which methods like plusDays or plusHours and what units of time you are dealing with!

        /************************************************************************************************************************/
        // 2. If a package gets picked up in Vienna (GMT+2) today at 8 AM and will be delivered in Sydney (GMT+10) taking exactly 
        // 5 hours and 30 minutes on a super-express international flight, when will the package arrive in Sydney?
        /***********************************************************************************************************************/

        // I will create the datetime instance at 8 AM this morning by creating the current datetime and adjust the hour value to be
        // 8 with withHour() and the minute value to be 0 with withMinute()
        ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("Europe/Vienna")).withHour(8).withMinute(0);
        print(zdt); // 2023-09-13T08:00:44.212+02:00[Europe/Vienna]

        // I want to showcase that despite "immutability" that the Java 8 DateTime API boasts of, you can STILL mutate or modify
        // the existing objects. This is why I wrote this weird line of code, but please don't actually use it for real anywhere.

        // The correct answer for this question is..
        ZonedDateTime answer = zdt.plusHours(5).plusMinutes(30).withZoneSameInstant(ZoneId.of("Australia/Sydney"));
        print(answer); // 2023-09-13T21:30:50.871+10:00[Australia/Sydney]

        // Basically, you try to find the arrival time by taking 8 AM and adding the transport duration to it which gives you 
        // 1.30 PM. But this represents the arrival time if the package arrives somewhere in Vienna or in the same timezone.
        
        // To get the arrival time IN SYDNEY, you will have to find a corresponding time point to 1.30 PM in Vienna. There is a method
        // called withZoneSameInstant() that does exactly this: it finds the local time of Sydney that corresponds with 1.30 PM in Vienna.
        // which is 9.30 PM in Sydney. Note that you can calculate this by hand by doing 8 AM + 5.30 hours + (GMT+10 - GMT+2) which 
        // gives you the same answer, but withZoneSameInstant() is more handy!

        // The documentation of withZoneSameInstant says: 
        /*
         * Returns a copy of this date-time with a different time-zone, retaining the instant.

          This method changes the time-zone and retains the instant. This normally results in a change to the local date-time.
         */

         print("Arrival date: " + answer.toLocalDate() + " at " + answer.toLocalTime());
         // Arrival date: 2023-09-13 at 21:30:21.805


    }
}
