package com.sparkdev.perimeter.models.Firebase;

import com.google.firebase.firestore.PropertyName;
import com.sparkdev.perimeter.models.Message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

    public void addMessage(Message message) {
        mMessages.add(message);
    }
  public ArrayList<Map<String, Object>> toMap() {
      ArrayList<Map<String, Object>> map = new ArrayList<>();

      for (Message message: mMessages) {
          map.add(message.toMap());
      }
      return map;
  }
}