package com.tokproject.setrip.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.tokproject.setrip.R;
import com.tokproject.setrip.activity.InfoCovid19Activity;
import com.tokproject.setrip.activity.InfoWisataActivity;
import com.tokproject.setrip.activity.TokoApdActivity;


public class TourismFragment extends Fragment {


    RelativeLayout infoWisata, infoCovid, tokoApd;


    public TourismFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_tourism, container, false);

        infoWisata = view.findViewById(R.id.infoWisata);
        infoCovid = view.findViewById(R.id.infoCovid);
        tokoApd = view.findViewById(R.id.tokoApd);

        infoWisata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), InfoWisataActivity.class));
            }
        });

        infoCovid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), InfoCovid19Activity.class));
            }
        });

        tokoApd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), TokoApdActivity.class));
            }
        });

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.option_menu, menu);

        //hide add group icon
        menu.findItem(R.id.option_setting).setVisible(false);
        menu.findItem(R.id.option_add).setVisible(false);
        menu.findItem(R.id.action_search).setVisible(false);
        menu.findItem(R.id.option_open_toko).setVisible(false);
        menu.findItem(R.id.option_about_feature).setVisible(false);
        menu.findItem(R.id.option_my_toko).setVisible(false);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.option_language) {
                Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}