package com.example.arvind.libo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class manage_membership_main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_membership_main);
    }

    public void goToadd_member(View view){
        Intent intent = new Intent (this,add_member.class);
        startActivity(intent);
    }

    public void goTorenew_member(View view){
        Intent intent = new Intent (this,renew_mebership.class);
        startActivity(intent);
    }


}
