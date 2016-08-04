package com.evanarendssgmail.sparespace;

import com.sinch.android.rtc.Sinch;
import com.sinch.android.rtc.SinchError;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends BaseActivity implements SinchService.StartFailedListener {

    //private Button mLoginButton;

    private EditText mLoginName;
    private ProgressDialog mSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        //mLoginButton = (Button) findViewById(R.id.loginButton);
        //mLoginButton.setEnabled(false);
        mLoginName = (EditText) findViewById(R.id.loginName);
        /*
        mLoginButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                loginClicked();
            }
        });
        */
    }

    @Override
    protected void onServiceConnected() {
        //mLoginButton.setEnabled(true);
        getSinchServiceInterface().setStartListener(this);
    }

    @Override
    protected void onPause() {
        if (mSpinner != null) {
            mSpinner.dismiss();
        }
        super.onPause();
    }

    @Override
    public void onStartFailed(SinchError error) {
        Toast.makeText(this, error.toString(), Toast.LENGTH_LONG).show();
        if (mSpinner != null) {
            mSpinner.dismiss();
        }
    }

    @Override
    public void onStarted() {
        openMessagingActivity();
    }

    private void loginClicked() {
        String userName = mLoginName.getText().toString();
        Log.d("YOUR TAG", "LOGIN CLICKED");

        if (userName.isEmpty()) {
            Toast.makeText(this, "Please enter a name", Toast.LENGTH_LONG).show();
            return;
        }


        if (!getSinchServiceInterface().isStarted()) {
            Log.d("YOUR TAG", "SINCH SERVICE INTERFACE");
            getSinchServiceInterface().startClient(userName);
            showSpinner();

        } else {
            openMessagingActivity();
        }


    }

    private void openMessagingActivity() {
        Intent messagingActivity = new Intent(this, MessagingActitivity.class);
        startActivity(messagingActivity);
    }

    private void showSpinner() {
        mSpinner = new ProgressDialog(this);
        mSpinner.setTitle("Logging in");
        mSpinner.setMessage("Please wait...");
        mSpinner.show();
    }

    public void work_click(View view) {
        loginClicked();
    }
}