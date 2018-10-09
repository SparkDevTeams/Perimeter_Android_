package perimeter.sparkdev.com.perimeter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
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
    //images
    private Integer[] IMAGES = {R.drawable.gc,R.drawable.ecs,R.drawable.pg6,R.drawable.pg5,
                            R.drawable.library,R.drawable.sasc,R.drawable.oe};
    private ArrayList<Integer> IMGS2 = new ArrayList<Integer>(Arrays.asList(IMAGES));

    //names
    private String[] NAMES = {"GC", "ECS", "PG6", "PG5",
                        "Library","SASC","OE"};
    private ArrayList<String> NAMES2 = new ArrayList<String>(Arrays.asList(NAMES));

    //descriptions
    private String [] DESCRIPTIONS = {"Whats for lunch?", "Im going to print now.", "Good luck on the test!"
                           , "Anyone found an ID?", "Im going to print now.", "Good luck on the test!","Im in the lab."} ;
    private ArrayList<String> DESCR2 = new ArrayList<String>(Arrays.asList(DESCRIPTIONS));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);
        //start here
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.messagesRecyclerView);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        InboxAdapter customAdapter = new InboxAdapter(this, NAMES2,DESCR2,IMGS2);
        recyclerView.setAdapter(customAdapter);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(recyclerView.getContext()
                                                                            ,llm.getOrientation());
        recyclerView.addItemDecoration(itemDecoration);

    }

}


