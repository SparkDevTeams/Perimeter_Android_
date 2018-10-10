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

import com.sparkdev.perimeter.R;
import com.sparkdev.perimeter.activities.Inbox.TempActivity;

import java.util.ArrayList;

public class InboxAdapter extends RecyclerView.Adapter<InboxAdapter.InboxViewHolder> {

  private ArrayList<String> mTitle;  //titleTextView
  private ArrayList<String> mLastMsg; //last message sent
  private ArrayList<Integer> mImgs; //image
  private Context mContext;

  // InboxAdapter Constructor
  public InboxAdapter(Context context, ArrayList list, ArrayList list2, ArrayList list3) {
    this.mTitle = list;
    this.mLastMsg = list2;
    this.mImgs = list3;
    mContext = context;

  }

  //inner class too retrieve the views
  public class InboxViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public ImageView imageView;
    public TextView titleTextView;
    public TextView lastMsgTextView;
    public RelativeLayout rowLayout;

    public InboxViewHolder(View view) {
      super(view);


      imageView = (ImageView) view.findViewById(R.id.chatImage);
      titleTextView = (TextView) view.findViewById(R.id.chatName);
      lastMsgTextView = (TextView) view.findViewById(R.id.description);
      rowLayout = (RelativeLayout) view.findViewById(R.id.rowItem);

      itemView.setOnClickListener(this);

    }

    //on click listener for each row
    @Override
    public void onClick(View view) {
      Intent intent = new Intent(mContext, TempActivity.class);
      mContext.startActivity(intent);
    }
  }

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
    String a = mTitle.get(i);
    String b = mLastMsg.get(i);
    Integer c = mImgs.get(i);

    inboxViewHolder.titleTextView.setText(a);
    inboxViewHolder.lastMsgTextView.setText(b);
    inboxViewHolder.imageView.setImageResource(c);

  }

  @Override
  public int getItemCount() {
    return mTitle.size();
  }
}
