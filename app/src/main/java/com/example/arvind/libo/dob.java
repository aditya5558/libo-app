package com.example.arvind.libo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.*;

import com.example.arvind.libo.R;
import com.microsoft.windowsazure.mobileservices.*;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilterResponse;
import com.microsoft.windowsazure.mobileservices.table.TableOperationCallback;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.http.NextServiceFilterCallback;
import com.microsoft.windowsazure.mobileservices.http.OkHttpClientFactory;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilter;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilterRequest;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilterResponse;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;
import com.microsoft.windowsazure.mobileservices.table.query.Query;
import com.microsoft.windowsazure.mobileservices.table.query.QueryOperations;
import com.microsoft.windowsazure.mobileservices.table.sync.MobileServiceSyncContext;
import com.microsoft.windowsazure.mobileservices.table.sync.MobileServiceSyncTable;
import com.microsoft.windowsazure.mobileservices.table.sync.localstore.ColumnDataType;
import com.microsoft.windowsazure.mobileservices.table.sync.localstore.MobileServiceLocalStoreException;
import com.microsoft.windowsazure.mobileservices.table.sync.localstore.SQLiteLocalStore;
import com.microsoft.windowsazure.mobileservices.table.sync.synchandler.SimpleSyncHandler;
import com.squareup.okhttp.OkHttpClient;

import org.json.JSONArray;

import java.util.*;

import static com.microsoft.windowsazure.mobileservices.table.query.QueryOperations.*;

import java.net.MalformedURLException;

public class dob extends AppCompatActivity {

    private MobileServiceClient mClient;
    EditText Nametxt;
    MobileServiceTable<Book> table;
    Book result;
    //  List<Person> result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



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
//                    final MobileServiceList<Person> result = mToDoTable.select("Name","Email").execute().get();
                    result = table.lookUp(find_a_book.key).get();
                    final BookAdapter adapter = new BookAdapter(x,arrayOfPersons);




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
