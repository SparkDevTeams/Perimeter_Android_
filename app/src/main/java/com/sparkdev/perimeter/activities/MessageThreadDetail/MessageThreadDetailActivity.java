package com.sparkdev.perimeter.activities.MessageThreadDetail;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;
import com.sparkdev.perimeter.R;
import com.sparkdev.perimeter.activities.Inbox.adapters.InboxAdapter;
import com.sparkdev.perimeter.activities.MessageThreadDetail.adapter.DetailAdapter;
import com.sparkdev.perimeter.models.ChatRoom;
import com.sparkdev.perimeter.models.Firebase.FirebaseAPI;
import com.sparkdev.perimeter.models.Firebase.LoginInterfaces.PerimeterGetUserCompletionListener;
import com.sparkdev.perimeter.models.UserProfile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MessageThreadDetailActivity extends AppCompatActivity {

  private LinearLayoutManager llm;
  private DividerItemDecoration itemDecoration;
  private RecyclerView recyclerView;
  private DetailAdapter mCustomAdapter;
  private FirebaseAPI fb;
  private List<UserProfile> mUsers = new ArrayList<>() ;
  private Context mContext = this;
  private ChatRoom mChatRoom = new ChatRoom();

  private String [] users = {"WTYeGambWghxX7K11IWLVS7Odmh2", "G811mzvJdl0OLWhB9D1M"};
  private ArrayList<String> userIds = new ArrayList<>(Arrays.asList(users));



  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_message_thread_detail);

    Toolbar toolbar = (Toolbar)findViewById(R.id.detailsToolbar);
    setSupportActionBar(toolbar);


    mChatRoom.setChatRoomImageUrl("https://firebasestorage.googleapis.com/v0/b/perimeter-b0dc8.appspot.com/o/ecs.jpg?alt=media&token=bfcf935f-cc4e-47c1-968d-89b960959d75");
    mChatRoom.setUsers(userIds);
    mChatRoom.setDescription("A chatroom for the ECS building on the MCC");

    // Get access to the activity's RecyclerView
    recyclerView = (RecyclerView) findViewById(R.id.usersRecycler);

    getUsers();

    if(mUsers.size() != 0) {
        // Create the InboxAdapter and supply the adapter with the data
        mCustomAdapter = new DetailAdapter(mContext, mUsers, mChatRoom);
        recyclerView.setAdapter(mCustomAdapter);
        recyclerView.setNestedScrollingEnabled(false);
    }

      // Define the RecyclerView's default layout manager and orientation
    llm = new LinearLayoutManager(this);
    llm.setOrientation(LinearLayoutManager.VERTICAL);
    recyclerView.setLayoutManager(llm);

  }

  private void getUsers()
  {
      FirebaseAPI fb = FirebaseAPI.getInstance(this);
      for(String user : mChatRoom.getUsers())
      {
          fb.getUserWithUserID(user, new PerimeterGetUserCompletionListener() {
              @Override
              public void onSuccess(UserProfile profile) {
                   mUsers.add(profile);
              }

              @Override
              public void onFailure() {
                  Toast.makeText(mContext,"Unable to load chat detail", Toast.LENGTH_SHORT).show();
              }
          });
      }

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
