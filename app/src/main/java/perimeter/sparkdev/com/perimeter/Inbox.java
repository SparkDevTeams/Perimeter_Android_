package perimeter.sparkdev.com.perimeter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class Inbox extends  AppCompatActivity {

    private int[] IMAGES = {R.drawable.person1, R.drawable.ecs,R.drawable.lab,
                    R.drawable.sun, R.drawable.panther_hall,R.drawable.person1,
                    R.drawable.ecs,R.drawable.lab,R.drawable.sun, R.drawable.panther_hall};

    private String[] NAMES = {"GC", "ECS", "CP", "Astronomy",
                        "Panther Hall","GC", "ECS", "CP", "Astronomy",
                         "Panther Hall"};
    private ArrayList<String> names2 = new ArrayList<String>(Arrays.asList(NAMES));
    private String [] DESCRIPTIONS = {"Whats for lunch?", "Im going to print now.", "Good luck on the test!"
                            ,"The starts look beautiful.", "Anyone found an ID?",
                            "Whats for lunch?", "Im going to print now.", "Good luck on the test!"
                             ,"The starts look beautiful.", "Anyone found an ID?"} ;
    private ArrayList<String> desc2 = new ArrayList<String>(Arrays.asList(DESCRIPTIONS));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);
        //start here
        RecyclerView view = (RecyclerView)findViewById(R.id.messagesRecyclerView);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        view.setLayoutManager(llm);
        InboxAdapter customAdapter = new InboxAdapter(names2,desc2);
        view.setAdapter(customAdapter);


    }

}


