package gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import service.Service;

public class OpretKategoriWindow extends Stage {
	private Service service;

	public OpretKategoriWindow() {
		service = Service.getService();
		initStyle(StageStyle.UTILITY);
		initModality(Modality.APPLICATION_MODAL);
		setResizable(false);
		setTitle("Opret Kategori Window");

		GridPane pane = new GridPane();
		Scene scene = new Scene(pane);
		initContent(pane);
		setScene(scene);
	}

	private Label lbKategoriNavn;
	private TextField txfKategoriNavn;
	private Button btnOpret, btnLuk;

	private void initContent(GridPane pane) {
		pane.setPadding(new Insets(10));
		pane.setHgap(10);
		pane.setVgap(10);
		pane.setGridLinesVisible(false);

		lbKategoriNavn = new Label("Navn paa kategori:");
		pane.add(lbKategoriNavn, 0, 0);

		txfKategoriNavn = new TextField();
		pane.add(txfKategoriNavn, 0, 1);

		btnOpret = new Button("Opret");
		pane.add(btnOpret, 0, 2);
		btnOpret.setOnAction(event -> btnOpretAction());

		btnLuk = new Button("Luk");
		pane.add(btnLuk, 1, 2);
		btnLuk.setOnAction(event -> btnLukAction());
	}

	/*
	 * Opretter en produktkategori. Derudover saa giver metoden ogsaa en alert, hvis
	 * textfieldet er tomt.
	 */
	private void btnOpretAction() {
		String navn = txfKategoriNavn.getText().trim();
		if (navn.isEmpty()) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Mangler");
			alert.setHeaderText("Kategori navn");
			alert.setContentText("Du mangler at give kategorien et navn!");

			alert.showAndWait();
		} else {
			service.opretProduktKategori(navn);
			hide();
		}

	}

	/*
	 * Lukker for vinduet
	 */
	private void btnLukAction() {
		hide();

	}

}
