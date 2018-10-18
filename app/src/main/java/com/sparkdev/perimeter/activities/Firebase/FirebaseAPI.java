package com.sparkdev.perimeter.activities.Firebase;

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
import com.google.gson.Gson;
import com.sparkdev.perimeter.models.UserProfile;

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
    }

    public static FirebaseAPI getInstance(Context context) {

        if (sFirebaseApiInstance != null){
            return sFirebaseApiInstance;
        } else {
            sFirebaseApiInstance = new FirebaseAPI(context);
            return sFirebaseApiInstance;
        }
    }

    public void loginUser(String email, String password, final PerimeterLoginCompletionListener listener1) {

        FirebaseAuth auth = FirebaseAuth.getInstance();

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d(TAG, "onComplete: Task completed");

                if(task.isSuccessful()){
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

                }
                else{
                    listener1.onFailure();
                }
            }
        });
        
    }

    public void getUserWithUserID(String userID, final PerimeterGetUserCompletionListener userListener){
        CollectionReference userReference = mFirestore.collection("Users");
        DocumentReference userDocuments = userReference.document(userID);


        userDocuments.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                if(task.isSuccessful()){
                    Gson g = new Gson();
                    UserProfile profile = g.fromJson(task.getResult().getData().toString(),UserProfile.class);
                    userListener.onSuccess(profile);

                    Log.d(TAG, "User with DisplayName "+ profile.getDisplayName());
                }
                else{
                    userListener.onFailure();
                    Log.d(TAG, task.getException().getMessage());

                }
            }
        });
    }

}