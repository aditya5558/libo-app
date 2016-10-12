package com.example.arvind.libo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class manage_books extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_books);
    }

    public void goToadd_book_main(View view){
        Intent intent = new Intent (this,add_book_main.class);
        startActivity(intent);
    }

    public void goviewallbooks(View view){
        Intent intent = new Intent (this,viewallbooks.class);
        startActivity(intent);
    }
}
