package com.sparkdev.perimeter.activities.MessageThreadDetail.adapter;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.sparkdev.perimeter.R;
import com.sparkdev.perimeter.activities.Inbox.adapters.InboxAdapter;
import com.sparkdev.perimeter.activities.MessageThread.adapters.RecyclerAdapter;
import com.sparkdev.perimeter.models.UserProfile;

import java.util.List;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.UsersViewHolder> {

    private List<UserProfile> mUsers;
    private Context mContext;

    //Adapter constructor
    public DetailAdapter(Context context, List<UserProfile> users)
    {
        mUsers = users;
        mContext = context;
    }

    //inner class
    public class UsersViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView userName;
        private RelativeLayout rowLayout;


        public UsersViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = (ImageView)itemView.findViewById(R.id.userImage);
            userName = (TextView) itemView.findViewById(R.id.userName);
            rowLayout = (RelativeLayout) itemView.findViewById(R.id.userRow);

        }
    }
    //end of inner class

    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View detailView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.users_layout,
                viewGroup, false);

        //creates view holder with custom layout for each row.
        return new UsersViewHolder(detailView);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersViewHolder usersViewHolder, int i) {
        UserProfile currentUsers = mUsers.get(i);

//        usersViewHolder.imageView   user profile?
        usersViewHolder.userName.setText(currentUsers.getDisplayName());
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }
}
