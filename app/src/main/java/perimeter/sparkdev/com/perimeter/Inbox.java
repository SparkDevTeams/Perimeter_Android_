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

    private Integer[] IMAGES = {R.drawable.gc,R.drawable.ecs,R.drawable.pg6,R.drawable.pg5,
                            R.drawable.library,R.drawable.library};
    private ArrayList<Integer> imgs2 = new ArrayList<Integer>(Arrays.asList(IMAGES));

    private String[] NAMES = {"GC", "ECS", "PG6", "PG5",
                        "Library","SASC"};
    private ArrayList<String> names2 = new ArrayList<String>(Arrays.asList(NAMES));
    private String [] DESCRIPTIONS = {"Whats for lunch?", "Im going to print now.", "Good luck on the test!"
                           , "Anyone found an ID?", "Im going to print now.", "Good luck on the test!"} ;
    private ArrayList<String> desc2 = new ArrayList<String>(Arrays.asList(DESCRIPTIONS));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);
        //start here
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.messagesRecyclerView);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        InboxAdapter customAdapter = new InboxAdapter(names2,desc2,imgs2);
        recyclerView.setAdapter(customAdapter);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(recyclerView.getContext()
                                                                            ,llm.getOrientation());
        recyclerView.addItemDecoration(itemDecoration);


    }

}


