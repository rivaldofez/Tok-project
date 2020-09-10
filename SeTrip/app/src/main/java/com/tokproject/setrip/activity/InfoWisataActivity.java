package com.tokproject.setrip.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.tokproject.setrip.R;
import com.tokproject.setrip.adapter.AdapterWisata;
import com.tokproject.setrip.data.DummyWisata;
import com.tokproject.setrip.model.ModelWisata;

import java.util.ArrayList;

public class InfoWisataActivity extends AppCompatActivity {

    private ActionBar actionBar;
    private RecyclerView rv;
    private ArrayList<ModelWisata> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_wisata);

        actionBar = getSupportActionBar();
        actionBar.setTitle("Info Pariwisata");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        rv = findViewById(R.id.rv_hotel);
        rv.setHasFixedSize(true);



        list.addAll(DummyWisata.getListData());
        showRecyclerView();

    }

    private void showRecyclerView() {
        rv.setLayoutManager(new LinearLayoutManager(this));
        AdapterWisata hc = new AdapterWisata(list);
        rv.setAdapter(hc);

        hc.SetOnItemClickCallback(new AdapterWisata.OnItemClickCallback() {
            @Override
            public void onItemClicked(ModelWisata hc) {
                Intent i = new Intent(InfoWisataActivity.this, DetailInfoWisataActivity.class);
                i.putExtra(DetailInfoWisataActivity.EXTRA_FOTO, hc.getFoto());
                i.putExtra(DetailInfoWisataActivity.EXTRA_NAME, hc.getNama());
                i.putExtra(DetailInfoWisataActivity.EXTRA_DETAIL, hc.getDetail());
                i.putExtra(DetailInfoWisataActivity.EXTRA_LOKASI, hc.getLokasi());

                startActivity(i);
            }

        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}