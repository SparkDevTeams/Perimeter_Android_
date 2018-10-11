package com.sparkdev.perimeter.models;

public class UserProfile {

    private String mFirstName;
    private String mLastName;
    private String mEmail;
    private String mDisplayName;
    private String mProfileImageUrl;

    public UserProfile(String firstName, String lastName, String email, String displayName, String profileImageUrl) {
        mFirstName = firstName;
        mLastName = lastName;
        mEmail = email;
        mDisplayName = displayName;
        mProfileImageUrl = profileImageUrl;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public String getEmail() {
        return mEmail;
    }

    public String getDisplayName() {
        return mDisplayName;
    }

    public String getProfileImageUrl() {
        return mProfileImageUrl;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public void setDisplayName(String displayName) {
        mDisplayName = displayName;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        mProfileImageUrl = profileImageUrl;
    }
}
