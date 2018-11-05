package com.sparkdev.perimeter.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.firestore.PropertyName;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatRoom implements Parcelable{

   @PropertyName("id")
   private String mId;
   @PropertyName("location")
   private String mLocation;
   @PropertyName("beaconIdMajor")
   private String mBeaconIdMajor;
   @PropertyName("beaconIdMinor")
   private String mBeaconIdMinor;
   @PropertyName("userProfileIds")
   private HashMap<String, Object> userProfileIds;

   private String[] usersId;
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
                   String[] usersId, String currentMessagesId, ArrayList<String> messagesIds,
                    String description, String chatRoomImageUrl, Message lastMessage) {
        mId = id;
        mLocation = location;
        mBeaconIdMajor = beaconIdMajor;
        mBeaconIdMinor = beaconIdMinor;
        this.usersId = usersId;
        mDescription = description;
        mCurrentMessagesId = currentMessagesId;
        mMessagesIds = messagesIds;
        mChatRoomImageUrl = chatRoomImageUrl;
        mLastMessage = lastMessage;
    }

    public ChatRoom() {}


  protected ChatRoom(Parcel in) {
    mId = in.readString();
    mLocation = in.readString();
    mBeaconIdMajor = in.readString();
    mBeaconIdMinor = in.readString();
    usersId = in.createStringArray();
    mCurrentMessagesId = in.readString();
    mMessagesIds = in.createStringArrayList();
    mChatRoomImageUrl = in.readString();
    mDescription = in.readString();
    userProfileIds = (HashMap<String, Object>) in.readSerializable();
  }

  public static final Creator<ChatRoom> CREATOR = new Creator<ChatRoom>() {
    @Override
    public ChatRoom createFromParcel(Parcel in) {
      return new ChatRoom(in);
    }

    @Override
    public ChatRoom[] newArray(int size) {
      return new ChatRoom[size];
    }
  };

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

    public String[] getUsers() {
        return usersId;
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

    public String[] getUsersId() {
        return usersId;
    }

    public void setCurrentMessagesId(String messagesId) { mCurrentMessagesId = messagesId;}

    public void setLocation (String location) { mLocation = location;}

    public void setChatRoomImageUrl(String chatRoomImageUrl) { mChatRoomImageUrl = chatRoomImageUrl; }

    public void setUsersId(ArrayList<String> usersId) {
        usersId = usersId;
    }

    public Map<String, Object> getUserProfileIds() {
        return userProfileIds;
    }

    public void setUserProfileIds(HashMap<String, Object> userProfileIds) {
        this.userProfileIds = userProfileIds;
    }

    public void setDescription(String description)
    {
        mDescription = description;
    }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel parcel, int i) {
    parcel.writeString(mId);
    parcel.writeString(mLocation);
    parcel.writeString(mBeaconIdMajor);
    parcel.writeString(mBeaconIdMinor);
    parcel.writeStringArray(usersId);
    parcel.writeString(mCurrentMessagesId);
    parcel.writeStringList(mMessagesIds);
    parcel.writeString(mChatRoomImageUrl);
    parcel.writeString(mDescription);
    parcel.writeSerializable(userProfileIds);
  }
}
