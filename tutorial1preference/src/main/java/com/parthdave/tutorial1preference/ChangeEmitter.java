package com.parthdave.tutorial1preference;

/**
 * Created by ParthDave on 10/22/2017.
 */

public interface ChangeEmitter<T> {
    public void onChange(T object);
}
