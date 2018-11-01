package gui;

import java.time.LocalDate;

import javafx.beans.value.ChangeListener;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Anlaeg;
import model.Fustage;
import model.Kulsyre;
import service.Service;

public class UdlejningsWindow extends Stage {

	private Service service;

	public UdlejningsWindow() {		

		service = Service.getService();

		initStyle(StageStyle.UTILITY);
		initModality(Modality.APPLICATION_MODAL);
		setResizable(false);
		setTitle("Udlejning");

		GridPane pane = new GridPane();
		Scene scene = new Scene(pane);
		initContent(pane);
		setScene(scene);
	}

	// Insert Attributes here:
	private ListView<Anlaeg> lwAnlag;
	private ListView<Kulsyre> lwKulsyre;
	private ListView<Fustage> lwFustager;
	private ListView<Object> lwValgt;
	private Label lblAnlag, lblDato, lblEmail, lblNavn, lblTlf, lbFustager, lblKulsyre, lblValgt, lblPris, lblPant, lblTotalPris;
	private TextField txfEmail, txfNavn, txfTlf, txfFPris, txfPant, txfTotal, txfAntalF, txfAntalA, txfAntalK;
	private CheckBox chbLevering;
	private DatePicker dp;
	private Button btnAnlag, btnFustage, btnKulsyure, btnFjern, btnOpret, btnLuk;
	private VBox vboks1, vboks2, vboks3, vboks4, vboks5, vboks6;

	private void initContent(GridPane pane) {
		pane.setPadding(new Insets(10));
		pane.setHgap(10);
		pane.setVgap(10);
		pane.setGridLinesVisible(false);

		// -----------------VBox 1 ---------------------------------------

		vboks1 = new VBox();
		pane.add(vboks1, 0, 0);

		lblNavn = new Label("Navn:");
		vboks1.getChildren().add(0, lblNavn);
		
		txfNavn = new TextField();
		vboks1.getChildren().add(1, txfNavn);
		
		lblDato = new Label("Dato:");
		vboks1.getChildren().add(2, lblDato);
		
		dp = new DatePicker();
		vboks1.getChildren().add(3, dp);
		
		lblEmail = new Label("Email:");
		vboks1.getChildren().add(4, lblEmail);
		
		txfEmail = new TextField();
		vboks1.getChildren().add(5, txfEmail);
		
		lblTlf = new Label("Telefon");
		vboks1.getChildren().add(6, lblTlf);
		
		txfTlf = new TextField();
		vboks1.getChildren().add(7, txfTlf);
		
		chbLevering = new CheckBox("Levering");
		vboks1.getChildren().add(8, chbLevering);
		
		

		// -----------------VBox 2 ---------------------------------------

		vboks2 = new VBox();
		pane.add(vboks2, 0, 1);

		lblAnlag = new Label("Anlaeg:");
		vboks2.getChildren().add(0, lblAnlag);
		
		lwAnlag = new ListView<>();
		vboks2.getChildren().add(1, lwAnlag);
		
		HBox hboks2= new HBox();
		vboks2.getChildren().add(2, hboks2);
		
		txfAntalA = new TextField("1");
		hboks2.getChildren().add(0, txfAntalA);
		
		btnAnlag = new Button("->");
		hboks2.getChildren().add(1, btnAnlag);
		btnAnlag.setOnAction(event -> btnAnlagAction());
		

		// -----------------VBox 3 ---------------------------------------

		vboks3 = new VBox();
		pane.add(vboks3, 1, 0);

		lbFustager = new Label("Fustager");
		vboks3.getChildren().add(0, lbFustager);

		lwFustager = new ListView<>();
		vboks3.getChildren().add(1, lwFustager);
		//lwFustager.getItems().addAll(service.getAllFustager());

		//ChangeListener<Fustage> listener = (op, oldProduct, newProduct) -> updateControls();
		//lwFustager.getSelectionModel().selectedItemProperty().addListener(listener);
		
		HBox hboks3= new HBox();
		vboks3.getChildren().add(2, hboks3);
		
		txfAntalF = new TextField("1");
		hboks3.getChildren().add(0, txfAntalF);
		
		btnFustage = new Button("->");
		hboks3.getChildren().add(1, btnFustage);
		
		

		// -----------------VBox 4 ---------------------------------------

		vboks4 = new VBox();
		pane.add(vboks4, 1, 1);
		
		lblKulsyre = new Label("Kulsyre");
		vboks4.getChildren().add(0, lblKulsyre);
		
		lwKulsyre = new ListView<>();
		vboks4.getChildren().add(1, lwKulsyre);
		
		HBox hboks4 = new HBox();
		vboks4.getChildren().add(2, hboks4);
		
		txfAntalK = new TextField("1");
		hboks4.getChildren().add(0, txfAntalK);

		btnKulsyure = new Button("->");
		hboks4.getChildren().add(1, btnKulsyure);
		


		// -----------------VBox 5 ---------------------------------------
		
		vboks5 = new VBox();
		pane.add(vboks5, 2, 0);
		
		lblValgt = new Label("Valgt:");
		vboks5.getChildren().add(0, lblValgt);
		
		lwValgt = new ListView<>();
		vboks5.getChildren().add(1, lwValgt);
		
		
		btnFjern = new Button("<-");
		vboks5.getChildren().add(2, btnFjern);
		
		
		
		// -----------------VBox 6 ---------------------------------------
		
		vboks6 = new VBox();
		pane.add(vboks6, 2, 1);
		
		lblPris = new Label("Pris");
		vboks6.getChildren().add(0, lblPris);
		
		txfFPris = new TextField();
		vboks6.getChildren().add(1, txfFPris);
		
		lblPant = new Label("Pant");
		vboks6.getChildren().add(2, lblPant);
		
		txfPant = new TextField();
		vboks6.getChildren().add(3, txfPant);
		
		lblTotalPris = new Label("Total");
		vboks6.getChildren().add(4, lblTotalPris);
		
		txfTotal = new TextField();
		vboks6.getChildren().add(5, txfTotal);
		
		HBox hboks6 = new HBox();
		vboks6.getChildren().add(6, hboks6);
		
		btnLuk = new Button("Luk");
		hboks6.getChildren().add(0, btnLuk);
		
		btnOpret = new Button("Book");
		hboks6.getChildren().add(1, btnOpret);
		//boks6.setAlignment(Pos.BASELINE_RIGHT);
		btnLuk.setOnAction(event -> btnLukAction());
	


	} 

	/**
	private void updateControls() {
		Fustage fustage = lwFustager.getSelectionModel().getSelectedItem();

		if (fustage != null) {
			txfFNavn.setText(fustage.getOeltype());
			txfFStr.setText(fustage.getStoerrelse());
			txfFPris.setText(Double.toString(fustage.getPris()));

		} else {
			txfFNavn.clear();
			txfFStr.clear();
			txfFPris.clear();

		}
	}*/

	public void btnAnlagAction() {
		
	}

	public void btnOpretAction() {
		String navn = txfNavn.getText().trim();
		String tlf = txfTlf.getText().trim();
		String email = txfEmail.getText().trim();
		LocalDate dato = dp.getValue();

		service.opretUdlejning(navn, tlf, email, dato);
		hide();
		//Make alert

	}

	public void btnLukAction() {
		hide();
	}

}
