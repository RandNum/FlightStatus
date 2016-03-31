package com.laptop.flightstatus;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.laptop.flightstatus.adapter.StatusAdapter;
import com.laptop.flightstatus.model.Flight;
import com.laptop.flightstatus.model.RootResponse;
import com.laptop.flightstatus.services.FoxAPIService;
import com.laptop.flightstatus.services.ServiceFactory;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ScrollingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Flight Status");
        setSupportActionBar(toolbar);


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
            service.getStatus("2016-03-30", "KL651")
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
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }



}