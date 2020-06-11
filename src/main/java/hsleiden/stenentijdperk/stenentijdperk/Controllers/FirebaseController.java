package hsleiden.stenentijdperk.stenentijdperk.Controllers;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class FirebaseController {
    InputStream serviceAccount;
    Firestore db;

    public void initializeFirebaseApp() {
        try {
            serviceAccount = new FileInputStream("./credentials.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        GoogleCredentials credentials = null;

        try {
            credentials = GoogleCredentials.fromStream(serviceAccount);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert credentials != null;
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(credentials)
                .build();

        FirebaseApp.initializeApp(options);
        db = FirestoreClient.getFirestore();
    }

    public void listenForUpdates(String lobby){
        DocumentReference docRef = db.collection("stenentijdperk").document(lobby);

        // De listener
        docRef.addSnapshotListener((snapshot, error) -> {
            if (error != null) {
                System.err.println("Listen failed: " + error);
                return;
            }

            if (snapshot != null && snapshot.exists()) {

                System.out.println("Current data: " + snapshot.getData());
            } else {
                System.out.print("Current data: null");
            }

        });
    }

    public void updateDocument(String document, String field, String value) {
        try {
            db.collection("test").document(document).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        DocumentReference docRef = db.collection("test").document(document);

        ApiFuture<WriteResult> future = docRef.update(field, value);
    }

    public void updateDocument(String document, String field, int value) {
        try {
            db.collection("test").document(document).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        DocumentReference docRef = db.collection("test").document(document);

        ApiFuture<WriteResult> future = docRef.update(field, value);
    }

    public void updateDocument(String document, String field, float value) {
        try {
            db.collection("test").document(document).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        DocumentReference docRef = db.collection("test").document(document);

        ApiFuture<WriteResult> future = docRef.update(field, value);
    }
}