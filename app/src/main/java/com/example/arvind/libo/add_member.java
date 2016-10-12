package com.example.arvind.libo;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.microsoft.windowsazure.mobileservices.*;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilterResponse;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;
import com.microsoft.windowsazure.mobileservices.table.TableOperationCallback;

import java.net.MalformedURLException;
import java.util.List;


public class add_member extends AppCompatActivity {

    private MobileServiceClient mClient;
    EditText Nametxt;
    EditText EmailTxt;
    EditText PwdTxt;
    EditText IdTxt;
    Button Submitbutton;

   MobileServiceTable<Member> table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member);

        try {
            mClient = new MobileServiceClient("https://libo.azurewebsites.net", this);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        table = mClient.getTable(Member.class);




        Nametxt = (EditText) findViewById(R.id.NameTxt);
        IdTxt = (EditText) findViewById(R.id.IdTxt);
        EmailTxt = (EditText) findViewById(R.id.EmailTxt);
        PwdTxt = (EditText) findViewById(R.id.PwdTxt);
        Submitbutton = (Button) findViewById(R.id.SubmitBtn);
        Submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Member p1 = new Member();


            p1.id = IdTxt.getText().toString();
                p1.name = Nametxt.getText().toString();
                p1.email = EmailTxt.getText().toString();
                p1.password = PwdTxt.getText().toString();
               // p1.real_id=05;

                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... params) {
                try {

                    final MobileServiceList<Member> result=table.execute().get();
                     p1.real_id = result.getTotalCount()+1;
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
                        return null;
                    }
                }.execute();





                mClient.getTable(Member.class).insert(p1, new TableOperationCallback<Member>() {
                    @Override
                    public void onCompleted(Member entity, Exception exception, ServiceFilterResponse response) {
                        if (exception==null)
                        {
                            Toast.makeText(add_member.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                            Nametxt.getText().clear();
                            IdTxt.getText().clear();
                            EmailTxt.getText().clear();
                            PwdTxt.getText().clear();
                        }
                        else
                        {
                            Toast.makeText(add_member.this, "Error Happened", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });

    }
}