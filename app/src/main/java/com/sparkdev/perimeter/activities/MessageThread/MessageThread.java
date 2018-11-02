package com.sparkdev.perimeter.activities.MessageThread;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.sparkdev.perimeter.R;
import com.sparkdev.perimeter.models.Firebase.ChatRoomInterfaces.GetChatRoomMessagesCompletionListener;
import com.sparkdev.perimeter.activities.MessageThread.adapters.RecyclerAdapter;
import com.sparkdev.perimeter.models.ChatRoom;
import com.sparkdev.perimeter.models.Firebase.ChatRoomInterfaces.UpdateChatRoomsMessageCompletionListener;
import com.sparkdev.perimeter.models.Firebase.FirebaseAPI;
import com.sparkdev.perimeter.models.Firebase.FirestoreMessagesCollection;
import com.sparkdev.perimeter.models.Message;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

public class MessageThread extends AppCompatActivity implements GetChatRoomMessagesCompletionListener {

  private static final String TAG = "MessageThread";
  private RecyclerView mRecyclerView;
  private RecyclerAdapter mAdapter;
  private LinearLayoutManager mLayoutManager;
  private List<Message> mMessages;
  private FirebaseAPI mFirebaseAPI;
  private ChatRoom mChatRoom = new ChatRoom();
  private Button mSendButton;
  private EditText mReceiveText;
  private Bundle data;
  private ChatRoom chRoom;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_message_thread);
    mFirebaseAPI = FirebaseAPI.getInstance(this);
    //Intent passing data must be in Oncreate()

    data = getIntent().getExtras();
    chRoom = (ChatRoom) data.getParcelable("chat_room");

    mReceiveText = findViewById(R.id.text_input);
    mSendButton= findViewById(R.id.send_button);
    mSendButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Message sendMessages = new Message(new Timestamp(20180212),mReceiveText.getText().toString(),"LaysChip","text","audio","image","video","ECS","37537854"
                ,"AstridR");
        
        mFirebaseAPI.sendMessage(chRoom, sendMessages, new UpdateChatRoomsMessageCompletionListener() {
          @Override
          public void onSuccess() {
            Log.d(TAG, "onSuccess: We have updated the chatroom");
          }

          @Override
          public void onFailure() {

          }
        });
      }
    });


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

  public void onSuccess(List<Message> messages) {
    mMessages = messages;
    // Create the adapter and supply the adapter with the data (i.e from an arraylist or database)
    mAdapter = new RecyclerAdapter(this, mMessages);
    // Connect the adapter to the RecyclerView
    mRecyclerView.setAdapter(mAdapter);

  }

  public void onFailure() {
    Toast.makeText(this, "Unable to load messages", Toast.LENGTH_SHORT).show();
  }

  private void getIncomingIntent() {


      setChatRoom(chRoom.getCurrentMessagesId());
      setChatRoomLocation(chRoom.getLocation());
      setChatRoomIcon(chRoom.getChatRoomImageUrl());

  }

  private void setChatRoom(String currentMessagesId) {
    mChatRoom.setCurrentMessagesId(currentMessagesId);
  }

  private void setChatRoomLocation(String chatRoomLocation) {
    mChatRoom.setLocation(chatRoomLocation);
  }

  private void setChatRoomIcon(String chatRoomImageUrl) {
    mChatRoom.setChatRoomImageUrl(chatRoomImageUrl);
  }

  //Add Listeners for new message from firebase- call method


}