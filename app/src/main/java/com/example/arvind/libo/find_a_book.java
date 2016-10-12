package com.example.arvind.libo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class find_a_book extends AppCompatActivity {

    EditText Nametxt;
    public static String key;
    Button Submitbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_a_book);

        Submitbutton = (Button) findViewById(R.id.sbtn);

        Submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Nametxt = (EditText) findViewById(R.id.IdTxt);
                key = Nametxt.getText().toString();
                Intent intent = new Intent (find_a_book.this,dob.class);
                find_a_book.this.startActivity(intent);

            }
        });


    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent (find_a_book.this,librarian_main.class);
        find_a_book.this.startActivity(intent);
    }

    public void goTodob(View view){
        Intent intent = new Intent (this,dob.class);
        startActivity(intent);
    }
}
