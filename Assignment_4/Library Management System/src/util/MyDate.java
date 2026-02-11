package util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MyDate {
    private static MyDate myDate;
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
    private static Calendar calendar = Calendar.getInstance();
    private Date date;

    private MyDate() {
        this.date = new Date();
    }

    public static MyDate getInstance() {
        if (myDate == null) {
            myDate = new MyDate();
        }
        return myDate;
    }

    public void nextDay() {
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        date = calendar.getTime();
    }

    public String getDate() {
        calendar.setTime(date);
        return simpleDateFormat.format(calendar.getTime());
    }

    public String inDays(int days) {
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, days);
        return simpleDateFormat.format(calendar.getTime());
    }
}
