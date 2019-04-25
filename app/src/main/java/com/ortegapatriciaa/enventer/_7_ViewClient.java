package com.ortegapatriciaa.enventer;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class _7_ViewClient extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String mTitle;
    List<EventInfo> eventInfos;
    DatabaseReference databaseReference;
    ListView listViewEvents;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__7__view_client);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Upcoming Events");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        databaseReference = FirebaseDatabase.getInstance().getReference("events");

        auth = FirebaseAuth.getInstance();

        listViewEvents = (ListView) findViewById(R.id.listViewArtists);

        eventInfos = new ArrayList<>();

    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                eventInfos.clear();


                for (DataSnapshot postSnapshot:  dataSnapshot.getChildren()){
                    EventInfo eventInfo = postSnapshot.getValue(EventInfo.class);
                    databaseReference.child("events").orderByChild("dateStart"  );
                    eventInfos.add(eventInfo);
                }

                EventInfo_Adapter eventInfoAdapter = new EventInfo_Adapter(_7_ViewClient.this, eventInfos);
                listViewEvents.setAdapter(eventInfoAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu._7__view_client, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        android.app.FragmentManager fragmentManager=getFragmentManager();

        if (id == R.id.nav_clie_home) {
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame,new _7_ViewClient_Home())
                    .commit();
            //    mTitle = "My Events";
        }
        else if (id == R.id.nav_clie_events) {
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame,new _7_ViewClient_Events())
                    .commit();
        //    mTitle = "My Events";
        }
        else if (id == R.id.nav_clie_feedback) {
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame,new _7_ViewClient_Feedback())
                    .commit();
        }

        else if (id == R.id.nav_clie_about) {
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame,new _7_ViewClient_AboutUs())
                    .commit();
        }
        else if (id == R.id.nav_clie_exit) {
            //new AlertDialog.Builder(this)
            AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.Theme_AppCompat_Dialog_Alert);
            builder.setIcon(android.R.drawable.ic_dialog_alert);
            builder .setTitle("Logout")
                    .setMessage("\nAre you sure you want to exit?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            logoutUser();
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void logoutUser() {

        Intent logout = new Intent(this, _2_ChooseUser.class);
        logout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(logout);
        this.finish();
        //sleep(5000);
    }

}