package com.salma.mydiary.model.dataBase;

public interface SharedPreferencesManager {
    void checkLoggedInUser(String userName,String password);
    void addUser(String userName,String password);
    boolean checkRememberMe ();
    //void checkUser();
}

