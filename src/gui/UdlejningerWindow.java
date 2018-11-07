package gui;

import java.time.LocalDate;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Anlaeg;
import model.Fustage;
import model.Kulsyre;
import model.Rentable;
import model.Udlejning;
import model.UdlejningsLinje;
import service.Service;

public class UdlejningerWindow extends Stage {

	private Service service;

	public UdlejningerWindow() {		

		service = Service.getService();

		initStyle(StageStyle.UTILITY);
		initModality(Modality.APPLICATION_MODAL);
		setResizable(false);
		setTitle("Udlejninger");

		GridPane pane = new GridPane();
		Scene scene = new Scene(pane);
		initContent(pane);
		setScene(scene);
	}
	
	private ListView<Udlejning> lwUdlejning;
	private Label lblUdlejninger, lblNavn, lblEmail, lblTlf, lblKvittering;
	private ListView<UdlejningsLinje> lwKvittering;
	private TextField txfNavn, txfEmail, txfTlf;
	private CheckBox chbLevering;
	private Button btnFjern, btnLuk, btnOpret, btnUpdate;
	private Udlejning u;
	
	
	private void initContent(GridPane pane) {
		pane.setPadding(new Insets(10));
		pane.setHgap(10);
		pane.setVgap(10);
		pane.setGridLinesVisible(false);
		
		lblUdlejninger = new Label("Udlejninger:");
		pane.add(lblUdlejninger, 0, 0);
		
		lwUdlejning = new ListView<>();
		pane.add(lwUdlejning, 0, 1, 2, 6);
		lwUdlejning.setOnMouseClicked(event -> update());
		lwUdlejning.setPrefHeight(50);
		lwUdlejning.setMaxHeight(400.0);
		lwUdlejning.getItems().addAll(service.getAllUdlejninger());
		lwUdlejning.setOnMouseClicked(event -> lwListener());
		
		btnOpret = new Button("Opret");
		pane.add(btnOpret, 0, 8);
		btnOpret.setOnAction(event -> opretReservation());
		
		btnFjern = new Button("Fjern");
		pane.add(btnFjern, 1, 8);
		//Missing methode
		
		lblNavn = new Label("Navn:");
		pane.add(lblNavn, 2, 1);
		
		txfNavn = new TextField();
		pane.add(txfNavn, 3, 1);
		
		lblEmail = new Label("Email:");
		pane.add(lblEmail, 2, 2);
		
		txfEmail = new TextField();
		pane.add(txfEmail, 3, 2);
		
		lblTlf = new Label("Telefon");
		pane.add(lblTlf, 2, 3);
		
		txfTlf = new TextField();
		pane.add(txfTlf, 3, 3);
		
		chbLevering = new CheckBox("Levering");
		pane.add(chbLevering, 3, 4);
		
		lblKvittering = new Label("Kvittering");
		pane.add(lblKvittering, 2, 5);
		
		lwKvittering = new ListView<>();
		pane.add(lwKvittering, 2, 6, 2,1);
		lwKvittering.setPrefHeight(200.0);
		
		btnUpdate = new Button("Opdatere");
		pane.add(btnUpdate, 2, 8);
		btnUpdate.setOnAction(event -> btnUpdateAction());
		
		btnLuk = new Button("Luk");
		pane.add(btnLuk, 3, 8);
		btnLuk.setAlignment(Pos.CENTER_RIGHT); //Not working
		
	
	}
	
	public void update() {
		if (u != null) { //Just to be safe
			txfTlf.setText(u.getKundeEmail());
			txfNavn.setText(u.getKundeNavn());
			txfEmail.setText(u.getKundeEmail());
			if (u.getLevering()) {
				chbLevering.setSelected(true);
			}
			else {
				chbLevering.setSelected(false);
			}
		}
		lwKvittering.getItems().addAll(u.getFullOrdre());
	}
	
	public void lukAction() {
		this.hide();
	}
	
	public void opretReservation() {
		UdlejningsWindow uw = new UdlejningsWindow();
		this.hide();
		uw.show();
	
	}
	
	public void lwListener() {
		u = lwUdlejning.getSelectionModel().getSelectedItem();
		update();
	}
	
	public void btnUpdateAction() {
		String navn = txfNavn.getText().trim();
		String tlf = txfTlf.getText().trim();
		String email = txfEmail.getText().trim();
		if (navn.length() > 0) {
			u.setKundeNavn(navn);
		}
		if (tlf.length() > 0) {
			u.setKundeTlf(tlf);
		}
		if (email.length() > 0) {
			u.setKundeEmail(email);
		}
		//Fix levering
	}
}


