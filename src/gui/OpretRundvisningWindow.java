package gui;

import java.time.LocalDate;
import java.time.LocalTime;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import service.Service;

public class OpretRundvisningWindow extends Stage {

	public OpretRundvisningWindow() {
		initStyle(StageStyle.UTILITY);
		initModality(Modality.APPLICATION_MODAL);
		setResizable(false);
		setTitle("Klippekort");

		GridPane pane = new GridPane();
		Scene scene = new Scene(pane);
		initContent(pane);
		setScene(scene);
	}

	private DatePicker dp;
	private ComboBox cbbRM;
	private Label lbDatePicker, lbRM, lbTidspunkt, lbAntal, lbTotalPris, lbKundeNavn;
	private CheckBox chbSpisning;
	private TextField txfTidspunkt, txfAntalM, txfAntalS, txfTotalPris, txfKundeNavn;
	private Button btnReserver, btnLuk;

	private void initContent(GridPane pane) {
		pane.setPadding(new Insets(10));
		pane.setHgap(10);
		pane.setVgap(10);
		pane.setGridLinesVisible(false);

		lbKundeNavn = new Label("Kunde's navn :");
		pane.add(lbKundeNavn, 0, 0);

		txfKundeNavn = new TextField();
		pane.add(txfKundeNavn, 1, 0);

		lbDatePicker = new Label("Vælg dato");
		pane.add(lbDatePicker, 0, 1);

		dp = new DatePicker();
		pane.add(dp, 0, 2);

		lbRM = new Label("Vælg Rundvisning");
		pane.add(lbRM, 0, 3);

		cbbRM = new ComboBox();
		pane.add(cbbRM, 0, 4);

		lbTidspunkt = new Label("Tidspunkt");
		pane.add(lbTidspunkt, 0, 6);

		txfTidspunkt = new TextField();
		pane.add(txfTidspunkt, 0, 7);

		lbAntal = new Label("Antal");
		pane.add(lbAntal, 1, 6);

		txfAntalM = new TextField();
		pane.add(txfAntalM, 1, 7);

		chbSpisning = new CheckBox("Spisning Ønskes");
		pane.add(chbSpisning, 0, 8);

		txfAntalS = new TextField();
		pane.add(txfAntalS, 0, 9);

		lbTotalPris = new Label("Total Pris");
		pane.add(lbTotalPris, 0, 10);

		txfTotalPris = new TextField();
		pane.add(txfTotalPris, 0, 11);

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

}
