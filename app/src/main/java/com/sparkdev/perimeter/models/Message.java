package com.sparkdev.perimeter.models;

import java.util.Date;

public class Message {

    private Date mDateSent;
    private String mMessage;
    private String mSenderId;
    private String mMessageType;
    private String mAudioLink;
    private String mImageLink;
    private String mVideoLink;
    private String mChatRoomId;
    private String mMessageId;

    public Message(Date dateSent, String message, String senderId, String messageType,
                   String audioLink, String imageLink, String videoLink, String chatRoomId,
                   String messageId) {
        mDateSent = dateSent;
        mMessage = message;
        mSenderId = senderId;
        mMessageType = messageType;
        mAudioLink = audioLink;
        mImageLink = imageLink;
        mVideoLink = videoLink;
        mChatRoomId = chatRoomId;
        mMessageId = messageId;
    }

    public Date getDateSent() {
        return mDateSent;
    }

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
}
