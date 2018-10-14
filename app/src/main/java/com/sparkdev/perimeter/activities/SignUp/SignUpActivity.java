package com.sparkdev.perimeter.activities.SignUp;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.sparkdev.perimeter.R;

public class SignUpActivity extends AppCompatActivity
{
    private static final int RESULT_LOAD_IMAGE = 1;
    private EditText mEmailAddress;
    private EditText mPassword1;
    private EditText mPassword2;
    private EditText mDisplayName;
    private Button mUploadIm;
    private Button mSignUp;
    private TextView mwarn;
    private ImageView mpfp;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mEmailAddress = findViewById(R.id.emailID);
        mPassword1 = findViewById(R.id.pass1ID);
        mPassword2 = findViewById(R.id.pass2ID);
        mDisplayName = findViewById(R.id.dNameID);
        mUploadIm = findViewById(R.id.uploadPfpID);
        mSignUp = findViewById(R.id.signUpBID);
        mwarn = (TextView) findViewById(R.id.warnID);
        mpfp = (ImageView) findViewById(R.id.pfpID);

        //fix profile picture*****
        //make password show button and round pfp require gradle change

        mUploadIm.setOnClickListener(new View.OnClickListener()
        { //uploads profile picture
            @Override
            public void onClick(View v)
            {
                if(v.getId() == R.id.uploadPfpID)
                {
                    Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(gallery, RESULT_LOAD_IMAGE);
                }
            }
        });

        mSignUp.setOnClickListener(new View.OnClickListener()
        { //validates
            @Override
            public void onClick(View v)
            {
                if(v.getId() == R.id.signUpBID)
                {
                    Toast click = Toast.makeText(SignUpActivity.this, "Click!", Toast.LENGTH_SHORT);
                    click.show();

                    mPassword1 = findViewById(R.id.pass1ID);
                    mPassword2 = findViewById(R.id.pass2ID);

                    String mPassword1Str = mPassword1.getText().toString();
                    String mPassword2Str = mPassword2.getText().toString();

                    Toast warning = Toast.makeText(SignUpActivity.this, "Passwords don't match!", Toast.LENGTH_SHORT);

                    if (!mPassword1Str.equals(mPassword2Str))//makes sure passwords are equal*****
                        //mwarn.setVisibility(View.VISIBLE);
                        warning.show();
                    else {
                        //mwarn.setVisibility(View.GONE);
                        validate(mEmailAddress.getText().toString(), mPassword1.getText().toString());
                    }
                }
            }
        });
    }

    @Override //uploads and displays image
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null)
        {
            Uri selectedImage = data.getData();
            mpfp.setImageURI(selectedImage);
        }
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