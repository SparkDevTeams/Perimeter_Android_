package perimeter.sparkdev.com.perimeter;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MessageThread extends Activity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private String[] words = new String[30];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_thread);

        for(int i = 0; i < 30; i++){
            words[i]=("Contact " + i);
        }

        // Get access to the RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.message_recycler_view);
        // Create the adapter and supply the adapter with the data (i.e from an arraylist or database)
        mAdapter = new RecyclerAdapter(this,words);
        // Connect the adapter to the RecyclerView
        mRecyclerView.setAdapter(mAdapter);
        // Define the RecyclerView's default layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }
}