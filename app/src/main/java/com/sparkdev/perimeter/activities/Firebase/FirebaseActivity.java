package com.sparkdev.perimeter.activities.Firebase;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


import java.util.HashMap;
import java.util.Map;

public class FirebaseActivity extends AppCompatActivity {
    private static final String TAG = " Sometjing ";
    //Declaring an Instance
    private FirebaseAuth mAuth;
    private TextView mStatusTextView;
    private TextView mDetailTextView;

    private EditText mEmailField;
    private EditText mPasswordField;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        FirebaseAPI hello = FirebaseAPI.getInstance(this);
        String userName = "apple5@apple.com";
        String passWord = "Password";
        hello.loginUser(userName, passWord, new PerimeterLoginCompletionListener() {
            @Override
            public void onSuccess() {
                Log.d(TAG, "FIrebase logged in");
            }

            @Override
            public void onFailure() {

            }
        });
        //Initializing the instance
//        mAuth = FirebaseAuth.getInstance();
//
//        String userName = "apple5@apple.com";
//        String passWord = "Password";
//
//        //Start [create EMAIL]
//        FirebaseUser user1 = mAuth.getCurrentUser();
//        mAuth.signInWithEmailAndPassword(userName, passWord)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            Log.d(null, "createUserWithEmail: success");
//                            FirebaseUser userIn = mAuth.getCurrentUser();
//                            Log.d(null, "UserID " + userIn.getUid());
//                        } else {
//                            Log.w(null, "createUserWithEmail: failure", task.getException());
//                            Toast.makeText(FirebaseActivity.this, "Auth failed oops", Toast.LENGTH_SHORT).show();
//                        }
//
//
//                    }
//                });
//        //END [CREATE EMAIL]
//
//        //Start [SIGN EXISTING USERS]
//        mAuth.createUserWithEmailAndPassword(userName, passWord)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                            public void onComplete(@NonNull Task<AuthResult> task) {
//                                if (task.isSuccessful()) {
//                                    Log.d(null, "signin was success");
//                                    FirebaseUser user = mAuth.getCurrentUser();
//                                } else {
//                                    Log.w(null, "failure", task.getException());
//                                    Toast.makeText(FirebaseActivity.this, "AUth faile",
//                                            Toast.LENGTH_SHORT).show();
//                                }
//                            }
//
//                        }
//                );
//
//        //Firestore stores data in documents which are stored in collections
//        //This method makes a new collection
//        Map<String, Object> user = new HashMap<>();
//        user.put("username", user1.getEmail());
//
//        user.put("email", user1.getEmail());
//        user.put("firstName", "astrid");
//        user.put("id", user1.getUid());
//        db.collection("Users")
//                .add(user)
//                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                    @Override
//                    public void onSuccess(DocumentReference documentReference) {
//                        Log.d(TAG, "Document snap added with ID: " +
//                                documentReference.getId());
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.w(TAG, "Error with doc", e);
//                    }
//                });
//
//        // update
//
////      public void update22(){
////          DocumentReference docRef = db.collection("Users").document("user2");
////          Map<String, Object> updates = new HashMap<String, Object>();
////          updates.put("firstname", "Robin");
////          user.put("updates", updates);
////          //its not working
////      }
//
//
//    }
//
//    public void getM() {
//        db.collection("users")
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                Log.d(TAG, document.getId() + "=>"
//                                        + document.getData());
//                            }
//                        } else {
//                            Log.w(TAG, "Error getting documents", task.getException());
//                        }
//                    }
//                });
//    }
//
//
////    public void onStart() {
////
////        super.onStart();
////        FirebaseUser currentUser = mAuth.getCurrentUser();
////
////        @Override
////        protected void onCreate (Bundle savedInstanceState){
////            super.onCreate(savedInstanceState);
////            setContentView(R.layout.activity_main);
////
////            //insertData();
////        }
//
////        private void insertData () {
////            LoginModel model = new LoginModel("bowser", "mario123@g.com"
////                    , "mario", "luigi", "5");
////
////            CollectionReference collectRef = db.collection("data");
////            collectRef.add(model);
////        }
////        //need to call it
    }
}







