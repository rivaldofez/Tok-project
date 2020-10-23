package com.tokproject.setrip.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.tokproject.setrip.R;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class InfoCovid19Activity extends AppCompatActivity {

    private CardView laporCV, tipsWisataCV, tipsCegahCV;
    private TextView inveksi, sembuh, meninggal;
    private ActionBar actionBar;

    int positive;
    int recover;
    int death;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_covid19);

        laporCV = findViewById(R.id.lapor);
        tipsWisataCV = findViewById(R.id.tips_wisata);
        tipsCegahCV = findViewById(R.id.tips_cegah);

        inveksi = findViewById(R.id.infeksi);
        sembuh = findViewById(R.id.sembuh);
        meninggal = findViewById(R.id.meninggal);

        actionBar = getSupportActionBar();
        actionBar.setTitle("Info Covid-19");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);


        getDataCovid();

        laporCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                laporToHotlineCovid19();
            }
        });

        tipsWisataCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tipsWisataAman();
            }
        });

        tipsCegahCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tipsCegahCovid19();
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    private void getDataCovid() {

        String url = "https://data.covid19.go.id/public/api/update.json";

        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("Authorization", "token fe20ff8bf90a61d6dfa9ff53f732ed6190eeb85f");
        client.addHeader("User-Agent", "request");
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                //jika koneksi berhasil


                String result = new String(responseBody);

                try {
                    JSONObject responseObject = new JSONObject(result);
                    positive = responseObject.getJSONObject("update").getJSONObject("total").getInt("jumlah_positif");
                    recover = responseObject.getJSONObject("update").getJSONObject("total").getInt("jumlah_sembuh");
                    death = responseObject.getJSONObject("update").getJSONObject("total").getInt("jumlah_meninggal");

                    inveksi.setText(String.valueOf(positive));
                    sembuh.setText(String.valueOf(recover));
                    meninggal.setText(String.valueOf(death));

                }catch (Exception e){
                    Toast.makeText(InfoCovid19Activity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                //jika koneksi gagal


                String errorMsg;
                switch (statusCode) {
                    case 401 :
                        errorMsg = statusCode + " : Bad Requests";
                        break;

                    case 403 :
                        errorMsg = statusCode + " : Forbidden";
                        break;

                    case 404 :
                        errorMsg = statusCode + " : Not Found";
                        break;

                    default:
                        errorMsg = statusCode + " : " +error.getMessage();
                }
                Toast.makeText(InfoCovid19Activity.this, errorMsg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void laporToHotlineCovid19() {
        String phoneNbr = "112";
        Intent k = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNbr));
        startActivity(k);
    }

    private void tipsWisataAman() {
        startActivity(new Intent(this, TipsWisataAmanActivity.class));
    }

    private void tipsCegahCovid19() {
        startActivity(new Intent(this, TpisCegahCovidActivity.class));
    }
}