package com.example.android912baseapp.helpers;

import android.os.AsyncTask;
import android.util.Log;

import com.example.android912baseapp.utils.L;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoadHelper {
    String url = "https://reqres.in/api/users/2"; // "http://pratikbutani.x10.mx/json_data.json";
    String result = "";
    OkHttpHandler okHttpHandler = new OkHttpHandler(url);

    public String loadData() {
        try {
            result = okHttpHandler.execute(url).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }
}

class OkHttpHandler extends AsyncTask<String, String, String> {
    String url;
    OkHttpClient client = new OkHttpClient();

    OkHttpHandler(String url) {
        this.url = url;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.d(L.D0, "onPreExecute -> Loading..");
    }

    @Override
    protected String doInBackground(String... params) {
        Log.d(L.D0, "doInBackground ->Loading..");
        Request.Builder builder = new Request.Builder();
        builder.url(url);
        Request request = builder.build();

        try {
            Response response = client.newCall(request).execute();
            if (response.body() != null) {
                L.d(response.body().string());
                return response.body().string();
            } else {
                Log.d(L.D0, "doInBackground -> data - null");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(L.D0, "doInBackground -> data - null");
        }
        return "";
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.d(L.D0, "onPostExecute.. " + s);
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
        Log.d(L.D0, "onProgressUpdate" + Arrays.toString(values));
    }
}
