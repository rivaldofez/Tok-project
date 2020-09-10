package com.tokproject.setrip.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.tokproject.setrip.R;
import com.tokproject.setrip.activity.CheckInActivity;
import com.tokproject.setrip.activity.CheckoutActivity;


public class CheckFragment extends Fragment {

    private RelativeLayout containerCheckin;
    private RelativeLayout containerCheckout;


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
}