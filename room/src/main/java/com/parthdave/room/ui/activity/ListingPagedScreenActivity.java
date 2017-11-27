package com.parthdave.room.ui.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.recyclerview.extensions.DiffCallback;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.parthdave.room.R;
import com.parthdave.room.database.DatabaseOperator;
import com.parthdave.room.database.dbmodels.Persons;
import com.parthdave.room.ui.adapter.ListAdapter;
import com.parthdave.room.ui.adapter.LivePagedViewHolder;
import com.parthdave.room.ui.adapter.PagedAdapter;
import com.parthdave.room.utils.PersonListDifference;

import java.sql.Date;

public class ListingPagedScreenActivity extends AppCompatActivity implements PagedAdapter.ListAction {

    DatabaseOperator databaseOperator;

    private RecyclerView rvList;
    private PagedAdapter listAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing_screen);

        rvList = findViewById(R.id.rvList);

        databaseOperator = new DatabaseOperator(this);


        listAdapter = new PagedAdapter(this, new DiffCallback<Persons>() {
            @Override
            public boolean areItemsTheSame(@NonNull Persons oldItem, @NonNull Persons newItem) {
                return oldItem.id == newItem.id;
            }

            @Override
            public boolean areContentsTheSame(@NonNull Persons oldItem, @NonNull Persons newItem) {
                return oldItem.getUpdatedAt().getTime() == newItem.getUpdatedAt().getTime();
            }
        });
        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.setAdapter(listAdapter);
        databaseOperator.fetchPersonsPaged().observe(this, object -> listAdapter.setList(object));
    }

    public void onAddClick(View view) {
        Persons persons = new Persons();
        long time = System.currentTimeMillis();
        persons.setCreatedAt(new Date(time));
        persons.setUpdatedAt(new Date(time));
        persons.setName("asd");

        databaseOperator.insertPerson(persons);
    }

    @Override
    public void onClick(Persons persons, int position) {
        persons.setUpdatedAt(new Date(System.currentTimeMillis()));
        databaseOperator.updatePerson(persons);
    }
}
