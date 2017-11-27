package com.parthdave.room.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.parthdave.room.database.dao.PersonsDao;
import com.parthdave.room.database.converters.Converter;
import com.parthdave.room.database.dbmodels.Persons;
import com.parthdave.room.database.dbmodels.Vehicle;

/**
 * Created by ParthDave on 10/22/2017.
 */

@Database(entities = {Persons.class}, version = 1)
@TypeConverters({Converter.class})
public abstract class RoomDb extends RoomDatabase {
    public abstract PersonsDao getPersonsDao();
}
