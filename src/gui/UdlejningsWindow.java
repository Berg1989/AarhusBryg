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
	// LISTVIEW SKAL ÆNDRES FRA PRODUKT TIL ANLÆG!
	private ListView<Anlaeg> lwAnlag;

	// SKal lige kigges igennem
	private ListView<Fustage> lwFustager;
	private Label lbMuligAnlag, lbDP, lbEmail, lbKNavn, lbTlf, lbFustager, lbFNavn, lbFStr, lbAntalF, lbFPris, lbSpace;
	private TextField txfEmail, txfKNavn, txfTlf, txfFNavn, txfFStr, txfFPris, txfAntalF;
	private CheckBox chbLevering;
	private DatePicker dp;
	private Button btnTilfoj, btnOpret, btnLuk;

	private void initContent(GridPane pane) {
		pane.setPadding(new Insets(10));
		pane.setHgap(10);
		pane.setVgap(10);
		pane.setGridLinesVisible(false);

		// -----------------VBox 1 ---------------------------------------

		VBox vboks1 = new VBox();
		pane.add(vboks1, 0, 0);

		lbMuligAnlag = new Label("Mulige anlæg til leje");
		vboks1.getChildren().add(lbMuligAnlag);

		lwAnlag = new ListView<>();
		vboks1.getChildren().add(lwAnlag);
		lwAnlag.getItems().addAll(service.getAllAnleag());

		// -----------------VBox 2 ---------------------------------------

		VBox vboks2 = new VBox();
		pane.add(vboks2, 1, 0);

		lbDP = new Label("Udlejningsperiode: ");
		vboks2.getChildren().add(lbDP);

		dp = new DatePicker();
		vboks2.getChildren().add(dp);

		lbKNavn = new Label("Kunde navn: ");
		vboks2.getChildren().add(lbKNavn);

		txfKNavn = new TextField();
		vboks2.getChildren().add(txfKNavn);

		lbEmail = new Label("Email: ");
		vboks2.getChildren().add(lbEmail);

		txfEmail = new TextField();
		vboks2.getChildren().add(txfEmail);

		lbTlf = new Label("Telefon nummer: ");
		vboks2.getChildren().add(lbTlf);

		txfTlf = new TextField();
		vboks2.getChildren().add(txfTlf);

		chbLevering = new CheckBox("Levering 800 kr.-");
		vboks2.getChildren().add(chbLevering);

		// -----------------VBox 3 ---------------------------------------

		VBox vboks3 = new VBox();
		pane.add(vboks3, 2, 0);

		lbFustager = new Label("Fustager");
		vboks3.getChildren().add(lbFustager);

		lwFustager = new ListView<>();
		vboks3.getChildren().add(lwFustager);
		lwFustager.getItems().addAll(service.getAllFustager());

		ChangeListener<Fustage> listener = (op, oldProduct, newProduct) -> updateControls();
		lwFustager.getSelectionModel().selectedItemProperty().addListener(listener);

		// -----------------VBox 4 ---------------------------------------

		VBox vboks4 = new VBox();
		pane.add(vboks4, 3, 0);

		lbFNavn = new Label("Fustage navn: ");
		vboks4.getChildren().add(lbFNavn);

		txfFNavn = new TextField();
		vboks4.getChildren().add(txfFNavn);
		txfFNavn.setEditable(false);

		lbFStr = new Label("Fustage størrelse: ");
		vboks4.getChildren().add(lbFStr);

		txfFStr = new TextField();
		vboks4.getChildren().add(txfFStr);

		lbFPris = new Label("Fustage Pris");
		vboks4.getChildren().add(lbFPris);

		txfFPris = new TextField();
		vboks4.getChildren().add(txfFPris);

		lbAntalF = new Label("Antal fustager: ");
		vboks4.getChildren().add(lbAntalF);

		txfAntalF = new TextField();
		vboks4.getChildren().add(txfAntalF);

		lbSpace = new Label();
		vboks4.getChildren().add(lbSpace);

		btnTilfoj = new Button("Tilføj til udlejning");
		vboks4.getChildren().add(btnTilfoj);
		btnTilfoj.setOnAction(event -> btnTilFojAction());

		// -----------------HBox 1 ---------------------------------------

		HBox hboks1 = new HBox();
		pane.add(hboks1, 0, 3);
		hboks1.setAlignment(Pos.BASELINE_LEFT);

		btnOpret = new Button("Opret Reservering");
		hboks1.getChildren().add(btnOpret);
		btnOpret.setOnAction(event -> btnOpretAction());

		HBox hboks2 = new HBox();
		pane.add(hboks2, 3, 3);
		hboks2.setAlignment(Pos.BASELINE_RIGHT);

		btnLuk = new Button("Luk");
		hboks2.getChildren().add(btnLuk);
		btnLuk.setOnAction(event -> btnLukAction());

	}

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
	}

	public void btnTilFojAction() {

	}

	public void btnOpretAction() {
		String navn = txfKNavn.getText().trim();
		String tlf = txfTlf.getText().trim();
		String email = txfEmail.getText().trim();
		LocalDate dato = dp.getValue();

		service.opretUdlejning(navn, tlf, email, dato);

	}

	public void btnLukAction() {
		hide();
	}

}
