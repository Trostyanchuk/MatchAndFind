package com.matchandfind.ui.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.databinding.ObservableArrayList;
import android.support.v7.widget.RecyclerView;

import com.matchandfind.BR;
import com.matchandfind.model.Person;

import java.util.List;

public class PersonsListViewModel extends BaseObservable {

    private ObservableArrayList<PersonViewModel> personsList;

    public PersonsListViewModel() {
        this.personsList = new ObservableArrayList<>();
    }

    @BindingAdapter("items")
    public void setPersonsList(RecyclerView recyclerView, ObservableArrayList<PersonViewModel> persons) {
        this.personsList = persons;
        notifyPropertyChanged(BR.personsList);
    }

    @Bindable
    public List<PersonViewModel> getPersonsList() {
        return personsList;
    }

    public void removePerson() {
        //TODO
    }
}
