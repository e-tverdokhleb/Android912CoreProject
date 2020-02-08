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
    private OnSwipeTouchListener onSwipeTouchListener;

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

        onSwipeTouchListener = new OnSwipeTouchListener(this) {
            @Override
            public boolean onSwipeRight() {
                Toast.makeText(MainActivity.this, "onSwipeRight", Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onSwipeLeft() {
                Toast.makeText(MainActivity.this, "onSwipeLeft", Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onSwipeTop() {
                Toast.makeText(MainActivity.this, "onSwipeTop", Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onSwipeBottom() {
                Toast.makeText(MainActivity.this, "onSwipeBottom", Toast.LENGTH_SHORT).show();
                return true;
            }
        };
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        onSwipeTouchListener.onTouch(getWindow().getDecorView().findViewById(android.R.id.content), event);
        return true;
    }
}

class OnSwipeTouchListener implements View.OnTouchListener {
    private final GestureDetector gestureDetector;

    OnSwipeTouchListener(Context context) {
        gestureDetector = new GestureDetector(context, new GestureListener());
    }

    public boolean onTouch(final View v, final MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    private final class GestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            boolean result = false;
            try {
                float diffY = e2.getY() - e1.getY();
                float diffX = e2.getX() - e1.getX();
                if (Math.abs(diffX) > Math.abs(diffY)) {
                    if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffX > 0) {
                            result = onSwipeRight();
                        } else {
                            result = onSwipeLeft();
                        }
                    }
                } else {
                    if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffY > 0) {
                            result = onSwipeBottom();
                        } else {
                            result = onSwipeTop();
                        }
                    }
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            return result;
        }
    }

    public boolean onSwipeRight() {
        return false;
    }

    public boolean onSwipeLeft() {
        return false;
    }

    public boolean onSwipeTop() {
        return false;
    }

    public boolean onSwipeBottom() {
        return false;
    }
}
