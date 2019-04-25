package com.ortegapatriciaa.enventer;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class _8_ViewEventMember extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    SharedPreferences settings;
    List<EventInfo> eventInfos;
    DatabaseReference databaseReference;
    ListView listViewEvents;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__8__view_event_member);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("E(n)venter: Events");


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //    .setAction("Action", null).show();
                Intent i = new Intent(_8_ViewEventMember.this, _11_EventAdd.class);
                startActivity(i);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        auth = FirebaseAuth.getInstance();
        String userUid = auth.getCurrentUser().getUid();

        databaseReference = FirebaseDatabase.getInstance().getReference("events_users").child(userUid);
        listViewEvents = (ListView) findViewById(R.id.listViewEvents);
        eventInfos = new ArrayList<>();

        // listViewEvents.setLongClickable(true);

        listViewEvents.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                EventInfo eventInfo = eventInfos.get(i);
                showUpdateDeleteDialog(eventInfo.getEventId(), eventInfo.getEventTitle());
                return true;
            }
        });

        listViewEvents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(_8_ViewEventMember.this, EventDetails.class);


                //startActivity(new Intent(_8_ViewEventMember.this, EventDetails.class));
            }
        });
    }

    private void showUpdateDeleteDialog(final String eventId, String eventTitle){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.update_delete_dialog, null);
        dialogBuilder.setView(dialogView);

        final Button buttonUpdate = (Button) dialogView.findViewById(R.id.buttonUpdateEvent);
        final Button buttonDelete = (Button) dialogView.findViewById(R.id.buttonDeleteEvent);

        dialogBuilder.setTitle(eventTitle);
        final AlertDialog b = dialogBuilder.create();
        b.show();

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteEvent(eventId);
                b.dismiss();
            }
        });

    }

    private boolean deleteEvent (String id) {
        //getting the specified artist reference
        auth = FirebaseAuth.getInstance();
        String userUid1 = auth.getCurrentUser().getUid();
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("events_users").child(userUid1).child(id);
        DatabaseReference dR1 = FirebaseDatabase.getInstance().getReference("events").child(id);

        //removing artist
        dR.removeValue();
        dR1.removeValue();

        Toast.makeText(getApplicationContext(), "Event Deleted", Toast.LENGTH_LONG).show();

        return true;
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

                    eventInfos.add(eventInfo);
                }

                EventInfo_Adapter eventInfoAdapter = new EventInfo_Adapter(_8_ViewEventMember.this, eventInfos);
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

        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu._8__view_event_member, menu);
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


        if (id == R.id.nav_em_profile) {
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame,new _8_ViewEventMember_Home())
                    .commit();

        }
        else if (id == R.id.nav_em_profile) {
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame,new _8_ViewEventMember_Profile())
                    .commit();

        } else if (id == R.id.nav_em_staffs) {
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame,new _8_ViewEventMember_Staffs())
                    .commit();
        }
        else if (id == R.id.nav_em_events) {
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame,new _8_ViewEventMember_Events())
                    .commit();
        }

        else if (id == R.id.nav_em_feedback) {
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame,new _8_ViewEventMember_Feedback())
                    .commit();
        }

        else if (id == R.id.nav_em_about) {
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame,new _8_ViewEventMember_AboutUs())
                    .commit();
        }

        else if (id == R.id.nav_em_logout) {
            //new AlertDialog.Builder(this)
            AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.Theme_AppCompat_Dialog_Alert);
            builder.setIcon(android.R.drawable.ic_dialog_alert);
            builder .setTitle("Logout")
                    .setMessage("\nAre you sure you want to Logout?")
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

        auth.signOut();
        Intent logout=new Intent(this,_2_ChooseUser.class);
        logout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(logout);
        this.finish();
    }

}
