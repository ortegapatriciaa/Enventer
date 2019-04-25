package com.ortegapatriciaa.enventer;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ortegapatriciaa on 7/11/2017.
 */

public class _8_ViewEventMember_Profile extends Fragment {

    private FirebaseAuth auth;
    TextView name, email, password, bday, country, mobileno;

    View myView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.activity__8__view_event_member_profile, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Profile");

        name = (TextView) getActivity().findViewById(R.id.textName);
        email = (TextView) getActivity().findViewById(R.id.textEmail);
        password = (TextView) getActivity().findViewById(R.id.textPassword);
        bday = (TextView) getActivity().findViewById(R.id.textCountry);
        country = (TextView) getActivity().findViewById(R.id.textCountry);
        mobileno = (TextView) getActivity().findViewById(R.id.textMobileNumber);

        auth = FirebaseAuth.getInstance();



        return myView;
    }


}
