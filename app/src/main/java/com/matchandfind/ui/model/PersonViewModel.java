package com.matchandfind.ui.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.matchandfind.model.Person;

public class PersonViewModel extends BaseObservable {

    private Person mPerson;

    private boolean imageDownloaded;

    public PersonViewModel(Person person) {
        this.mPerson = person;
    }

    @Bindable
    public boolean isImageDownloaded() {
        return imageDownloaded;
    }

    public void setImageDownloaded(boolean imageDownloaded) {
        this.imageDownloaded = imageDownloaded;
    }

    @Bindable
    public String getImage() {
        return mPerson.getPhoto();
    }

    public void setImage(String image) {

    }
}
