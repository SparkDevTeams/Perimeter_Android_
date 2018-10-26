package com.sparkdev.perimeter.models;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.QuerySnapshot;
import com.sparkdev.perimeter.activities.Firebase.ChatRoomInterfaces.GetChatRoomMessagesCompletionListener;
import com.sparkdev.perimeter.activities.Firebase.ChatRoomInterfaces.GetChatRoomsCompletionListener;
//import com.sparkdev.perimeter.activities.Firebase.GetChatRoomMessagesCompletionListener;
//import com.sparkdev.perimeter.activities.Firebase.GetChatRoomsCompletionListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A firebase method wrapper
 */
public class FirebaseAPI2 {

    private static FirebaseAPI2 sFirebaseApi2Instance;

    private static final String TAG = "FIREBASE_API";

    private FirebaseFirestore mFirestore;

    /**
     * Private fireabase API init
     */
    private FirebaseAPI2(Context context) {
        Log.d(TAG, "Firestore has been initialzed ");
        FirebaseApp.initializeApp(context);
        mFirestore = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setTimestampsInSnapshotsEnabled(true)
                .build();
        mFirestore.setFirestoreSettings(settings);
    }

    public static FirebaseAPI2 getInstance(Context context) {

        if (sFirebaseApi2Instance != null){
            return sFirebaseApi2Instance;
        } else {
            sFirebaseApi2Instance = new FirebaseAPI2(context);
            return sFirebaseApi2Instance;
        }
    }

    public void getAllChatRooms(final GetChatRoomsCompletionListener listener) {

        CollectionReference chatroomsRef = mFirestore.collection("ChatRooms");

        chatroomsRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {

                    List<ChatRoom> chatRooms = new ArrayList<>();

                    List<DocumentSnapshot> documents = task.getResult().getDocuments();

                    for(DocumentSnapshot document: documents) {
                        ChatRoom chatRoom = document.toObject(ChatRoom.class);
                        chatRooms.add(chatRoom);
                    }

                    listener.onSuccess(chatRooms);
                } else {
                    listener.onFailure();
                }
            }
        });
    }

    public void getMessagesForChatRoom(ChatRoom chatRoom, final GetChatRoomMessagesCompletionListener listener) {

        DocumentReference messagesRef = mFirestore.collection("Messages").document(chatRoom.getCurrentMessagesId());
        messagesRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                if(task.isSuccessful()) {

                    DocumentSnapshot document = task.getResult();

                    FirestoreMessagesCollection messagesCollection = document.toObject(FirestoreMessagesCollection.class);

                    listener.onSuccess(messagesCollection.getMessages());

                } else {
                    listener.onFailure();
                }
            }
        });
    }

    public void loginUser(String email, String password) {

        FirebaseAuth auth = FirebaseAuth.getInstance();

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d(TAG, "onComplete: Task completed");
            }
        });
        
    }

}