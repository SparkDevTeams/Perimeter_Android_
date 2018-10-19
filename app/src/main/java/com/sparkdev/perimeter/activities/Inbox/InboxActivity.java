package com.sparkdev.perimeter.activities.Inbox;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;

import com.sparkdev.perimeter.activities.Inbox.adapters.InboxAdapter;
import com.sparkdev.perimeter.R;

import java.util.ArrayList;
import java.util.Arrays;

public class InboxActivity extends AppCompatActivity {
  // Chat Room Images
  private Integer[] images = {R.drawable.gc, R.drawable.ecs, R.drawable.pg6, R.drawable.pg5,
      R.drawable.library, R.drawable.sasc, R.drawable.oe};
  private ArrayList<Integer> images2 = new ArrayList<Integer>(Arrays.asList(images));

  // Chat Room Names
  private String[] names = {"GC", "ECS", "PG6", "PG5",
      "Library", "SASC", "OE"};
  private ArrayList<String> names2 = new ArrayList<String>(Arrays.asList(names));

  // Chat Room Last Message Sent
  private String[] lastMessage = {"Whats for lunch?", "Im going to print now.", "Good luck on the test!"
      , "Anyone found an ID?", "Im going to print now.", "Good luck on the test!", "Im in the lab."};
  private ArrayList<String> lastMessage2 = new ArrayList<String>(Arrays.asList(lastMessage));

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_inbox);

    //Set action bar title
    getSupportActionBar().setTitle("Message Inbox");

    // Get access to the activity's RecyclerView
    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.messagesRecyclerView);

    // Define the RecyclerView's default layout manager and orientation
    LinearLayoutManager llm = new LinearLayoutManager(this);
    llm.setOrientation(LinearLayoutManager.VERTICAL);
    recyclerView.setLayoutManager(llm);

    // Create the InboxAdapter and supply the adapter with the data
    InboxAdapter customAdapter = new InboxAdapter(this, names2, lastMessage2, images2);
    recyclerView.setAdapter(customAdapter);

    // Add the line divider between each row
    DividerItemDecoration itemDecoration = new DividerItemDecoration(recyclerView.getContext()
        , llm.getOrientation());
    recyclerView.addItemDecoration(itemDecoration);

  }



  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.inbox_menu, menu);
    return true;
  }
}


