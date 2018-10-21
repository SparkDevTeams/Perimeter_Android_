package com.sparkdev.perimeter.models;

import com.google.firebase.firestore.PropertyName;
import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Message {

    @ServerTimestamp
    @PropertyName("timestamp")
    private Date mTimestamp;
    @PropertyName("message")
    private String mMessage;
    @PropertyName("senderId")
    private String mSenderId;
    @PropertyName("messageType")
    private String mMessageType;
    @PropertyName("audioLink")
    private String mAudioLink;
    @PropertyName("imageLink")
    private String mImageLink;
    @PropertyName("videoLink")
    private String mVideoLink;
    @PropertyName("chatRoomId")
    private String mChatRoomId;
    @PropertyName("messageId")
    private String mMessageId;
    @PropertyName("senderDisplayName")
    private String mSenderDisplayName;

    public Message(Date timestamp, String message, String senderId, String messageType,
                   String audioLink, String imageLink, String videoLink, String chatRoomId,
                   String messageId, String senderDisplayName) {
        mTimestamp = timestamp;
        mMessage = message;
        mSenderId = senderId;
        mMessageType = messageType;
        mAudioLink = audioLink;
        mImageLink = imageLink;
        mVideoLink = videoLink;
        mChatRoomId = chatRoomId;
        mMessageId = messageId;
        mSenderDisplayName = senderDisplayName;
    }

    public Message(){}

    public String getMessage() {
        return mMessage;
    }

    public String getSenderId() {
        return mSenderId;
    }

    public String getMessageType() {
        return mMessageType;
    }

    public String getAudioLink() {
        return mAudioLink;
    }

    public String getImageLink() {
        return mImageLink;
    }

    public String getVideoLink() {
        return mVideoLink;
    }

    public String getChatRoomId() {
        return mChatRoomId;
    }

    public String getMessageId() {
        return mMessageId;
    }

    public Date getTimestamp() {
        return mTimestamp;
    }

    public String getSenderDisplayName() {
        return mSenderDisplayName;
    }

}
