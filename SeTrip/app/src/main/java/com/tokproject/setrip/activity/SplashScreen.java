package com.tokproject.setrip.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.tokproject.setrip.DetectorActivity;
import com.tokproject.setrip.R;

import java.util.Locale;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        ImageView imageView = findViewById(R.id.logo_app);

        Glide.with(this).load(R.drawable.full).into(imageView);

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
                startActivity(new Intent(SplashScreen.this, DetectorActivity.class));
                finish();
            }
        }, splashscreen_time);


    }
}