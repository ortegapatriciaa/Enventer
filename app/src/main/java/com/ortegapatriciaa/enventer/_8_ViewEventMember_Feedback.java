package com.ortegapatriciaa.enventer;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ortegapatriciaa on 7/11/2017.
 */

public class _8_ViewEventMember_Feedback extends Fragment {

    View myView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.activity__8__view_event_member_feedback, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Feedback");
        return myView;
    }
}
