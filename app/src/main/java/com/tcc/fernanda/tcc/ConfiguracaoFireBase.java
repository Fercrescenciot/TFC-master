package com.tcc.fernanda.tcc;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by fernanda on 02/03/18.
 */

public class ConfiguracaoFireBase {

    private static DatabaseReference databaseReference;
    private static FirebaseAuth firebaseAuth;

    public static DatabaseReference getFirebase(){
        if(databaseReference == null){
            databaseReference = FirebaseDatabase.getInstance().getReference();
        }

        return  databaseReference;
    }


    public static FirebaseAuth getFirebaseAuth(){
        if(firebaseAuth == null){
            firebaseAuth = FirebaseAuth.getInstance();
        }

        return  firebaseAuth;
    }

}
