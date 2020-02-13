package com.example.android912baseapp.network;

import java.util.concurrent.TimeUnit;

public class LoadHelper {
    public static String loadData() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "some data";
    }
}
