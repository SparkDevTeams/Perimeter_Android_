package com.sparkdev.perimeter.activities.Inbox;


import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Toast;

import com.sparkdev.perimeter.activities.Firebase.ChatRoomInterfaces.GetChatRoomMessagesCompletionListener;
import com.sparkdev.perimeter.activities.Firebase.ChatRoomInterfaces.GetChatRoomsCompletionListener;
import com.sparkdev.perimeter.activities.Inbox.adapters.InboxAdapter;
import com.sparkdev.perimeter.R;
import com.sparkdev.perimeter.models.ChatRoom;
import com.sparkdev.perimeter.models.FirebaseAPI2;
import java.util.List;

public class InboxActivity extends AppCompatActivity {

  private LinearLayoutManager llm;
  private DividerItemDecoration itemDecoration;
  private RecyclerView recyclerView;
  private FirebaseAPI2 fb;
  private List<ChatRoom> mChatRooms ;
  private Context mContext = this;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_inbox);

    //Set action bar title
    getSupportActionBar().setTitle("Message Inbox");

    // Get access to the activity's RecyclerView
    recyclerView = (RecyclerView) findViewById(R.id.messagesRecyclerView);

    //fetch ChatRooms list from Firebase
      fb = FirebaseAPI2.getInstance(this);
      fb.getAllChatRooms( new GetChatRoomsCompletionListener(){

          public void onSuccess(List<ChatRoom> chatRooms) {
            mChatRooms = chatRooms;

            // Create the InboxAdapter and supply the adapter with the data
            InboxAdapter customAdapter = new InboxAdapter(mContext, mChatRooms);
            recyclerView.setAdapter(customAdapter);
          }


          public void onFailure() {
            Toast.makeText(mContext,"Unable to load chats", Toast.LENGTH_SHORT).show();
          }
      });

    // Define the RecyclerView's default layout manager and orientation
    llm = new LinearLayoutManager(this);
    llm.setOrientation(LinearLayoutManager.VERTICAL);
    recyclerView.setLayoutManager(llm);


    // Add the line divider between each row
    itemDecoration = new DividerItemDecoration(recyclerView.getContext()
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


