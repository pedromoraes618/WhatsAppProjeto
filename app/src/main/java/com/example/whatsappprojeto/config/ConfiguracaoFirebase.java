package com.example.whatsappprojeto.config;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
;

public class ConfiguracaoFirebase {
    private static DatabaseReference datatase;
    private static FirebaseAuth auth;

    //retorna a instancia do firebaseDatabase
    public static DatabaseReference getFirebaseDatabase(){
    if(datatase == null){
        datatase = FirebaseDatabase.getInstance().getReference();
    }
    return datatase;
    }

    //retorna a instacia do firebaseAuth
    public static FirebaseAuth getFirebaseAuthenticacao (){
        if(auth == null){
            auth = FirebaseAuth.getInstance();
        }
        return auth;
    }
}
