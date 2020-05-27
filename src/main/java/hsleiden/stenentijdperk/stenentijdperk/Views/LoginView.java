package hsleiden.stenentijdperk.stenentijdperk.Views;

import hsleiden.stenentijdperk.stenentijdperk.Controllers.LoginController;
import hsleiden.stenentijdperk.stenentijdperk.Models.PlayerModel;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class LoginView {
	private LoginController controller;
	private PlayerModel playermodel; 
	private GridPane view;
	
	public LoginView(LoginController controller, PlayerModel model) {
		this.controller = controller;
		this.playermodel = model;
		
		setupPane();
	}
	public Parent setScene() {
		return view;
	}
	
	private void setupPane() {
		this.view = new GridPane();
		ColumnConstraints leftCol = new ColumnConstraints();
	    leftCol.setHalignment(HPos.RIGHT);
	    leftCol.setHgrow(Priority.NEVER);

	    ColumnConstraints rightCol = new ColumnConstraints();
	    rightCol.setHgrow(Priority.SOMETIMES);

	    view.getColumnConstraints().addAll(leftCol, rightCol);

	    view.setAlignment(Pos.CENTER);
	    view.setHgap(5);
	    view.setVgap(10);
		
	}
	
}
