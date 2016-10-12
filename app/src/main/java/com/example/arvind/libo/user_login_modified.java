package com.example.arvind.libo;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceList;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

import java.net.MalformedURLException;

public class user_login_modified extends AppCompatActivity {

    EditText EmailTxt;
    EditText PwdTxt;
    Button Submitbutton;
    private MobileServiceClient mClient;
    MobileServiceTable<Member> table;
    public static String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login_modified);

        try {
            mClient = new MobileServiceClient("https://libo.azurewebsites.net", this);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        table = mClient.getTable(Member.class);
        EmailTxt = (EditText) findViewById(R.id.EmailTxt);
        PwdTxt = (EditText) findViewById(R.id.PwdTxt);
        Submitbutton = (Button) findViewById(R.id.SubmitBtn);
        Submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String ekey = EmailTxt.getText().toString();
                final String pkey = PwdTxt.getText().toString();
//                Intent intent = new Intent(user_login_modified.this, user_main.class);
//                             user_login_modified.this.startActivity(intent);
                // p1.real_id=05;

                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... params) {
                        try {

                            final MobileServiceList<Member> result = table.where().field("email").eq(ekey).execute().get();
                            Member x = result.get(0);
                            if (x.password.equals(pkey)) {

                                userid = x.id;
                                Intent intent = new Intent(user_login_modified.this, user_main_new.class);
                                user_login_modified.this.startActivity(intent);
                            } else {
                                runOnUiThread(new Runnable() {

                                    @Override
                                    public void run() {

                                        EmailTxt.getText().clear();
                                        PwdTxt.getText().clear();


                                        Toast.makeText(user_login_modified.this, "Invalid Credentials ", Toast.LENGTH_SHORT).show();
//                            }
                                    }
                                });


                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                }.execute();


            }
        });
    }
}
