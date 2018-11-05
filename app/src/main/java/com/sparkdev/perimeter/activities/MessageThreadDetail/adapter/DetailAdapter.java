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

import com.bumptech.glide.Glide;
import com.sparkdev.perimeter.R;
import com.sparkdev.perimeter.models.ChatRoom;
import com.sparkdev.perimeter.models.UserProfile;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

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
        private CircleImageView imageView;
        private TextView userName;
        private TextView displayName;
        private RelativeLayout rowLayout;


        public UsersViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = (CircleImageView) itemView.findViewById(R.id.userImage);
            userName = (TextView) itemView.findViewById(R.id.userName);
            displayName = (TextView) itemView.findViewById(R.id.displayName);
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

        if(currentUsers.getProfileImageUrl() == null)
            usersViewHolder.imageView.setImageResource(R.drawable.ic_user);
        else
            Glide.with(mContext).load(currentUsers.getProfileImageUrl()).into(usersViewHolder.imageView);

        usersViewHolder.userName.setText(currentUsers.getFirstName() + " " + currentUsers.getLastName());
        usersViewHolder.displayName.setText("(" + currentUsers.getDisplayName()+ ")");
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public void setNewUsers(List<UserProfile> userProfiles) {mUsers = userProfiles;}
}
