package com.tokproject.setrip.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.tokproject.setrip.R;
import com.tokproject.setrip.activity.CheckInActivity;


public class CheckFragment extends Fragment {

    private ImageView imgCheckin;
    private ImageView imgCheckout;


    public CheckFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_check, container, false);

        imgCheckin = view.findViewById(R.id.imgCheckin);
        imgCheckout = view.findViewById(R.id.imgCheckout);

        imgCheckin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CheckInActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}