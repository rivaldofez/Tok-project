package com.tokproject.setrip.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SearchView;

import com.bumptech.glide.Glide;
import com.tokproject.setrip.R;
import com.tokproject.setrip.activity.AddPostActivity;
import com.tokproject.setrip.activity.CheckInActivity;
import com.tokproject.setrip.activity.CheckoutActivity;
import com.tokproject.setrip.activity.HistoryTrip;


public class CheckFragment extends Fragment {

    private RelativeLayout containerCheckin;
    private RelativeLayout containerCheckout;

    private ImageView imgCheckin, imgCheckout;

    public CheckFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.fragment_check, container, false);

        containerCheckin = view.findViewById(R.id.containerCheckin);
        containerCheckout = view.findViewById(R.id.containerCheckout);

        imgCheckin = view.findViewById(R.id.imgCheckin);
        imgCheckout = view.findViewById(R.id.imgCheckout);

        Glide.with(getActivity()).load(R.drawable.checkin).into(imgCheckin);
        Glide.with(getActivity()).load(R.drawable.checkout).into(imgCheckout);

        containerCheckin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CheckInActivity.class);
                startActivity(intent);
            }
        });

        containerCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CheckoutActivity.class);
                startActivity(intent);
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
        inflater.inflate(R.menu.menu_check, menu);

        MenuItem item = menu.findItem(R.id.option_history);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        if(id == R.id.option_history) {
            startActivity(new Intent(getActivity(), HistoryTrip.class));
        }
        return super.onOptionsItemSelected(item);
    }
}