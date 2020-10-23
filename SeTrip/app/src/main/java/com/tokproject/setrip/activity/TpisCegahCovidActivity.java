package com.tokproject.setrip.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.tokproject.setrip.R;

public class TpisCegahCovidActivity extends AppCompatActivity {


    ActionBar actionBar;
    ImageView ilustrasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tpis_cegah_covid);

        actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle(R.string.tips_wisata_aman);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        ilustrasi = findViewById(R.id.ilustrasi);

        Glide.with(this).load(R.drawable.ilustrasi_cegah_covid).into(ilustrasi);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}