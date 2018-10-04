package perimeter.sparkdev.com.perimeter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //test inbox screen
        Intent intent = new Intent(this,Inbox.class);
        startActivity(intent);
    }
}
