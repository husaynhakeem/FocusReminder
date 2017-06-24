package husaynhakeem.io.focusreminder;

import android.content.Context;

import com.firebase.jobdispatcher.Driver;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.Trigger;

/**
 * Created by husaynhakeem on 6/24/17.
 */

public class ReminderUtils {

    private static final int REMINDER_INTERVAL_HOURS = 2;
//    private static final int REMINDER_INTERVAL_SECONDS = (int) TimeUnit.HOURS.toSeconds(REMINDER_INTERVAL_HOURS);
    private static final int REMINDER_INTERVAL_SECONDS = 60;

    private static final int REMINDER_FLEXTIME_HOURS = 1;
//    private static final int REMINDER_FLEXTIME_SECONDS = (int) TimeUnit.HOURS.toSeconds(REMINDER_FLEXTIME_HOURS);
    private static final int REMINDER_FLEXTIME_SECONDS = 30;

    private static final String FOCUS_REMINDER_JOB_TAG = "focus-reminder-job-tag";

    private static boolean sInitialized;

    synchronized public static void scheduleFocusReminder(Context context) {

        if (sInitialized)
            return;

        sInitialized = true;

        Driver driver = new GooglePlayDriver(context);
        FirebaseJobDispatcher jobDispatcher = new FirebaseJobDispatcher(driver);

        Job job = jobDispatcher.newJobBuilder()
                .setService(FocusReminderJobService.class)
                .setTag(FOCUS_REMINDER_JOB_TAG)
                .setTrigger(Trigger.executionWindow(REMINDER_INTERVAL_SECONDS, REMINDER_INTERVAL_SECONDS + REMINDER_FLEXTIME_SECONDS))
                .setLifetime(Lifetime.FOREVER)
                .setReplaceCurrent(true)
                .build();

        jobDispatcher.schedule(job);
    }
}
