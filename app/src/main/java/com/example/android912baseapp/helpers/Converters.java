package com.example.android912baseapp.helpers;

import java.util.ArrayList;
import java.util.List;

public class Converters {
    public static List<Object> convertJSonToList(String dataToConvert) {
        List<Object> mWordList = new ArrayList<>();
        int wordListSize = mWordList.size();
        for (int i = 0; i < 20; i++) {
            mWordList.add(" - " + dataToConvert + "_" + wordListSize);
        }
        return mWordList;
    }
}
