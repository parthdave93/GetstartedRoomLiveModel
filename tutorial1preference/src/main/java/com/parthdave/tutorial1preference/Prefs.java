package com.parthdave.tutorial1preference;

import android.content.SharedPreferences;

import org.reactivestreams.Subscriber;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by ParthDave on 10/19/2017.
 */

public class Prefs {
    private SharedPreferences sharedPreferences;
    private String isLoggedIn = "isLoggedIn";
    private Map<String, Set<ChangeEmitter>> mappings = new HashMap<>();

    public Prefs(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;

    }

    public void setUserLoggedIn(boolean isLoggedIn) {
        putBoolean(this.isLoggedIn, isLoggedIn);
        prefsChangedEvent(this.isLoggedIn, isLoggedIn);
    }

    public Boolean getUserLoggedIn(boolean defaultValue) {
        return getBoolean(this.isLoggedIn, defaultValue);
    }

    public void registerForLoginEvent(ChangeEmitter subscriber) {
        registerForEvent(isLoggedIn, subscriber);
    }

    private void registerForEvent(String key, ChangeEmitter subscriber) {
        if (mappings.containsKey(key)) {
            Set<ChangeEmitter> subscribersSet = mappings.get(key);
            subscribersSet.add(subscriber);
        } else {
            Set<ChangeEmitter> subscriberSet = new HashSet<>();
            subscriberSet.add(subscriber);
            mappings.put(key, subscriberSet);
        }
    }

    private void prefsChangedEvent(String key, Object changedValue) {
        if (mappings.containsKey(key)) {
            Set<ChangeEmitter> subscribersSet = mappings.get(key);
            Iterator<ChangeEmitter> subscriberIterator = subscribersSet.iterator();
            while (subscriberIterator.hasNext()) {
                ChangeEmitter subscriber = subscriberIterator.next();
                subscriber.onChange(changedValue);
            }
        }
    }

    private void putBoolean(String key, Boolean value) {
        sharedPreferences.edit().putBoolean(key, value).commit();
    }

    private Boolean getBoolean(String key, boolean defaultValue) {
        return sharedPreferences.getBoolean(key, defaultValue);
    }
}
