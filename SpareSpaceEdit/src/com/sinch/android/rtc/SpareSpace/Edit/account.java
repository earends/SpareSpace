package com.sinch.android.rtc.SpareSpace.Edit;

/*
Class: Account
Description: This class managaes the account activity -
- the user is able to look at their postings, move on to the messaging activity.
- shows username and profile pic if there is one
 */

import android.accounts.Account;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.sinch.android.rtc.SinchError;
import com.sinch.android.rtc.sample.messaging.R;

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
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

//in use currently
public class account extends Activity  {

    public static account self;
    public static TextView nameID;
    private static final int SELECT_FILE = 1;
    private static final int REQUEST_IMAGE_CAPTURE = 0;
    private ImageView userImage;
    private int Jlength;
    private int check;
    private ArrayList<Bitmap> bitList;
    private ArrayList<String> usernames;
    private ArrayList<String> titles;
    private ArrayList<String> descriptions;
    private ArrayList<String> locations;
    private ArrayList<String> costs;
    private ArrayList<String> obos;
    private ArrayList<String> dimmensions;
    private ArrayList<String> phones;
    private ArrayList<String> emails;
    private ArrayList<String> image;
    private ArrayList<String> image2;
    private static final String SERVER_ADDRESS = "http://sparespace.netai.net/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        // intitialize variables
        nameID = (TextView) findViewById(R.id.name_id); // username imageview
        userImage = (ImageView) findViewById(R.id.imageView2); // profile pic iamgeview


        // reponse listener to grab postings off the server
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    if (success) {
                        Log.d("YOUR TAG", "SUCESS");

                        Jlength = jsonResponse.length()/11;
                        int leng = jsonResponse.length()/11;
                        //intitialize arrayLists

                        usernames = new ArrayList<String>();
                        titles = new ArrayList<String>();
                        descriptions = new ArrayList<String>();
                        locations = new ArrayList<String>();
                        costs = new ArrayList<String>();
                        obos = new ArrayList<String>();
                        dimmensions = new ArrayList<String>();
                        phones = new ArrayList<String>();
                        emails = new ArrayList<String>();
                        image = new ArrayList<String>();
                        image2 = new ArrayList<String>();

                        String length = "";
                        int index = 0;
                        for (int i = 0; i < leng; i ++) {
                            check = i;
                            index = 11 * i;

                            Log.d("YOUR TAG","IN ARRAY");
                            length = Integer.toString(index);
                            usernames.add(jsonResponse.getString(length));
                            length = Integer.toString(index + 1);
                            titles.add( jsonResponse.getString(length));
                            length = Integer.toString(index+ 2);
                            descriptions.add(  jsonResponse.getString(length));
                            length = Integer.toString(index + 3);
                            locations.add( jsonResponse.getString(length));
                            length = Integer.toString(index + 4);
                            costs.add( jsonResponse.getString(length));
                            length = Integer.toString(index + 5);
                            obos.add( jsonResponse.getString(length));
                            length = Integer.toString(index + 6);
                            dimmensions.add( jsonResponse.getString(length));
                            length = Integer.toString(index + 7);
                            phones.add(  jsonResponse.getString(length));
                            length = Integer.toString(index + 8);
                            emails.add( jsonResponse.getString(length));
                            length = Integer.toString(index + 9);
                            image.add(  jsonResponse.getString(length));
                            length = Integer.toString(index + 10);
                            image2.add( jsonResponse.getString(length));
                            if (i == leng - 1) {
                                setupList();
                                new DownloadImage(Login.usernme + "p").execute();
                            }

                        }




                        //String name = jsonResponse.getString("name");
                        //int age = jsonResponse.getInt("age");
                        //String user = jsonResponse.getString("username");
                    } else {
                        Log.d("YOUR TAG", "FAIL");
                        AlertDialog.Builder builder = new AlertDialog.Builder(account.this);
                        builder.setMessage("Posting SYNC Failed")
                                .setNegativeButton("Retry", null)
                                .create()
                                .show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };


        Request request = new Request(null,null,null,4,null,null,null,null,null,null,null,null,null,null,1,"http://sparespace.netai.net/PostingsRead.php",responseListener);
        RequestQueue queue = Volley.newRequestQueue(account.this);
        queue.add(request);


    }




    public void setupList() {
        ArrayList<Integer> index = new ArrayList<Integer>();
        // grabs the index(s) at which your user has a posting
        for (int i = 0; i < usernames.size(); i ++) {
            if (usernames.get(i).equals(Login.usernme)) {
                index.add(i);
            }
        }
        // adds the titles to its own array List
        ArrayList<String> accountTitles = new ArrayList<String>();
        for (int i = 0; i < index.size(); i ++) {
            accountTitles.add(titles.get(index.get(i)));
        }
        //creates a listView for specific user accounts
        ListView list = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, accountTitles);
        list.setAdapter(adapter);

        //new DownloadImage(Login.usernme + "profilePic");

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(account.this, "You Clicked at " + titles.get(+position) + " " + position, Toast.LENGTH_SHORT).show();
            }
        });
    }


    /**
     * Sets the Textview to the right of the icon to the name of the logged in user
     */
    public void setUp() {
        Log.d(Home_page.userName,"TAG");
        nameID = (TextView) findViewById(R.id.name_id);
        nameID.setText(Home_page.userName);
    }


    /**
     * if pressed goes to the settings activity
     * @param v
     */
    public void settings_click(View v) {
        Intent i = new Intent(getApplicationContext(),settings.class);
        startActivity(i);
    }

    public void picture_click(View v) {
        selectImage();
    }

    /**
     * @name selctImage
     * @Description: creates a dialog box with the options to take or upload images to selected Image Views
     */
    private void selectImage() {
        final CharSequence[] items = { "Take Photo", "Choose from Library", "Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(account.this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                //boolean result = Utility.checkPermission(sell.this);
                if (items[item].equals("Take Photo")) {
                    cameraIntent();
                } else if (items[item].equals("Choose from Library")) {
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
                userImage.setImageBitmap(imageBitmap);
                new UploadImage(imageBitmap,Login.usernme + "p").execute();

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
        userImage.setImageBitmap(bm);
        new UploadImage(bm,Login.usernme + "p").execute();
    }

    public void msg_click(View view) {
        Intent i = new Intent(getApplicationContext(),ListUsers.class);
        startActivity(i);
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
                userImage.setImageBitmap(bitmap);
            }
        }
    }


    private HttpParams getHttpRequestParams() {
        HttpParams httpRequestParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpRequestParams,1000* 30);
        HttpConnectionParams.setSoTimeout(httpRequestParams,1000* 30);
        return httpRequestParams;

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





}
