package com.example.amazonclone.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.auth.FirebaseToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class UserService {

    private static final String COLLECTION_NAME = "users";

    public void initUserIfNotExists(FirebaseToken token) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        String uid = token.getUid();

        DocumentReference docRef = db.collection(COLLECTION_NAME).document(uid);
        DocumentSnapshot doc = docRef.get().get();

        if (!doc.exists()) {
            Map<String, Object> data = new HashMap<>();
            data.put("uid", uid);
            data.put("email", token.getEmail());
            data.put("role", "user"); // по умолчанию

            docRef.set(data);
        }
    }

    public String getUserRole(String uid) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        DocumentSnapshot doc = db.collection(COLLECTION_NAME).document(uid).get().get();
        return doc.contains("role") ? doc.getString("role") : "user";
    }
}
