package com.matchandfind.ui.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

import com.matchandfind.model.Person;
import com.matchandfind.ui.adapter.OnPersonClickActionListener;

public class PersonViewModel extends BaseObservable {

    public static final String LIKE_TAG = "LIKE";
    public static final String DISLIKE_TAG = "DISLIKE";

    private Person mPerson;
    private OnPersonClickActionListener mListener;

    private boolean imageDownloaded;

    public PersonViewModel(Person person) {
        this.mPerson = person;
    }

    public PersonViewModel(Person person, OnPersonClickActionListener listener) {
        this.mPerson = person;
        this.mListener = listener;
    }

    public Person getPerson() {
        return mPerson;
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

    public boolean wasPersonLike() {
        return mPerson.getStatus().equals("like");
    }

    public void clickOnPerson(View view) {
        if (mListener == null) return;

        String tag = (String) view.getTag();
        if (tag.equals(LIKE_TAG)) {
            mListener.onPersonClick("like", mPerson);
        } else if (tag.equals(DISLIKE_TAG)) {
            mListener.onPersonClick("dislike", mPerson);
        }
    }
}
