package com.evanarendssgmail.sparespace;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        final EditText etUsername = (EditText) findViewById(R.id.user2_text);
        final EditText etPassword = (EditText) findViewById(R.id.password3_text);
        final Button bLogin = (Button) findViewById(R.id.login_button);

        bLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d("your tag","barely there");
                final String username = etUsername.getText().toString();
                final String password = etPassword.getText().toString();

                // Response received from the server
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.d("Your tag", "nice try");
                            JSONObject jsonResponse = new JSONObject(response);
                            Log.d("your tag","response");
                            boolean success = jsonResponse.getBoolean("success");
                            Log.d("Your tag", "sucess1");

                            if (success) {
                                Log.d("Your tag","success2");
                                String name = jsonResponse.getString("name");
                                int age = jsonResponse.getInt("age");
                                Log.d("Your tag","before intent");
                                Intent intent = new Intent(MainActivity.this, UserArea.class);
                                Log.d("Your tag","after intent");
                                //Intent intent = new Intent(getApplicationContext(), UserArea.class);
                                intent.putExtra("name", name);
                                intent.putExtra("age", age);
                                intent.putExtra("username", username);
                                Log.d("Your tag","varaiables");
                                //startActivity(intent);
                                MainActivity.this.startActivity(intent);
                                Log.d("YOUR TAG","COMPLETED");
                            } else {
                                Log.d("YOUR TAG","WONT LOAD");
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
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

                LoginRequest loginRequest = new LoginRequest(username, password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                queue.add(loginRequest);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * What happens when the user presses the login Button
     * @param v
     */
    /*
    public void login_click(View v) {
        Intent i = new Intent(getApplicationContext(), Home_page.class);
        startActivity(i);
    }
    */

    public void register_click(View v) {
        Intent i = new Intent(getApplicationContext(),Register.class);
        startActivity(i);
    }
}
