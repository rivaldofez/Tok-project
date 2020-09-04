package com.tokproject.setrip.activity;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tokproject.setrip.R;
import com.tokproject.setrip.fragment.ProfileFragment;

import java.util.HashMap;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.tokproject.setrip.R;


public class SettingProfileActivity extends AppCompatActivity {

    private ProgressBar progressBar;

    private ImageView logoApp;

    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    FirebaseDatabase database;
    DatabaseReference databaseReference;

    Dialog dialog;
    Button btnConfirm;
    Button btndismiss;
    EditText etChangeName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_profile);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle(R.string.setting_profile_page);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);


        logoApp = findViewById(R.id.logo_app);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("User");

        Glide.with(this).load(R.drawable.text_only)
                .into(logoApp);


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    private void showLoader(boolean b) {
        if(b) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    public void changeName(View view) {


        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_change_name);
        btnConfirm = findViewById(R.id.btn_ubah);
        btndismiss = findViewById(R.id.btn_dismiss_ubah);
        logoApp = findViewById(R.id.logo_app);
        etChangeName = findViewById(R.id.et_ubahnama);

        Glide.with(this).load(R.drawable.full).into(logoApp);

        btndismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etChangeName.getText().toString().trim();

                if(name.isEmpty()) {
                    Toast.makeText(SettingProfileActivity.this, R.string.invalid_name, Toast.LENGTH_SHORT).show();
                } else {
                    showLoader(true);

                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("username", name);



                    assert user != null;
                    databaseReference.child(user.getUid()).updateChildren(hashMap)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    showLoader(false);
                                    dialog.dismiss();
                                    Toast.makeText(SettingProfileActivity.this,  R.string.data_diperbarui, Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    showLoader(false);
                                    Toast.makeText(SettingProfileActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();

                                }
                            });
                }
            }
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

    }


    public void aboutApps(View view) {
        startActivity(new Intent(this, AboutApps.class));
    }


    public void logout(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.konfirmasi_keluar_aplikasi);
        builder.setIcon(R.drawable.ic_baseline_exit_to_app_24);
        builder.setMessage(R.string.apakah_anda_yakin);
        builder.setCancelable(false);

        builder.setPositiveButton(R.string.ya, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(SettingProfileActivity.this, Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                SettingProfileActivity.this.finish();
            }
        });

        builder.setNegativeButton(R.string.tidak, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void setAlarm(View view) {

    }
}