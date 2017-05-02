package com.example.pablo.joinme;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class Profile extends AppCompatActivity {
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_layout);
        getSupportActionBar().hide();
        image = (ImageView) findViewById(R.id.user_picture);
    }
}
