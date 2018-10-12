package com.sparkdev.perimeter.activities.MessageThread;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sparkdev.perimeter.R;
import com.sparkdev.perimeter.activities.MessageThread.adapters.RecyclerAdapter;

import java.util.ArrayList;

public class MessageThread extends Activity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ArrayList <String> mContacts = new ArrayList<>();
    private ArrayList <String> mMessages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_thread);

        for(int i = 0; i < 30; i++){
            mContacts.add("Contact " + (i+1));
            mMessages.add("This is an example of a text message from Contact " + (i+1));
        }

        // Get access to the RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.message_recycler_view);
        // Create the adapter and supply the adapter with the data (i.e from an arraylist or database)
        mAdapter = new RecyclerAdapter(this,mContacts, mMessages);
        // Connect the adapter to the RecyclerView
        mRecyclerView.setAdapter(mAdapter);
        // Define the RecyclerView's default layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }
}