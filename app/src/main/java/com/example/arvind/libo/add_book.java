package com.example.arvind.libo;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceList;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilterResponse;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;
import com.microsoft.windowsazure.mobileservices.table.TableOperationCallback;

import java.net.MalformedURLException;

public class add_book extends AppCompatActivity {

    private MobileServiceClient mClient;
    EditText Nametxt;
    EditText IdTxt;
    Button Submitbutton;

    MobileServiceTable<Book> table1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        try {
            mClient = new MobileServiceClient("https://libo.azurewebsites.net", this);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        table1 = mClient.getTable(Book.class);




        Nametxt = (EditText) findViewById(R.id.NameTxt);
        IdTxt = (EditText) findViewById(R.id.IdTxt);
        Submitbutton = (Button) findViewById(R.id.SubmitBtn);
        Submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Book p1 = new Book();


                p1.id = IdTxt.getText().toString();
                p1.name = Nametxt.getText().toString();




                mClient.getTable(Book.class).insert(p1, new TableOperationCallback<Book>() {
                    @Override
                    public void onCompleted(Book entity, Exception exception, ServiceFilterResponse response) {
                        if (exception==null)
                        {
                            Toast.makeText(add_book.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                            Nametxt.getText().clear();
                            IdTxt.getText().clear();
                        }
                        else
                        {
                            Toast.makeText(add_book.this, "Error Happened", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });

    }
}
