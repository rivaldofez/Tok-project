package com.tokproject.setrip.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

import com.tokproject.setrip.R;

import java.util.Locale;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        //inisiasi view
        final ProgressBar progressBar = findViewById(R.id.splashscreen_progressbar);
        progressBar.setVisibility(View.VISIBLE);

        setContentView(R.layout.splash_screen);
        //waktu splash screen
        int splashscreen_time = 3500;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //setelah waktu splashscreen habis, maka langsung berpindah ke Halaman Login
                progressBar.setVisibility(View.GONE);
                startActivity(new Intent(SplashScreen.this, Login.class));
                finish();
            }
        }, splashscreen_time);


    }
}