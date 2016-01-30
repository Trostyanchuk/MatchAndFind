package com.matchandfind;

import android.app.Application;

import com.matchandfind.utils.PreferencesUtil;

import org.testpackage.test_sdk.android.testlib.API;

public class MatchAndFindApp extends Application {

    private GraphComponent mComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        PreferencesUtil.markLoginAsFirstIfNotMarked(this);

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
}
