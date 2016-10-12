package com.example.arvind.libo;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.MobileServiceList;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;

import java.net.MalformedURLException;

public class generate_bill extends AppCompatActivity {

    private MobileServiceClient mClient;

    MobileServiceTable<Member> table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_bill);

        try {
            mClient = new MobileServiceClient("https://libo.azurewebsites.net", this);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        table = mClient.getTable(Member.class);
        //final PersonAdapter adapter ;
        final ListView listView = (ListView) findViewById(R.id.lista);

        final Context x=this;

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    final MobileServiceList<Member> result = table.execute().get();
                   // result = table.lookUp(view_member.key).get();
                    final MemberAdapter adapter = new MemberAdapter(x,result);




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

    @Override
    public void onBackPressed(){
        Intent intent = new Intent (generate_bill.this,librarian_main.class);
        generate_bill.this.startActivity(intent);
    }
}
