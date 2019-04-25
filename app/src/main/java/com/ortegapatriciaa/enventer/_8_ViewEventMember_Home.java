package com.ortegapatriciaa.enventer;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ortegapatriciaa on 9/19/2017.
 */

public class _8_ViewEventMember_Home extends Fragment {

    View myView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.content__8__view_event_member, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Upcoming Events");

        return myView;
    }
}

