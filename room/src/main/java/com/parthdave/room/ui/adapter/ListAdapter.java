package com.parthdave.room.ui.adapter;

import android.content.Context;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.parthdave.room.R;
import com.parthdave.room.constants.DateFormats;
import com.parthdave.room.database.converters.PropertyChangeCallback;
import com.parthdave.room.database.dbmodels.Persons;
import com.parthdave.room.database.dbmodels.StaticPropertyChangeIds;
import com.parthdave.room.utils.PersonListDifference;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ParthDave on 10/22/2017.
 */

public class ListAdapter extends RecyclerView.Adapter<ViewHolder> {

    Context context;
    Flowable<List<Persons>> items;
    List<Persons> itemList;
    LayoutInflater layoutInflater;
    ListAction listAction;

    public interface ListAction {
        public void onClick(Persons persons, int position);
    }

    public ListAdapter(Context context, ListAction listAction) {
        this.context = context;
        this.listAction = listAction;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_person, null, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.itemView.setOnClickListener(view1 -> {
            int position = viewHolder.getAdapterPosition();
            listAction.onClick(itemList.get(position), position);
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Persons persons = itemList.get(position);
        holder.tvCreatedAt.setText("Created At:" + DateFormats.showDateAndTime.format(persons.getCreatedAt()));
        holder.tvTitle.setText(persons.getName());
        holder.tvUpdatedAt.setText("Updated At:" + DateFormats.showDateAndTime.format(persons.getUpdatedAt()));

        persons.setPropertyChangeCallback(propertyId -> {
            if (propertyId == StaticPropertyChangeIds.nameChange)
                holder.tvTitle.setText(persons.getName());
        });
    }

    @Override
    public int getItemCount() {
        if (itemList == null)
            return 0;
        return itemList.size();
    }

    public Flowable<List<Persons>> getItems() {
        return items;
    }

    public void setItems(Flowable<List<Persons>> items) {
        this.items = items;

        items.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(o -> {
            if (itemList != null) {
                DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new PersonListDifference(itemList, o));
                itemList = o;
                diffResult.dispatchUpdatesTo(this);
            } else {
                itemList = o;
                notifyDataSetChanged();
            }
        });
    }
}
