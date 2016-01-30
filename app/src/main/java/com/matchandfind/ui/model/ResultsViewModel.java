package com.matchandfind.ui.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v4.app.FragmentManager;
import android.view.View;

import com.matchandfind.BR;
import com.matchandfind.model.Person;
import com.matchandfind.ui.adapter.VPFragmentsAdapter;
import com.matchandfind.ui.fragment.listeners.OnUpdatePersonsListListener;

import java.util.List;

public class ResultsViewModel extends BaseObservable {

    private boolean personsFragmentChosen;
    private boolean mapFragmentChosen;

    private VPFragmentsAdapter mVPFragmentsAdapter;
    private ItemChangeListener mItemChangeListener;

    public ResultsViewModel(FragmentManager fragmentManager, ItemChangeListener listener) {
        this.mVPFragmentsAdapter = new VPFragmentsAdapter(fragmentManager);
        this.mItemChangeListener = listener;
    }

    public void onUsersClick(View view) {
        setMapFragmentChosen(false);
        setPersonsFragmentChosen(true);
        setTabItem(0);
    }

    public void onMapClick(View view) {
        setMapFragmentChosen(false);
        setPersonsFragmentChosen(true);
        setTabItem(1);
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

    public void updateFragmentsWithList(List<Person> persons) {
        ((OnUpdatePersonsListListener) mVPFragmentsAdapter.getItem(0)).onPersonsListUpdated(persons);
        ((OnUpdatePersonsListListener) mVPFragmentsAdapter.getItem(1)).onPersonsListUpdated(persons);
    }

    public interface ItemChangeListener {
        void onItemChanged(int newIndex);
    }
}
