package com.salma.mydiary.screens.HomeScreen;

import android.content.Context;

import com.salma.mydiary.model.dataBase.SharedPreferencesManager;
import com.salma.mydiary.model.dataBase.SharedPreferencesManagerImpl;

public class HomePresenterImpl implements HomeContract.HomePresenter {
    private HomeContract.HomeView homeView;
    private SharedPreferencesManager sharedPrefrencesManager;

    public HomePresenterImpl(HomeContract.HomeView homeView, Context context) {
        this.homeView = homeView;
        sharedPrefrencesManager = new SharedPreferencesManagerImpl(this, context);

    }

    @Override
    public void login(String userName, String password) {
        sharedPrefrencesManager.checkLoggedInUser(userName, password);
    }

    @Override
    public void register(String userName, String password) {
        sharedPrefrencesManager.addUser(userName, password);
    }

    @Override
    public void announceValidLogin() {
        homeView.respondToValidLogin();
    }

    @Override
    public void announceInvalidLogin() {
        homeView.respondToInvalidLogin();
    }

    @Override
    public void announceValidRegistration() {
        homeView.respondToValidRegistration();
    }

    @Override
    public void announceInvalidRegistration() {
        homeView.respondToInvalidRegistration();
    }

    @Override
    public boolean checkIfLoggedIn() {
        return sharedPrefrencesManager.checkRememberMe();
    }
}
