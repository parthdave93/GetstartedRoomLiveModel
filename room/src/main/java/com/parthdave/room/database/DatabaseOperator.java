package com.parthdave.room.database;

import android.arch.lifecycle.LiveData;
import android.arch.paging.PagedList;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.parthdave.room.database.dbmodels.Persons;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ParthDave on 10/22/2017.
 */

public class DatabaseOperator {

    static RoomDb roomDb;
    private String RoomDatabaseName = "test";

    public DatabaseOperator(Context context) {
        if (roomDb == null)
            roomDb = Room.databaseBuilder(context, RoomDb.class, RoomDatabaseName).build();
    }

    public DatabaseOperator insertPerson(Persons persons) {
        Single.fromCallable((Callable<Object>) () -> {
            roomDb.getPersonsDao().insertPerson(persons);
            return true;
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe((o) -> {
        }, e -> e.printStackTrace());

        return this;
    }


    /*public DatabaseOperator insertVehicle(Vehicle persons) {
        Single.fromCallable((Callable<Object>) () -> {
            roomDb.getPersonsDao().insertVehicle(persons);
            return true;
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe((o) -> {
        }, e -> e.printStackTrace());

        return this;
    }


    public DatabaseOperator insertVehicle(List<Vehicle> persons) {
        Single.fromCallable((Callable<Object>) () -> {
            roomDb.getPersonsDao().insertVehicle(persons);
            return true;
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe((o) -> {
        }, e -> e.printStackTrace());

        return this;
    }*/

    public DatabaseOperator updatePerson(Persons persons) {
        Single.fromCallable((Callable<Object>) () -> {
            roomDb.getPersonsDao().updatePerson(persons);
            return true;
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe((o) -> {
        }, e -> e.printStackTrace());

        return this;
    }

    public void fetchPersons(FetchData<Flowable<List<Persons>>> fetchData) {
        Single.fromCallable(() -> {
            Flowable<List<Persons>> personsList = roomDb.getPersonsDao().findAllPersons();
            return personsList;
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe((o) -> {
            fetchData.onNext(o);
        }, e -> e.printStackTrace());
    }

    public LiveData<PagedList<Persons>> fetchPersonsPaged() {
        LiveData<PagedList<Persons>> personsList =
                roomDb.getPersonsDao()
                        .findPersonsPaged()
                        .create(0,
                                new PagedList.Config.Builder().setPageSize(10).setEnablePlaceholders(false).setInitialLoadSizeHint(10).build()
                        );
        return personsList;
    }
}
