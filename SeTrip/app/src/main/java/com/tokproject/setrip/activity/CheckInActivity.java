package com.tokproject.setrip.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.zxing.WriterException;
import com.tokproject.setrip.R;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class CheckInActivity extends AppCompatActivity {
    ImageView QRCode;
    Button btnGenerate;

    private static final String TAG = "Checkin Activity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in);

        QRCode = findViewById(R.id.imgQR);
        btnGenerate = findViewById(R.id.btnGenerate);

        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String data = "1234567890";
                QRGEncoder qrgEncoder = new QRGEncoder(data, null, QRGContents.Type.TEXT, 600);
                    Bitmap bitmap = qrgEncoder.getBitmap();
                    QRCode.setImageBitmap(bitmap);
            }
        });
    }
}