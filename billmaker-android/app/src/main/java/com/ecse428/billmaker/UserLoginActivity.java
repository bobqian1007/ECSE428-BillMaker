package com.ecse428.billmaker;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class UserLoginActivity extends AppCompatActivity {
    static final String USERNAME_KEY = "USERNAME";

    TextInputEditText usernameInput;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_login);
        init();
    }

    private void init() {
        Button loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startUserInfoActivity();
            }
        });
        usernameInput = findViewById(R.id.login_username_input);
    }

    private void startUserInfoActivity() {
        Intent userInfoIntent = new Intent(this, UserInfoActivity.class);
        userInfoIntent.putExtra(USERNAME_KEY, usernameInput.getText().toString());
        Log.println(Log.DEBUG, "debug", usernameInput.getText().toString());

        startActivity(userInfoIntent);
    }
}
