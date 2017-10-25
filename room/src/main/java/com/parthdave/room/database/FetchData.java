package com.parthdave.room.database;

/**
 * Created by ParthDave on 10/22/2017.
 */

public interface FetchData<T> {
    public void onNext(T object);
}
