package com.ortegapatriciaa.enventer;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class _2_ChooseUser extends Activity  {

    Button loginClient;
    Button loginEventMember;
    private Fragment logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  getActionBar().hide();
        setContentView(R.layout.activity__2__choose_user);

        loginClient = (Button)findViewById(R.id.button_client);
        loginClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(_2_ChooseUser.this, _7_ViewClient.class);
                startActivity(i);
            }
        });

        loginEventMember = (Button)findViewById(R.id.button_member);
        loginEventMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(_2_ChooseUser.this, _4_LoginEventMember.class);
                startActivity(i);
            }
        });
    }
}
