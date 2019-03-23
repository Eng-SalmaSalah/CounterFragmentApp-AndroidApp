package com.salma.mydiary;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {
    EditText userName;
    EditText password;
    Button register;
    Button login;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        sharedPreferences = getSharedPreferences("userData", MODE_PRIVATE);
        boolean retrievedUserName = sharedPreferences.getBoolean("RememberMe", false);

        if (retrievedUserName) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();

        } else {
            userName = findViewById(R.id.userName);
            password = findViewById(R.id.password);
            login = findViewById(R.id.loginBtn);
            register = findViewById(R.id.registerBtn);

            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sharedPreferences = getSharedPreferences("userData", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("UserName", userName.getText().toString());
                    editor.putString("Password", password.getText().toString());
                    editor.commit();
                    userName.getText().clear();
                    password.getText().clear();
                    Toast.makeText(getApplicationContext(), "Registered successfully", Toast.LENGTH_SHORT).show();

                }
            });

            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String insertedUserName = userName.getText().toString();
                    String insertedPasswordValue = password.getText().toString();

                    sharedPreferences = getSharedPreferences("userData", MODE_PRIVATE);
                    String retrievedUserName = sharedPreferences.getString("UserName", "NotFound");
                    String retrievedPassword = sharedPreferences.getString("Password", "NotFound");
                    if ((insertedUserName.equals(retrievedUserName)) && (insertedPasswordValue.equals(retrievedPassword))) {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean("RememberMe", true);
                        editor.commit();
                        userName.getText().clear();
                        password.getText().clear();
                        Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Not Registered!", Toast.LENGTH_SHORT).show();
                    }


                }
            });


        }
    }

    @Override
    protected void onStop() {
        super.onStop();

    }
}
