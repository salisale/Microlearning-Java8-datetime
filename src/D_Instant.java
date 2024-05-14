import java.time.Instant;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

import static helper.Printer.*;

import java.time.LocalDateTime;

public class D_Instant{

    public static void main(String[] args) {
        
        // create Instant for a current timestamp
        Instant instantNow = Instant.now();
        // create Instant from other DateTime objects e.g. LocalDate, LocalDateTime, ZonedDateTime,..
        Instant instantValentine = LocalDateTime.of(2024, 2, 14, 19, 0).toInstant(ZoneOffset.UTC);

        // Note that if you wish to convert LocalDateTime to Instant, you do need to give it a Zone offset,
        // otherwise the system could not know how to convert the given time back to UTC

        // manipulate it
        Instant secondFromNow = instantNow.plusSeconds(1);
        long milliFromThen = instantNow.until(secondFromNow, ChronoUnit.MILLIS);
    
        // Note that until() which computes an interval also works with the DateTime classes. 
        // Like the plusXXX() method, you also need to be careful with Daylight Saving Time because 
        // one day can have more than or fewer than 24 hours!

        /************************************************/
        // 1. Check if a token has expired using Instant
        /************************************************/
        String tokenExpires = "2023-09-08T07:06:25.092Z";
        // simple comparison like this is sufficient
        boolean isExpiredIssuer = Instant.now().isAfter(Instant.parse(tokenExpires));
        // you can also give it a small margin like 2 seconds for example
        boolean isExpiredIssued = Instant.now().minusSeconds(2).isAfter(Instant.parse(tokenExpires));
    }

}
