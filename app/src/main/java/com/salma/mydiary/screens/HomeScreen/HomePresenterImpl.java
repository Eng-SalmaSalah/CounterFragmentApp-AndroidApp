package com.salma.mydiary.screens.HomeScreen;

import com.salma.mydiary.model.dataBase.SharedPreferencesManager;
import com.salma.mydiary.model.dataBase.SharedPreferencesManagerImpl;

public class HomePresenterImpl implements HomeContract.HomePresenter {
    private HomeContract.HomeView _homeView;
    private SharedPreferencesManager sharedPreferencesManager;

    public HomePresenterImpl(HomeContract.HomeView homeView) {
        this._homeView = homeView;
        sharedPreferencesManager = new SharedPreferencesManagerImpl(this, _homeView);

    }

    @Override
    public void login(String userName, String password) {
        sharedPreferencesManager.checkLoggedInUser(userName, password);
    }

    @Override
    public void register(String userName, String password) {
        sharedPreferencesManager.addUser(userName, password);
    }

    @Override
    public void announceValidLogin() {
        _homeView.respondToValidLogin();
    }

    @Override
    public void announceInvalidLogin() {
        _homeView.respondToInvalidLogin();
    }

    @Override
    public void announceValidRegistration() {
        _homeView.respondToValidRegistration();
    }

    @Override
    public void announceInvalidRegistration() {
        _homeView.respondToInvalidRegistration();
    }

    @Override
    public boolean checkIfLoggedIn() {
        return sharedPreferencesManager.checkRememberMe();
    }
}
