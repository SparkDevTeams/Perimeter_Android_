package com.sparkdev.perimeter.activities.MessageThread;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import com.sparkdev.perimeter.R;
import com.sparkdev.perimeter.activities.Firebase.ChatRoomInterfaces.GetChatRoomMessagesCompletionListener;
import com.sparkdev.perimeter.activities.MessageThread.adapters.RecyclerAdapter;
import com.sparkdev.perimeter.models.ChatRoom;
import com.sparkdev.perimeter.models.FirebaseAPI2;
import com.sparkdev.perimeter.models.Message;
import java.util.ArrayList;
import java.util.List;

public class MessageThread extends AppCompatActivity implements GetChatRoomMessagesCompletionListener {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private ArrayList <String> mContacts = new ArrayList<>();
    private List <Message> mMessages;
    private com.sparkdev.perimeter.models.FirebaseAPI2 mFirebaseAPI;
    private ChatRoom mChatRoom = new ChatRoom();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_thread);

        for(int i = 0; i < 30; i++){
            mContacts.add("Contact " + (i+1));
        }
        mFirebaseAPI = FirebaseAPI2.getInstance(this);
        mChatRoom.setCurrentMessageId("lHEXmV32Vt5SFSiQ4fnq");
        mFirebaseAPI.getMessagesForChatRoom(mChatRoom,this);

        //Set action bar title
        getSupportActionBar().setTitle("Group 1");

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
        mAdapter = new RecyclerAdapter(this,mContacts, mMessages);
        // Connect the adapter to the RecyclerView
        mRecyclerView.setAdapter(mAdapter);
    }

    public void onFailure() {
        //Add toast message
    }

}