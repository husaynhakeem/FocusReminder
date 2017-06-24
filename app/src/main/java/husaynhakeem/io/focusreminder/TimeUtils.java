package husaynhakeem.io.focusreminder;

import java.util.Calendar;

/**
 * Created by husaynhakeem on 6/24/17.
 */

public class TimeUtils {


    private static final int REMINDER_START_TIME = 9;
    private static final int REMINDER_END_TIME = 18;


    public static boolean isWithinReminderTimeInterval() {
        return isPastReminderStartTime() && isBeforeReminderEndTime();
    }


    private static boolean isPastReminderStartTime() {
        int currentHour = currentHourOfDay();
        return currentHour >= REMINDER_START_TIME;
    }


    private static boolean isBeforeReminderEndTime() {
        int currentHour = currentHourOfDay();
        return currentHour < REMINDER_END_TIME;
    }


    private static int currentHourOfDay() {
        Calendar now = Calendar.getInstance();
        return now.get(Calendar.HOUR_OF_DAY);
    }
}
