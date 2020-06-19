package hsleiden.stenentijdperk.stenentijdperk.Controllers;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import hsleiden.stenentijdperk.stenentijdperk.Models.BoardModel;
import hsleiden.stenentijdperk.stenentijdperk.Models.PlayerModel;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

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




    public static void setSpeler(String spelerNummer, PlayerModel player){
        System.out.println(player.getNaam());
        System.out.println(player.getVillagers());
        ApiFuture<WriteResult> future = db.collection("stenentijdperk").document(spelerNummer).set(player);
        try {
            System.out.println("Update time : " + future.get().getUpdateTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public static ArrayList<PlayerModel> getPlayersInLobby(int lobby){
        ArrayList<PlayerModel> players = new ArrayList<>();
        ApiFuture<QuerySnapshot> docRef = db.collection("stenentijdperk")
                .document(String.valueOf(lobby))
                .collection("players")
                .get();
        List<QueryDocumentSnapshot> documents = null;
        try {
            documents = docRef.get().getDocuments();
            for (QueryDocumentSnapshot document : documents) {
                players.add(document.toObject(PlayerModel.class));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return players;
    }

    public static int getAmountofPlayersInLobby(int lobby){
        ApiFuture<QuerySnapshot> docRef = db.collection("stenentijdperk")
                .document(String.valueOf(lobby))
                .collection("players")
                .get();
        List<QueryDocumentSnapshot> documents = null;
        try {
            documents = docRef.get().getDocuments();
            return documents.size();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static void addPlayers(int lobby, String speler, String naam){
        PlayerModel player = new PlayerModel(naam);
        ApiFuture<WriteResult> future = db.collection("stenentijdperk").document(String.valueOf(lobby)).collection("players").document(speler).set(player);
        try {
            System.out.println("Update time : " + future.get().getUpdateTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setGamestatus(int lobby, boolean gameStarted){
        try {
            db.collection("stenentijdperk").document(String.valueOf(lobby)).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        DocumentReference docRef = db.collection("stenentijdperk").document(String.valueOf(lobby));
        docRef.update("isActive", gameStarted);
    }

    public static boolean getGamestatus(int lobby){
        try {
            DocumentReference f = db.collection("stenentijdperk").document(String.valueOf(lobby));
            ApiFuture<DocumentSnapshot> docRef = f.get();
            DocumentSnapshot data = docRef.get();
            Object temp = data.get("isActive");
            System.out.println(temp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Do this on game end.
    public static void resetLobby(int lobby){
        db.collection("stenentijdperk").document(String.valueOf(lobby)).collection("players").document("speler1").delete();
        db.collection("stenentijdperk").document(String.valueOf(lobby)).collection("players").document("speler2").delete();
        db.collection("stenentijdperk").document(String.valueOf(lobby)).collection("players").document("speler3").delete();
        db.collection("stenentijdperk").document(String.valueOf(lobby)).collection("players").document("speler4").delete();

        setGamestatus(lobby, false);
    }

    public static void switchLobby(int oldLobby, PlayerModel player){
        ArrayList<PlayerModel> players = getPlayersInLobby(oldLobby);
        for(PlayerModel curPlayer : players) {
            if(curPlayer.getNaam().equals(player.getNaam())) {
                db.collection("stenentijdperk").document(String.valueOf(oldLobby)).collection("players").document("speler1").delete();
            }
        }
    }

    public static void addBoard(int lobby, BoardModel model){
        ApiFuture<WriteResult> future = db.collection("stenentijdperk").document(String.valueOf(lobby)).collection("boardData").document("board").set(model);
        try {
            System.out.println("Update time : " + future.get().getUpdateTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}