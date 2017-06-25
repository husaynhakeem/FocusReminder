package husaynhakeem.io.focusreminder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView reminderMessageTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        reminderMessageTextView = (TextView) findViewById(R.id.tv_reminder_message);
        reminderMessageTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationUtils.remindUserToFocus(MainActivity.this);
            }
        });

        ReminderUtils.scheduleFocusReminder(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        handleIncomingFromNotification();
    }

    private void handleIncomingFromNotification() {
        String action = getIntent().getAction();

        if (isIncomingFromNotification(action)) {
            customFocusMessage(action);
            return;
        }

        defaultFocusMessage();
    }


    private boolean isIncomingFromNotification(String action) {
        return !TextUtils.isEmpty(action) &&
                (NotificationUtils.ACTION_USER_IS_FOCUSED.equals(action) ||
                        NotificationUtils.ACTION_USER_IS_NOT_FOCUSED.equals(action));
    }


    private void defaultFocusMessage() {
        setFocusMessage(getString(R.string.default_message));
    }


    private void customFocusMessage(String action) {
        switch (action) {

            case NotificationUtils.ACTION_USER_IS_FOCUSED:
                NotificationUtils.dismissAllNotifications(this);
                setFocusMessage(getString(R.string.user_focused_message));
                break;

            case NotificationUtils.ACTION_USER_IS_NOT_FOCUSED:
                NotificationUtils.dismissAllNotifications(this);
                setFocusMessage(getString(R.string.user_not_focused_message));
                break;
        }
    }


    public void setFocusMessage(String message) {
        if (reminderMessageTextView != null)
            reminderMessageTextView.setText(message);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        reminderMessageTextView.setText(null);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }
}
