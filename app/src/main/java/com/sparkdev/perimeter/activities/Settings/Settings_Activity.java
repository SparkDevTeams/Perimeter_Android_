package com.sparkdev.perimeter;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.v7.widget.SwitchCompat;
import android.view.View;


public class Settings_Activity extends PreferenceActivity {

    SwitchCompat switch_1, switch_2;
    SharedPreferences preferences;

    boolean stateSwitch1, stateSwitch2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        preferences = getSharedPreferences("PREFS", 0);
        stateSwitch1 = preferences.getBoolean("switch_1", false);
        stateSwitch2 = preferences.getBoolean("switch_2", false);


        switch_1 = (SwitchCompat) findViewById(R.id.switch_1);
        switch_2 = (SwitchCompat) findViewById(R.id.switch_2);

        switch_1.setChecked(stateSwitch1);
        switch_2.setChecked(stateSwitch2);

        switch_1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                stateSwitch1 = !stateSwitch1;
                switch_1.setChecked(stateSwitch1);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("switch_1", stateSwitch1);
                editor.apply();
            }
        });

        switch_2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                stateSwitch2 = !stateSwitch2;
                switch_2.setChecked(stateSwitch2);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("switch_2", stateSwitch2);
                editor.apply();
            }
        });


    }
}
