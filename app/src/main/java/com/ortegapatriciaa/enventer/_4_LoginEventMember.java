package com.ortegapatriciaa.enventer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class _4_LoginEventMember extends AppCompatActivity {

    TextView signupEventMember;
    TextView forgotpassEventMember;
    Button loginEventEm;
    EditText em_emailuname;
    EditText em_password;

    SharedPreferences settings;

    private FirebaseAuth auth;

    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseDatabase database;
    private DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__4__login_event_member);

        em_emailuname = (EditText)findViewById(R.id.login_em_uname);
        em_password = (EditText)findViewById(R.id.login_em_passw);

        loginEventEm = (Button)findViewById(R.id.button_login);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        loginEventEm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String em_unameemail = em_emailuname.getText().toString();
                final String em_pass = em_password.getText().toString();

                if(TextUtils.isEmpty(em_unameemail)){
                    Toast.makeText(getApplicationContext(), "Field cannot be empty", Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(em_pass)){
                    Toast.makeText(getApplicationContext(), "Field cannot be empty", Toast.LENGTH_LONG).show();
                    return;
                }

                auth.signInWithEmailAndPassword(em_unameemail, em_pass)
                        .addOnCompleteListener(_4_LoginEventMember.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){

                                    Toast.makeText(_4_LoginEventMember.this, "Successfully Login",
                                            Toast.LENGTH_LONG).show();
                                    Intent i = new Intent(_4_LoginEventMember.this, _8_ViewEventMember.class);
                                    startActivity(i);
                                    finish();
                                }else {
                                    Toast.makeText(_4_LoginEventMember.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                                }
                            }
                        });

            }
        });

        signupEventMember = (TextView)findViewById(R.id.signupeventmem);
        signupEventMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(_4_LoginEventMember.this, _5_SignupEventMember.class);
                startActivity(i);
            }
        });

        settings = getSharedPreferences("mySharedPref", 0);
        if(settings.getBoolean("connected", false)){
            startActivity(new Intent(getApplicationContext(), _8_ViewEventMember.class));
        }
    }

}
