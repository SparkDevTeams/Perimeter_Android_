package com.sparkdev.perimeter.activities.SignUp;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import java.util.regex.Pattern;

import com.sparkdev.perimeter.R;
import com.sparkdev.perimeter.activities.Inbox.InboxActivity;
import com.sparkdev.perimeter.activities.Login.LoginActivity;
import com.sparkdev.perimeter.models.Firebase.FirebaseAPI;
import com.sparkdev.perimeter.models.Firebase.SignUpInterface.PerimeterSignUpCompletionListener;


public class SignUpActivity extends Activity {
  private EditText mEmAddr;
  private EditText mPW1;
  private EditText mPW2;
  private EditText mname;
  private Button mSignUp;
  private TextView mwarn1;
  private TextView mwarn2;
  private TextView mcrit;
  private TextView mLogin;
  private int color;
  private ColorStateList tintList;

  private FirebaseAPI mFirebaseAPI;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_signup);

    mSignUp = findViewById(R.id.signUpBID);
    mEmAddr = findViewById(R.id.emailID);
    mPW1 = findViewById(R.id.pass1ID);
    mPW2 = findViewById(R.id.pass2ID);
    mname = findViewById(R.id.dNameID);
    mcrit = (TextView) findViewById(R.id.criteriaID);
    mwarn1 = (TextView) findViewById(R.id.warn1ID);
    mwarn2 = (TextView) findViewById(R.id.warn2ID);
    mLogin = (TextView) findViewById(R.id.loginID);

    String text = "Already have an account? Login.";
    SpannableString ss = new SpannableString(text);

    ClickableSpan clickableSpan1 = new ClickableSpan() {
      @Override
      public void onClick(View v) {
        startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
      }
    };
    ss.setSpan(clickableSpan1, 25, 31, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    mLogin.setText(ss);
    mLogin.setMovementMethod(LinkMovementMethod.getInstance());


    mSignUp.setEnabled(false);
    color = ContextCompat.getColor(this, R.color.colorGreyish);
    tintList = ColorStateList.valueOf(color);
    ViewCompat.setBackgroundTintList(mSignUp, tintList);


    mEmAddr.addTextChangedListener(signUpTextWatcher);
    mPW1.addTextChangedListener(signUpTextWatcher);
    mPW2.addTextChangedListener(signUpTextWatcher);
    mname.addTextChangedListener(signUpTextWatcher);

    mPW1.setOnClickListener(new View.OnClickListener() { //validates
      @Override
      public void onClick(View v) {
        if (v.getId() == R.id.pass1ID)
          mcrit.setVisibility(View.VISIBLE);
      }
    });

    mSignUp.setOnClickListener(new View.OnClickListener() { //validates
      @Override
      public void onClick(View v) {
        if (v.getId() == R.id.signUpBID) {
          String emAddrStr = mEmAddr.getText().toString();
          String pw1Str = mPW1.getText().toString();
          String pw2Str = mPW2.getText().toString();
          String nameStr = mname.getText().toString();
          String warning = "Invalid Email Address";

          if (!emAddrStr.contains("@")) { //makes sure email address has @
            Toast.makeText(SignUpActivity.this, warning,
                Toast.LENGTH_SHORT).show();
          } else if (!emAddrStr.contains(".")) {
            Toast.makeText(SignUpActivity.this, warning,
                Toast.LENGTH_SHORT).show();
          } else if (validPassword(pw1Str, pw2Str)) {//makes sure passwords are equal
            mwarn1.setVisibility(View.GONE);
            validate(emAddrStr, pw1Str); //FIREBASE
          }
        }
      }
    });
  }

  public TextWatcher signUpTextWatcher = new TextWatcher() {
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
      String emAddrStr = mEmAddr.getText().toString();
      String pw1Str = mPW1.getText().toString();
      String pw2Str = mPW2.getText().toString();
      String nameStr = mname.getText().toString();

      if (!pw1Str.isEmpty())
        mcrit.setVisibility(View.VISIBLE);

      mSignUp.setEnabled(!emAddrStr.isEmpty() && !pw1Str.isEmpty() &&
          !pw2Str.isEmpty() && !nameStr.isEmpty());
      if (mSignUp.isEnabled()) {
        color = ContextCompat.getColor(SignUpActivity.this, R.color.colorAccent);
        tintList = ColorStateList.valueOf(color);
        ViewCompat.setBackgroundTintList(mSignUp, tintList);
      } else {
        color = ContextCompat.getColor(SignUpActivity.this, R.color.colorGreyish);
        tintList = ColorStateList.valueOf(color);
        ViewCompat.setBackgroundTintList(mSignUp, tintList);
      }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
  };

  public boolean validPassword(String pw, String pw2) {
    Pattern spCharP = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
    Pattern uCaseP = Pattern.compile("[A-Z ]");
    Pattern lCaseP = Pattern.compile("[a-z ]");
    Pattern digitCaseP = Pattern.compile("[0-9 ]");
    boolean flag = true;

    if (!pw.equals(pw2)) {//makes sure pw is equal
      mwarn2.setVisibility(View.VISIBLE);
      mwarn1.setVisibility(View.GONE);
      flag = false;
    } else {
      mwarn2.setVisibility(View.GONE);
      if (pw.length() < 8) //length > 8
        flag = false;
      if (!spCharP.matcher(pw).find()) //has a special character
        flag = false;
      if (!uCaseP.matcher(pw).find())//has an uppercase
        flag = false;
      if (!lCaseP.matcher(pw).find()) //has a lowercase
        flag = false;
      if (!digitCaseP.matcher(pw).find()) //has numbers
        flag = false;
      if (!flag)
        mwarn1.setVisibility(View.VISIBLE);
    }
    return flag;
  }

    /*
    @Override
    public void onStart()
    {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }*/

  private void validate(String userName, String password) {
    mFirebaseAPI.createSignUpUser(userName, password, new PerimeterSignUpCompletionListener() {
      @Override
      public void onSuccess() {
        startActivity(new Intent());
      }

      @Override
      public void onFailure() {

      }
    });
  }
}