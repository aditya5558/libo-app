package com.example.arvind.libo;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceList;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

import java.net.MalformedURLException;
import java.util.ArrayList;

public class mybooklist extends AppCompatActivity {

    private MobileServiceClient mClient;
    EditText Nametxt;
    MobileServiceTable<Book> table;
    Book result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mybooklist);


        setContentView(R.layout.activity_view_member_display);
        // textView=(TextView)findViewById(R.id.fullscreen);

        try {
            mClient = new MobileServiceClient("https://libo.azurewebsites.net", this);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        table = mClient.getTable(Book.class);
        //final PersonAdapter adapter ;
        final ListView listView = (ListView) findViewById(R.id.list);

        final Context x=this;
//        Nametxt = (EditText) findViewById(R.id.vmember);
//        final String key = Nametxt.getText().toString();

        final ArrayList<Book> arrayOfPersons = new ArrayList<>();
////// Create the adapter to convert the array to views
        //final  PersonAdapter adapter = new PersonAdapter(this,arrayOfPersons);

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    final MobileServiceList<Book> result = table.where().field("is_issued").eq(user_login_modified.userid).select("id","name").execute().get();
                    //result = table.lookUp(user_login_modified.userid).get();
                    final bookadapter2  adapter = new bookadapter2 (x,result);




                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            listView.setAdapter(adapter);
                           // adapter.add(result);

                        }
                    });
//

                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                return null;
            }
        }.execute();

    }
}
