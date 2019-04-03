package com.salma.mydiary.screens.HomeScreen;

public interface HomeContract {
    interface HomePresenter {
        void login(String userName, String password);
        void register(String userName, String password);
        void announceValidLogin();
        void announceInvalidLogin();
        void announceValidRegistration();
        void announceInvalidRegistration();
        boolean checkIfLoggedIn ();
    }

    interface HomeView {
        void respondToValidLogin ();
        void respondToInvalidLogin();
        void respondToValidRegistration();
        void respondToInvalidRegistration();
        void respondToRememberUser();
    }
}
