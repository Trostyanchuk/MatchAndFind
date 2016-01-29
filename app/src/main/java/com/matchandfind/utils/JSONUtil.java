package com.matchandfind.utils;

import com.google.gson.Gson;
import com.matchandfind.model.Person;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public final class JSONUtil {

    public static List<Person> getPersonsListFromJSOSNStr(String json) {
        List<Person> personsList = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(json);
            for (int i = 0; i < array.length(); i++) {
                personsList.add(new Gson().fromJson(array.getString(i), Person.class));
            }
        } catch (JSONException e) {
            //return empty list
        }
        return personsList;
    }
}
