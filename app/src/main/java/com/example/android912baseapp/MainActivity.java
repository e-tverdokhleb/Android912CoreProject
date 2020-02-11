package com.example.android912baseapp;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.android912baseapp.fragments.Fragment1;
import com.example.android912baseapp.fragments.Fragment2;

public class MainActivity extends AppCompatActivity {
    private Fragment1 fragment1 = new Fragment1();
    private Fragment2 fragment2 = new Fragment2();
    private FragmentManager fm = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView tvMain = findViewById(R.id.tvMain);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, fragment1, "fragment1")
                    .commit();

            tvMain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if ((fm.findFragmentByTag("fragment1") != null) && (fm.findFragmentByTag("fragment1").isVisible())) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, fragment2)
                            .commit();
                } else {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.container, fragment1)
                            .commit();
                }
            }
        });
    }
}

