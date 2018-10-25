package com.sparkdev.perimeter.activities.Firebase.ChatRoomInterfaces;

import com.sparkdev.perimeter.models.Message;

import java.util.List;

public interface GetChatRoomMessagesCompletionListener {

    void onSuccess(List<Message> messages);

    void onFailure();
}