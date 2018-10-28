package com.sparkdev.perimeter.activities.MessageThreadDetail;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.widget.ScrollView;

import com.sparkdev.perimeter.R;
import com.sparkdev.perimeter.activities.Inbox.adapters.InboxAdapter;
import com.sparkdev.perimeter.activities.MessageThreadDetail.adapter.DetailAdapter;
import com.sparkdev.perimeter.models.ChatRoom;
import com.sparkdev.perimeter.models.Firebase.FirebaseAPI;
import com.sparkdev.perimeter.models.UserProfile;

import java.util.ArrayList;
import java.util.List;

public class MessageThreadDetailActivity extends AppCompatActivity {

  private LinearLayoutManager llm;
  private DividerItemDecoration itemDecoration;
  private RecyclerView recyclerView;
  private DetailAdapter mCustomAdapter;
  private FirebaseAPI fb;
  private List<UserProfile> mUsers = new ArrayList<>() ;
  private Context mContext = this;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_message_thread_detail);

    getSupportActionBar().setTitle("Chat Details");

    //test the adapter with dummy data
    populateList();

    // Get access to the activity's RecyclerView
    recyclerView = (RecyclerView) findViewById(R.id.usersRecycler);

    // Create the InboxAdapter and supply the adapter with the data
    mCustomAdapter = new DetailAdapter(mContext, mUsers);
    recyclerView.setAdapter(mCustomAdapter);
    recyclerView.setNestedScrollingEnabled(false);

      // Define the RecyclerView's default layout manager and orientation
    llm = new LinearLayoutManager(this);
    llm.setOrientation(LinearLayoutManager.VERTICAL);
    recyclerView.setLayoutManager(llm);

  }

  private void populateList()
  {
        UserProfile user = new UserProfile();
        UserProfile user2 = new UserProfile();
        UserProfile user3 = new UserProfile();
        UserProfile user4 = new UserProfile();
        UserProfile user5= new UserProfile();
        UserProfile user6 = new UserProfile();
        UserProfile user7 = new UserProfile();
        UserProfile user8 = new UserProfile();
        UserProfile user9 = new UserProfile();
        UserProfile user10 = new UserProfile();
        UserProfile user11= new UserProfile();
        UserProfile user12 = new UserProfile();
        mUsers.add(user);
        mUsers.add(user2);
        mUsers.add(user3);
        mUsers.add(user4);
        mUsers.add(user5);
        mUsers.add(user6);
        mUsers.add(user7);
        mUsers.add(user8);
        mUsers.add(user9);
        mUsers.add(user10);
        mUsers.add(user11);
        mUsers.add(user12);
        mUsers.get(0).setDisplayName("Vanessa");
        mUsers.get(1).setDisplayName("Richard");
        mUsers.get(2).setDisplayName("Davonne");
        mUsers.get(3).setDisplayName("Cassandra");
        mUsers.get(4).setDisplayName("Astrid");
        mUsers.get(5).setDisplayName("Dayana");
        mUsers.get(6).setDisplayName("Vanessa");
        mUsers.get(7).setDisplayName("Richard");
        mUsers.get(8).setDisplayName("Davonne");
        mUsers.get(9).setDisplayName("Cassandra");
        mUsers.get(10).setDisplayName("Astrid");
        mUsers.get(11).setDisplayName("Dayana");
  }

}
