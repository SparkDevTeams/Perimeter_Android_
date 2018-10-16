package com.sparkdev.perimeter.activities.Firebase;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

public interface FirebaseAPI {
FirebaseAuth auth = FirebaseAuth.getInstance();

FirebaseFirestore database = FirebaseFirestore.getInstance();
CollectionReference mChatRoomCollection = database.collection("Users");
CollectionReference mMessageCollection = database.collection("Messages");
CollectionReference mUserCollection = database.collection("Users");

    //inbox
    //method to login users with username and password
    //1) Authentication
    //add references of firebase docs here lke auth, docref
    public void LoginUsers(FirebaseAuth mUser);

    //method to create new user
    public void createUser();

    //method to update User information
    public void updateUser(FirebaseFirestore db, Map<String,Object> user);

    //method to upload media
    public void uploadMedia();




}
