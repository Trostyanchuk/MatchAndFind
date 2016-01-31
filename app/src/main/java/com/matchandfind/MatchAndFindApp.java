package com.matchandfind;

import android.app.Application;

import com.matchandfind.utils.PreferencesUtil;

import org.testpackage.test_sdk.android.testlib.API;

public class MatchAndFindApp extends Application {

    private static MatchAndFindApp mInstance;

    private GraphComponent mComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;

        initApi();

        mComponent = DaggerGraphComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    private void initApi() {
        API.INSTANCE.init(getApplicationContext());
    }

    public GraphComponent getComponent() {
        return mComponent;
    }

    public static MatchAndFindApp getInstance() {
        return mInstance;
    }
}
