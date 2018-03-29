package com.example.hamza.imageupload;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginActivity extends Activity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    String urlPost;
    int check;

    @Bind(R.id.input_email)
    EditText _emailText;
    @Bind(R.id.input_password)
    EditText _passwordText;
    @Bind(R.id.btn_login)
    Button _loginButton;
    @Bind(R.id.link_signup)
    TextView _signupLink;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    ProgressDialog progressDialog;
    public String name;
    public String lastname;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });
    }

    public void login() {
        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        urlPost = "http://"+Splashscreen.prefixe+".ngrok.io/NikeApp/login.php?pass="+password+"&email="+email;
        Log.d("url",urlPost);
        new AsyncTaskNewsParseJson().execute(urlPost);

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        //
                    }
                }, 3000);
    }

    public class AsyncTaskNewsParseJson extends AsyncTask<String, String, String> {


        @Override
        protected void onPreExecute() {
            LoginActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    progressDialog.show();
                }
            });
        }

        @Override
        protected String doInBackground(String... url) {

            urlPost = url[0];
            try {
                JSONObject jsonObjectDesignPosts = JsonParser.readJsonFromUrl(urlPost);
                check = jsonObjectDesignPosts.getInt("result");
                if (check==0){
                }else {
                    JSONObject user=jsonObjectDesignPosts.getJSONObject("1");
                    int id=user.getInt("id");
                    name=user.getString("Name");
                    String username=user.getString("Email");
                    lastname=user.getString("LastName");
                    String profilePictureImgPath=user.getString("profilePictureImgPath");
                    sharedPreferences = getSharedPreferences("VALUES", Context.MODE_PRIVATE);
                    editor = sharedPreferences.edit();
                    editor.putInt("USERID", id);
                    editor.putString("NAME",name);
                    editor.putString("LASTNAME",lastname);
                    editor.putString("USERNAME",username);
                    editor.commit();
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
            if (check==1){
                Toast.makeText(LoginActivity.this, "Login successful",
                        Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(LoginActivity.this, MainActivity.class);
                LoginActivity.this.startActivity(myIntent);
            } else {
                Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                //this.finish();
            }
        }
    }

}
