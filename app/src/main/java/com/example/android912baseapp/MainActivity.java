package com.example.android912baseapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.android912baseapp.adapters.WordListAdapter;

import java.util.LinkedList;

import static com.example.android912baseapp.utils.L.logD;

public class MainActivity extends AppCompatActivity {
    private final LinkedList<String> mWordList = new LinkedList<>();
    private RecyclerView mRecyclerView;
    private WordListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logD("MainActivity-> onCreate");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findViewById(R.id.btnLoadData).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = LoadHelper.loadData()
            }
        });

    }
}
