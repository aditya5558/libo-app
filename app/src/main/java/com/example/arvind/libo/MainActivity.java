package com.example.arvind.libo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToLibLogin(View view){
        Intent intent = new Intent (this,librarian_login.class);
        startActivity(intent);
    }
    public void goToUserLogin(View view){
        Intent intent = new Intent (this,user_login_modified.class);
        startActivity(intent);
    }
}
