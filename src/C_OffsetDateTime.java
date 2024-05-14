import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;


import static helper.Printer.*;

public class C_OffsetDateTime {
    
    public static void main(String[] args) {
        // create OffsetDateTime from hour offset
        OffsetDateTime zone = OffsetDateTime.now(ZoneOffset.ofHours(-2));

        // The difference between ZonedDateTime and OffsetDateTime..
        ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("Europe/Vienna"));
        print(zdt); // 2023-09-13T08:30:21.902+02:00[Europe/Vienna]
        OffsetDateTime odt3 = zdt.toOffsetDateTime(); // convert to OffsetDateTime
        print(odt3); // 2023-09-13T08:30:21.902+02:00

        // .. is that OffsetDateTime gets rid of the zone information and retains the hour offset

        // several ways to create UTC string 
        OffsetDateTime utc = Instant.now().atOffset(ZoneOffset.UTC);
        Instant utc2 = Instant.now();
        OffsetDateTime utc3 = OffsetDateTime.now(ZoneOffset.UTC);
        print(utc, utc2, utc3); // all print out 2023-09-13T06:33:22.401Z

        // "Instant.now().atOffset(ZoneOffset.UTC)" is a round-about way of creating a UTC timestamp;
        // you can simply use "Instant.now()" because it is already in UTC time. So, the shortest way
        // to get a string representation of the current UTC timestamp is simply "Instant.now().toString()"
        
        // isAfter, isBefore,.. also applicable here
        boolean isAfter = OffsetDateTime.now().isAfter(OffsetDateTime.now(ZoneId.of("America/New_York")));

        /*************************************************************************************************/
        // 1. Print me the current UTC datetime as converted from Thailand and Vienna ZonedDateTime and simply Instant UTC
        /*************************************************************************************************/
        
        // This question should be under Instant section.. not OffsetDateTime!

        // Anyway, I simply want to show that these methods all print out the same string which is
        // 2023-09-13T06:33:22.494Z
    
        print(Instant.now().toString());
        print(OffsetDateTime.now().toInstant());
        print(ZonedDateTime.now(ZoneId.of("Europe/Vienna")).toInstant());
        print(ZonedDateTime.now(ZoneId.of("Asia/Bangkok")).toInstant());
    }
}
