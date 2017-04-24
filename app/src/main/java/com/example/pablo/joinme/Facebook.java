package com.example.pablo.joinme;

import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;


public class Facebook extends AppCompatActivity {
    LoginButton facebookLoginButton;
    CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Facebook Initialization
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        setContentView(R.layout.facebook_layout);
        initFacebook();
        Profile profile = Profile.getCurrentProfile();
        if (profile != null){
            startActivity(new Intent(getBaseContext(),MainActivity.class));
        }
    }

    private void initFacebook() {
        facebookLoginButton = (LoginButton) findViewById(R.id.login_button);
        // callback registration
        callbackManager = CallbackManager.Factory.create();
        facebookLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_LONG).show();
                startActivity(new Intent(getBaseContext(),MainActivity.class));
            }
            @Override
            public void onCancel() {
                // App code
                Toast.makeText(getApplicationContext(),"Cancel",Toast.LENGTH_LONG).show();
            }
            @Override
            public void onError(FacebookException error) {
                // App code
                Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
