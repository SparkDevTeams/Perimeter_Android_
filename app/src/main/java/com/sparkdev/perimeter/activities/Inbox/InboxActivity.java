package com.sparkdev.perimeter.activities.Inbox;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.sparkdev.perimeter.R;
import com.sparkdev.perimeter.activities.Inbox.adapters.InboxAdapter;
import com.sparkdev.perimeter.activities.Settings.Settings_Activity;
import com.sparkdev.perimeter.models.ChatRoom;
import com.sparkdev.perimeter.models.Firebase.ChatRoomInterfaces.GetChatRoomsCompletionListener;
import com.sparkdev.perimeter.models.Firebase.FirebaseAPI;

import java.util.ArrayList;
import java.util.List;

public class InboxActivity extends AppCompatActivity {

  private LinearLayoutManager llm;
  private DividerItemDecoration itemDecoration;
  private RecyclerView recyclerView;
  private InboxAdapter mCustomAdapter;
  private FirebaseAPI fb;
  private List<ChatRoom> mChatRooms ;
  private Context mContext = this;
    private static final String TAG = "InboxActivity";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_inbox);

    //Set action bar title
    getSupportActionBar().setTitle("Message Inbox");

    // Get access to the activity's RecyclerView
    recyclerView = (RecyclerView) findViewById(R.id.messagesRecyclerView);

    //fetch ChatRooms list from Firebase
      fb = FirebaseAPI.getInstance(this);
      fb.getAllChatRooms( new GetChatRoomsCompletionListener(){

          public void onSuccess(List<ChatRoom> chatRooms) {
            mChatRooms = chatRooms;

            // Create the InboxAdapter and supply the adapter with the data
            mCustomAdapter = new InboxAdapter(mContext, mChatRooms);
            recyclerView.setAdapter(mCustomAdapter);

              //set up listners
              setUpListeners();
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

  public boolean onOptionsItemSelected(MenuItem item){
    switch(item.getItemId()){
      case R.id.item1:
        startActivity(new Intent(InboxActivity.this, Settings_Activity.class));
        return true;

      default:
        return super.onOptionsItemSelected(item) ;
    }
  }
  
    public void setUpListeners()
    {
        DocumentReference docRef = (DocumentReference) FirebaseFirestore.getInstance().collection("ChatRooms").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot snapshots,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w(TAG, "listen:error", e);
                    return;
                }
                List<ChatRoom> newChats = new ArrayList<>();
                for(int i = 0; i< snapshots.getDocuments().size(); i++)
                {
                    DocumentSnapshot snapshot = snapshots.getDocuments().get(i);
                    ChatRoom chatRoom = snapshot.toObject(ChatRoom.class);
                    newChats.add(chatRoom);
                }
                mChatRooms = newChats;
                mCustomAdapter.changeChatList(mChatRooms);
                mCustomAdapter.notifyDataSetChanged();
            }
        });
    }
}


