package com.sparkdev.perimeter.activities.MessageThread;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Toast;
import com.sparkdev.perimeter.R;
import com.sparkdev.perimeter.activities.Firebase.ChatRoomInterfaces.GetChatRoomMessagesCompletionListener;
import com.sparkdev.perimeter.activities.MessageThread.adapters.RecyclerAdapter;
import com.sparkdev.perimeter.models.ChatRoom;
import com.sparkdev.perimeter.models.FirebaseAPI2;
import com.sparkdev.perimeter.models.Message;
import java.util.List;

public class MessageThread extends AppCompatActivity implements GetChatRoomMessagesCompletionListener {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private List <Message> mMessages;
    private ChatRoom mChatRoom = new ChatRoom();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_thread);

        FirebaseAPI2 mFirebaseAPI = FirebaseAPI2.getInstance(this);

//       mChatRoom.setCurrentMessagesId("lHEXmV32Vt5SFSiQ4fnq");
//       mChatRoom.setLocation("ECS");
        getIncomingIntent();
        mFirebaseAPI.getMessagesForChatRoom(mChatRoom,this);

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

        if (getIntent().hasExtra("chat_room") && getIntent().hasExtra("chat_location") && getIntent().hasExtra("chat_icon")) {
            String currentMessagesId = getIntent().getStringExtra("chat_room");
            String chatRoomLocation = getIntent().getStringExtra("chat_location");
            String chatRoomImageUrl = getIntent().getStringExtra("chat_icon");

            setChatRoom(currentMessagesId);
            setChatRoomLocation(chatRoomLocation);
            setChatRoomIcon(chatRoomImageUrl);
        }
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
}