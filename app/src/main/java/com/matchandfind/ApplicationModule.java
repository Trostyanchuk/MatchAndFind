package com.matchandfind;

import android.app.Application;
import android.content.Context;

import com.matchandfind.database.DBManager;
import com.matchandfind.database.IDBManager;
import com.matchandfind.network.INetworkManager;
import com.matchandfind.network.NetworkManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private Application mApplication;

    public ApplicationModule(MatchAndFindApp application) {
        this.mApplication = application;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    public INetworkManager provideNetworkManager() {
        return new NetworkManager();
    }

    @Provides
    @Singleton
    public IDBManager provideDBManager() {
        return new DBManager();
    }
}
