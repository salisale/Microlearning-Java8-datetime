import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static helper.Printer.print;

public class Z_Test {

    public static void main(String[] args) throws ParseException {
        String str1 = "2024-02-02 06:28:00";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        var date = sdf.parse(str1);


        print(date);
    }
    


}
