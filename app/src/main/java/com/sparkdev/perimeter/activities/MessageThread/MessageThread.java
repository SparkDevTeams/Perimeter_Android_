package com.sparkdev.perimeter.activities.MessageThread;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.app.AppCompatActivity;
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
import com.sparkdev.perimeter.activities.MessageThreadDetail.MessageThreadDetailActivity;
import com.sparkdev.perimeter.models.Firebase.ChatRoomInterfaces.GetChatRoomMessagesCompletionListener;
import com.sparkdev.perimeter.activities.MessageThread.adapters.MessageThreadAdapter;
import com.sparkdev.perimeter.models.ChatRoom;
import com.sparkdev.perimeter.models.Firebase.FirebaseAPI;
import com.sparkdev.perimeter.models.Firebase.FirestoreMessagesCollection;
import com.sparkdev.perimeter.models.Message;

import java.util.ArrayList;
import java.util.List;

public class MessageThread extends AppCompatActivity implements GetChatRoomMessagesCompletionListener {

  private static final String TAG = "MessageThread";
  private RecyclerView mRecyclerView;
  private MessageThreadAdapter mAdapter;
  private LinearLayoutManager mLayoutManager;
  private List<Message> mMessages;
  private FirebaseAPI mFirebaseAPI;
  private ChatRoom mChatRoom = new ChatRoom();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_message_thread);

    mFirebaseAPI = FirebaseAPI.getInstance(this);

    getIncomingIntent();
    mFirebaseAPI.getMessagesForChatRoom(mChatRoom, this);

    //Set action bar title
    getSupportActionBar().setTitle(mChatRoom.getLocation());

    // Get access to the RecyclerView
    mRecyclerView = findViewById(R.id.message_recycler_view);

    // Define the RecyclerView's default layout manager
    mLayoutManager = new LinearLayoutManager(this);
    mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

    mRecyclerView.setLayoutManager(mLayoutManager);

    //Add a divider between each cell
    DividerItemDecoration cellDivider = new DividerItemDecoration(mRecyclerView.getContext(),
        mLayoutManager.getOrientation());
    mRecyclerView.addItemDecoration(cellDivider);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.message_thread_menu, menu);
    return true;
  }

  public boolean onOptionsItemSelected(MenuItem item){
      switch(item.getItemId()){
          case R.id.chat_settings:
              Intent intent = new Intent(MessageThread.this, MessageThreadDetailActivity.class);
              intent.putExtra("currentChatRoom", (Parcelable) mChatRoom);
              startActivity(intent);
              return true;
          default:
              return super.onOptionsItemSelected(item) ;
      }
    }

  public void onSuccess(List<Message> messages) {
    mMessages = messages;
    // Create the adapter and supply the adapter with the data (i.e from an arraylist or database)
    mAdapter = new MessageThreadAdapter(this, mMessages);
    // Connect the adapter to the RecyclerView
    mRecyclerView.setAdapter(mAdapter);

    setUpListeners();
  }

  public void onFailure() {
    Toast.makeText(this, "Unable to load messages", Toast.LENGTH_SHORT).show();
  }

  private void setUpListeners()
  {
      DocumentReference docRef = (DocumentReference) FirebaseFirestore.getInstance()
              .collection("Messages").addSnapshotListener(new EventListener<QuerySnapshot>() {
                  @Override
                  public void onEvent(@Nullable QuerySnapshot snapshots,
                                      @Nullable FirebaseFirestoreException e) {
                      if (e != null) {
                          Log.w(TAG, "listen:error", e);
                          return;
                      }
                      //update message list and notify adapter of the change.
                      List<Message> newMsgs = new ArrayList<>();
                      for(int i = 0; i< snapshots.getDocuments().size(); i++)
                      {
                          DocumentSnapshot document = snapshots.getDocuments().get(i);
                          if(document.getId().equals(mChatRoom.getCurrentMessagesId()))
                          {
                              FirestoreMessagesCollection messagesCollection = document.toObject(FirestoreMessagesCollection.class);
                              newMsgs = messagesCollection.getMessages();
                              break;
                          }
                      }
                      mAdapter.changeMessageList(newMsgs);
                      mAdapter.notifyDataSetChanged();
                  }
              });
  }

  private void getIncomingIntent() {
    Bundle data = getIntent().getExtras();
    mChatRoom = (ChatRoom) data.getParcelable("chat_room");
  }

  /*
  private void setChatRoom(String currentMessagesId) {
    mChatRoom.setCurrentMessagesId(currentMessagesId);
  }

  private void setChatRoomLocation(String chatRoomLocation) {
    mChatRoom.setLocation(chatRoomLocation);
  }

  private void setChatRoomIcon(String chatRoomImageUrl) {
    mChatRoom.setChatRoomImageUrl(chatRoomImageUrl);
  }
  */
}