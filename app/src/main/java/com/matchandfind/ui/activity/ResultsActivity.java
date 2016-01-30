package com.matchandfind.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;

import com.matchandfind.R;
import com.matchandfind.database.IDBManager;
import com.matchandfind.databinding.ActivityResultsBinding;
import com.matchandfind.model.Person;
import com.matchandfind.network.INetworkManager;
import com.matchandfind.ui.model.ResultsViewModel;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;

public class ResultsActivity extends BaseActivity implements Observer {

    @Inject
    INetworkManager networkManager;

    @Inject
    IDBManager dbManager;

    private ActivityResultsBinding mBindingObject;
    private ResultsViewModel mModel;
    private ResultsViewModel.ItemChangeListener mItemChangeListener = new ResultsViewModel.ItemChangeListener() {
        @Override
        public void onItemChanged(int newIndex) {
            mBindingObject.fragmentViewPager.setCurrentItem(newIndex);
        }
    };

    private Subscription mSubscription;
    private List<Person> mPersonsList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBindingObject = DataBindingUtil.setContentView(this, R.layout.activity_results);
        mModel = new ResultsViewModel(getSupportFragmentManager(), mItemChangeListener);
        mBindingObject.setModel(mModel);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mSubscription != null && mSubscription.isUnsubscribed()) {
            subscribeToResults();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }

    private void subscribeToResults() {
        mSubscription = Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                mPersonsList = dbManager.getPersons();
                if (mPersonsList != null) {
                    mModel.updateFragmentsWithList(mPersonsList);
                }
            }
        }).subscribeOn(Schedulers.newThread()).subscribe(this);
    }

    @Override
    public void onCompleted() {
        Log.d("", "");
    }

    @Override
    public void onError(Throwable e) {
        Log.d("", "");
    }

    @Override
    public void onNext(Object o) {
        Log.d("", "");
    }
}
