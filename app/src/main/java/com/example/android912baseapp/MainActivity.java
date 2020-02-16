package com.example.android912baseapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android912baseapp.adapters.MovieAdapter;
import com.example.android912baseapp.helpers.Converters;
import com.example.android912baseapp.helpers.LoadHelper;
import com.example.android912baseapp.model.Movie;
import com.example.android912baseapp.utils.L;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Movie> list = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private MovieAdapter mAdapter;

    private LoadHelper.OnDataReceived onDataRetrievedListener = new LoadHelper.OnDataReceived() {
        @Override
        public void onDataReceived(final String data) {
            list = Converters.convertJSonToList(data);
            Log.d(L.D0, "LoadHelper -> onDataReceived -> Parsed: " + Arrays.toString(list.toArray()));

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mAdapter.setItems(list);
                    mAdapter.notifyDataSetChanged();
                }
            });
        }

        @Override
        public void onFailure(Exception e) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(L.D0, "MainActivity -> onCreate");

        findViewById(R.id.btnLoadData).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
            }
        });

        mRecyclerView = findViewById(R.id.recyclerView);
        mAdapter = new MovieAdapter(this, list);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadData();
    }

    private void loadData() {
        LoadHelper.loadDataAsync(onDataRetrievedListener);
    }
}
