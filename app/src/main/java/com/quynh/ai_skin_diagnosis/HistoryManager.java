package com.quynh.ai_skin_diagnosis;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class HistoryManager {
    private static final String PREF_NAME = "history_pref";
    private static final String KEY_HISTORY = "history_list";
    // SAVE
    public static void saveHistory(Context context, ArrayList<HistoryItem> list) {
        SharedPreferences preferences = context.getSharedPreferences(
                        PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString(KEY_HISTORY, json);
        editor.apply();
    }

    // LOAD
    public static ArrayList<HistoryItem> loadHistory(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(
                        PREF_NAME, Context.MODE_PRIVATE);
        String json = preferences.getString(KEY_HISTORY, null);
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<HistoryItem>>(){}.getType();
        ArrayList<HistoryItem> list = gson.fromJson(json, type);
        if (list == null) {
            list = new ArrayList<>();
        }
        return list;
    }
}