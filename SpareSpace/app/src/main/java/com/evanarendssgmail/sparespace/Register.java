package com.evanarendssgmail.sparespace;
/*
Description: This activity is activated when the user would like to sign up for an account
They input details regarding their account information and that info is sent to the server using register request class
 */
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Register extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        // UI COMPONENETS
        final EditText etAge = (EditText) findViewById(R.id.register_age);
        final EditText etName = (EditText) findViewById(R.id.register_name);
        final EditText etUsername = (EditText) findViewById(R.id.register_username);
        final EditText etPassword = (EditText) findViewById(R.id.register_password);
        final Button bRegister = (Button) findViewById(R.id.button_register);
        // listener for if someone clicks register button
        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // grab all the users Text info
                // later on check if any detials are null
                final String name = etName.getText().toString();
                final String username = etUsername.getText().toString();
                final int age = Integer.parseInt(etAge.getText().toString());
                final String password = etPassword.getText().toString();
                // activates new reponses listener to the server
                //waits for a repsonse from server
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            // puts reponse into form of JsonObject
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            // if php file returns sucess
                            //Start Login activity so user can Login
                            if (success) {
                                Intent intent = new Intent(Register.this, Login.class);
                                Register.this.startActivity(intent);
                            } else { // if returns false
                                //Stay on page and have user try to Login again
                                AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
                                builder.setMessage("Register Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                // Before there can be a reponse make a request to access the servers information, pass along the...
                // name, username.. etc to write into the server
                RegisterRequest registerRequest = new RegisterRequest(name, username, age, password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(Register.this);
                queue.add(registerRequest);
            }
        });
    }
}