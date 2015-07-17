package com.framgia.lupx.androidtrainingframgia.service;

import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by FRAMGIA\pham.xuan.lu on 17/07/2015.
 */
public class LoadHtmlAsyncTask extends AsyncTask<String, Void, Void> {

    String html = "";

    public interface OnLoadCompleteListener {
        void onLoaded(String html);
    }

    private OnLoadCompleteListener listener;

    public void setListener(OnLoadCompleteListener listener) {
        this.listener = listener;
    }

    @Override
    protected Void doInBackground(String... params) {
        try {
            String url = params[0];

            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);
            InputStream in = response.getEntity().getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder str = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                str.append(line);
            }
            in.close();
            html = str.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if (listener != null) {
            listener.onLoaded(html);
        }
    }
}
