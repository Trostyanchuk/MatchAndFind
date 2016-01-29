package com.matchandfind;

import android.app.Application;

import com.matchandfind.database.DBManager;
import com.matchandfind.database.IDBManager;
import com.matchandfind.utils.PreferencesUtil;

import org.testpackage.test_sdk.android.testlib.API;

import io.realm.Realm;

public class MatchAndFindApp extends Application {

    private static Application mApp;
    private static IDBManager dbManager;

    @Override
    public void onCreate() {
        super.onCreate();

        mApp = this;

        PreferencesUtil.markLoginAsFirstIfNotMarked(this);

        API.INSTANCE.init(getApplicationContext());


        dbManager = new DBManager();
    }

    public static IDBManager getDbManager() {
        return dbManager;
    }

    public static Application getApp() {
        return mApp;
    }
}
