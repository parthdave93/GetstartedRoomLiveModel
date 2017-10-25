package com.parthdave.tutorial1preference;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/**
 * Created by ParthDave on 10/19/2017.
 */

public class Tutorial1Preference extends AppCompatActivity {

    Prefs prefs;
    //dummy shared preference to check observable changes
    private String sharedPreferenceName = "Tutorial1";

    Button btnLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = new Prefs(getSharedPreferences(sharedPreferenceName, MODE_PRIVATE));
        setContentView(R.layout.activity_tutorial1);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setText(prefs.getUserLoggedIn(false) ? "(logged in)" : "!(logged in)");

        prefs.registerForLoginEvent(new ChangeEmitter<Boolean>() {
            @Override
            public void onChange(Boolean value) {
                btnLogin.setText(value ? "(logged in)" : "!(logged in)");
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prefs.setUserLoggedIn(!prefs.getUserLoggedIn(false));
            }
        });
    }
}
