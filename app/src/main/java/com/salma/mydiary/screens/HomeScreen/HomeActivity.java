package com.salma.mydiary.screens.HomeScreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.salma.mydiary.R;
import com.salma.mydiary.screens.AddNoteScreen.MainActivity;

public class HomeActivity extends AppCompatActivity implements HomeContract.HomeView {
    EditText userName;
    EditText password;
    Button register;
    Button login;
    HomeContract.HomePresenter homePresenter;
    String insertedUserName;
    String insertedPasswordValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        homePresenter = new HomePresenterImpl(this,getApplicationContext());

        boolean loggedIn = homePresenter.checkIfLoggedIn();
        if (loggedIn) {
            respondToRememberUser();

        } else {
            userName = findViewById(R.id.userName);
            password = findViewById(R.id.password);
            login = findViewById(R.id.loginBtn);
            register = findViewById(R.id.registerBtn);

            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    insertedUserName = userName.getText().toString();
                    insertedPasswordValue = password.getText().toString();
                    homePresenter.register(insertedUserName,insertedPasswordValue);

                }
            });

            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    insertedUserName = userName.getText().toString();
                    insertedPasswordValue = password.getText().toString();
                    homePresenter.login(insertedUserName, insertedPasswordValue);
                }
            });


        }
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    public void respondToValidLogin() {
        userName.getText().clear();
        password.getText().clear();
        Intent intent = new Intent(HomeActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void respondToInvalidLogin() {
        Toast.makeText(getApplicationContext(), "Please enter Valid User name and Password!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void respondToValidRegistration() {
        userName.getText().clear();
        password.getText().clear();
        Toast.makeText(getApplicationContext(), "Registered successfully", Toast.LENGTH_LONG).show();
    }

    @Override
    public void respondToInvalidRegistration() {
        Toast.makeText(getApplicationContext(), "Registration Failed!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void respondToRememberUser() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
