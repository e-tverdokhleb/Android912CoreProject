package com.example.android912baseapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.android912baseapp.adapters.WordListAdapter;
import com.example.android912baseapp.helpers.Converters;
import com.example.android912baseapp.helpers.LoadHelper;
import com.example.android912baseapp.model.Movie;
import com.example.android912baseapp.utils.L;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final LinkedList<String> mWordList = new LinkedList<>();
    private RecyclerView mRecyclerView;
    private WordListAdapter mAdapter;

    private LoadHelper.OnDataReceived onDataRetrievedListener = new LoadHelper.OnDataReceived() {
        @Override
        public void onDataReceived(final String data) {
            List<Movie> l = Converters.convertJSonToList(data);
            Log.d(L.D0, "LoadHelper -> onDataReceived: " + data);
            Log.d(L.D0, "LoadHelper -> onDataReceived -> Parsed: " + Arrays.toString(l.toArray()));

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ((TextView) findViewById(R.id.tvData)).setText(data);
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
                LoadHelper.loadDataAsync(onDataRetrievedListener);
            }
        });

        mRecyclerView = findViewById(R.id.recyclerView);
        mAdapter = new WordListAdapter(this, new ArrayList<Object>());
        mRecyclerView.setAdapter(mAdapter);
    }
}
