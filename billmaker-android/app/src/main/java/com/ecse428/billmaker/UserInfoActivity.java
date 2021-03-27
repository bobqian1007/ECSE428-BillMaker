package com.ecse428.billmaker;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class UserInfoActivity extends AppCompatActivity {
    String tempEmail = "1234@mail.com";
    Integer monthlyLimitValue = 3000;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info);
        Intent intent = getIntent();
        Log.println(Log.DEBUG, "debug", intent.getStringExtra(UserLoginActivity.USERNAME_KEY));

        init(intent);
    }

    private void init(Intent intent) {
        TextView username = findViewById(R.id.username);
        Log.println(Log.DEBUG, "debug", intent.getStringExtra(UserLoginActivity.USERNAME_KEY));
        username.setText(intent.getStringExtra(UserLoginActivity.USERNAME_KEY));
        TextView email = findViewById(R.id.email);
        email.setText(tempEmail);
        EditText monthlyLimit = findViewById(R.id.monthly_limit);
        monthlyLimit.setText(String.valueOf(monthlyLimitValue));
    }
}
