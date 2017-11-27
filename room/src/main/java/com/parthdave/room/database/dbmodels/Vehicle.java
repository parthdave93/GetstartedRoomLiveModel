package com.parthdave.room.database.dbmodels;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Parth Dave on 27/10/17.
 * Spaceo Technologies Pvt Ltd.
 * parthd.spaceo@gmail.com
 */

@Entity(foreignKeys = @ForeignKey(entity = Persons.class,parentColumns = "id",childColumns = "user_id"))
public class Vehicle {
    private String name;
    @PrimaryKey(autoGenerate = true)
    private Integer id;
    private Integer user_id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Vehicle(String name, Integer user_id) {
        this.name = name;
        this.user_id = user_id;
    }
}
