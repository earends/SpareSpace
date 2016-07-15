package com.evanarendssgmail.sparespace;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.util.ArrayList;

public class ImageUpload extends Activity implements View.OnClickListener {


    private ImageView upload_im;
    private ImageView download_im;
    private EditText n_upload;
    private EditText n_download;
    private Button b_upload;
    private Button b_download;

    private static final int RESULT_LOAD_IMAGE = 1;
    private static final String SERVER_ADDRESS = "http://sparespace.netai.net/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_upload);



        upload_im = (ImageView) findViewById(R.id.image_upload);
        download_im = (ImageView) findViewById(R.id.image_download);
        n_upload = (EditText) findViewById(R.id.upload_name);
        n_download = (EditText) findViewById(R.id.download_name);
        b_upload = (Button) findViewById(R.id.image_upload_button);
        b_download = (Button) findViewById(R.id.download_button);

        upload_im.setOnClickListener(this);
        b_upload.setOnClickListener(this);
        b_download.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_upload:
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent,RESULT_LOAD_IMAGE);
                break;
            case R.id.image_upload_button:
                uploadName();
                Bitmap image = ((BitmapDrawable)upload_im.getDrawable()).getBitmap();
                new UploadImage(image,n_upload.getText().toString()).execute();

                break;
            case R.id.download_button:
                new DownloadImage(n_download.getText().toString()).execute();
                break;
        }

    }

    private void uploadName() {
        Log.d("upload name", "initiated");
        String name = n_upload.getText().toString();
        String username = name;
        String password = name;
        int age = 21;
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    if (success) {
                        Log.d("imageUPLOAD", "SUCESS");
                        //Intent intent = new Intent(Ima.this, MainActivity.class);
                        //Register.this.startActivity(intent);
                    } else {
                        Log.d("imageUPLOAD", "FAIL");
                        AlertDialog.Builder builder = new AlertDialog.Builder(ImageUpload.this);
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

        RegisterNameRequest registerRequest = new RegisterNameRequest(name, username, age, password, responseListener);
        RequestQueue queue = Volley.newRequestQueue(ImageUpload.this);
        queue.add(registerRequest);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            upload_im.setImageURI(selectedImage);
        }
    }

    private class UploadImage extends AsyncTask<Void,Void,Void> {

        Bitmap image;
        String name;

        public UploadImage(Bitmap image, String name) {
            this.image = image;
            this.name = name;
        }

        @Override
        protected Void doInBackground(Void... params) {
            ByteArrayOutputStream byt = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.JPEG,50,byt); // the '50' some how adjsuts size of pic allowed over stream
            String encodedImage = Base64.encodeToString(byt.toByteArray(),Base64.DEFAULT);

            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("image",encodedImage));
            dataToSend.add(new BasicNameValuePair("name",name));

            HttpParams httpRequest = getHttpRequestParams();
            HttpClient client = new DefaultHttpClient(httpRequest);
            HttpPost post = new HttpPost(SERVER_ADDRESS + "SavePicture.php");

            try {
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                client.execute(post);
            } catch (Exception e) {

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(), "Image Uploaded", Toast.LENGTH_SHORT).show();

        }
    }

    private class DownloadImage extends AsyncTask<Void,Void,Bitmap> {
        String name;

        public DownloadImage(String name) {
            Log.d("downloading", "constructor");
            this.name = name;
        }

        @Override
        protected Bitmap doInBackground(Void... params) {
            String url = SERVER_ADDRESS + "pictures/" + name + ".jpg";
            try {
                Log.d("downloading", "try");
                Log.d("downloading", url);
                URLConnection connection = new URL(url).openConnection();
                connection.setConnectTimeout(1000*30);
                connection.setReadTimeout(1000*30);
                return BitmapFactory.decodeStream((InputStream)connection.getContent(),null,null);

            } catch( Exception e) {
                Log.d("downloading", "catch");
                e.printStackTrace();
                return null;
            }


        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
                if (bitmap != null) {
                    download_im.setImageBitmap(bitmap);
                }
        }
    }


    private HttpParams getHttpRequestParams() {
        HttpParams httpRequestParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpRequestParams,1000* 30);
        HttpConnectionParams.setSoTimeout(httpRequestParams,1000* 30);
        return httpRequestParams;

    }

}
