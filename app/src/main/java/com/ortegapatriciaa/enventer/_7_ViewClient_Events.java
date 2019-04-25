package com.ortegapatriciaa.enventer;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ortegapatriciaa on 7/10/2017.
 */

public class _7_ViewClient_Events extends Fragment {

    View myView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView=inflater.inflate(R.layout.activity__7__view_client_events, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("My Events");
        return myView;
    }
}

