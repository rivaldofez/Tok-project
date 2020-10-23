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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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
import com.tokproject.setrip.adapter.AdapterTokoApd;
import com.tokproject.setrip.adapter.AdapterTourGuide;
import com.tokproject.setrip.model.ModelTokoApd;
import com.tokproject.setrip.model.ModelTourGuide;

import java.util.ArrayList;
import java.util.List;

public class TourGuideActivity extends AppCompatActivity {

    ActionBar actionBar;
    private FirebaseAuth firebaseAuth;

    private RecyclerView recyclerView;
    private List<ModelTourGuide> tourGuideList;
    private AdapterTourGuide adapterTourGuide;
    private Button karir;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_guide);

        actionBar = getSupportActionBar();
        actionBar.setTitle("Pemandu Wisata");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        //init
        firebaseAuth = FirebaseAuth.getInstance();
        karir = findViewById(R.id.karir_tourguide);

        //recyclerview and its properties
        recyclerView = findViewById(R.id.tgRecyclerview);
        progressBar =  findViewById(R.id.progressBar);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        //show newest postFirst
        linearLayoutManager.setStackFromEnd(true);
        linearLayoutManager.setReverseLayout(true);

        //set layout to recyclerView
        recyclerView.setLayoutManager(linearLayoutManager);

        //init post list
        tourGuideList = new ArrayList<>();

        showLoader(true);
        loadPost();

        karir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TourGuideActivity.this, AddTourGuideActivity.class));
            }
        });
    }

    private void loadPost() {
        //path of all posts
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Tour_guide");
        //getAll date from reference
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                tourGuideList.clear();

                for(DataSnapshot ds: snapshot.getChildren()) {
                    ModelTourGuide modelTourGuide = ds.getValue(ModelTourGuide.class);

                    tourGuideList.add(modelTourGuide);

                    //adapter
                    adapterTourGuide = new AdapterTourGuide(TourGuideActivity.this, tourGuideList);

                    //set adapter to recyclerView
                    recyclerView.setAdapter(adapterTourGuide);
                    showLoader(false);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //in case of error
                Toast.makeText(TourGuideActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
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
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.option_menu, menu);

        //hide add group icon
        menu.findItem(R.id.option_setting).setVisible(false);
        menu.findItem(R.id.option_language).setVisible(false);
        menu.findItem(R.id.option_add).setVisible(false);
        menu.findItem(R.id.option_my_toko).setVisible(false);
        menu.findItem(R.id.option_open_toko).setVisible(false);


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
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Tour_guide");
        //getAll date from reference
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                tourGuideList.clear();
                for(DataSnapshot ds: snapshot.getChildren()) {
                    ModelTourGuide modelTourGuide = ds.getValue(ModelTourGuide.class);

                    assert modelTourGuide != null;
                    if(modelTourGuide.getpTitle().toLowerCase().contains(query.toLowerCase())
                            || modelTourGuide.getpLocation().toLowerCase().contains(query.toLowerCase())
                            || modelTourGuide.getpDescription().toLowerCase().contains(query.toLowerCase())) {
                        tourGuideList.add(modelTourGuide);
                    }

                    //adapter
                    adapterTourGuide = new AdapterTourGuide(TourGuideActivity.this, tourGuideList);

                    //set adapter to recyclerView
                    recyclerView.setAdapter(adapterTourGuide);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //in case of error
                Toast.makeText(TourGuideActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


}