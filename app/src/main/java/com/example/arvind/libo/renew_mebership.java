package com.example.arvind.libo;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilterResponse;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;
import com.microsoft.windowsazure.mobileservices.table.TableOperationCallback;

import java.net.MalformedURLException;

public class renew_mebership extends AppCompatActivity {

    private MobileServiceClient mClient;
    EditText Nametxt;
    Button Submitbutton;
    Member result;
    MobileServiceTable<Member> table;
    int flag=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renew_mebership);


        try {
            mClient = new MobileServiceClient("https://libo.azurewebsites.net", this);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        table = mClient.getTable(Member.class);


        Nametxt = (EditText) findViewById(R.id.renew);
        Submitbutton = (Button) findViewById(R.id.srenew);

        Submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String key = Nametxt.getText().toString();

                new AsyncTask<Void, Void, Void>() {

                    @Override
                    protected Void doInBackground(Void... params) {
                        try {
                            result = table.lookUp(key).get();
                            result.expiry_date.setYear(result.expiry_date.getYear() + 1);
                            table.update(result).get();

                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {


                                    Nametxt.getText().clear();
                                    Toast.makeText(renew_mebership.this, "Renewed by 1 year ", Toast.LENGTH_SHORT).show();
//                            }
                                }
                            });
                           // Toast.makeText(renew_mebership.this, "Renewed by 1 year ", Toast.LENGTH_SHORT).show();

//                            table.insert(result);
//



                        } catch (Exception e) {
                            e.printStackTrace();
                            flag=0;
                            Toast.makeText(renew_mebership.this, "Invalid ", Toast.LENGTH_SHORT).show();
                        }
                        return null;
                    }
                }.execute();

//                if(flag==1)
//                {Toast.makeText(renew_mebership.this, "Renewed by 1 year ", Toast.LENGTH_SHORT).show();}
//
//
//               table.update(result, new TableOperationCallback<Member>() {
//                    @Override
//                    public void onCompleted(Member entity, Exception exception, ServiceFilterResponse response) {
//                        if (exception == null) {
//                            Toast.makeText(renew_mebership.this, "Data Inserted", Toast.LENGTH_SHORT).show();
//                            Nametxt.getText().clear();
//
//                        } else {
//                            Toast.makeText(renew_mebership.this, "Error Happened", Toast.LENGTH_SHORT).show();
//                        }
//
//                    }
//                });
            }
        });

    }


}