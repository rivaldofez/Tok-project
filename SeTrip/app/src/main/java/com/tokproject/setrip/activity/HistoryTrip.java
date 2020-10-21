package com.tokproject.setrip.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tokproject.setrip.R;
import com.tokproject.setrip.adapter.AdapterTrip;
import com.tokproject.setrip.model.ModelTrip;

import java.util.ArrayList;
import java.util.List;

public class HistoryTrip extends AppCompatActivity {

    List<ModelTrip> modelTrip;
    RecyclerView recyclerView;
    AdapterTrip adapterTrip;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;
    String uid;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_trip);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        uid = firebaseUser.getUid();

        actionBar = getSupportActionBar();
        actionBar.setTitle("History Trip");
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        modelTrip = new ArrayList<>();
        fetchHistory();

    }

    private void fetchHistory(){
        modelTrip.clear();
        databaseReference = FirebaseDatabase.getInstance().getReference("Trip").child(uid);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds:snapshot.getChildren()){
                    ModelTrip data = ds.getValue(ModelTrip.class);
                    modelTrip.add(data);
                }

                adapterTrip = new AdapterTrip(modelTrip);
                recyclerView.setAdapter(adapterTrip);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search_history,menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if(!TextUtils.isEmpty(s)) {
                    adapterTrip.getFilter().filter(s);
                } else {
                    fetchHistory();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if(!TextUtils.isEmpty(s)) {
                    adapterTrip.getFilter().filter(s);
                } else {
                    fetchHistory();
                }
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}