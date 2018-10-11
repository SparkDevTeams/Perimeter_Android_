package com.sparkdev.perimeter.models;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ChatRoom {

    private String mId;
    private String mLocation;
    private String mBeaconIdMajor;
    private String mBeaconIdMinor;
    private ArrayList<UserProfile> mUsers;
    private ArrayList<Message> mMessages;
    private String description;

    public ChatRoom(String id, String location, String beaconIdMajor, String beaconIdMinor,
                    ArrayList<UserProfile> users, ArrayList<Message> messages, String description) {
        mId = id;
        mLocation = location;
        mBeaconIdMajor = beaconIdMajor;
        mBeaconIdMinor = beaconIdMinor;
        mUsers = users;
        mMessages = messages;
        this.description = description;
    }

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

    public ArrayList<UserProfile> getUsers() {
        return mUsers;
    }

    public ArrayList<Message> getMessages() {
        return mMessages;
    }

    public String getDescription() {
        return description;
    }


}
