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

public class returnpage extends AppCompatActivity {

    private MobileServiceClient mClient;
    EditText BookTxt;
    Button Submitbutton;
    Book result;
    MobileServiceTable<Member> table;
    MobileServiceTable<Book> table2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_returnpage);

        try {
            mClient = new MobileServiceClient("https://libo.azurewebsites.net", this);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        table = mClient.getTable(Member.class);
        table2 = mClient.getTable(Book.class);

        BookTxt=(EditText) findViewById(R.id.BookTxt);
        Submitbutton = (Button) findViewById(R.id.sbtn);

        Submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String bid = BookTxt.getText().toString();

                new AsyncTask<Void, Void, Void>() {

                    @Override
                    protected Void doInBackground(Void... params) {
                        try {
                            final int  flag;
                            result = table2.lookUp(bid).get();
                            if(result.is_issued.equals(user_login_modified.userid))
                            {
                                flag=1;
                                result.is_issued = "0000";
                                table2.update(result).get();
                            }
                            else
                            {
                                flag=0;
                            }







                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {



                                    BookTxt.getText().clear();
                                    if(flag==1)
                                    {
                                        Toast.makeText(returnpage.this, "Book returned ", Toast.LENGTH_SHORT).show();}
                                    else
                                    {Toast.makeText(returnpage.this, "Book not available for return", Toast.LENGTH_SHORT).show();}
//                            }
                                }
                            });
                            // Toast.makeText(renew_mebership.this, "Renewed by 1 year ", Toast.LENGTH_SHORT).show();

//                            table.insert(result);
//


                        } catch (Exception e) {
                            e.printStackTrace();

                            Toast.makeText(returnpage.this, "could not return ", Toast.LENGTH_SHORT).show();
                        }
                        return null;
                    }
                }.execute();

            }
        });
    }
}
