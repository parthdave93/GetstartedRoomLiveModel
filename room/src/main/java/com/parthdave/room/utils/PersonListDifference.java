package com.parthdave.room.utils;

import android.support.v7.util.DiffUtil;

import com.parthdave.room.database.dbmodels.Persons;

import java.util.List;

/**
 * Created by ParthDave on 10/22/2017.
 */

public class PersonListDifference extends DiffUtil.Callback {
    List<Persons> oldPersonList;
    List<Persons> newPersonList;

    public PersonListDifference(List<Persons> oldPersonList, List<Persons> newPersonList) {
        this.oldPersonList = oldPersonList;
        this.newPersonList = newPersonList;
    }

    @Override
    public int getOldListSize() {
        return oldPersonList.size();
    }

    @Override
    public int getNewListSize() {
        return newPersonList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldPersonList.get(oldItemPosition).getUpdatedAt().getTime() != newPersonList.get(newItemPosition).getUpdatedAt().getTime();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldPersonList.get(oldItemPosition).id == newPersonList.get(newItemPosition).id;
    }
}
