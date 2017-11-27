package com.parthdave.room.database.dao;

import android.arch.paging.LivePagedListProvider;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.parthdave.room.database.dbmodels.Persons;
import com.parthdave.room.database.dbmodels.Vehicle;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by ParthDave on 10/22/2017.
 */

@Dao
public interface PersonsDao {
    @Query("Select * from Persons order by id")
    Flowable<List<Persons>> findAllPersons();

    @Query("Select * from Persons  order by id")
    abstract LivePagedListProvider<Integer,Persons> findPersonsPaged();

    @Insert
    void insertPerson(Persons persons);

    @Delete
    void deletePerson(Persons persons);

    @Update
    void updatePerson(Persons persons);

/*
    @Insert
    void insertVehicle(Vehicle persons);


    @Insert
    void insertVehicle(List<Vehicle> persons);

    @Delete
    void deleteVehicle(Vehicle persons);

    @Update
    void updatePerson(Vehicle persons);*/
}
