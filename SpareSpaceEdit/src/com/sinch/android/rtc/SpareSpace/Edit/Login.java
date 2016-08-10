package com.sinch.android.rtc.SpareSpace.Edit;

import android.app.Activity;
import android.content.Intent;
import android.app.AlertDialog;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.sinch.android.rtc.sample.messaging.R;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends Activity {


    public static String usernme;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText etUsername = (EditText) findViewById(R.id.login_username);
        final EditText etPassword = (EditText) findViewById(R.id.login_password);
        final TextView bLogin = (TextView) findViewById(R.id.login_button);



        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = etUsername.getText().toString();
                final String password = etPassword.getText().toString();

                // Response received from the server
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                                String name = jsonResponse.getString("name");
                                int age = jsonResponse.getInt("age");
                                String user = jsonResponse.getString("username");
                                Log.d("json Respnse",user);
                                Intent intent = new Intent(Login.this, Home_page.class);
                                intent.putExtra("name", name);
                                intent.putExtra("age", age);
                                intent.putExtra("username", user);
                                usernme = etUsername.getText().toString();

                                Login.this.startActivity(intent);
                                finish();
                                return;
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                                builder.setMessage("Login Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                //LoginRequest loginRequest = new LoginRequest(username, password, responseListener);
                Request req = new Request(password,username,null,4,null,null,null,null,null,null,null,null,null,null,0,"http://sparespace.netai.net/Login.php",responseListener);
                RequestQueue queue = Volley.newRequestQueue(Login.this);
                //queue.add(loginRequest);
                queue.add(req);
            }
        });
    }

    public void register_click(View v) {
        Intent i = new Intent(getApplicationContext(),Register.class);
        startActivity(i);

    }


}