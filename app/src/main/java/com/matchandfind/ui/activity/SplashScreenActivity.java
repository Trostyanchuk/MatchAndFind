package com.matchandfind.ui.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.matchandfind.MatchAndFindApp;
import com.matchandfind.R;
import com.matchandfind.database.IDBManager;
import com.matchandfind.databinding.ActivitySplashBinding;
import com.matchandfind.model.Person;
import com.matchandfind.network.INetworkManager;
import com.matchandfind.network.wrappers.PersonsExtendedCallbackWrapper;
import com.matchandfind.network.wrappers.RefreshSuccessCallbackWrapper;
import com.matchandfind.ui.model.SplashViewModel;
import com.matchandfind.utils.JSONUtil;
import com.matchandfind.utils.PreferencesUtil;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;

public class SplashScreenActivity extends BaseActivity {

    @Inject
    INetworkManager networkManager;
    @Inject
    IDBManager dbManager;

    private SplashViewModel mSplashModel;
    private ActivitySplashBinding mBindingObject;
    private Subscription subscription;
    private PersonsExtendedCallbackWrapper mPersonsExtendedCallback = new PersonsExtendedCallbackWrapper() {
        @Override
        public void onResult(String persons) {
            List<Person> personsList = JSONUtil.getPersonsListFromJSOSNStr(persons);
            dbManager.savePersons(personsList);
            openResultsActivity();
        }

        @Override
        public void onFail(String reason) {
            mSplashModel.updateUIWithError(reason);
        }
    };
    private RefreshSuccessCallbackWrapper mSuccessCallback = new RefreshSuccessCallbackWrapper() {
        @Override
        public void onSuccess() {
            networkManager.getPersonsJSON(mPersonsExtendedCallback);
        }
    };
    private SplashViewModel.GenerationActionListener mGeneratorActionListener = new SplashViewModel.GenerationActionListener() {
        @Override
        public void onStartGeneration() {
            subscribeToResults();
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBindingObject = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        mSplashModel = new SplashViewModel(mGeneratorActionListener);
        mBindingObject.setModel(mSplashModel);

        if (PreferencesUtil.alreadyLoggedIn(this)) {
            openResultsActivity();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (subscription != null && subscription.isUnsubscribed()) {
            subscribeToResults();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    private void subscribeToResults() {
        subscription = Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                mSplashModel.updateUIWithProgress();
                networkManager.refreshPersons(mSuccessCallback);
            }
        }).subscribeOn(Schedulers.newThread()).subscribe();
    }

    private void openResultsActivity() {
        Intent resultsIntent = new Intent(this, ResultsActivity.class);
        resultsIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(resultsIntent);
    }
}
