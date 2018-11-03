package com.sparkdev.perimeter.activities.MessageThread.adapters;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.sparkdev.perimeter.R;
import com.sparkdev.perimeter.models.Message;

import java.text.SimpleDateFormat;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.Context.CLIPBOARD_SERVICE;

public class MessageThreadAdapter extends RecyclerView.Adapter<MessageThreadAdapter.MyViewHolder> {

    private List<Message> mMessages;        // This will hold your data
    private LayoutInflater contactInflater;      // This will be the inflater for ContactListAdapter
    private Context mContext;
    private LinearLayout mLinearLayout;

    // ContactListAdapter Constructor
    public MessageThreadAdapter(Context mContext, List<Message> mMessages) {
        contactInflater = LayoutInflater.from(mContext); // Initialize the layout inflater
        this.mMessages = mMessages;
        this.mContext = mContext;
    }

    // Inner class to the ContactListAdapter and extends
    public class MyViewHolder extends RecyclerView.ViewHolder {
        // The following variables are for the text view and the adapter for each row
        public final TextView mContactTextView;
        public final TextView mMessageTextView;
        public final TextView mTimeStampTextView;
        public final CircleImageView mContactImage;
        final MessageThreadAdapter rowAdapter;

        // Constructor where the first parameter is to inflate the layout and the second
        // parameter is to associate the ContactViewHolder with its adapter
        public MyViewHolder(View itemView, MessageThreadAdapter adapter) {
            super(itemView);
            // Initialize the view holder's text view from the XML resources (activity_contact_list.xml)
            // Be sure to cast it to the View type that you need it to be (i.e TextView)
            mContactTextView = itemView.findViewById(R.id.contact_name);
            mMessageTextView = itemView.findViewById(R.id.message_text);
            mTimeStampTextView = itemView.findViewById(R.id.time_stamp);
            mContactImage = itemView.findViewById(R.id.contact_icon);
            mLinearLayout = itemView.findViewById(R.id.cell_layout);
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
        final Message currentMessage = mMessages.get(position);    // Hold the current message

        //holder.mContactImage.setText(currentMessage.getTimestamp().toString());    // Set contact icon at position to TextView
        holder.mContactTextView.setText(currentMessage.getSenderDisplayName());    // Set contact name at position to TextView
        holder.mMessageTextView.setText(currentMessage.getMessage());    // Set message at position to TextView

        //fix time formatting
        SimpleDateFormat df = new SimpleDateFormat("h:mm a");
        String formattedDate = df.format(currentMessage.getTimestamp());

        holder.mTimeStampTextView.setText(formattedDate);    // Set time stamp at position to TextView

        //Get clipboard manager object
        Object clipboardService = mContext.getSystemService(CLIPBOARD_SERVICE);
        final ClipboardManager clipboardManager = (ClipboardManager)clipboardService;

        // Copies message text on long click
        holder.mMessageTextView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View currentMessageView) {
                String srcText = currentMessage.getMessage();

                // Create a new ClipData.
                ClipData clipData = ClipData.newPlainText("Source Text", srcText);
                // Set it as primary clip data to copy text to system clipboard.
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(mContext, "Message text copied", Toast.LENGTH_SHORT).show();

                return false;
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mMessages.size();
    }

    public void changeMessageList(List<Message> newMsgs){
        mMessages = newMsgs;
    }
}