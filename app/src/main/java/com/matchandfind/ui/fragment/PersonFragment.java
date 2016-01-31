package com.matchandfind.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.matchandfind.MatchAndFindApp;
import com.matchandfind.R;
import com.matchandfind.database.IDBManager;
import com.matchandfind.databinding.FragmentPersonsBinding;
import com.matchandfind.model.Person;
import com.matchandfind.network.APIStates;
import com.matchandfind.network.INetworkManager;
import com.matchandfind.ui.activity.FragmentActionsListener;
import com.matchandfind.ui.adapter.OnPersonClickActionListener;
import com.matchandfind.ui.adapter.PersonsAdapter;
import com.matchandfind.ui.fragment.listener.OnPersonsUpdatesListeners;
import com.matchandfind.ui.model.PersonViewModel;
import com.matchandfind.ui.model.PersonsListViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observer;

public class PersonFragment extends Fragment implements Observer, OnPersonsUpdatesListeners, OnPersonClickActionListener {

    @Inject
    INetworkManager networkManager;

    @Inject
    IDBManager dbManager;

    private PersonsListViewModel mListViewModel;
    private FragmentPersonsBinding mBinding;
    private PersonsAdapter mPersonsAdapter;

    public static PersonFragment getInstance() {
        return new PersonFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MatchAndFindApp.getInstance().getComponent().inject(this);
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_persons, container, false);
        mListViewModel = new PersonsListViewModel();
        mBinding.setPersonsModel(mListViewModel);
        mBinding.personsList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mBinding.personsList.setAdapter(mPersonsAdapter = new PersonsAdapter());
        return mBinding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();

        loadPersonsToAdapter();
    }

    private void loadPersonsToAdapter() {
        List<Person> personList = dbManager.getPersons();
        List<PersonViewModel> personViewModels = new ArrayList<>();
        for (Person person : personList) {
            personViewModels.add(new PersonViewModel(person, this));
        }
        mPersonsAdapter.setPersonsList(personViewModels);
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        //TODO set text view with error
    }

    @Override
    public void onNext(Object o) {
        //stub
    }

    @Override
    public void onPersonUpdate(Person person) {
        if (person.getStatus().equals(APIStates.REMOVED)) {
            mPersonsAdapter.removeItem(person);
        }
    }

    @Override
    public void onListUpdate() {

    }

    @Override
    public void onPersonClick(String action, Person person) {
        mPersonsAdapter.removeItem(person);
        dbManager.removePerson(person.getId());
        ((FragmentActionsListener)getActivity()).updateList();
    }
}
