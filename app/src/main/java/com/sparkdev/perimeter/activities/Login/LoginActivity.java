package com.sparkdev.perimeter.activities.Login;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
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
import com.sparkdev.perimeter.models.Firebase.LoginInterfaces.PerimeterLoginCompletionListener;
import com.sparkdev.perimeter.models.UserProfile;

public class LoginActivity extends Activity {
  private EditText mName;
  private EditText mPassword;
  //private TextView mInfo;
  private Button mLogin;
  private TextView mCreateAccount;
  private int mCounter = 1;
  private FirebaseAPI mFirebaseAPI;
  private final String TAG = "LoginActivity";
  private int color;
  private ColorStateList tintList;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    mName = findViewById(R.id.etName);
    mPassword = findViewById(R.id.etPassword);
    mLogin = findViewById(R.id.btnLogin);
    mCreateAccount = findViewById(R.id.tvCreateAccount);


    mLogin.setEnabled(false);
    color = ContextCompat.getColor(this, R.color.colorGreyish);
    tintList = ColorStateList.valueOf(color);
    ViewCompat.setBackgroundTintList(mLogin, tintList);


    mName.addTextChangedListener(signUpTextWatcher);
    mPassword.addTextChangedListener(signUpTextWatcher);

    mFirebaseAPI = FirebaseAPI.getInstance(this);

    String text = "Don't have an account? Create One";
    SpannableString ss = new SpannableString(text);

    ClickableSpan clickableSpan1 = new ClickableSpan() {
      @Override
      public void onClick(View widget) {
        startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
        Log.d(TAG, "To sign-up!");
      }
    };
    ss.setSpan(clickableSpan1, 23, 33, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    mCreateAccount.setText(ss);
    mCreateAccount.setMovementMethod(LinkMovementMethod.getInstance());

    mLogin.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if(!mName.getText().toString().isEmpty() && !mPassword.getText().toString().isEmpty())
          validate(mName.getText().toString(), mPassword.getText().toString());
      }
    });
  }

  public TextWatcher signUpTextWatcher = new TextWatcher() {
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
      String name = mName.getText().toString();
      String pw = mPassword.getText().toString();

      mLogin.setEnabled(!name.isEmpty() && !pw.isEmpty());
      if (mLogin.isEnabled()) {
        color = ContextCompat.getColor(LoginActivity.this, R.color.colorAccent);
        tintList = ColorStateList.valueOf(color);
        ViewCompat.setBackgroundTintList(mLogin, tintList);
      } else {
        color = ContextCompat.getColor(LoginActivity.this, R.color.colorGreyish);
        tintList = ColorStateList.valueOf(color);
        ViewCompat.setBackgroundTintList(mLogin, tintList);
      }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
  };

  private void validate(final String userName, String password) {
    mFirebaseAPI.loginUser(userName, password, new PerimeterLoginCompletionListener() {
      @Override
      public void onSuccess() {
        Log.d(TAG, "Login successful");
          Toast.makeText(LoginActivity.this, "Login passed", Toast.LENGTH_SHORT).show();
          startActivity(new Intent(LoginActivity.this, InboxActivity.class));
          finish();
      }

      @Override
      public void onFailure() {
          Log.d(TAG, "Login unsuccessful");
          Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
      }
    });
  }

}
