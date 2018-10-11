package com.sparkdev.perimeter.models;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

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