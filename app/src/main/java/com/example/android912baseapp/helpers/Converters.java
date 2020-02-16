package com.example.android912baseapp.helpers;

import com.example.android912baseapp.model.Movie;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Converters {
    public static List<Movie> convertJSonToList(String dataToConvert) {
        List<Movie> list = new ArrayList<>();
        try {
//            JSONArray jsonArray = new JsonArray();
            JSONObject jsonObject = new JSONObject();
            JSONArray jsonArray = jsonObject.getJSONArray(dataToConvert);
            Type listType = new TypeToken<ArrayList<Movie>>() {
            }.getType();
            list = new GsonBuilder().create().fromJson(jsonArray.toString(), listType);

            for (Movie user : list) {
                list.add(user);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}