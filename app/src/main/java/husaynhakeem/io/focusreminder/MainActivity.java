package husaynhakeem.io.focusreminder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import static husaynhakeem.io.focusreminder.NotificationUtils.ACTION_USER_IS_FOCUSED;
import static husaynhakeem.io.focusreminder.NotificationUtils.ACTION_USER_IS_NOT_FOCUSED;

public class MainActivity extends AppCompatActivity {

    private TextView reminderMessageTextView;
    private String action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        action = getIntent().getAction();
        if (action != null)
            setCorrectTheme();

        setContentView(R.layout.activity_main);

        reminderMessageTextView = (TextView) findViewById(R.id.tv_reminder_message);
        reminderMessageTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationUtils.remindUserToFocus(MainActivity.this);
            }
        });

        handleIncomingFromNotification();
        ReminderUtils.scheduleFocusReminder(this);
    }


    private void setCorrectTheme() {

        switch (action) {
            case ACTION_USER_IS_FOCUSED:
                getTheme().applyStyle(R.style.AppThemeUserFocused, true);
                break;

            case ACTION_USER_IS_NOT_FOCUSED:
                getTheme().applyStyle(R.style.AppThemeUserNotFocused, true);
                break;

            default:
                getTheme().applyStyle(R.style.AppTheme, true);
                break;
        }
    }


    private void handleIncomingFromNotification() {

        if (action == null) {
            setFocusMessage(FocusReminderMessageUtils.getRandomDefaultMessage(this), "");
            return;
        }

        switch (action) {
            case ACTION_USER_IS_FOCUSED:
                NotificationUtils.dismissAllNotifications(this);
                setFocusMessage(FocusReminderMessageUtils.getRandomUserFocusedMessage(this),
                        ACTION_USER_IS_FOCUSED);
                break;

            case ACTION_USER_IS_NOT_FOCUSED:
                NotificationUtils.dismissAllNotifications(this);
                setFocusMessage(FocusReminderMessageUtils.getRandomUserNotFocusedMessage(this),
                        ACTION_USER_IS_NOT_FOCUSED);
                break;

            default:
                setFocusMessage(FocusReminderMessageUtils.getRandomDefaultMessage(this), "");
                break;
        }
    }


    public void setFocusMessage(String message, String action) {
        if (reminderMessageTextView != null) {
            reminderMessageTextView.setText(message);
            reminderMessageTextView.setBackgroundColor(FocusReminderMessageUtils.getBackgroundColorForFocusMessage(this, action));
        }
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }
}