package com.evanarendssgmail.sparespace;

/**
 * Created by Evan on 7/19/2016.
 */
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class PostRequest extends StringRequest {
    private static final String LOGIN_REQUEST_URL = "http://sparespace.netai.net/PostingsRead.php";
    private Map<String, String> params;

    public PostRequest(Response.Listener<String> listener) {
        super(Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
