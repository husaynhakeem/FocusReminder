package husaynhakeem.io.focusreminder.utilities;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import husaynhakeem.io.focusreminder.R;

/**
 * Created by husaynhakeem on 6/24/17.
 */

public class FocusReminderMessageUtils {


    public static String getRandomDefaultMessage(Context context) {
        return getMessageToDisplay(context, R.array.default_messages, SharedPreferencesUtils.MOST_RECENT_DEFAULT_MESSAGE_INDEX_KEY);
    }


    public static String getRandomUserFocusedMessage(Context context) {
        return getMessageToDisplay(context, R.array.user_focused_messages, SharedPreferencesUtils.MOST_RECENT_USER_FOCUSED_MESSAGE_INDEX_KEY);
    }


    public static String getRandomUserNotFocusedMessage(Context context) {
        return getMessageToDisplay(context, R.array.user_not_focused_messages, SharedPreferencesUtils.MOST_RECENT_USER_NOT_FOCUSED_MESSAGE_INDEX_KEY);
    }


    private static String getMessageToDisplay(Context context, int arrayResourceId, String key) {

        String[] messages = context.getResources().getStringArray(arrayResourceId);

        int mostRecentUsedIndex = SharedPreferencesUtils.with(context).getSharedPreference(context, key);
        mostRecentUsedIndex++;
        if (mostRecentUsedIndex >= messages.length)
            mostRecentUsedIndex = 0;

        SharedPreferencesUtils.with(context).saveSharedPreference(context, key, mostRecentUsedIndex);

        return messages[mostRecentUsedIndex];
    }


    public static int getBackgroundColorForFocusMessage(Context context, String action) {
        switch (action) {

            case NotificationUtils.ACTION_USER_IS_FOCUSED:
                return ContextCompat.getColor(context, R.color.colorPrimaryUserFocused);

            case NotificationUtils.ACTION_USER_IS_NOT_FOCUSED:
                return ContextCompat.getColor(context, R.color.colorPrimaryUserNotFocused);

            default:
                return ContextCompat.getColor(context, R.color.colorPrimary);
        }
    }
}
