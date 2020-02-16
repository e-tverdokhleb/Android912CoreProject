package com.example.android912baseapp.helpers;

import android.util.Log;

import com.example.android912baseapp.model.Movie;
import com.example.android912baseapp.utils.L;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Converters {
    public static List<Movie> convertJSonToList(String dataToConvert) {
        List<Movie> list = new ArrayList<>();
        try {
            Type listType = new TypeToken<ArrayList<Movie>>() {
            }.getType();
            list = new GsonBuilder().create().fromJson(dataToConvert, listType);
        } catch (Exception e) {
            Log.e(L.D0, e.toString());
        }
        return list;
    }
}