package com.sparkdev.perimeter.models;

import com.google.firebase.firestore.PropertyName;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ChatRoom {

   @PropertyName("id")
   private String mId;
   @PropertyName("location")
   private String mLocation;
   @PropertyName("beaconIdMajor")
   private String mBeaconIdMajor;
   @PropertyName("beaconIdMinor")
   private String mBeaconIdMinor;
   @PropertyName("usersId")
   private ArrayList<String> mUsers;
   @PropertyName("currentMessagesId")
   private String mCurrentMessagesId;
   @PropertyName("messagesIds")
   private ArrayList<String> mMessagesIds;
   @PropertyName("chatRoomImageUrl")
   private  String mChatRoomImageUrl;
   @PropertyName("description")
   private String mDescription;
   @PropertyName("lastMessage")
   private  Message mLastMessage;

    public ChatRoom(String id, String location, String beaconIdMajor, String beaconIdMinor,
                    ArrayList<String> users, String currentMessagesId, ArrayList<String> messagesIds,
                    String description, String chatRoomImageUrl, Message lastMessage) {
        mId = id;
        mLocation = location;
        mBeaconIdMajor = beaconIdMajor;
        mBeaconIdMinor = beaconIdMinor;
        mUsers = users;
        mDescription = description;
        mCurrentMessagesId = currentMessagesId;
        mMessagesIds = messagesIds;
        mChatRoomImageUrl = chatRoomImageUrl;
        mLastMessage = lastMessage;
    }

    public ChatRoom() {}


    public String getId() {
        return mId;
    }

    public String getLocation() {
        return mLocation;
    }

    public String getBeaconIdMajor() {
        return mBeaconIdMajor;
    }

    public String getBeaconIdMinor() {
        return mBeaconIdMinor;
    }

    public ArrayList<String> getUsers() {
        return mUsers;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getCurrentMessagesId() {
        return mCurrentMessagesId;
    }

    public ArrayList<String> getMessagesIds() {
        return mMessagesIds;
    }

    public String getChatRoomImageUrl() {
        return mChatRoomImageUrl;
    }

    public Message getLastMessage() {
        return mLastMessage;
    }
}
