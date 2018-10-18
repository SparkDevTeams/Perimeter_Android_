package com.sparkdev.perimeter.activities.Settings;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.sparkdev.perimeter.R;

public class MainActivity extends AppCompatActivity {
    Button btnShowSetting;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnShowSetting = (Button) findViewById(R.id.button);

        btnShowSetting.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                startActivity(new Intent(MainActivity.this, Settings_Activity.class));

            }

        });

    }
}
