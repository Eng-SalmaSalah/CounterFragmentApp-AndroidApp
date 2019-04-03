package com.salma.mydiary.model.dataBase;

import android.content.Context;
import android.content.SharedPreferences;

import com.salma.mydiary.screens.HomeScreen.HomeContract;

import static android.content.Context.MODE_PRIVATE;

public class SharedPreferencesManagerImpl implements SharedPreferencesManager {
    private SharedPreferences sharedPreferences;
    private HomeContract.HomePresenter homePresenter;
    private Context context;

    public SharedPreferencesManagerImpl(HomeContract.HomePresenter homePresenter, HomeContract.HomeView homeView) {
        this.homePresenter = homePresenter;
        this.context = (Context)homeView;
    }

    @Override
    public void checkLoggedInUser(String userName, String password) {
        sharedPreferences = context.getSharedPreferences("userData", MODE_PRIVATE);
        String retrievedUserName = sharedPreferences.getString("UserName", "NotFound");
        String retrievedPassword = sharedPreferences.getString("Password", "NotFound");
        if ((userName.equals(retrievedUserName)) && (password.equals(retrievedPassword))) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("RememberMe", true);
            boolean rememberMeDone = editor.commit();
            if (rememberMeDone)
                homePresenter.announceValidLogin();


        } else {
            homePresenter.announceInvalidLogin();
        }
    }

    @Override
    public void addUser(String userName, String password) {
        sharedPreferences = context.getSharedPreferences("userData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("UserName", userName);
        editor.putString("Password", password);
        boolean registrationDone = editor.commit();
        if (registrationDone) {
            homePresenter.announceValidRegistration();
        } else {
            homePresenter.announceInvalidRegistration();
        }
    }

    @Override
    public boolean checkRememberMe() {
        sharedPreferences = context.getSharedPreferences("userData", MODE_PRIVATE);
        return sharedPreferences.getBoolean("RememberMe", false);
    }
}
