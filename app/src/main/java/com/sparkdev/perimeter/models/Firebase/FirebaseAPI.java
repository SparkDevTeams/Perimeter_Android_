package com.sparkdev.perimeter.models.Firebase;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;
import com.sparkdev.perimeter.models.ChatRoom;
import com.sparkdev.perimeter.models.Firebase.ChatRoomInterfaces.GetChatRoomMessagesCompletionListener;
import com.sparkdev.perimeter.models.Firebase.ChatRoomInterfaces.GetChatRoomsCompletionListener;
import com.sparkdev.perimeter.models.Firebase.LoginInterfaces.PerimeterGetUserCompletionListener;
import com.sparkdev.perimeter.models.Firebase.LoginInterfaces.PerimeterLoginCompletionListener;
import com.sparkdev.perimeter.models.Firebase.SignUpInterface.PerimeterGetSignUpCompletionListener;
import com.sparkdev.perimeter.models.Firebase.SignUpInterface.PerimeterSignUpCompletionListener;
import com.sparkdev.perimeter.models.UserProfile;

import java.util.ArrayList;
import java.util.List;


/**
 * A firebase method wrapper
 */
public class FirebaseAPI {

  private static FirebaseAPI sFirebaseApiInstance;

  private static final String TAG = "FIREBASE_API";

  private FirebaseFirestore mFirestore;

  /**
   * Private fireabase API init
   */
  private FirebaseAPI(Context context) {
    Log.d(TAG, "Firestore has been initialzed ");
    FirebaseApp.initializeApp(context);
    mFirestore = FirebaseFirestore.getInstance();
    FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
        .setTimestampsInSnapshotsEnabled(true)
        .build();
    mFirestore.setFirestoreSettings(settings);
  }

  public static FirebaseAPI getInstance(Context context) {

    if (sFirebaseApiInstance != null) {
      return sFirebaseApiInstance;
    } else {
      sFirebaseApiInstance = new FirebaseAPI(context);
      return sFirebaseApiInstance;
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

          for (DocumentSnapshot document : documents) {
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

        if (task.isSuccessful()) {

          DocumentSnapshot document = task.getResult();

          FirestoreMessagesCollection messagesCollection = document.toObject(FirestoreMessagesCollection.class);

          listener.onSuccess(messagesCollection.getMessages());

        } else {
          listener.onFailure();
        }
      }
    });
  }

  public void loginUser(String email, String password, final PerimeterLoginCompletionListener listener1) {

    FirebaseAuth auth = FirebaseAuth.getInstance();

    auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
      @Override
      public void onComplete(@NonNull Task<AuthResult> task) {
        Log.d(TAG, "onComplete: Task completed");

        if (task.isSuccessful()) {
          AuthResult loginResult = task.getResult();
          String userID = loginResult.getUser().getUid();

          getUserWithUserID(userID, new PerimeterGetUserCompletionListener() {
            @Override
            public void onSuccess(UserProfile profile) {
              listener1.onSuccess();
            }

            @Override
            public void onFailure() {
              listener1.onFailure();
            }
          });

        } else {
          listener1.onFailure();
        }
      }
    });

  }

  public void getUserWithUserID(String userID, final PerimeterGetUserCompletionListener userListener) {
    CollectionReference userReference = mFirestore.collection("Users");
    DocumentReference userDocuments = userReference.document(userID);


    userDocuments.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
      @Override
      public void onComplete(@NonNull Task<DocumentSnapshot> task) {

        if (task.isSuccessful()) {
          Gson g = new Gson();
          UserProfile profile = g.fromJson(task.getResult().getData().toString(), UserProfile.class);
          userListener.onSuccess(profile);

          Log.d(TAG, "User with DisplayName " + profile.getDisplayName());
        } else {
          userListener.onFailure();
          Log.d(TAG, task.getException().getMessage());

        }
      }
    });
  }

  public void createSignUpUser(String email, String password, final PerimeterSignUpCompletionListener signListener) {

    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser user = auth.getCurrentUser();

    auth.createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
          @Override
          public void onComplete(@NonNull Task<AuthResult> task) {
            if (task.isSuccessful()) {
              FirebaseAuth authSign = FirebaseAuth.getInstance();
              Log.d(TAG, "SignUpUser: succcess");

              FirebaseUser signUpUser = authSign.getCurrentUser();

              getSignUpUserInfo(signUpUser, new PerimeterGetSignUpCompletionListener() {
                @Override
                public void onSuccess(UserProfile profile) {
                  signListener.onSuccess();
                }

                @Override
                public void onFailure() {
                  signListener.onFailure();
                }
              });
            }

          }
        });
  }

  public void getSignUpUserInfo(FirebaseUser newUser, final PerimeterGetSignUpCompletionListener listenerSign) {
    CollectionReference signUpUserReference = mFirestore.collection("Users");
    mFirestore.collection("Users").add(newUser.getIdToken(true));
    DocumentReference signUpDocuments = signUpUserReference.document();
    //mFirestore.collection(“Users”).add(newUser);

    signUpDocuments.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
      @Override
      public void onComplete(@NonNull Task<DocumentSnapshot> task) {
        if (task.isSuccessful()) {
          Gson signObject = new Gson();
          UserProfile signUpProfile = signObject.fromJson(task.getResult().getData().toString(), UserProfile.class);
          listenerSign.onSuccess(signUpProfile);

          Log.d(TAG, "User with DisplayName " + signUpProfile.getDisplayName());
        } else {
          listenerSign.onFailure();
          Log.d(TAG, task.getException().getMessage());
        }
      }
    });


  }


}