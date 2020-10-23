package com.tokproject.setrip.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tokproject.setrip.R;
import com.tokproject.setrip.adapter.AdapterPosts;
import com.tokproject.setrip.adapter.AdapterTokoApd;
import com.tokproject.setrip.model.ModelPost;
import com.tokproject.setrip.model.ModelTokoApd;

import java.util.ArrayList;
import java.util.List;

public class TokoApdActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    private RecyclerView recyclerView;
    private List<ModelTokoApd> tokoApdList;
    private AdapterTokoApd adapterTokoApd;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toko_apd);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Toko Alat Pelindung Diri");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        //init
        firebaseAuth = FirebaseAuth.getInstance();

        //recyclerview and its properties
        recyclerView = findViewById(R.id.tokoRecyclerview);
        progressBar =  findViewById(R.id.progressBar);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        //show newest postFirst
        linearLayoutManager.setStackFromEnd(true);
        linearLayoutManager.setReverseLayout(true);

        //set layout to recyclerView
        recyclerView.setLayoutManager(linearLayoutManager);

        //init post list
        tokoApdList = new ArrayList<>();

        showLoader(true);
        loadPost();

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    private void loadPost() {
        //path of all posts
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Toko");
        //getAll date from reference
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                tokoApdList.clear();

                for(DataSnapshot ds: snapshot.getChildren()) {
                    ModelTokoApd modelAddToko = ds.getValue(ModelTokoApd.class);

                    tokoApdList.add(modelAddToko);

                    //adapter
                    adapterTokoApd = new AdapterTokoApd(TokoApdActivity.this, tokoApdList);

                    //set adapter to recyclerView
                    recyclerView.setAdapter(adapterTokoApd);
                    showLoader(false);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //in case of error
                Toast.makeText(TokoApdActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showLoader(boolean b) {
        if(b) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.option_menu, menu);

        //hide add group icon
        menu.findItem(R.id.option_setting).setVisible(false);
        menu.findItem(R.id.option_language).setVisible(false);
        menu.findItem(R.id.option_add).setVisible(false);

        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);

        //search lisitener
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //called when user press search button
                if(!TextUtils.isEmpty(query)) {
                    searchPosts(query);
                } else {
                    loadPost();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //called realtime search / AJAX
                if(!TextUtils.isEmpty(newText)) {
                    searchPosts(newText);
                } else {
                    loadPost();
                }
                return false;
            }
        });

        return true;
    }

    private void searchPosts(final String query) {
        //path of all posts
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Toko");
        //getAll date from reference
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                tokoApdList.clear();
                for(DataSnapshot ds: snapshot.getChildren()) {
                    ModelTokoApd modelTokoApd = ds.getValue(ModelTokoApd.class);

                    assert modelTokoApd != null;
                    if(modelTokoApd.getpTitle().toLowerCase().contains(query.toLowerCase())
                            || modelTokoApd.getpLocation().toLowerCase().contains(query.toLowerCase())
                            || modelTokoApd.getpPrice().toLowerCase().contains(query.toLowerCase())
                            || modelTokoApd.getpDescription().toLowerCase().contains(query.toLowerCase())) {
                        tokoApdList.add(modelTokoApd);
                    }

                    //adapter
                    adapterTokoApd = new AdapterTokoApd(TokoApdActivity.this, tokoApdList);

                    //set adapter to recyclerView
                    recyclerView.setAdapter(adapterTokoApd);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //in case of error
                Toast.makeText(TokoApdActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        switch (id) {
            case R.id.option_open_toko:
                startActivity(new Intent(TokoApdActivity.this, AddTokoApdActivity.class));
                break;

            case R.id.option_my_toko:
                Toast.makeText(TokoApdActivity.this, "Fitur Sedang Dikembangkan", Toast.LENGTH_SHORT).show();
                break;

            case R.id.option_about_feature:
                aboutFeature();
        }
        return super.onOptionsItemSelected(item);
    }

    private void aboutFeature() {

    }
}