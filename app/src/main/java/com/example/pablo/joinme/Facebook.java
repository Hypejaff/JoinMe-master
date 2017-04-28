package com.example.pablo.joinme;

import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Facebook extends AppCompatActivity {
    LoginButton facebookLoginButton;
    TextView textView;
    CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Facebook Initialization
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        setContentView(R.layout.facebook_layout);
        getSupportActionBar().hide();
        textView = (TextView)findViewById(R.id.textView);

        PrintHashKey();

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

    private void PrintHashKey() {
        PackageInfo info;
        try {
            info = getPackageManager().getPackageInfo("com.example.pablo.joinme", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                //String something = new String(Base64.encodeBytes(md.digest()));
                Log.e("hash key", something);
                textView.setText(something);
            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("name not found", e1.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.e("no such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("exception", e.toString());
        }

    }
}
