package gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AnlaegWindow extends Stage {

	public AnlaegWindow() {
		initStyle(StageStyle.UTILITY);
		initModality(Modality.APPLICATION_MODAL);
		setResizable(false);
		setTitle("Anlaeg Window");

		GridPane pane = new GridPane();
		Scene scene = new Scene(pane);
		initContent(pane);
		setScene(scene);
	}

	// Attributter til klassen
	private Button btnUdlejning, btnReserveringer;

	private void initContent(GridPane pane) {
		pane.setPadding(new Insets(10));
		pane.setHgap(10);
		pane.setVgap(10);
		pane.setGridLinesVisible(false);

		btnUdlejning = new Button("Udlejning");
		pane.add(btnUdlejning, 0, 1);
		btnUdlejning.setOnAction(event -> btnUdlejningAction());
		btnUdlejning.setPrefSize(200, 100);

		btnReserveringer = new Button("Reserveringer");
		pane.add(btnReserveringer, 0, 2);
		btnReserveringer.setPrefSize(200, 100);

	}

	/*
	 * Aabner for UdlejningsWindow'et og venter paa at det vindue lukker.
	 */
	public void btnUdlejningAction() {
		UdlejningsWindow uw = new UdlejningsWindow();
		uw.showAndWait();
		hide();
	}
}
