package com.ortegapatriciaa.enventer;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class _5_SignupEventMember extends AppCompatActivity {

    public Spinner spinner;
    public Spinner spinner_country;
    public Button signUp;

    EditText em_firstname;
    EditText em_lastname;
    EditText em_email;
    EditText em_password;
    EditText em_cpassword;
    EditText em_birthday;
    EditText em_mobile;

    DatabaseReference mDatabaseReference;
    private FirebaseAuth auth;

    private Calendar cal;
    private int day;
    private int month;
    private int year;

    //  FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__5__signup_event_member);

        auth = FirebaseAuth.getInstance();

        em_firstname = (EditText) findViewById(R.id.signup_em_firstname);
        em_lastname = (EditText) findViewById(R.id.signup_em_lastname);
        em_email = (EditText) findViewById(R.id.signup_em_email);
        em_password = (EditText) findViewById(R.id.signup_em_password);
        em_cpassword = (EditText) findViewById(R.id.signup_em_confirmpassword);
        em_birthday = (EditText) findViewById(R.id.signup_em_bday);
        em_mobile = (EditText) findViewById(R.id.signup_em_mobilenum);

        spinner_country = (Spinner) findViewById(R.id.spinner_em_country);
        signUp = (Button) findViewById(R.id.button_signup_event);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference("accounts");

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String em_emailadd = em_email.getText().toString().trim();
                final String em_passw = em_password.getText().toString().trim();

                final String em_fname = em_firstname.getText().toString().trim();
                final String em_lname = em_lastname.getText().toString().trim();
                final String em_cpassw = em_cpassword.getText().toString().trim();
                final String em_bday = em_birthday.getText().toString().trim();
                final String em_mobileno = em_mobile.getText().toString().trim();

                final String em_country = spinner_country.getSelectedItem().toString();

                final String fullname = em_fname.concat(" ").concat(em_lname);

                final String userID = mDatabaseReference.push().getKey();
                final String userID1 = auth.getInstance().getCurrentUser().getUid();

                if (TextUtils.isEmpty(em_fname)) {
                    Toast.makeText(getApplicationContext(), "Field cannot be empty", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(em_lname)) {
                    Toast.makeText(getApplicationContext(), "Field cannot be empty", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(em_emailadd)) {
                    Toast.makeText(getApplicationContext(), "Field cannot be empty", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(em_passw)) {
                    Toast.makeText(getApplicationContext(), "Field cannot be empty", Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(em_mobileno)) {
                    Toast.makeText(getApplicationContext(), "Field cannot be empty", Toast.LENGTH_LONG).show();
                    return;
                }
                if (em_passw.length() < 8) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 8 character", Toast.LENGTH_LONG).show();
                    return;
                }
                if (!TextUtils.equals(em_passw, em_cpassw)) {
                    Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
                    return;
                }

                auth.createUserWithEmailAndPassword(em_emailadd, em_passw)
                        .addOnCompleteListener(_5_SignupEventMember.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (!task.isSuccessful()) {
                                    Toast.makeText(_5_SignupEventMember.this, "Authentication Failed" + task.getException(),
                                            Toast.LENGTH_LONG).show();
                                } else {
                                    AccountInfo accountInfo = new AccountInfo(userID, fullname, em_emailadd, em_passw, em_bday, em_country, em_mobileno);
                                    mDatabaseReference.child(userID).setValue(accountInfo);
                                    Toast.makeText(_5_SignupEventMember.this, "Successfully Registered" + task.isSuccessful(), Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(_5_SignupEventMember.this, _8_ViewEventMember.class));
                                    finish();
                                }
                            }
                        });
            }
        });

        cal = Calendar.getInstance();
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH);
        year = cal.get(Calendar.YEAR);

        //DATE START
        em_birthday.setText(new StringBuilder().append(day).append("/").append(month + 1).append("/").append(year));

        em_birthday.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                BirthdayDate();


            }
        });

    }

    public void BirthdayDate() {

        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                em_birthday.setText(new StringBuilder().append(dayOfMonth).append("/").append(monthOfYear + 1).append("/").append(year));

            }
        };

        DatePickerDialog dpDialog = new DatePickerDialog(this, listener, year, month, day);
        dpDialog.show();

    }

}

