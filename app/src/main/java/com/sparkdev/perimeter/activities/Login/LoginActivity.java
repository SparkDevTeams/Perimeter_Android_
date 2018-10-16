package com.sparkdev.perimeter.activities.Login;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.sparkdev.perimeter.R;
import com.sparkdev.perimeter.activities.Firebase.FirebaseAPI;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
  private EditText mName;
  private EditText mPassword;
  //private TextView mInfo;
  private Button mLogin;
  private TextView mCreateAccount;
  private int mCounter = 1;
  private FirebaseAuth mAuth;
  private final String TAG = "LoginActivity";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    mName = findViewById(R.id.etName);
    mPassword = findViewById(R.id.etPassword);
    mLogin = findViewById(R.id.btnLogin);
    mCreateAccount = findViewById(R.id.tvCreateAccount);
    mAuth = FirebaseAuth.getInstance();
    // mName.setSelection(1);
    //mPassword.setSelection(1);
    String text = "Don't have an account? Create One";
    SpannableString ss = new SpannableString(text);

    ClickableSpan clickableSpan1 = new ClickableSpan() {
      @Override
      public void onClick(View widget) {
        //I think I'll refer to Dayana's code here for when she sets up her part.
        //for now it opens up a to a random page but will be needd to go to the create account page
        //going to take out the SecondActivity thing that I had and then gonn add a toast
        //Intent intent =new Intent(LoginActivity.this, SecondActivity.class);
        // startActivity(intent);
        Log.d(TAG, "To sign-up!");
        Toast.makeText(LoginActivity.this, "To the sign up page", Toast.LENGTH_SHORT).show();
      }
    };
    ss.setSpan(clickableSpan1, 23, 33, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    mCreateAccount.setText(ss);
    mCreateAccount.setMovementMethod(LinkMovementMethod.getInstance());

    mLogin.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        validate(mName.getText().toString(), mPassword.getText().toString());
      }
    });
  }

  @Override
  public void onStart() {
    super.onStart();
    // Check if user is signed in (non-null) and update UI accordingly.
    FirebaseUser currentUser = mAuth.getCurrentUser();
    //updateUI(currentUser);
  }

  private void validate(String userName, String password) {
    //Firebase stuff will probably end up here
    mAuth.signInWithEmailAndPassword(userName, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
      @Override
      public void onComplete(@NonNull Task<AuthResult> task) {

        if (task.isSuccessful()) {
          Log.d(TAG, "Login successful");
          Toast.makeText(LoginActivity.this, "Login passed", Toast.LENGTH_SHORT).show();
          FirebaseUser user = mAuth.getCurrentUser();
          //updateUI(user);
        } else {
          Log.d(TAG, "Login unsuccessful");
          Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
          //updateUI(null);
        }
      }
    });
  }

  //might not need at all
  private void updateUI(FirebaseUser user) {
    //hideProgressDialog();
    if (user != null) {

    }
  }



//  public void updateUser(FirebaseFirestore db, Map<String,Object> user){
//    DocumentReference docRef = db.collection("Users").document("user2");
//    Map<String, Object> updates = new HashMap<String, Object>();
//    updates.put("firstname", "Robin");
//    user.put("updates", updates);
//    //its not working
//  }

}
