package hsleiden.stenentijdperk.stenentijdperk.Controllers;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.FileInputStream;
import java.io.IOException;

public class FirebaseController {
    static Firestore db;

    public static void initializeFirebaseApp() {
        GoogleCredentials credentials = null;

        try {
            credentials = GoogleCredentials.fromStream(new FileInputStream("./iipsene-stenentijdperk-firebase-adminsdk-9kdre-d4805147e1.json"));
        } catch (IOException e) {
            System.out.println("File couldn't be read");
        }

        assert credentials != null;
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(credentials)
                .build();


        FirebaseApp.initializeApp(options);
        db = FirestoreClient.getFirestore();
    }

    public static void listenForUpdates(String lobby){
        DocumentReference docRef = db.collection("stenentijdperk").document(lobby);
        System.out.println("listener");
        // De listener
        docRef.addSnapshotListener((snapshot, e) -> {
            System.out.println("Listening");
            if (e != null) {
                System.err.println("Listen failed: " + e);
                return;
            }

            if (snapshot != null && snapshot.exists()) {
                System.out.println("Current data: " + snapshot.getData());
            } else {
                System.out.print("Current data: null");
            }
        });

    }

    public static void updateDocument(String document, String field, String value) {
        try {
            db.collection("stenentijdperk").document(document).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        DocumentReference docRef = db.collection("stenentijdperk").document(document);

        ApiFuture<WriteResult> future = docRef.update(field, value);
    }

    public static void updateDocument(String document, String field, int value) {
        try {
            db.collection("stenentijdperk").document(document).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        DocumentReference docRef = db.collection("stenentijdperk").document(document);

        ApiFuture<WriteResult> future = docRef.update(field, value);
    }

    public static void updateDocument(String document, String field, float value) {
        try {
            db.collection("stenentijdperk").document(document).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        DocumentReference docRef = db.collection("stenentijdperk").document(document);

        ApiFuture<WriteResult> future = docRef.update(field, value);
    }

    public static void updateDocument(String document, String field, boolean value) {
        try {
            db.collection("stenentijdperk").document(document).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        DocumentReference docRef = db.collection("stenentijdperk").document(document);

        ApiFuture<WriteResult> future = docRef.update(field, value);
    }
}