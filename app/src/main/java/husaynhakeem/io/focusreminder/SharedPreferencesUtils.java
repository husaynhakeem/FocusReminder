package husaynhakeem.io.focusreminder;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by husaynhakeem on 6/24/17.
 */

public class SharedPreferencesUtils {

    public static final String MOST_RECENT_DEFAULT_MESSAGE_INDEX_KEY = "most-recent-default-message-index";
    public static final String MOST_RECENT_USER_FOCUSED_MESSAGE_INDEX_KEY = "most-recent-user-focused-message-index";
    public static final String MOST_RECENT_USER_NOT_FOCUSED_MESSAGE_INDEX_KEY = "most-recent-user-not-focused-message-index";

    private static final int MESSAGE_INDEX_DEFAULT_VALUE = 0;


    synchronized public static void saveSharedPreference(final Context context, final String key, final int valueToSave) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt(key, valueToSave);
                editor.apply();
            }
        }).run();
    }


    public static int getSharedPreference(Context context, String key) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getInt(key, MESSAGE_INDEX_DEFAULT_VALUE);
    }
}
