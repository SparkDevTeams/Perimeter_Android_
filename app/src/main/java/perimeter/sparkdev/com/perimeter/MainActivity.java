package perimeter.sparkdev.com.perimeter;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    //Declaring an Instance
    private FirebaseAuth mAuth;
    private TextView mStatusTextView;
    private TextView mDetailTextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       //Initializing the instance
        mAuth = FirebaseAuth.getInstance();


        String userName = "apple5@apple.com";
        String passWord = "Password";

        //Start [CREATE EMAIL]
        mAuth.signInWithEmailAndPassword(userName,passWord)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Log.d(null,"createUserWithEmail: success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    Log.d(null, "UserID " + user.getUid());
                } else{
                    Log.w(null, "createUserWithEmail: failure", task.getException());
                    Toast.makeText(MainActivity.this, "Auth failed oops", Toast.LENGTH_SHORT).show();
                }


            }
        });
        //END [CREATE EMAIL]

        //Start [SIGN EXISTING USERS]
        mAuth.createUserWithEmailAndPassword(userName,passWord)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>(){
                public void onComplete(@NonNull Task<AuthResult> task){
                    if (task.isSuccessful()){
                        Log.d(null, "signin was success");
                        FirebaseUser user = mAuth.getCurrentUser();
                    } else {
                        Log.w(null, "failure", task.getException());
                        Toast.makeText(MainActivity.this, "AUth faile",
                                Toast.LENGTH_SHORT).show();
                    }
                }

                }
                );

    }




}
