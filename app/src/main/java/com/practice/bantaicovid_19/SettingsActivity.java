package com.practice.bantaicovid_19;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.practice.bantaicovid_19.receiver.NotificationReceiver;

import java.util.Calendar;

import static com.practice.bantaicovid_19.receiver.NotificationReceiver.EXTRA_TYPE;
import static com.practice.bantaicovid_19.receiver.NotificationReceiver.REMINDER_ID;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String PREF_SWITCH_DAILY_NOTIFICATION = "daily notification";

    private AlarmManager dailyNotificationManager;
    private Intent dailyNotificationIntent;
    private PendingIntent dailyPendingIntent;
    private View layoutLanguage;

    private Switch dailyNotificationSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        dailyNotificationManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);

        // daily reminder
        dailyNotificationIntent = new Intent(this, NotificationReceiver.class);
        dailyNotificationIntent.putExtra(EXTRA_TYPE, "daily reminder");
        dailyPendingIntent = PendingIntent.getBroadcast(
                this, REMINDER_ID, dailyNotificationIntent, 0);

        getSupportActionBar().setTitle("Settings");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        layoutLanguage = findViewById(R.id.layout_settings);
        layoutLanguage.setOnClickListener(this);
        
        setSwitch();
    }

    private void setSwitch() {
        SharedPreferences sharedPreferences = getSharedPreferences("switch settings", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        dailyNotificationSwitch = findViewById(R.id.daily_notification_settings_switch);
        if (sharedPreferences.getBoolean(PREF_SWITCH_DAILY_NOTIFICATION, true))
            dailyNotificationSwitch.setChecked(true);
        else dailyNotificationSwitch.setChecked(false);

        dailyNotificationSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    editor.putBoolean(PREF_SWITCH_DAILY_NOTIFICATION, true);
                    setRepeatingAlarm();
                    Toast.makeText(compoundButton.getContext(), getResources().getString(R.string.daily_notification_on),
                            Toast.LENGTH_SHORT).show();
                } else {
                    editor.putBoolean(PREF_SWITCH_DAILY_NOTIFICATION, false);
                    dailyNotificationManager.cancel(dailyPendingIntent);
                    Toast.makeText(compoundButton.getContext(), getString(R.string.daily_notification_off),
                            Toast.LENGTH_SHORT).show();
                }
                editor.apply();
            }

        });
    }

    private void setRepeatingAlarm() {
        Calendar calendar = Calendar.getInstance();
        if (calendar.get(Calendar.HOUR_OF_DAY)>=7 && calendar.get(Calendar.MINUTE)>0) calendar.add(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 7);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        // alarm manager
        dailyNotificationManager.setInexactRepeating(
                AlarmManager.RTC_WAKEUP,
                calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY,
                dailyPendingIntent
        );
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_settings:
                startActivity(new Intent(Settings.ACTION_LOCALE_SETTINGS));
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
