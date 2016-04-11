package com.laptop.flightstatus;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.laptop.flightstatus.adapter.StatusAdapter;
import com.laptop.flightstatus.model.Flight;
import com.laptop.flightstatus.model.RootResponse;
import com.laptop.flightstatus.services.FoxAPIService;
import com.laptop.flightstatus.services.ServiceFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ScrollingActivity extends AppCompatActivity {

    private String pickerDate = ("");
    private String flightNumber = "KL651";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //toolbar.setTitle("Flight Status");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        final StatusAdapter mStatusAdapter = new StatusAdapter();
        mRecyclerView.setAdapter(mStatusAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "More information", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        EditText mEdit;
        mEdit   = (EditText)findViewById(R.id.launch_codes);
        flightNumber = mEdit.getText().toString();

        Button bClear = (Button) findViewById(R.id.button_clear);
        Button bFetch = (Button) findViewById(R.id.button_fetch);
        bClear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mStatusAdapter.clear();
            }
        });

            bFetch.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    FoxAPIService service = ServiceFactory.createRetrofitService(FoxAPIService.class, FoxAPIService.SERVICE_ENDPOINT);
            try {
                service.getStatus(pickerDate, flightNumber)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<RootResponse>() {
                            @Override
                            public final void onCompleted() {

                            }

                            @Override
                            public final void onError(Throwable e) {
                                Log.e("FoxAPIService", e.getMessage());

                            }

                            @Override
                            public final void onNext(RootResponse response) {
                                Log.v("onNext", response.toString());

                                List<Flight> mList = response.getFlights();
                                Flight item = mList.get(0);

                                mStatusAdapter.addData(item.getOperatingFlightLeg());

                            }
                        });
            }
            catch (Exception e){
                e.printStackTrace();
            }
                }

            });

        ArrayList<String> airportsArrayList = new ArrayList<>();

                BufferedReader reader;

                try{

                    //for raw
                    final InputStream file = getResources().openRawResource(R.raw.airportsnotfile);
                    //for assets
                    //final InputStream file = getAssets().open("airports");
                    reader = new BufferedReader(new InputStreamReader(file));
                    String line = reader.readLine();
                    while(line != null){
                        //Log.d("airport: ", line);
                        airportsArrayList.add(line);
                        line = reader.readLine();
                        //populate string array here

                    }
                } catch(IOException ioe){
                    ioe.printStackTrace();
                }

        // Get a reference to the AutoCompleteTextView in the layout
        AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.autocomplete_from);
        AutoCompleteTextView textViewTo = (AutoCompleteTextView) findViewById(R.id.autocomplete_to);

        //Log.d("array size: " , "int " + airportsArrayList.size());

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, airportsArrayList);
        textView.setAdapter(adapter);
        textViewTo.setAdapter(adapter);
        }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void setDate(String newDate){
        pickerDate = newDate;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }
}