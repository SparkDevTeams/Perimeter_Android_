package com.sparkdev.perimeter.activities.Firebase;

import com.sparkdev.perimeter.models.ChatRoom;

import java.util.List;

public interface GetChatRoomsCompletionListener {

     void onSuccess(List<ChatRoom> chatRooms);

     void onFailure();
}
