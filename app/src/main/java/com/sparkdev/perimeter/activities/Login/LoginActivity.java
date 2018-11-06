package com.sparkdev.perimeter.activities.Login;

import android.app.Activity;
import android.content.Intent;
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
import com.sparkdev.perimeter.R;
import com.sparkdev.perimeter.activities.Inbox.InboxActivity;
import com.sparkdev.perimeter.activities.SignUp.SignUpActivity;
import com.sparkdev.perimeter.models.Firebase.FirebaseAPI;
import com.sparkdev.perimeter.models.Firebase.LoginInterfaces.PerimeterGetUserCompletionListener;
import com.sparkdev.perimeter.models.UserProfile;

public class LoginActivity extends Activity {
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
    String text = "Don't have an account? Create One";
    SpannableString ss = new SpannableString(text);

    ClickableSpan clickableSpan1 = new ClickableSpan() {
      @Override
      public void onClick(View widget) {
        startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
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

  private void validate(final String userName, String password) {
    //Firebase stuff will probably end up here
    mAuth.signInWithEmailAndPassword(userName, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
      @Override
      public void onComplete(@NonNull Task<AuthResult> task) {

        if (task.isSuccessful()) {
          Log.d(TAG, "Login successful");
          Toast.makeText(LoginActivity.this, "Login passed", Toast.LENGTH_SHORT).show();
          startActivity(new Intent(LoginActivity.this, InboxActivity.class));
        } else {
          Log.d(TAG, "Login unsuccessful");
          Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
        }
      }
    });
  }

}
