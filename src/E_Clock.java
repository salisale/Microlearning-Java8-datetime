import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;

import static helper.Printer.*;


public class E_Clock {
    public static void main(String[] args) {
        
        Instant instant1 = Instant.now();
        Instant instant2 = Instant.now(Clock.systemUTC());
        Instant instant3 = Instant.now(Clock.systemDefaultZone());
        Instant instant4 = Instant.now(Clock.system(ZoneId.of("Asia/Bangkok")));
        Instant instant5 = Instant.now(Clock.fixed(Instant.now(), ZoneOffset.UTC));
        Instant instant6 = Instant.now(Clock.fixed(Instant.parse("2017-04-10T17:59:00Z"), ZoneOffset.UTC));
        Instant instant7 = Instant.now(Clock.fixed(Instant.parse("2017-04-10T17:59:00Z"), ZoneId.of(("Asia/Bangkok"))));

        
        print(instant1, instant2, instant3, instant4, instant5, instant6, instant7);
        
    }
}
