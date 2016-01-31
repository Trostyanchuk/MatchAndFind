package com.matchandfind.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.matchandfind.MatchAndFindApp;
import com.matchandfind.R;
import com.matchandfind.database.IDBManager;
import com.matchandfind.databinding.ActivityResultsBinding;
import com.matchandfind.model.Person;
import com.matchandfind.network.APIStates;
import com.matchandfind.network.INetworkManager;
import com.matchandfind.ui.model.ResultsViewModel;

import org.testpackage.test_sdk.android.testlib.services.UpdateService;

import javax.inject.Inject;

public class ResultsActivity extends BaseActivity implements FragmentActionsListener{

    @Inject
    INetworkManager mNetworkManager;
    @Inject
    IDBManager mDBManager;

    private ActivityResultsBinding mBindingObject;
    private ResultsViewModel mModel;
    private ResultsViewModel.ItemChangeListener mItemChangeListener = new ResultsViewModel.ItemChangeListener() {
        @Override
        public void onItemChanged(int newIndex) {
            mBindingObject.fragmentViewPager.setCurrentItem(newIndex);
        }
    };
    private UpdateService.UpdateServiceListener mUpdateServiceListener = new UpdateService.UpdateServiceListener() {
        @Override
        public void onChanges(String person) {
            Person updatedPerson = new Gson().fromJson(person, Person.class);

            Log.d("TAG", "updated " + updatedPerson.getId() + ", " + updatedPerson.getStatus());
            if (updatedPerson.getStatus().equals(APIStates.REMOVED)) {
                mDBManager.removePerson(updatedPerson.getId());
            }
            mModel.updateFragmentsWithItem(updatedPerson);
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MatchAndFindApp.getInstance().getComponent().inject(this);
        mBindingObject = DataBindingUtil.setContentView(this, R.layout.activity_results);
        mModel = new ResultsViewModel(getSupportFragmentManager(), mItemChangeListener);
        mBindingObject.setModel(mModel);
    }

    @Override
    public void onStart() {
        super.onStart();
        mNetworkManager.subscribeUpdates(mUpdateServiceListener);
    }

    @Override
    public void onPause() {
        super.onPause();
        mNetworkManager.unSubscribeUpdates();
    }

    @Override
    public void updateList() {
        mModel.notifyMapReloadList();
    }
}
