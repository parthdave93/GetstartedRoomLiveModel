package com.parthdave.rxjavaandroom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.parthdave.room.ui.activity.ListingScreenActivity;
import com.parthdave.tutorial1preference.Tutorial1Preference;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onTutorial1Preference(View view) {
        startActivity(new Intent(this, Tutorial1Preference.class));
    }

    public void onRoomDatabaseClickEvent(View view){
        startActivity(new Intent(this, ListingScreenActivity.class));
    }
}
