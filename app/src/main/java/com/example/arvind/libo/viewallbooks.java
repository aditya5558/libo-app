package com.example.arvind.libo;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceList;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

import java.net.MalformedURLException;

public class viewallbooks extends AppCompatActivity {

    private MobileServiceClient mClient;
    MobileServiceTable<Book> table;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewallbooks);

        try {
            mClient = new MobileServiceClient("https://libo.azurewebsites.net", this);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        table = mClient.getTable(Book.class);

        final ListView listView = (ListView) findViewById(R.id.list);

        final Context x=this;

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                 final MobileServiceList<Book> result = table.select("id","name","is_issued").execute().get();
                    //result = table.lookUp(view_member.key).get();
                    final BookAdapter adapter= new BookAdapter(x,result);




                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            listView.setAdapter(adapter);
                            //adapter.add(result);

                        }
                    });


                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                return null;
            }
        }.execute();
    }


}
