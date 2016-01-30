package com.matchandfind;


import com.matchandfind.database.DBManager;
import com.matchandfind.ui.activity.ResultsActivity;
import com.matchandfind.ui.activity.SplashScreenActivity;
import com.matchandfind.ui.model.SplashViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component (modules =  {ApplicationModule.class})
public interface GraphComponent {

    void inject(MatchAndFindApp app);

    void inject(SplashScreenActivity activity);

    void inject(ResultsActivity activity);

    void inject(DBManager manager);
}
