package com.parthdave.room.ui.adapter;

import android.arch.lifecycle.LiveData;
import android.arch.paging.PagedList;
import android.arch.paging.PagedListAdapter;
import android.arch.paging.TiledDataSource;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.DiffCallback;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.parthdave.room.R;
import com.parthdave.room.constants.DateFormats;
import com.parthdave.room.database.dbmodels.Persons;
import com.parthdave.room.database.dbmodels.StaticPropertyChangeIds;
import com.parthdave.room.utils.PersonListDifference;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ParthDave on 10/22/2017.
 */

public class PagedAdapter extends PagedListAdapter<Persons,LivePagedViewHolder> {

    Context context;
    LayoutInflater layoutInflater;
    ListAction listAction;

    public PagedAdapter(Context context,@NonNull DiffCallback diffCallback) {
        super(diffCallback);
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        listAction = (ListAction) context;
    }

    public interface ListAction {
        public void onClick(Persons persons, int position);
    }


    @Override
    public LivePagedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_person, null, false);
        LivePagedViewHolder viewHolder = new LivePagedViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(LivePagedViewHolder holder, int position) {
        Persons persons = getItem(position);
        if(persons==null){
            return;
        }
        holder.tvCreatedAt.setText("Created At:" + DateFormats.showDateAndTime.format(persons.getCreatedAt()));
        holder.tvTitle.setText(persons.getName());
        holder.tvUpdatedAt.setText("Updated At:" + DateFormats.showDateAndTime.format(persons.getUpdatedAt()));

        persons.setPropertyChangeCallback(propertyId -> {
            if (propertyId == StaticPropertyChangeIds.nameChange)
                holder.tvTitle.setText(persons.getName());
        });

        holder.itemView.setOnClickListener(view1 -> {
            listAction.onClick(persons, position);
        });
    }
}
