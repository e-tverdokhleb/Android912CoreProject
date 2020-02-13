package com.example.android912baseapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.android912baseapp.adapters.WordListAdapter;
import com.example.android912baseapp.helpers.Converters;
import com.example.android912baseapp.helpers.LoadHelper;
import com.example.android912baseapp.utils.L;

import java.util.LinkedList;

import static com.example.android912baseapp.utils.L.d;

public class MainActivity extends AppCompatActivity {
    private final LinkedList<String> mWordList = new LinkedList<>();
    private RecyclerView mRecyclerView;
    private WordListAdapter mAdapter;
    private LoadHelper loadHelper = new LoadHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        d("MainActivity-> onCreate");

        findViewById(R.id.btnLoadData).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = loadHelper.loadData();
                Log.d(L.D0, data);
            }
        });

        mRecyclerView = findViewById(R.id.recyclerView);
        mAdapter = new WordListAdapter(this, Converters.convertJSonToList(""));
        mRecyclerView.setAdapter(mAdapter);
    }
}
