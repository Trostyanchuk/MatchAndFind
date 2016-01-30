package com.matchandfind.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.matchandfind.MatchAndFindApp;
import com.matchandfind.R;
import com.matchandfind.databinding.FragmentPersonsBinding;
import com.matchandfind.model.Person;
import com.matchandfind.ui.activity.FragmentActionsListener;
import com.matchandfind.ui.adapter.PersonsAdapter;
import com.matchandfind.ui.fragment.listeners.OnUpdatePersonsListListener;
import com.matchandfind.ui.model.PersonViewModel;
import com.matchandfind.ui.model.PersonsListViewModel;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.schedulers.Schedulers;

public class PersonFragment extends Fragment implements OnUpdatePersonsListListener{

    private PersonsListViewModel mListViewModel;
    private FragmentPersonsBinding mBinding;
    private PersonsAdapter mPersonsAdapter;

    public static PersonFragment getInstance() {
        return new PersonFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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
        mPersonsAdapter.setPersonsList(new ArrayList<PersonViewModel>());
    }

    @Override
    public void onPersonsListUpdated(List<Person> personList) {
        List<PersonViewModel> personViewModels = new ArrayList<>();
        for (Person person : personList) {
            personViewModels.add(new PersonViewModel(person));
        }
        mPersonsAdapter.setPersonsList(personViewModels);
    }
}
