package com.matchandfind.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.matchandfind.R;
import com.matchandfind.databinding.ActivityResultsBinding;
import com.matchandfind.ui.model.ResultsViewModel;

public class ResultsActivity extends BaseActivity implements ResultsViewModel.ItemChangeListener {

    private ActivityResultsBinding mBindingObject;
    private ResultsViewModel mModel;

    private ResultsViewModel.ItemChangeListener mItemChangeListener = new ResultsViewModel.ItemChangeListener() {
        @Override
        public void onItemChanged(int newIndex) {
            mBindingObject.fragmentViewPager.setCurrentItem(newIndex);
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBindingObject = DataBindingUtil.setContentView(this, R.layout.activity_results);
        mModel = new ResultsViewModel(getSupportFragmentManager(), this);
        mBindingObject.setModel(mModel);
    }

    @Override
    public void onItemChanged(int newIndex) {

    }
}
