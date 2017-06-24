package husaynhakeem.io.focusreminder;

import android.content.Context;

import static husaynhakeem.io.focusreminder.SharedPreferencesUtils.MOST_RECENT_DEFAULT_MESSAGE_INDEX_KEY;
import static husaynhakeem.io.focusreminder.SharedPreferencesUtils.MOST_RECENT_USER_FOCUSED_MESSAGE_INDEX_KEY;
import static husaynhakeem.io.focusreminder.SharedPreferencesUtils.MOST_RECENT_USER_NOT_FOCUSED_MESSAGE_INDEX_KEY;

/**
 * Created by husaynhakeem on 6/24/17.
 */

public class FocusReminderMessageUtils {


    public static String getRandomDefaultMessage(Context context) {
        return getMessageToDisplay(context, R.array.default_messages, MOST_RECENT_DEFAULT_MESSAGE_INDEX_KEY);
    }


    public static String getRandomUserFocusedMessage(Context context) {
        return getMessageToDisplay(context, R.array.user_focused_messages, MOST_RECENT_USER_FOCUSED_MESSAGE_INDEX_KEY);
    }


    public static String getRandomUserNotFocusedMessage(Context context) {
        return getMessageToDisplay(context, R.array.user_not_focused_messages, MOST_RECENT_USER_NOT_FOCUSED_MESSAGE_INDEX_KEY);
    }


    private static String getMessageToDisplay(Context context, int arrayResourceId, String key) {

        String[] messages = context.getResources().getStringArray(arrayResourceId);

        int mostRecentUsedIndex = SharedPreferencesUtils.getSharedPreference(context, key);
        mostRecentUsedIndex++;
        if (mostRecentUsedIndex >= messages.length)
            mostRecentUsedIndex = 0;

        SharedPreferencesUtils.saveSharedPreference(context, key, mostRecentUsedIndex);

        return messages[mostRecentUsedIndex];
    }
}
