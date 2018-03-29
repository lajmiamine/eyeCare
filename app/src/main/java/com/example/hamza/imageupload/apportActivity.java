package com.example.hamza.imageupload;

import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;

public class apportActivity extends Activity {

    private String urlPost;
    private ProgressDialog progressDialog;
    private int check;
    private int id;
    private String name;
    private String username;
    private String rapport;
    private String pourc;
    TextView namet, lastnamet, pourct, rapportt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apport);

        namet = (TextView) findViewById(R.id.name);
        lastnamet = (TextView) findViewById(R.id.lastname);
        pourct = (TextView) findViewById(R.id.pourcentage);
        rapportt = (TextView) findViewById(R.id.rapport);
        SharedPreferences sharedPreferences = getSharedPreferences("VALUES", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("NAME", "bla");
        urlPost="http://f85bb8bc.ngrok.io/androidfileupload/result.php?username="+username;
        Log.e("url",urlPost);
        new AsyncTaskNewsParseJson().execute(urlPost);
    }


    public class AsyncTaskNewsParseJson extends AsyncTask<String, String, String> {


        @Override
        protected void onPreExecute() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    progressDialog = new ProgressDialog(apportActivity.this);
                    progressDialog.setIndeterminate(true);
                    progressDialog.setMessage("Authenticating...");
                    apportActivity.this.progressDialog.show();
                }
            });
        }

        @Override
        protected String doInBackground(String... url) {

            urlPost = url[0];
            try {
                JSONObject jsonObjectDesignPosts = JsonParser.readJsonFromUrl(urlPost);
                check = jsonObjectDesignPosts.getInt("count");
                if (check==0){
                }else {
                    JSONObject user=jsonObjectDesignPosts.getJSONObject(""+check);
                    id=user.getInt("id");
                    name=user.getString("username");
                    username=user.getString("lastname");
                    rapport=user.getString("rapport");
                    pourc=user.getString("pourcentage");
                    Log.e("pour",pourc);
                }

            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        // Set facebook items to the textviews and imageviews
        @Override
        protected void onPostExecute(String result) {
            progressDialog.dismiss();
            namet.setText(name);
            lastnamet.setText(username);
            pourct.setText(pourc);
            rapportt.setText(rapport);
        }
    }

}
