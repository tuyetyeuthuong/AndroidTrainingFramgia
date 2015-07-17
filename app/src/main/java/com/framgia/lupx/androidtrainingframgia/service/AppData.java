package com.framgia.lupx.androidtrainingframgia.service;

/**
 * Created by FRAMGIA\pham.xuan.lu on 17/07/2015.
 */
public class AppData {
    private AppData() {
    }

    private static AppData _instance;

    public static AppData getInstance() {
        if (_instance == null)
            _instance = new AppData();
        return _instance;
    }

    public String html;
}
