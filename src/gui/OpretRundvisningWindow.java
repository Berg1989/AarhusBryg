package gui;

import java.time.LocalDate;
import java.time.LocalTime;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import service.Service;

public class OpretRundvisningWindow extends Stage {

	public OpretRundvisningWindow() {
		initStyle(StageStyle.UTILITY);
		initModality(Modality.APPLICATION_MODAL);
		setResizable(false);
		setTitle("Opretning af Rundvisning");

		GridPane pane = new GridPane();
		Scene scene = new Scene(pane);
		initContent(pane);
		setScene(scene);
	}

	private DatePicker dp;
	private Label lbDatePicker, lbAntalS, lbTidspunkt, lbAntal, lbTotalPris, lbKundeNavn;
	private CheckBox chbSpisning, chbStuderende;
	private TextField txfTidspunkt, txfAntalM, txfAntalS, txfTotalPris, txfKundeNavn;
	private Button btnReserver, btnLuk;

	private void initContent(GridPane pane) {
		pane.setPadding(new Insets(10));
		pane.setHgap(10);
		pane.setVgap(10);
		pane.setGridLinesVisible(false);

		HBox Hnavn = new HBox();
		pane.add(Hnavn, 0, 0);
		Hnavn.setAlignment(Pos.TOP_LEFT);

		lbKundeNavn = new Label("Kunde's navn:  ");
		Hnavn.getChildren().add(lbKundeNavn);

		txfKundeNavn = new TextField();
		Hnavn.getChildren().add(txfKundeNavn);

		lbDatePicker = new Label("Vælg dato");
		pane.add(lbDatePicker, 0, 1);

		dp = new DatePicker();
		pane.add(dp, 0, 2);

		chbStuderende = new CheckBox("Studerende");
		pane.add(chbStuderende, 0, 3);

		lbTidspunkt = new Label("Tidspunkt");
		pane.add(lbTidspunkt, 0, 5);

		txfTidspunkt = new TextField();
		pane.add(txfTidspunkt, 0, 6);

		lbAntal = new Label("Antal");
		pane.add(lbAntal, 1, 5);

		txfAntalM = new TextField();
		pane.add(txfAntalM, 1, 6);

		chbSpisning = new CheckBox("Spisning Ønskes - 120 kr.- pr. person");
		pane.add(chbSpisning, 0, 7);
		chbSpisning.setOnAction(event -> selectedSpisning());

		lbAntalS = new Label("Antal for spisning");
		pane.add(lbAntalS, 0, 8);

		txfAntalS = new TextField();
		pane.add(txfAntalS, 0, 9);
		txfAntalS.setDisable(true);

		lbTotalPris = new Label("Total Pris");
		pane.add(lbTotalPris, 0, 10);

		txfTotalPris = new TextField();
		pane.add(txfTotalPris, 0, 11);
		txfTotalPris.setEditable(false);

		btnReserver = new Button("Reserver");
		pane.add(btnReserver, 0, 12);
		btnReserver.setOnAction(event -> btnReserverAction());

		btnLuk = new Button("Luk");
		pane.add(btnLuk, 1, 12);
		btnLuk.setOnAction(event -> btnLukAction());

	}

	private void btnReserverAction() {
		String KundeNavn = txfKundeNavn.getText().trim();
		int Antal = Integer.parseInt(txfAntalM.getText().trim());
		LocalDate dato = dp.getValue();
		LocalTime startTime = LocalTime.parse(txfTidspunkt.getText());
		Service.getService();
		Service.opretRundvisning(KundeNavn, dato, Antal, startTime);
		hide();

	}

	private void btnLukAction() {
		hide();
	}

	private void selectedSpisning() {
		if (chbSpisning.isSelected()) {
			txfAntalS.setDisable(false);
		}
	}

}
