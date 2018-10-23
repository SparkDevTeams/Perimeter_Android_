package com.sparkdev.perimeter.models;

import com.google.firebase.firestore.PropertyName;

import java.util.ArrayList;

public class FirestoreMessagesCollection {
    @PropertyName("messages")
    private ArrayList<Message> mMessages;

    public FirestoreMessagesCollection(){};

    public FirestoreMessagesCollection(ArrayList<Message> messages){
        mMessages = messages;
    };

    public ArrayList<Message> getMessages() {
        return mMessages;
    }

    public void setMessages(ArrayList<Message> messages) {
        mMessages = messages;
    }
}