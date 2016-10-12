package com.example.arvind.libo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.*;

import static android.R.attr.key;

public class view_member extends AppCompatActivity {

    EditText Nametxt;
    public static String key;
    Button Submitbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_member);

        Submitbutton = (Button) findViewById(R.id.sviewm);

        Submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Nametxt = (EditText) findViewById(R.id.vmember);
                key = Nametxt.getText().toString();
                Intent intent = new Intent (view_member.this,view_member_display.class);
                view_member.this.startActivity(intent);

            }
        });
    }
    @Override
    public void onBackPressed(){
        Intent intent = new Intent (view_member.this,librarian_main.class);
        view_member.this.startActivity(intent);
    }

//    public void goToview_member_display(View view){
//        Intent intent = new Intent (this,view_member_display.class);
//        startActivity(intent);
//    }
}
