package com.sparkdev.perimeter.activities.SignUp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.sparkdev.perimeter.R;
import com.sparkdev.perimeter.activities.Firebase.FirebaseAPI;
import com.sparkdev.perimeter.activities.Firebase.LoginInterfaces.PerimeterLoginCompletionListener;
import com.sparkdev.perimeter.activities.Login.LoginActivity;

public class SignUpActivity extends AppCompatActivity
{
    private EditText mEmailAddress;
    private EditText mPassword1;
    private EditText mPassword2;
    private EditText mDisplayName;
    private Button mSignUp;
    private TextView mwarn1;
    private TextView mwarn2;
    private TextView mwarn3;
    private FirebaseAPI mFirebaseAPI;
    private final String TAG = "SignUp Activity ";
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mSignUp = findViewById(R.id.signUpBID);
        mEmailAddress = findViewById(R.id.emailID);
        mPassword1 = findViewById(R.id.pass1ID);
        mPassword2 = findViewById(R.id.pass2ID);
        mDisplayName = findViewById(R.id.dNameID);
        mwarn1 = (TextView) findViewById(R.id.warn1ID);
        mwarn2 = (TextView) findViewById(R.id.warn2ID);
        mwarn3 = (TextView) findViewById(R.id.warn3ID);

        //make password show button
        //password requirement
        //disable button

        mSignUp.setOnClickListener(new View.OnClickListener()
        { //validates
            @Override
            public void onClick(View v)
            {
                if(v.getId() == R.id.signUpBID)
                {
                    String mEmailAddressStr = mEmailAddress.getText().toString();
                    String mPassword1Str = mPassword1.getText().toString();
                    String mPassword2Str = mPassword2.getText().toString();
                    String mDisplayNameStr = mDisplayName.getText().toString();

                    if (!mPassword1Str.equals(mPassword2Str))//makes sure passwords are equal
                        mwarn2.setVisibility(View.VISIBLE);
                    else
                    {
                        mwarn2.setVisibility(View.GONE);
                        if (!validPassword(mPassword1Str))//password follows requirements
                            mwarn1.setVisibility(View.VISIBLE);
                        else
                        {
                            mwarn1.setVisibility(View.GONE);
                            validate(mEmailAddressStr, mPassword1Str);
                        }
                    }
                }
            }
        });
    }

    public boolean validPassword(String pw)
    {
        String criteria = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
        if(pw.matches(criteria))
            return true;
        else
            return false;
    }

    /*
    @Override
    public void onStart()
    {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }*/

    private void validate(String userName, String password)
    {
        //firebase api create login method
        mFirebaseAPI.loginUser(userName, password, new PerimeterLoginCompletionListener() {
            @Override
            public void onSuccess() {
                Log.d(TAG, "Login successful");
                Toast.makeText(SignUpActivity.this,"Login passed", Toast.LENGTH_SHORT).show();
                FirebaseUser user= mAuth.getCurrentUser();
            }

            @Override
            public void onFailure() {

            }
        });

        /*mAuth.signInWithEmailAndPassword(userName, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if (task.isSuccessful())
                {
                    Log.d(TAG, "Login successful");
                    Toast.makeText(LoginActivity.this,"Login passed", Toast.LENGTH_SHORT).show();
                    FirebaseUser user= mAuth.getCurrentUser();
                }
                else
                {
                    Log.d(TAG, "Login unsuccessful");
                    Toast.makeText(LoginActivity.this,"Login failed", Toast.LENGTH_SHORT).show();
                }
            }
        });*/
    }
}