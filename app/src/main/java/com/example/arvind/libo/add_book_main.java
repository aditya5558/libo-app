package com.example.arvind.libo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class add_book_main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book_main);


    }
    public void goTo_ocr_ms(View view){
        Intent intent = new Intent (this,ocr_ms.class);
        startActivity(intent);
    }

    public void goTo_manual(View view){
        Intent intent = new Intent (this,add_book.class);
        startActivity(intent);
    }

}
