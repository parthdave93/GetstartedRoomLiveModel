package com.parthdave.room.database.converters;

import android.arch.persistence.room.TypeConverter;

import java.sql.Date;

import io.reactivex.Observable;

/**
 * Created by ParthDave on 10/22/2017.
 */

public class Converter {

    @TypeConverter
    public static Long fromDate(Date date){
        return date.getTime();
    }

    @TypeConverter
    public static Date toDate(Long fromTime){
        return new Date(fromTime);
    }

}
