package husaynhakeem.io.focusreminder;

import android.os.AsyncTask;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

/**
 * Created by husaynhakeem on 6/24/17.
 */

public class FocusReminderJobService extends JobService {


    private AsyncTask<Void, Void, Void> mBackgroundTask;

    @Override
    public boolean onStartJob(final JobParameters job) {

        mBackgroundTask = new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                NotificationUtils.remindUserToFocus(FocusReminderJobService.this);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                jobFinished(job, false);
            }
        };

        mBackgroundTask.execute();
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters job) {
        if (mBackgroundTask != null)
            mBackgroundTask.cancel(true);
        return true;
    }
}
