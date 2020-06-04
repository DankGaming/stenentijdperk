package hsleiden.stenentijdperk.stenentijdperk.Managers;

public class ViewManager {
<<<<<<< Updated upstream
=======
    private static Stage currentStage;
    private static GridPane currentView;

    public ViewManager() {
    }

    public static void loadLoginView() {
        closeView();
        currentView = new LoginView().setScene();
        createStageFromView(800, 800, "Login");
        showView();
    }

    public static void loadLobbyView(PlayerModel playerModel) {
        closeView();
        currentView = new LobbyView(playerModel).setScene();
        createStageFromView(800, 800, "Lobby");
        showView();
    }

    public static void createStageFromView(int width, int height, String title) {
        currentStage = new Stage();
        currentStage.setScene(new Scene(currentView, width, height));
        currentStage.setTitle(title);
    }

    public static void showView() {
        if(currentStage != null) {
            currentStage.show();
        }
    }

    public static void closeView() {
        try {
            currentStage.close();
        } catch(Exception e) {
        }
    }
>>>>>>> Stashed changes
}
