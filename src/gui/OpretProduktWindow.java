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
import model.ProduktKategori;
import service.Service;

public class OpretProduktWindow extends Stage {

	private ProduktKategori pk;
	private Service service;

	public OpretProduktWindow(ProduktKategori pk) {
		service = Service.getService();
		this.pk = pk;
		initStyle(StageStyle.UTILITY);
		initModality(Modality.APPLICATION_MODAL);
		setResizable(false);
		setTitle("Opret produkt window");

		GridPane pane = new GridPane();
		Scene scene = new Scene(pane);
		initContent(pane);
		setScene(scene);
	}

	private Label lbNavn, lbStr, lbPris;
	private TextField txfNavn, txfStr, txfPris;
	private Button btnOpret, btnLuk;

	private void initContent(GridPane pane) {
		pane.setPadding(new Insets(10));
		pane.setHgap(10);
		pane.setVgap(10);
		pane.setGridLinesVisible(false);

		lbNavn = new Label("Produkt navn:");
		pane.add(lbNavn, 0, 0);

		txfNavn = new TextField();
		pane.add(txfNavn, 1, 0);

		lbStr = new Label("Produktets St�rrelse:");
		pane.add(lbStr, 0, 1);

		txfStr = new TextField();
		pane.add(txfStr, 1, 1);

		lbPris = new Label("Produkt Pris:");
		pane.add(lbPris, 0, 2);

		txfPris = new TextField();
		pane.add(txfPris, 1, 2);

		btnOpret = new Button("Opret");
		pane.add(btnOpret, 0, 3);
		btnOpret.setOnAction(event -> btnOpretAction());

		btnLuk = new Button("Luk");
		pane.add(btnLuk, 1, 3);
		btnLuk.setOnAction(event -> btnLukAction());

	}

	private void btnOpretAction() {
		String navn = txfNavn.getText().trim();
		String str = txfStr.getText().trim();
		String prisString = txfPris.getText().trim();

		try {
			if (navn.isEmpty()) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Mangler");
				alert.setHeaderText("Produkt navn");
				alert.setContentText("Du mangler at give produktet et navn!");
				alert.showAndWait();

			}
			if (str.isEmpty()) {
				Alert alert1 = new Alert(AlertType.INFORMATION);
				alert1.setTitle("Information Mangler");
				alert1.setHeaderText("Produkt størrelse");
				alert1.setContentText("Du mangler at give produktet en størrelse!");

				alert1.showAndWait();

			}
			if (prisString.isEmpty()) {
				Alert alert2 = new Alert(AlertType.INFORMATION);
				alert2.setTitle("Information Mangler");
				alert2.setHeaderText("Produkt Pris");
				alert2.setContentText("Du mangler at give produktet en pris!");

				alert2.showAndWait();

			}
			if (!prisString.isEmpty() && !navn.isEmpty() && !str.isEmpty()) {
				Double pris = Double.parseDouble(txfPris.getText().trim());
				service.opretProdukt(pk, navn, pris, str);
				hide();
			}

		} catch (Exception e) {
			e.getMessage();

		}

	}

	private void btnLukAction() {
		hide();
	}

}
