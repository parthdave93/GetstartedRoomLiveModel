package com.parthdave.room.database.dbmodels;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.parthdave.room.database.converters.PropertyChangeCallback;

import java.sql.Date;

import io.reactivex.Observable;

/**
 * Created by ParthDave on 10/22/2017.
 */
@Entity
public class Persons {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @Ignore
    PropertyChangeCallback propertyChangeCallback;

    private String name;
    private boolean isDeleted;
    private Date createdAt;
    private Date updatedAt;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(StaticPropertyChangeIds.nameChange);
    }

    private void notifyPropertyChanged(Integer ids) {
        if(propertyChangeCallback!=null)
            propertyChangeCallback.onPropertyChange(ids);
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public PropertyChangeCallback getPropertyChangeCallback() {
        return propertyChangeCallback;
    }

    public void setPropertyChangeCallback(PropertyChangeCallback propertyChangeCallback) {
        this.propertyChangeCallback = propertyChangeCallback;
    }
}
