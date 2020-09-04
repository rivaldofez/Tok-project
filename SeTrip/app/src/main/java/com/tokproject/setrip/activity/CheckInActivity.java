package com.tokproject.setrip.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.zxing.WriterException;
import com.tokproject.setrip.R;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class CheckInActivity extends AppCompatActivity {
    ImageView QRCode;
    Button btnGenerate;
    String uid;
    private FirebaseAuth firebaseAuth;

    private static final String TAG = "Checkin Activity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in);

        firebaseAuth = FirebaseAuth.getInstance();
        QRCode = findViewById(R.id.imgQR);
        btnGenerate = findViewById(R.id.btnGenerate);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();

        generateQR(uid);

        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateQR(uid);
            }
        });
    }

    public void generateQR(String data){
        QRGEncoder qrgEncoder = new QRGEncoder(data, null, QRGContents.Type.TEXT, 600);
        Bitmap bitmap = qrgEncoder.getBitmap();
        QRCode.setImageBitmap(bitmap);
    }
}