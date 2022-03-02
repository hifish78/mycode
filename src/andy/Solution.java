package andy;



import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class Solution {
   public static void main(String[] args) {
       Date date = new Date();
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       System.out.println(sdf.format(date));

       LocalDateTime now = LocalDateTime.now();
       System.out.println(now);
   }
}
