package husaynhakeem.io.focusreminder;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

/**
 * Created by husaynhakeem on 6/24/17.
 */

public class FocusReminderIntentService extends IntentService {


    public FocusReminderIntentService() {
        super(FocusReminderIntentService.class.getName());
    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String action = intent.getAction();

        switch (action) {

            case NotificationUtils.ACTION_USER_IS_FOCUSED:
                MainActivity.updateFocusMessage(getString(R.string.user_focused_message));
                break;

            case NotificationUtils.ACTION_USER_IS_NOT_FOCUSED:
                MainActivity.updateFocusMessage(getString(R.string.user_not_focused_message));
                break;
        }
    }
}
