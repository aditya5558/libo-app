package com.example.arvind.libo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class user_main_new extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main_new);
    }

    public void goToreturnpage(View view){
        Intent intent = new Intent (this,returnpage.class);
        startActivity(intent);
    }

    public void goTofind_a_book(View view){
        Intent intent = new Intent (this,find_a_book.class);
        startActivity(intent);
    }

    public void godmp(View view){
        Intent intent = new Intent (this,myprofile.class);
        startActivity(intent);
    }

    public void gombl(View view){
        Intent intent = new Intent (this,mybooklist.class);
        startActivity(intent);
    }
}
