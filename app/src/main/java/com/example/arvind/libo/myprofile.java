package com.example.arvind.libo;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

import java.net.MalformedURLException;
import java.util.ArrayList;

public class myprofile extends AppCompatActivity {


    private MobileServiceClient mClient;

    MobileServiceTable<Member> table;
    Member result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myprofile);

        try {
            mClient = new MobileServiceClient("https://libo.azurewebsites.net", this);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        table = mClient.getTable(Member.class);
        //final PersonAdapter adapter ;
        final ListView listView = (ListView) findViewById(R.id.list);

        final Context x=this;
//

        final ArrayList<Member> arrayOfPersons = new ArrayList<>();


        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
//                    final MobileServiceList<Person> result = mToDoTable.select("Name","Email").execute().get();
                    result = table.lookUp(user_login_modified.userid).get();
                    final MemberAdapter adapter = new MemberAdapter(x,arrayOfPersons);




                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            listView.setAdapter(adapter);
                            adapter.add(result);

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
