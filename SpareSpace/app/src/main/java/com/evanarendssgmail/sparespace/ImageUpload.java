package com.evanarendssgmail.sparespace;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.toolbox.StringRequest;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;


import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class ImageUpload extends Activity implements View.OnClickListener {


    private ImageView upload;
    private ImageView download;
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



        upload = (ImageView) findViewById(R.id.image_upload);
        download = (ImageView) findViewById(R.id.image_download);
        n_upload = (EditText) findViewById(R.id.upload_name);
        n_download = (EditText) findViewById(R.id.download_name);
        b_upload = (Button) findViewById(R.id.image_upload_button);
        b_download = (Button) findViewById(R.id.download_button);

        upload.setOnClickListener(this);
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
                Bitmap image = ((BitmapDrawable)upload.getDrawable()).getBitmap();
                new UploadImage(image,n_upload.getText().toString()).execute();
                break;
            case R.id.download_button:
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            upload.setImageURI(selectedImage);
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
            image.compress(Bitmap.CompressFormat.JPEG,100,byt);
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

    private HttpParams getHttpRequestParams() {
        HttpParams httpRequestParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpRequestParams,1000* 30);
        HttpConnectionParams.setSoTimeout(httpRequestParams,1000* 30);
        return httpRequestParams;

    }

}
