package com.framgia.lupx.androidtrainingframgia.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.framgia.lupx.androidtrainingframgia.R;


public class ServiceTestActivity extends AppCompatActivity {

    private Button btnStart;
    private Button btnStop;
    private TextView txtHtml;
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnStart:
                    startService();
                    break;
                case R.id.btnStop:
                    stopService();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStart = (Button) findViewById(R.id.btnStart);
        btnStop = (Button) findViewById(R.id.btnStop);
        btnStart.setOnClickListener(onClickListener);
        btnStop.setOnClickListener(onClickListener);
        txtHtml = (TextView) findViewById(R.id.txtHtml);

        LocalBroadcastManager.getInstance(getBaseContext()).registerReceiver(receiver, new IntentFilter(DownloadHtmlService.SERVICE_TO_UI));
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int action = intent.getIntExtra(DownloadHtmlService.SERVICE_TO_UI, 0);
            switch (action) {
                case DownloadHtmlService.LOAD_HTML_COMPLETED:
                    txtHtml.setText(AppData.getInstance().html);
                    break;
                default:

                    break;
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(receiver);
        super.onDestroy();
    }

    private void startService() {
        startService(new Intent(getBaseContext(), DownloadHtmlService.class));
    }

    private void stopService() {
        stopService(new Intent(getBaseContext(), DownloadHtmlService.class));
    }
}
