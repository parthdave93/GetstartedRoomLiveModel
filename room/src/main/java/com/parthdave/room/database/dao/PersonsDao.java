package com.parthdave.room.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.parthdave.room.database.dbmodels.Persons;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by ParthDave on 10/22/2017.
 */

@Dao
public interface PersonsDao {
    @Query("Select * from Persons")
    Flowable<List<Persons>> findAllPersons();

    @Insert
    void insertPerson(Persons persons);

    @Delete
    void deletePerson(Persons persons);

    @Update
    void updatePerson(Persons persons);
}
