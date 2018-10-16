package com.sparkdev.perimeter.activities.MessageThread.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sparkdev.perimeter.R;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private final ArrayList<String> mContacts;        // This will hold your data
    private final ArrayList<String> mMessages;        // This will hold your data
    private LayoutInflater contactInflater;      // This will be the inflater for ContactListAdapter

    // ContactListAdapter Constructor
    public RecyclerAdapter(Context context, ArrayList<String> mContacts, ArrayList<String> mMessages) {
        contactInflater = LayoutInflater.from(context); // Initialize the layout inflater
        this.mContacts = mContacts;
        this.mMessages = mMessages;
    }

    // Inner class to the ContactListAdapter and extends
    public class MyViewHolder extends RecyclerView.ViewHolder {
        // The following variables are for the text view and the adapter for each row
        public final TextView mContactTextView;
        public final TextView mMessageTextView;
        final RecyclerAdapter rowAdapter;

        // Constructor where the first parameter is to inflate the layout and the second
        // parameter is to associate the ContactViewHolder with its adapter
        public MyViewHolder(View itemView, RecyclerAdapter adapter) {
            super(itemView);
            // Initialize the view holder's text view from the XML resources (activity_contact_list.xml)
            // Be sure to cast it to the View type that you need it to be (i.e TextView)
            mContactTextView = (TextView) itemView.findViewById(R.id.contact_name);
            mMessageTextView = (TextView) itemView.findViewById(R.id.message_text);
            // Set up the adapter
            this.rowAdapter = adapter;
        }
    }

    // The onCreateViewHolder() method is very similar to the onCreate() method. In this method,
    // the LAYOUT will be inflated and it will return a view holder with the specified layout
    // and the corresponding adapter
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Inflate the layout
        View customView = contactInflater.inflate(R.layout.message_layout, viewGroup, false);
        // Return the new view holder
        return new MyViewHolder(customView, this);
    }

    // The onBindViewHolder() connects your data to your view holder
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String currentContact = mContacts.get(position);    // Hold the current contact name
        String currentMessage = mMessages.get(position);    // Hold the current contact name
        holder.mContactTextView.setText(currentContact);    // Set contact name at i position to TextView
        holder.mMessageTextView.setText(currentMessage);    // Set contact name at i position to TextView
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mContacts.size();
    }
}