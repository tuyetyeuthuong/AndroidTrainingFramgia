package com.framgia.lupx.androidtrainingframgia.widgets;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

import com.framgia.lupx.androidtrainingframgia.R;

/**
 * Created by FRAMGIA\pham.xuan.lu on 17/07/2015.
 */
public class SpeedWidget extends AppWidgetProvider {
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for(int i=0;i<appWidgetIds.length;i++){
            int currentWidgetId = appWidgetIds[i];
            RemoteViews views = new RemoteViews(context.getPackageName(),
                    R.layout.widget_activity_layout);
            views.setTextViewText(R.id.txtSpeed,"98");
            views.setTextViewText(R.id.txtUnit,"kmph");
            appWidgetManager.updateAppWidget(currentWidgetId,views);
        }
    }
}
