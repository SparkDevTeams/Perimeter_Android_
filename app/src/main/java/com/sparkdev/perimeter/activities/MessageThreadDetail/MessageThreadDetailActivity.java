package com.sparkdev.perimeter.activities.MessageThreadDetail;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
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
  private RecyclerView recyclerView;
  private DetailAdapter mCustomAdapter;
  private FirebaseAPI fb;
  private List<UserProfile> mUsers = new ArrayList<>() ;
  private Context mContext = this;
  private final ChatRoom mChatRoom = new ChatRoom();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_message_thread_detail);

    Toolbar toolbar = (Toolbar)findViewById(R.id.detailsToolbar);
    setSupportActionBar(toolbar);

    // Get access to the activity's RecyclerView
    recyclerView = (RecyclerView) findViewById(R.id.usersRecycler);

    getIncomingIntent();

    if(mUsers.size() != 0) {
        // Create the InboxAdapter and supply the adapter with the data
        mCustomAdapter = new DetailAdapter(mContext, mUsers);
        recyclerView.setAdapter(mCustomAdapter);
        recyclerView.setNestedScrollingEnabled(false);
    }

      // Define the RecyclerView's default layout manager and orientation
    llm = new LinearLayoutManager(this);
    llm.setOrientation(LinearLayoutManager.VERTICAL);
    recyclerView.setLayoutManager(llm);

  }

  private void getIncomingIntent()
  {
      Bundle data = getIntent().getExtras();
      final ChatRoom chatRoom = (ChatRoom) data.getParcelable("currentChatRoom");
      setImage(chatRoom);
      setDescription(chatRoom);
      fillUsers(chatRoom);
  }

  private void fillUsers(ChatRoom chatRoom)
  {
      FirebaseAPI fb = FirebaseAPI.getInstance(this);
      if(chatRoom.getUsers() == null)
      {
          Toast.makeText(mContext,"No users ", Toast.LENGTH_SHORT).show();
          return;
      }
      for(String user : chatRoom.getUsers())
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

  private void setImage(ChatRoom chatRoom)
  {
      ImageView image = (ImageView) findViewById(R.id.chatImageDetail);
      Glide.with(this).load(chatRoom.getChatRoomImageUrl()).into(image);
  }

  private void setDescription(ChatRoom chatRoom)
  {
      TextView description = (TextView)findViewById(R.id.descriptionText);
      description.setText(chatRoom.getDescription());
  }



}
