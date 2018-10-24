package com.sparkdev.perimeter.activities.Inbox.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sparkdev.perimeter.R;
import com.sparkdev.perimeter.activities.MessageThread.MessageThread;
import com.sparkdev.perimeter.models.ChatRoom;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InboxAdapter extends RecyclerView.Adapter<InboxAdapter.InboxViewHolder> {

  private List<ChatRoom> mChats ;
  private Context mContext;

  // InboxAdapter Constructor
  public InboxAdapter(Context context, List<ChatRoom> chats ) {
    mContext = context;
    mChats = chats;


  }

  //inner class to retrieve the views
  public class InboxViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public ImageView imageView;
    public TextView titleTextView;
    public TextView lastMsgTextView;
    public TextView time;
    public RelativeLayout rowLayout;

    //inbox view holder constructor
    public InboxViewHolder(View view) {
      super(view);

      imageView = (ImageView) view.findViewById(R.id.chatImage);
      titleTextView = (TextView) view.findViewById(R.id.chatName);
      lastMsgTextView = (TextView) view.findViewById(R.id.description);
      time = (TextView)view.findViewById(R.id.timeStamp);
      rowLayout = (RelativeLayout) view.findViewById(R.id.rowItem);

      itemView.setOnClickListener(this);

    }

    //on click listener for each row
    @Override
    public void onClick(View view) {
      Intent intent = new Intent(mContext, MessageThread.class);
      mContext.startActivity(intent);
    }
  }
  //end of inner class


  @NonNull
  @Override
  public InboxViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
    View inboxView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listview_row,
        viewGroup, false);

    //creates InboxViewHolder with the custom layout for the row
    return new InboxViewHolder(inboxView);
  }

  @Override
  public void onBindViewHolder(InboxAdapter.InboxViewHolder inboxViewHolder, int i) {
    ChatRoom currentChat = mChats.get(i);

    //fix time formatting
    SimpleDateFormat df = new SimpleDateFormat("h:mm a");
    String formattedDate = df.format(currentChat.getLastMessage().getTimestamp());

    inboxViewHolder.titleTextView.setText(currentChat.getId());
    inboxViewHolder.lastMsgTextView.setText(currentChat.getLastMessage().getMessage());
    inboxViewHolder.time.setText(formattedDate);
    Glide.with(mContext).load(currentChat.getChatRoomImageUrl()).into(inboxViewHolder.imageView);

  }

  @Override
  public int getItemCount() {
    return mChats.size();
  }
}
