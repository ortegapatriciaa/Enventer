package com.ortegapatriciaa.enventer;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.app.Activity;
import android.text.format.DateFormat;
import android.view.Menu;

import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;

import android.app.AlertDialog;
import android.icu.text.TimeZoneFormat;
import android.widget.Toast;

import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

public class _11_EventAdd extends AppCompatActivity {

    AccountInfo accountInfo;
    private Calendar cal;
    private int mHour, mMinute, mSeconds, mTimePicker;
    private int day;
    private int month;
    private int year;

    EditText eventTitle, editTextdateStart, editTextTimeStart,
            editTextdateEnd, editTextTimeEnd, eventdescription;

    Button btnlocation, btnSave;
    ImageButton add_photo;

    LinearLayout linearL;
    ImageButton addStaff;

    ImageView imageView;

    List<EventInfo> events;
    ListView listViewEvents;


    int PLACE_PICKER_REQUEST = 1;
    private final int REQUEST_CODE_PLACEPICKER = 1;
    private final int RESULT_LOAD_IMAGE = 2;

    private DatabaseReference mDatabaseReference;
   private DatabaseReference mDatabaseReference1;
    FirebaseAuth auth;

    @SuppressWarnings("deprecation")
    @SuppressLint("SimpleDateFormat")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__11__event_add);
        getSupportActionBar().setTitle("Add Event: Information");

        Intent intent = getIntent();

        //SHOW CURRENT TIME

        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR);
        mMinute = c.get(Calendar.MINUTE);
        mSeconds = c.get(Calendar.SECOND);
        editTextTimeStart = (EditText) findViewById(R.id.textTimeStart);
        editTextTimeEnd = (EditText) findViewById(R.id.textTimeEnd);
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a", Locale.US);
        editTextTimeStart.setText(sdf.format(c.getTime()));
        editTextTimeEnd.setText(sdf.format(c.getTime()));

        eventTitle = (EditText)findViewById(R.id.eventTitle);
        eventdescription = (EditText)findViewById(R.id.event_description);

        imageView = (ImageView) findViewById(R.id.imgView);

        linearL = (LinearLayout) findViewById(R.id.add_staff);
        listViewEvents = (ListView)findViewById(R.id.listViewEvents);

        addStaff = (ImageButton) findViewById(R.id.btnaddstaff);

        btnSave = (Button)findViewById(R.id.btnsave);

        _12_EventAssign_LayoutOperation.add(this, addStaff);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference("events");
        mDatabaseReference1 = FirebaseDatabase.getInstance().getReference("events_users");
        events = new ArrayList<>();

        //TIME START
        // perform click event listener on edit text
        editTextTimeStart.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog mTimePicker;
                final Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);

                mTimePicker = new TimePickerDialog(_11_EventAdd.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {


                        String time = selectedHour + ":" + selectedMinute;

                        SimpleDateFormat fmt = new SimpleDateFormat("HH:mm");
                        java.util.Date date = null;
                        try {
                            date = fmt.parse(time);
                        } catch (ParseException e) {

                            e.printStackTrace();
                        }

                        SimpleDateFormat fmtOut = new SimpleDateFormat("hh:mm aa");

                        String formattedTime = fmtOut.format(date);

                        editTextTimeStart.setText(formattedTime);
                    }
                }, hour, minute, false);//No 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        //TIME END
        // perform click event listener on edit text
        editTextTimeEnd.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog mTimePicker;
                final Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);

                mTimePicker = new TimePickerDialog(_11_EventAdd.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {


                        String time = selectedHour + ":" + selectedMinute;

                        SimpleDateFormat fmt = new SimpleDateFormat("HH:mm");
                        java.util.Date date = null;
                        try {
                            date = fmt.parse(time);
                        } catch (ParseException e) {

                            e.printStackTrace();
                        }

                        SimpleDateFormat fmtOut = new SimpleDateFormat("hh:mm aa");

                        String formattedTime = fmtOut.format(date);

                        editTextTimeEnd.setText(formattedTime);
                    }
                }, hour, minute, false);//No 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        //DATE
        editTextdateStart = (EditText) findViewById(R.id.textDateStart);
        editTextdateEnd = (EditText) findViewById(R.id.textDateEnd);
        cal = Calendar.getInstance();
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH);
        year = cal.get(Calendar.YEAR);

        ///DATE START
        editTextdateStart.setText(new StringBuilder().append(day).append("/").append(month + 1).append("/").append(year));
        editTextdateStart.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                DateDialogStart();

            }
        });

        //DATE END

        editTextdateEnd.setText(new StringBuilder().append(day).append("/").append(month + 1).append("/").append(year));

        editTextdateEnd.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                DateDialogEnd();

            }
        });

        // add photo
        add_photo = (ImageButton) findViewById(R.id.add_photo);
        add_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });

        //get location
        btnlocation = (Button) findViewById(R.id.btnlocation);
        btnlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPlacePickerActivity();
            }

        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String title = eventTitle.getText().toString().trim();
                final String dateStart = editTextdateStart.getText().toString().trim();
                final String timeStart = editTextTimeStart.getText().toString().trim();
                final String dateEnd = editTextdateEnd.getText().toString().trim();
                final String timeEnd = editTextTimeEnd.getText().toString().trim();
                final String location = btnlocation.getText().toString().trim();
                final String description = eventdescription.getText().toString().trim();

                String userId = auth.getInstance().getCurrentUser().getUid();
                String id = mDatabaseReference.push().getKey();

                if (title.isEmpty()){
                    eventTitle.setError("This field cannot be blank");
                }
                if (description.isEmpty()){
                    eventdescription.setError("This field cannot be blank");
                }
                else{
                    EventInfo eventInfo = new EventInfo(id, title, dateStart, timeStart, dateEnd, timeEnd, location, description);
                    mDatabaseReference.child(id).setValue(eventInfo);
                    mDatabaseReference1.child(userId).child(id).setValue(eventInfo);
                    Toast.makeText(_11_EventAdd.this, "Successfully Added", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(_11_EventAdd.this, _8_ViewEventMember.class));
                    finish();
                }
            }
        });
    }

    @Override
    /*protected void onStart() {
        super.onStart();

        mDatabaseReference = FirebaseDatabase.getInstance().getReference("events");
        auth.getInstance().getCurrentUser().getUid();

        //mDatabaseReference1 = FirebaseDatabase.getInstance().getReference().child("events").child("userId");

        mDatabaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    EventInfo eventInfo = postSnapshot.getValue(EventInfo.class);

                    events.add(eventInfo);
                }

                EventsList eventsAdapter = new EventsList(_11_EventAdd.this, events);
                listViewEvents.setAdapter(eventsAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }*/

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch(requestCode){
            case REQUEST_CODE_PLACEPICKER:
                displaySelectedPlaceFromPlacePicker(data);

                break;

            case RESULT_LOAD_IMAGE:
                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String picturePath = cursor.getString(columnIndex);
                cursor.close();

                imageView = (ImageView) findViewById(R.id.imgView);
                imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));

                break;

        }
    }

    private void startPlacePickerActivity(){
        PlacePicker.IntentBuilder intentBuilder = new PlacePicker.IntentBuilder();

        try {
            Intent intent = intentBuilder.build(this);
            startActivityForResult(intent, REQUEST_CODE_PLACEPICKER);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void displaySelectedPlaceFromPlacePicker(Intent data){
        Place placeSelected = PlacePicker.getPlace(data, this);

        String name = placeSelected.getName().toString();
        String address = placeSelected.getAddress().toString();

        TextView selectedLocation = (TextView) findViewById(R.id.btnlocation);
        selectedLocation.setText(name + ", " + address);
    }

    public void DateDialogStart() {

        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                editTextdateStart.setText(new StringBuilder().append(monthOfYear + 1).append("/").append(dayOfMonth).append("/").append(year));
            }
        };

        DatePickerDialog dpDialog = new DatePickerDialog(this, listener, year, month, day);
        dpDialog.show();

    }

    public void DateDialogEnd() {

        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                editTextdateEnd.setText(new StringBuilder().append(monthOfYear + 1).append("/").append(dayOfMonth).append("/").append(year));

            }
        };

        DatePickerDialog dpDialog = new DatePickerDialog(this, listener, year, month, day);
        dpDialog.show();

    }

}