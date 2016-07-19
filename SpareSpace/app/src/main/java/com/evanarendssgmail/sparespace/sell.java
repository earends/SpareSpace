package com.evanarendssgmail.sparespace;
/*
Description: This class is meant for the user to be able to uplaod a posting to their account class, as well as the home page.
The picture and detials of the post will be uploaded to the server
 */
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class sell extends Activity {

    private static final int SELECT_FILE = 1;
    //private static final int REQUEST_CAMERA = 1;
    private static final int REQUEST_IMAGE_CAPTURE = 0;
    //private static final int RESULT_LOAD_IMAGE = 1;
    private static final String SERVER_ADDRESS = "http://sparespace.netai.net/";
    private String userChoosenTask;
    private ImageView view;
    private ImageView view2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);
        view = (ImageView) findViewById(R.id.upload_1);
        view2 = (ImageView) findViewById(R.id.upload_2);


    }


    /**
     * if the upload button is pressed open dialog box for options of uploading image
     * @param v
     */
    public void upload_click(View v) {
        Log.d("YOUR TAG" ,"manual click");
        selectImage();
    }

    /**
     * if the finish button is clicked upload the postinf details to the server
     * @param v
     */
    public void finish_click(View v) {
        Log.d(Home_page.userName,"USERNAME");
        Bitmap image = ((BitmapDrawable)view.getDrawable()).getBitmap();
        new UploadImage(image,Home_page.userName).execute();
    }

    /**
     * @name selctImage
     * @Description: creates a dialog box with the options to take or upload images to selected Image Views
     */
    private void selectImage() {
        final CharSequence[] items = { "Take Photo", "Choose from Library", "Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(sell.this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                //boolean result = Utility.checkPermission(sell.this);
                if (items[item].equals("Take Photo")) {
                    userChoosenTask = "Take Photo";
                    cameraIntent();
                } else if (items[item].equals("Choose from Library")) {
                    userChoosenTask="Choose from Library";
                    galleryIntent();
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    /**
     * @Description Creates an intent to use the camera and open its features
     */
    private void cameraIntent() {
        /*
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
        */
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }

        
    }

    /**
     * @Description creates an intent to use your phones picture gallery features
     */
    private void galleryIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"),SELECT_FILE);
        
    }

    /**
     * @Description depending on whether or not you chose to take or upload an image it would place
     * .. that image into an image view
     * @param requestCode
     * @param resultCode
     * @param data
     */
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            //if choose to take image from gallery
            if (resultCode == Activity.RESULT_OK) {
                if (requestCode == SELECT_FILE)
                    onSelectFromGalleryResult(data);
                // if chose to take image
                else if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
                    Bundle extras = data.getExtras();
                    Bitmap imageBitmap = (Bitmap) extras.get("data");
                    view2.setImageBitmap(imageBitmap);
                }
            }
        }

    @SuppressWarnings("deprecation")
    /**
     * @Description: Once you select the image from your phone gallery place it into imageView
     */
    private void onSelectFromGalleryResult(Intent data) {
        Bitmap bm=null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        view.setImageBitmap(bm);
    }

    /**
     * @Name: uploadImage - class
     * @Description: manages the uploading of images to the server
     */
    private class UploadImage extends AsyncTask<Void,Void,Void> {

        Bitmap image;
        String name;

        /**
         *
         * @param image
         * @param name
         * @Description: Will upload image to server with a name associated with it
         */
        public UploadImage(Bitmap image, String name) {
            Log.d("YOUR TAG", "INITATE UPLOADiMAGE");
            this.image = image;
            this.name = name;
        }

        @Override
        /**
         * @dESCRIPTION: Takes care of initiating a request to the server
         */
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
    } // end of UploadImage class

    /**
     * @Name: HTTP REQUEST PARAMS
     * @return
     */
    private HttpParams getHttpRequestParams() {
        HttpParams httpRequestParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpRequestParams,1000* 30);
        HttpConnectionParams.setSoTimeout(httpRequestParams,1000* 30);
        return httpRequestParams;

    }





} // end bracket
