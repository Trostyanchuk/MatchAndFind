package com.matchandfind.ui.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

import com.matchandfind.BR;

public class SplashViewModel extends BaseObservable {

    private GenerationActionListener mGenerationListener;
    private String generateLabel;
    private boolean generateEnabled;

    public SplashViewModel(GenerationActionListener listener) {
        this.mGenerationListener = listener;
        this.generateEnabled = true;
    }

    public void generateItems(View view) {
        if (mGenerationListener != null) {
            mGenerationListener.onStartGeneration();
        }
    }

    public void updateUIWithProgress() {
        setGenerateEnabled(false);
    }

    public void updateUIWithError(String reason) {
        setGenerateEnabled(true);
    }

    private void goToResultsScreen() {
        if (mGenerationListener != null) {
            mGenerationListener.onStartGeneration();
        }
    }

    @Bindable
    public boolean isGenerateEnabled() {
        return generateEnabled;
    }

    public void setGenerateEnabled(boolean generateEnabled) {
        this.generateEnabled = generateEnabled;
        notifyPropertyChanged(BR.generateEnabled);
    }

    public interface GenerationActionListener {
        public void onStartGeneration();
    }
}
