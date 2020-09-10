package com.tokproject.setrip.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.tokproject.setrip.R;

public class DetailInfoWisataActivity extends AppCompatActivity {

    ImageView img;
    TextView tv_name, tv_detail, tv_lokasi;

    Button btnTandai, btnLike, btnShare;

    public static final String EXTRA_NAME = "extra_name";
    public static final String EXTRA_DETAIL = "extra_detail";
    public static final String EXTRA_FOTO = "extra_foto";
    public  static  final String EXTRA_LOKASI = "extra_lokasi";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_info_wisata);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("Detail Info Pariwisata");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        final String name = getIntent().getStringExtra(EXTRA_NAME);
        String detail = getIntent().getStringExtra(EXTRA_DETAIL);
        String lokasi = getIntent().getStringExtra(EXTRA_LOKASI);

        img = findViewById(R.id.img_item_foto);
        tv_name = findViewById(R.id.tv_hotelName);
        tv_detail = findViewById(R.id.tv_hotelDetail);
        tv_lokasi = findViewById(R.id.tv_lokasiHotel);

        btnTandai = findViewById(R.id.bt_pesan);
        btnLike = findViewById(R.id.bt_whistlist);
        btnShare = findViewById(R.id.bt_chat);

        Glide.with(this).load(getIntent().getIntExtra(EXTRA_FOTO, 0))
                .error(R.drawable.ic_baseline_face_24)
                .placeholder(R.drawable.ic_baseline_face_24)
                .into(img);
        tv_name.setText(name);
        tv_detail.setText(detail);
        tv_lokasi.setText(lokasi);

        btnTandai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnTandai.setBackgroundResource(R.drawable.rounded_btn);
                btnTandai.setTextColor(Color.parseColor("#FFFFFF"));
                Toast.makeText(getApplicationContext(), "Kamu Menandai " + name, Toast.LENGTH_SHORT).show();
            }
        });

        btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnLike.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_baseline_favorite_24, 0,0,0);
                Toast.makeText(getApplicationContext(), "Kamu Menyukai " + name, Toast.LENGTH_SHORT).show();
            }
        });

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Kamu Membagikan " + name, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}