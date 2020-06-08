package hsleiden.stenentijdperk.stenentijdperk.Views;

import hsleiden.stenentijdperk.stenentijdperk.Controllers.BoardController;
import hsleiden.stenentijdperk.stenentijdperk.Models.BoardModel;
import javafx.geometry.Insets;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class BoardView {
	private BoardController controller;
	private BoardModel model;
	private GridPane view;

	public BoardView() {
		this.controller = new BoardController();
		setupPane();
	}

	public GridPane setScene() {
		return view;
	}

	private void setupPane() {
		this.view = new GridPane();
		this.view.setPadding(new Insets(10, 10, 10, 10));
		this.view.setStyle("-fx-background-color: d0c9b5");
		int amountOfRows = 50;
		int amountOfColumns = 50;

		for (int i = 0; i < amountOfColumns; i++) {
			ColumnConstraints columnConstraints = new ColumnConstraints();
			columnConstraints.setPercentWidth(100 / amountOfColumns);
			this.view.getColumnConstraints().add(columnConstraints);
		}
		for (int j = 0; j < amountOfRows; j++) {
			RowConstraints rowConstraints = new RowConstraints();
			rowConstraints.setPercentHeight(100 / amountOfRows);
			this.view.getRowConstraints().add(rowConstraints);
		}
	}
}
