package husaynhakeem.io.focusreminder.utilities;

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


    private static SharedPreferencesUtils instance;
    private static SharedPreferences preferences;


    private SharedPreferencesUtils(Context context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }


    public static SharedPreferencesUtils with(Context context) {
        if (instance == null)
            instance = new SharedPreferencesUtils(context);
        return instance;
    }


    synchronized public void saveSharedPreference(final Context context, final String key, final int valueToSave) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt(key, valueToSave);
                editor.apply();
            }
        }).run();
    }


    public int getSharedPreference(Context context, String key) {
        return preferences.getInt(key, MESSAGE_INDEX_DEFAULT_VALUE);
    }
}
