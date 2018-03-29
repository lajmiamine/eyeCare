package com.example.hamza.imageupload;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Splashscreen extends Activity {

    private TextView textView;
    private Animation animSlide;
    public static String prefixe="830a4fda";
    private Animation animSlideSlogan;
    private TextView textViewSlogan;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        setContentView(R.layout.activity_splashscreen);

        // Refer the ImageView like this
        textView = (TextView) findViewById(R.id.appName);

        // Load the animation like this
        animSlide = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide);

        // Start the animation like this
        //textView.startAnimation(animSlide);



        // Refer the ImageView like this
        textViewSlogan = (TextView) findViewById(R.id.appSlogan);

        // Load the animation like this
        animSlideSlogan = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide2);

        // Start the animation like this
        textViewSlogan.startAnimation(animSlideSlogan);


        final Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
            }
        }, 500);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                sharedPreferences = getSharedPreferences("VALUES", Context.MODE_PRIVATE);
                int user = sharedPreferences.getInt("USERID", 0);
                if (user!=0){
                    Intent myIntent = new Intent(Splashscreen.this, MainActivity.class);
                    Splashscreen.this.startActivity(myIntent);
                } else {
                    Intent myIntent = new Intent(Splashscreen.this, IntroActivity.class);
                    Splashscreen.this.startActivity(myIntent);
                }
                finish();
            }
        }, 1800);
    }
}
