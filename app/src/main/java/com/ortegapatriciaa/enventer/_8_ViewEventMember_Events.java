package com.ortegapatriciaa.enventer;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

/**
 * Created by ortegapatriciaa on 7/11/2017.
 */

public class _8_ViewEventMember_Events extends Fragment {
    ListView listView;
    ArrayList<String> list = new ArrayList<>();
    View myView;
    DatabaseReference db;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.activity__8__view_event_member_events, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Events");
        listView = (ListView)getActivity().findViewById(R.id.listView);
        return myView;
    }
}
