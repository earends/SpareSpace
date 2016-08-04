package com.sinch.android.rtc.sample.messaging;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


public class Request extends StringRequest {
    //private static final String LOGIN_REQUEST_URL = "http://sparespace.netai.net/Login.php";
    //private static final String LOGIN_REQUEST_URL1 = "http://sparespace.netai.net/Login.php";
    //private static final String REGISTER_REQUEST_URL = "http://sparespace.netai.net/postingsWrite.php";
    private Map<String, String> params;

    public Request( String password,String username, String name, int age, String title, String description, String location,String cost, String obo, String dimmension, String phone, String email, String image,String image2, int choice, String request_url, Response.Listener<String> listener) {
        super(Method.POST, request_url, listener, null);
        if (choice == 0) {  // Login Request
            params = new HashMap<>();
            Log.d("usernameee",username);
            params.put("username", username);
            params.put("password", password);
        }

        if (choice  == 2) {  // RegisterName Request
            params = new HashMap<>();
            params.put("username", username);
            params.put("title",title);
            params.put("description",description);
            params.put("location", location);
            params.put("cost",cost);
            params.put("obo",obo);
            params.put("dimmension",dimmension);
            params.put("phone",phone);
            params.put("email",email);
            params.put("image",image);
            params.put("image2",image2);
        }

        if (choice == 3) { // RegisterRequest
            params = new HashMap<>();
            params.put("name", name);
            params.put("username", username);
            params.put("age", age + "");
            params.put("password", password);
        }
        if (choice == 4) { // post Request
            params = new HashMap<>();
        }

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
