package com.sparkdev.perimeter.models;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sparkdev.perimeter.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseAPI firebaseAPI = FirebaseAPI.getInstance(this);
        String email = "poop@poo.com";
        String password = "password";

        firebaseAPI.loginUser(email, password);
    }
}
