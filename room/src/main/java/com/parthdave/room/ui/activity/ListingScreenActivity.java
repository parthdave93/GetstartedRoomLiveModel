package com.parthdave.room.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.parthdave.room.R;
import com.parthdave.room.database.DatabaseOperator;
import com.parthdave.room.database.FetchData;
import com.parthdave.room.database.dbmodels.Persons;
import com.parthdave.room.ui.adapter.ListAdapter;

import java.sql.Date;

public class ListingScreenActivity extends AppCompatActivity implements ListAdapter.ListAction {

    DatabaseOperator databaseOperator;

    private RecyclerView rvList;
    private ListAdapter listAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing_screen);

        rvList = findViewById(R.id.rvList);

        databaseOperator = new DatabaseOperator(this);


        listAdapter = new ListAdapter(this, this);
        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.setAdapter(listAdapter);
        databaseOperator.fetchPersons(object -> {
            listAdapter.setItems(object);
        });
    }

    public void onAddClick(View view) {
        Persons persons = new Persons();
        long time = System.currentTimeMillis();
        persons.setCreatedAt(new Date(time));
        persons.setUpdatedAt(new Date(time));
        persons.setName("asd " + time);
        databaseOperator.insertPerson(persons);
    }

    @Override
    public void onClick(Persons persons, int position) {
        persons.setUpdatedAt(new Date(System.currentTimeMillis()));
        databaseOperator.updatePerson(persons);

    }
}
