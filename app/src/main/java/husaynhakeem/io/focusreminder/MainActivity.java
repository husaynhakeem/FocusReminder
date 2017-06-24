package husaynhakeem.io.focusreminder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private static TextView reminderMessageTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        reminderMessageTextView = (TextView) findViewById(R.id.tv_reminder_message);
        ReminderUtils.scheduleFocusReminder(this);
    }


    public static void updateFocusMessage(String message) {
        if (reminderMessageTextView != null)
            reminderMessageTextView.setText(message);
    }
}
