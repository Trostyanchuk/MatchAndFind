package com.matchandfind.ui.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.matchandfind.BR;
import com.matchandfind.model.Person;
import com.matchandfind.ui.adapter.VPFragmentsAdapter;
import com.matchandfind.ui.fragment.listener.OnPersonsUpdatesListeners;

public class ResultsViewModel extends BaseObservable {

    private boolean personsFragmentChosen = true;
    private boolean mapFragmentChosen = false;

    private VPFragmentsAdapter mVPFragmentsAdapter;
    private ItemChangeListener mItemChangeListener;

    private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            if (position == 0) {
                setUserTabUI();
            } else if (position == 1) {
                setMapTabUI();
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };

    public ResultsViewModel(FragmentManager fragmentManager, ItemChangeListener listener) {
        this.mVPFragmentsAdapter = new VPFragmentsAdapter(fragmentManager);
        this.mItemChangeListener = listener;
    }

    public void onUsersClick(View view) {
        setUserTabUI();
        setTabItem(0);
    }

    public void setUserTabUI() {
        setMapFragmentChosen(false);
        setPersonsFragmentChosen(true);
    }

    public void onMapClick(View view) {
        setMapTabUI();
        setTabItem(1);
    }

    public void setMapTabUI() {
        setMapFragmentChosen(true);
        setPersonsFragmentChosen(false);
    }

    private void setTabItem(int newIndex) {
        if (mItemChangeListener != null) {
            mItemChangeListener.onItemChanged(newIndex);
        }
    }

    @Bindable
    public boolean isPersonsFragmentChosen() {
        return personsFragmentChosen;
    }

    public void setPersonsFragmentChosen(boolean personsFragmentChosen) {
        this.personsFragmentChosen = personsFragmentChosen;
        notifyPropertyChanged(BR.personsFragmentChosen);
    }

    @Bindable
    public boolean isMapFragmentChosen() {
        return mapFragmentChosen;
    }

    public void setMapFragmentChosen(boolean mapFragmentChosen) {
        this.mapFragmentChosen = mapFragmentChosen;
        notifyPropertyChanged(BR.mapFragmentChosen);
    }

    public VPFragmentsAdapter getVPFragmentsAdapter() {
        return mVPFragmentsAdapter;
    }

    public ViewPager.OnPageChangeListener getOnPageChangeListener() {
        return onPageChangeListener;
    }

    public void updateFragmentsWithItem(Person person) {
        ((OnPersonsUpdatesListeners) mVPFragmentsAdapter.getItem(0)).onPersonUpdate(person);
        ((OnPersonsUpdatesListeners) mVPFragmentsAdapter.getItem(1)).onPersonUpdate(person);
    }

    public void notifyMapReloadList() {
        ((OnPersonsUpdatesListeners) mVPFragmentsAdapter.getItem(1)).onListUpdate();
    }

    public interface ItemChangeListener {
        void onItemChanged(int newIndex);
    }
}
