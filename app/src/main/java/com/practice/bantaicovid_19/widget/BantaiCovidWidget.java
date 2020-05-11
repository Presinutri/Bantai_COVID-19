package com.practice.bantaicovid_19.widget;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.practice.bantaicovid_19.R;

import java.util.Calendar;

import static com.practice.bantaicovid_19.model.SpreadDataModel.PREFERENCE_MENINGGAL;
import static com.practice.bantaicovid_19.model.SpreadDataModel.PREFERENCE_POSITIF;
import static com.practice.bantaicovid_19.model.SpreadDataModel.PREFERENCE_SEMBUH;


/**
 * Implementation of App Widget functionality.
 */
public class BantaiCovidWidget extends AppWidgetProvider {

    private static long seJam = 3600L;
    private TextView widgetPositif, widgetSembuh, widgetMeninggal;

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.bantai_covid_widget);

        SharedPreferences sharedPreferences = context.getSharedPreferences("spread data widget", Context.MODE_PRIVATE);

        views.setTextViewText(R.id.widget_positif, sharedPreferences.getString(PREFERENCE_POSITIF, "O"));
        views.setTextViewText(R.id.widget_sembuh, sharedPreferences.getString(PREFERENCE_SEMBUH, "-"));
        views.setTextViewText(R.id.widget_meninggal, sharedPreferences.getString(PREFERENCE_MENINGGAL, "-"));

        Intent intentUpdate = new Intent(context, BantaiCovidWidget.class);
        intentUpdate.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);

        int[] idArray = new int[]{appWidgetId};
        intentUpdate.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, idArray);
        PendingIntent pendingUpdate = PendingIntent.getBroadcast(
                context, appWidgetId, intentUpdate,
                PendingIntent.FLAG_UPDATE_CURRENT);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 3);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 10);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setInexactRepeating(AlarmManager.RTC, calendar.getTimeInMillis(),
                seJam*1000, pendingUpdate);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

