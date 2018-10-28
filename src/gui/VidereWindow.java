package gui;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.EnumSet;

import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Betalingsmetode;
import model.Produkt;
import model.Salg;
import model.SalgsLinie;
import service.Service;

public class VidereWindow extends Stage {
	private Service service;
	private Salg s;

	public VidereWindow(Salg s) {
		service = Service.getService();
		this.s = s;

		initStyle(StageStyle.UTILITY);
		initModality(Modality.APPLICATION_MODAL);
		setResizable(false);
		setTitle("Salgslinie Window");

		GridPane pane = new GridPane();
		Scene scene = new Scene(pane);
		initContent(pane);
		setScene(scene);
		

	}

	private ListView<SalgsLinie> lwTilFojet;
	private Label lbTF, lbTP, lbTPNy, lbBetalling, lbKredit;
	private TextField txfTP, txfTPNy, txfBP, txfPD, txfKlippekort;
	private CheckBox cbBP, cbPD, cbKlippekort;
	private Button btnLuk, btnBetal;
	private ComboBox<Betalingsmetode> cbbKredit;
	private EnumSet<Betalingsmetode> enumBetaling = EnumSet.allOf(Betalingsmetode.class);

	private void initContent(GridPane pane) {
		pane.setPadding(new Insets(10));
		pane.setHgap(10);
		pane.setVgap(10);
		pane.setGridLinesVisible(false);

		// ---------- VBox 1 ----------------

		VBox vboks1 = new VBox();
		pane.add(vboks1, 0, 0);

		lbTF = new Label("Tilføjet produkter");
		vboks1.getChildren().add(lbTF);

		lwTilFojet = new ListView<>();
		vboks1.getChildren().add(lwTilFojet);
		lwTilFojet.getItems().addAll(s.getProdukter());
		lwTilFojet.setDisable(true);

		// ---------- VBox 2 ----------------

		VBox vboks2 = new VBox(10);
		pane.add(vboks2, 1, 0);

		lbTP = new Label("Total pris");
		vboks2.getChildren().add(lbTP);

		txfTP = new TextField();
		vboks2.getChildren().add(txfTP);
		txfTP.setEditable(false);
		txfTP.setText(Double.toString(s.getTotalPris()));
		cbBP = new CheckBox("Bestemt pris");
		vboks2.getChildren().add(cbBP);
		cbBP.setOnAction(event -> cbBPIsSelected());

		txfBP = new TextField();
		vboks2.getChildren().add(txfBP);
		txfBP.setDisable(true);

		cbPD = new CheckBox("Procent Discount");
		vboks2.getChildren().add(cbPD);
		cbPD.setOnAction(event -> cbPDIsSelected());

		txfPD = new TextField();
		vboks2.getChildren().add(txfPD);
		txfPD.setDisable(true);

		lbTPNy = new Label("Ny total pris");
		vboks2.getChildren().add(lbTPNy);

		txfTPNy = new TextField();
		vboks2.getChildren().add(txfTPNy);

		btnLuk = new Button("Luk");
		pane.add(btnLuk, 0, 1);
		btnLuk.setOnAction(event -> btnLukAction());

		btnBetal = new Button("Betal");
		pane.add(btnBetal, 1, 1);
		btnBetal.setOnAction(event -> btnBetalAction());

		lbBetalling = new Label("----------Betalling---------");
		vboks2.getChildren().add(lbBetalling);

		lbKredit = new Label("Betalingstype");
		vboks2.getChildren().add(lbKredit);

		cbbKredit = new ComboBox<>();
		vboks2.getChildren().add(cbbKredit);
		cbbKredit.getItems().addAll(enumBetaling);
		cbbKredit.setOnAction(event -> updateNyPris());

		cbKlippekort = new CheckBox("Klippekort");
		vboks2.getChildren().add(cbKlippekort);
		cbKlippekort.setOnAction(event -> cbKlippekortSelected());

		txfKlippekort = new TextField();
		vboks2.getChildren().add(txfKlippekort);
		txfKlippekort.setDisable(true);

	}

	private void cbBPIsSelected() {
		if (cbBP.isSelected()) {
			txfBP.setDisable(false);
		} else {
			txfBP.setDisable(true);
		}

	}

	private void cbPDIsSelected() {
		if (cbPD.isSelected()) {
			txfPD.setDisable(false);
		} else {
			txfPD.setDisable(true);
		}

	}

	private void cbKlippekortSelected() {
		if (cbKlippekort.isSelected()) {
			txfKlippekort.setDisable(false);
		} else {
			txfKlippekort.setDisable(true);
		}
	}
	
	private void updateNyPris(){
		Double nyPris;
		if (cbBP.isSelected() && !txfBP.getText().isEmpty()){
			txfTPNy.setText(txfBP.getText().trim());
			if (cbBP.isSelected() && !txfBP.getText().isEmpty() && cbPD.isSelected() && !txfPD.getText().isEmpty()){
				nyPris = (Double.parseDouble(txfBP.getText().trim()) * ((Double.parseDouble(txfPD.getText().trim())/100)));
				txfTPNy.setText(Double.toString(nyPris));
			}
		}
		else if (cbPD.isSelected() && !txfPD.getText().isEmpty()) {
		nyPris = (Double.parseDouble(txfTP.getText().trim()) * ((Double.parseDouble(txfPD.getText().trim())/100)));
		txfTPNy.setText(Double.toString(nyPris));
	} else {
		txfTPNy.setText(txfTP.getText());
	}
		}
	
	private void btnBetalAction(){
		if (!txfTPNy.getText().isEmpty()){
		s.setPris(Double.parseDouble(txfTPNy.getText().trim()));
	} else {
		s.setPris(Double.parseDouble(txfTP.getText().trim()));
	}
		s.setBetalingsMetode(cbbKredit.getSelectionModel().getSelectedItem());
		s.setDato(LocalDate.now());
		s.setTid(LocalTime.now());
		service.completeSalg(s);
	}
	
	private void btnLukAction(){
		hide();
	}
}
