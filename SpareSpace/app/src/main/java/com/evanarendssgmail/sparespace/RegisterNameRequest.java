package com.evanarendssgmail.sparespace;
/*
Description: Duplicate class of RegisterRequest, used to write the names of each picture uploaded to server
 */

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterNameRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "http://sparespace.netai.net/postingsWrite.php";
    private Map<String, String> params;

    public RegisterNameRequest(String username, String title, String description, String location,String cost, String obo, String dimmension, String phone, String email, String image,String image2, Response.Listener<String> listener) {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
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

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

