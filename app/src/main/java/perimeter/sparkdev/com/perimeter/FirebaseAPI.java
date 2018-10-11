package perimeter.sparkdev.com.perimeter;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class FirebaseAPI  {

    private FirebaseAuth mFirebaseAuth;
    private FirebaseFirestore mFirebaseFirestore;

    public FirebaseFirestore getFirebaseFirestore(){
        return FirebaseFirestore.getInstance();
    }


}
