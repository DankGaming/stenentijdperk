package hsleiden.stenentijdperk.stenentijdperk.Controllers;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.core.view.View;
import hsleiden.stenentijdperk.stenentijdperk.App;
import hsleiden.stenentijdperk.stenentijdperk.Helpers.Beschavingskaarten.Kaart;
import hsleiden.stenentijdperk.stenentijdperk.Helpers.Resource;
import hsleiden.stenentijdperk.stenentijdperk.Helpers.StaticHut;
import hsleiden.stenentijdperk.stenentijdperk.Helpers.Test;
import hsleiden.stenentijdperk.stenentijdperk.Helpers.Tool;
import hsleiden.stenentijdperk.stenentijdperk.Managers.ViewManager;
import hsleiden.stenentijdperk.stenentijdperk.Models.BoardModel;
import hsleiden.stenentijdperk.stenentijdperk.Models.PlayerModel;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.ExecutionException;

public class FirebaseController {
    static Firestore db;
    static BoardModel board;
    static BoardModel usedBoard;
    static ArrayList<PlayerModel> players;
    static Test test;
    static ListenerRegistration lobbylisterner;
    static ListenerRegistration playerlistener;
    static ListenerRegistration boardlistener;

    public static void initializeFirebaseApp(Test test) {
        test = test;
        GoogleCredentials credentials = null;

        try {
            InputStream serviceAccount = App.class.getResourceAsStream("/trump123-5eb5e-firebase-adminsdk-n7z18-bb9eee0739.json");
            credentials = GoogleCredentials.fromStream(serviceAccount);
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

    public static void listenForLobbyUpdates(String lobby, PlayerModel pl){
        System.out.println("Lobby " + lobby);
        DocumentReference docRef = db.collection("stenentijdperk").document(lobby);
        lobbylisterner = docRef.addSnapshotListener((snapshot, e) -> {
            System.out.println("Lobby listening" + lobby);
            if (e != null) {
                System.err.println("Listen failed: " + e);
                return;
            }

            if (snapshot != null && snapshot.exists()) {
                Boolean data = snapshot.getBoolean("isActive");
                System.out.println(data);
                if (data){
                    System.out.println("arive");
                    ViewManager.loadBoardView(getPlayersInLobby(Integer.parseInt(lobby)), pl);
                }
            } else {
                System.out.print("Current data: null");
            }
        });
    }

    public static void listenForPlayerUpdates(String lobby){
        CollectionReference colRef = db.collection("stenentijdperk")
                .document(lobby)
                .collection("players");
        playerlistener = colRef.addSnapshotListener((queryDocumentSnapshots, e) -> {
            ArrayList<PlayerModel> updatedPlayers = new ArrayList<>();
            for(DocumentSnapshot s : Objects.requireNonNull(queryDocumentSnapshots)){
                updatedPlayers.add(s.toObject(PlayerModel.class));
            }
            setPlayers(updatedPlayers);
        });
    }

    public static ArrayList<PlayerModel> getPlayers()
    {
        return players;
    }

    static void setPlayers(ArrayList<PlayerModel> newPlayers){
        players = newPlayers;
    }

    public static void listenForBoardUpdates(String lobby){
        DocumentReference docRef = db.collection("stenentijdperk").document(lobby).collection("boardData").document("board");
        boardlistener = docRef.addSnapshotListener((snapshot, e) -> {
            if (e != null) {
                System.err.println("Listen failed: " + e);
                return;
            }

            if (snapshot != null && snapshot.exists()) {
                BoardModel newModel = snapshot.toObject(BoardModel.class);
                setBoard(newModel);
                BoardModel b = BoardModel.getInstance();
                b = newModel;
                System.out.println("updated model");
            } else {
                System.out.print("Current data: null");
            }
        });
    }

    public static BoardModel getBoardLive(){
        return board;
    }
    public static void cancelPlayerListener(){
        if (playerlistener != null)
            playerlistener.remove();
    }

    public static void cancelLobbyListener(){
        if (lobbylisterner != null)
            lobbylisterner.remove();
    }

    public static void cancelBoardListener(){
        if (boardlistener != null)
            boardlistener.remove();
    }

    public static void setBoard(BoardModel newBoard){
        board = newBoard;
    }

    public static BoardModel getBoard(String lobby){
        DocumentReference docRef = db.collection("stenentijdperk").document(lobby).collection("boardData").document("board");
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = null;
        try {
            document = future.get();
        } catch (Exception e) {
            System.out.println("ERROR IS HERE");
            e.printStackTrace();
        }
        BoardModel model = null;
        if (document.exists()) {
            model = document.toObject(BoardModel.class);
            System.out.println(model);
        } else {
            System.out.println("No such document!");
        }
        return model;
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

    public static void updateDocument(String lobby, String player, String field, String value) {
        try {
            db.collection("stenentijdperk").document(lobby).collection("players").document(player).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        DocumentReference docRef = db.collection("stenentijdperk").document(lobby).collection("players").document(player);

        ApiFuture<WriteResult> future = docRef.update(field, value);
    }

    public static void updateDocument(String lobby, String player, String field, int value) {
        try {
            db.collection("stenentijdperk").document(lobby).collection("players").document(player).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        DocumentReference docRef = db.collection("stenentijdperk").document(lobby).collection("players").document(player);

        ApiFuture<WriteResult> future = docRef.update(field, value);
    }

    public static void updateDocument(String lobby, String player, String field, float value) {
        try {
            db.collection("stenentijdperk").document(lobby).collection("players").document(player).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        DocumentReference docRef = db.collection("stenentijdperk").document(lobby).collection("players").document(player);

        ApiFuture<WriteResult> future = docRef.update(field, value);
    }

    public static void updateDocument(String lobby, String player, String field, boolean value) {
        try {
            db.collection("stenentijdperk").document(lobby).collection("players").document(player).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        DocumentReference docRef = db.collection("stenentijdperk").document(lobby).collection("players").document(player);

        ApiFuture<WriteResult> future = docRef.update(field, value);
    }

    public static void updateDocument(String lobby, String player, String field, ArrayList<Tool> value) {
        try {
            db.collection("stenentijdperk").document(lobby).collection("players").document(player).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        DocumentReference docRef = db.collection("stenentijdperk").document(lobby).collection("players").document(player);

        ApiFuture<WriteResult> future = docRef.update(field, value);
    }

    public static void updateDocumentList(String lobby, String player, String field, ArrayList<StaticHut> value) {
        try {
            db.collection("stenentijdperk").document(lobby).collection("players").document(player).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        DocumentReference docRef = db.collection("stenentijdperk").document(lobby).collection("players").document(player);

        ApiFuture<WriteResult> future = docRef.update(field, value);
    }

    public static void setPlayerKaarten(String lobby, String player, String field, ArrayList<Kaart> value) {
        try {
            db.collection("stenentijdperk").document(lobby).collection("players").document(player).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        DocumentReference docRef = db.collection("stenentijdperk").document(lobby).collection("players").document(player);

        ApiFuture<WriteResult> future = docRef.update(field, value);
    }

    public static void updateDocumentList(String lobby, String player, String field, List<Integer> value) {
        try {
            db.collection("stenentijdperk").document(lobby).collection("players").document(player).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        DocumentReference docRef = db.collection("stenentijdperk").document(lobby).collection("players").document(player);

        ApiFuture<WriteResult> future = docRef.update(field, value);
    }

    public static void updateDocument(String lobby, String player, String field, List<String> value) {
        try {
            db.collection("stenentijdperk").document(lobby).collection("players").document(player).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        DocumentReference docRef = db.collection("stenentijdperk").document(lobby).collection("players").document(player);

        ApiFuture<WriteResult> future = docRef.update(field, value);
    }

    public static void updateBoardFieldObj(String lobby, String field, List<String> value) {
        try {
            db.collection("stenentijdperk").document(lobby).collection("boardData").document("board").get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        DocumentReference docRef = db.collection("stenentijdperk").document(lobby).collection("boardData").document("board");

        ApiFuture<WriteResult> future = docRef.update(field, value);
    }

    public static void updateBoardField(String lobby, String field, PlayerModel value) {
        try {
            db.collection("stenentijdperk").document(lobby).collection("boardData").document("board").get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateBoardResource(String lobby, String field, ArrayList<Resource> value) {
        try {
            db.collection("stenentijdperk").document(lobby).collection("boardData").document("board").get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        DocumentReference docRef = db.collection("stenentijdperk").document(lobby).collection("boardData").document("board");

        ApiFuture<WriteResult> future = docRef.update(field, value);
    }

    public static void updateBoardHutjes(String lobby, String field, ArrayList<StaticHut> value) {
        try {
            db.collection("stenentijdperk").document(lobby).collection("boardData").document("board").get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        DocumentReference docRef = db.collection("stenentijdperk").document(lobby).collection("boardData").document("board");

        ApiFuture<WriteResult> future = docRef.update(field, value);
    }

    public static void updateBoardField(String lobby, String field, boolean value) {
        try {
            db.collection("stenentijdperk").document(lobby).collection("boardData").document("board").get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        DocumentReference docRef = db.collection("stenentijdperk").document(lobby).collection("boardData").document("board");

        ApiFuture<WriteResult> future = docRef.update(field, value);
    }

    public static void updateBoardField(String lobby, String field, String value) {
        try {
            db.collection("stenentijdperk").document(lobby).collection("boardData").document("board").get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        DocumentReference docRef = db.collection("stenentijdperk").document(lobby).collection("boardData").document("board");

        ApiFuture<WriteResult> future = docRef.update(field, value);
    }

    public static void updateBoardField(String lobby, String field, List<Kaart> value) {
        try {
            db.collection("stenentijdperk").document(lobby).collection("boardData").document("board").get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        DocumentReference docRef = db.collection("stenentijdperk").document(lobby).collection("boardData").document("board");
        System.out.println(value);
        ApiFuture<WriteResult> future = docRef.update(field, value);
    }

    public static void updateBoardFieldInt(String lobby, String field, List<Integer> value) {
        try {
            db.collection("stenentijdperk").document(lobby).collection("boardData").document("board").get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        DocumentReference docRef = db.collection("stenentijdperk").document(lobby).collection("boardData").document("board");

        ApiFuture<WriteResult> future = docRef.update(field, value);
    }

    public static void updateBoardFieldInt(String lobby, String field, int value) {
        try {
            db.collection("stenentijdperk").document(lobby).collection("boardData").document("board").get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        DocumentReference docRef = db.collection("stenentijdperk").document(lobby).collection("boardData").document("board");

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

    public static void addPlayers(int lobby, String speler, PlayerModel player){
        player.setLobby(lobby);
        System.out.println(player.getPlayerNumber());
        System.out.println(lobby);
        System.out.println(player.toString());
        ApiFuture<WriteResult> future = db.collection("stenentijdperk").document(String.valueOf(lobby)).collection("players").document(speler).set(player);
        try {
            System.out.println("Update time : " + future.get().getUpdateTime());
        } catch (Exception e) {
            System.out.println(future);
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

    public static void setLobbyLeader(int lobby, PlayerModel leader){
        try {
            db.collection("stenentijdperk").document(String.valueOf(lobby)).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        DocumentReference docRef = db.collection("stenentijdperk").document(String.valueOf(lobby));
        docRef.update("leader", leader.getNaam());
    }

    public static String getLobbyLeader(int lobby){
        try {
            DocumentReference f = db.collection("stenentijdperk").document(String.valueOf(lobby));
            ApiFuture<DocumentSnapshot> docRef = f.get();
            DocumentSnapshot data = docRef.get();
            Object temp = data.get("leader");
            return temp != null ? temp.toString() : null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void resetLobby(int lobby){
        db.collection("stenentijdperk").document(String.valueOf(lobby)).collection("players").document("speler1").delete();
        db.collection("stenentijdperk").document(String.valueOf(lobby)).collection("players").document("speler2").delete();
        db.collection("stenentijdperk").document(String.valueOf(lobby)).collection("players").document("speler3").delete();
        db.collection("stenentijdperk").document(String.valueOf(lobby)).collection("players").document("speler4").delete();
        db.collection("stenentijdperk").document(String.valueOf(lobby)).collection("boardData").document("board").delete();
        setGamestatus(lobby, false);
        setLobbyLeader(lobby, new PlayerModel(""));
    }

    public static void switchLobby(int oldLobby, PlayerModel player){
        ArrayList<PlayerModel> players = getPlayersInLobby(oldLobby);
        for(PlayerModel curPlayer : players) {
            if(curPlayer.getNaam().equals(player.getNaam())) {
                db.collection("stenentijdperk").document(String.valueOf(oldLobby)).collection("players").document("speler" + "2").delete();
            }
        }
    }

    public static void addBoard(int lobby, BoardModel model){
        ApiFuture<WriteResult> future = db.collection("stenentijdperk").document("1").collection("boardData").document("board").set(model);
        System.out.println("hello");
        try {
            System.out.println("Update time : " + future.get().getUpdateTime());
        } catch (Exception e) {
            System.out.println(future);
            e.printStackTrace();
        }
    }

    public static void updateBoard(String lobby, BoardModel board) {
        ApiFuture<WriteResult> future = db.collection("stenentijdperk").document(String.valueOf(lobby)).collection("boardData").document("board").set(board);
        try {
            System.out.println("Update time : " + future.get().getUpdateTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}