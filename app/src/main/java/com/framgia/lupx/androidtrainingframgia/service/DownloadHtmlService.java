package com.framgia.lupx.androidtrainingframgia.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by FRAMGIA\pham.xuan.lu on 17/07/2015.
 */
public class DownloadHtmlService extends Service {
    public static final String SERVICE_TO_UI = "SERVICE_TO_UI";
    public static final int LOAD_HTML_COMPLETED = 1;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        String url = "https://www.youtube.com/watch?v=g7XXYFPCdyU";
        LoadHtmlAsyncTask task = new LoadHtmlAsyncTask();
        task.setListener(new LoadHtmlAsyncTask.OnLoadCompleteListener() {
            @Override
            public void onLoaded(String html) {
                Log.v("HTML", html);
                AppData.getInstance().html = html;
                Intent loadCompleteIntent = new Intent(SERVICE_TO_UI);
                loadCompleteIntent.putExtra(SERVICE_TO_UI, LOAD_HTML_COMPLETED);
                LocalBroadcastManager.getInstance(DownloadHtmlService.this).sendBroadcast(loadCompleteIntent);
            }
        });
        task.execute(url);
        Log.v("HTML", "Loading");
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "SERVICE_OnStartCommand", Toast.LENGTH_SHORT).show();


        return START_STICKY;
    }

    @Override
    public boolean onUnbind(Intent intent) {

        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Service_Stop", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }


}
