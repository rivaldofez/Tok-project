package com.tokproject.setrip.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.tokproject.setrip.R;
import com.tokproject.setrip.activity.Login;


public class ProfileFragment extends Fragment {

    private Button btnLogout;
    private FirebaseAuth firebaseAuth;

    String uid;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_profile, container, false);

        btnLogout = view.findViewById(R.id.btnLogout);
        firebaseAuth = FirebaseAuth.getInstance();

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                checkUserStatus();
            }
        });

        return view;
    }

    private void checkUserStatus() {
        //get current user
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null) {
            //user is signed in
            uid = user.getUid();
        } else {
            startActivity(new Intent(getActivity(), Login.class));
            getActivity().finish();
        }
    }
}