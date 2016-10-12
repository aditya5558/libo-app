package com.example.arvind.libo;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

import java.net.MalformedURLException;

public class issue extends AppCompatActivity {

    private MobileServiceClient mClient;
    EditText MemTxt;
    EditText BookTxt;
    Button Submitbutton;
    Book result;
    MobileServiceTable<Member> table;
    MobileServiceTable<Book> table2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue);

        try {
            mClient = new MobileServiceClient("https://libo.azurewebsites.net", this);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        table = mClient.getTable(Member.class);
        table2 = mClient.getTable(Book.class);
        MemTxt=(EditText) findViewById(R.id.MemTxt);
        BookTxt=(EditText) findViewById(R.id.BookTxt);
        Submitbutton = (Button) findViewById(R.id.sbtn);

        Submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String bid = BookTxt.getText().toString();
                final String mid = MemTxt.getText().toString();

                new AsyncTask<Void, Void, Void>() {

                    @Override
                    protected Void doInBackground(Void... params) {
                        try {
                            final int  flag;
                            result = table2.lookUp(bid).get();
                            if(result.is_issued.equals("0000"))
                            {
                                flag=1;
                                result.is_issued = mid;
                                table2.update(result).get();
                            }
                            else
                            {
                                flag=0;
                            }
                            table.lookUp(mid);






                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {


                                    MemTxt.getText().clear();
                                    BookTxt.getText().clear();
                                    if(flag==1)
                                    {Toast.makeText(issue.this, "Book issued ", Toast.LENGTH_SHORT).show();}
                                    else
                                    {Toast.makeText(issue.this, "Book already issued to another member ", Toast.LENGTH_SHORT).show();}
//                            }
                                }
                            });
                            // Toast.makeText(renew_mebership.this, "Renewed by 1 year ", Toast.LENGTH_SHORT).show();

//                            table.insert(result);
//


                        } catch (Exception e) {
                            e.printStackTrace();

                            Toast.makeText(issue.this, "could not issue ", Toast.LENGTH_SHORT).show();
                        }
                        return null;
                    }
                }.execute();

            }
        });





    }
}
