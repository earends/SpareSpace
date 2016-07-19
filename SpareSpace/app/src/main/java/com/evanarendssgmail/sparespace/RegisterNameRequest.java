package com.evanarendssgmail.sparespace;
/*
Description: Duplicate class of RegisterRequest, used to write the names of each picture uploaded to server
 */

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterNameRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "http://sparespace.netai.net/pictureCountWrite.php";
    private Map<String, String> params;

    public RegisterNameRequest(String name, String username, int age, String password, Response.Listener<String> listener) {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("name", name);
        params.put("username", username);
        params.put("age", age + "");
        params.put("password", password);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

