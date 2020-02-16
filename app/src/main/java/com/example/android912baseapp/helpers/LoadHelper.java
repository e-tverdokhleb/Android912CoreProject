package com.example.android912baseapp.helpers;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.android912baseapp.utils.L;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class LoadHelper {
    private static String url = "https://raw.githubusercontent.com/FEND16/movie-json-data/master/json/movies-coming-soon.json";
//    https://github.com/FEND16/movie-json-data/blob/master/json/movies-coming-soon.json

    public interface OnDataReceived {
        void onDataReceived(String data);

        void onFailure(Exception e);
    }

    public static void loadDataAsync(final OnDataReceived callback) {
        final OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                try (ResponseBody rb = response.body()) {
                    if (rb != null) {
                        String s = rb.string();
                        callback.onDataReceived(s);
                        Log.d(L.D0, s);
                    }
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailure(e);
                Log.e(L.D0, e.toString());
            }
        });
    }
}
