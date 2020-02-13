package com.example.android912baseapp.helpers;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;

public class Converters {
    public static List<String> convertJSonToList(String dataToConvert) {
        List<String> mWordList = new ArrayList<>();
        int wordListSize = mWordList.size();
        for (int i = 0; i < 20; i++) {
            mWordList.add(" - " + dataToConvert + "_" + wordListSize);
        }
        return mWordList;
    }

}
