package gui;

import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Produkt;
import model.ProduktKategori;
import model.Salg;
import model.SalgSted;
import model.SalgsLinie;
import service.Service;

public class SalgsWindow extends Stage {
	private Service service;
	private Salg s;

	public SalgsWindow() {
		service = Service.getService();

		initStyle(StageStyle.UTILITY);
		initModality(Modality.APPLICATION_MODAL);
		setResizable(false);
		setTitle("Administrator Window");

		GridPane pane = new GridPane();
		Scene scene = new Scene(pane);
		initContent(pane);
		setScene(scene);

	}

	private Label lbSS, lbPK, lbP, lbAntal, lbTP, lbTilfojet;
	private TextField txfAntal, txfTP;
	private ListView<SalgSted> lwSS;
	private ListView<ProduktKategori> lwPK;
	private ListView<Produkt> lwP;
	private ListView<SalgsLinie> lwTilfojet;
	private Button btnPilVenstre, btnPilHojre, btnLuk, btnVidere;

	private void initContent(GridPane pane) {
		pane.setPadding(new Insets(10));
		pane.setHgap(10);
		pane.setVgap(10);
		pane.setGridLinesVisible(false);

		// ---------- VBox 1 ----------------

		VBox vboks1 = new VBox();
		pane.add(vboks1, 0, 0);

		lbSS = new Label("Produktkategorier: ");
		vboks1.getChildren().add(lbSS);

		lwSS = new ListView<>();
		vboks1.getChildren().add(lwSS);
		lwSS.setMaxWidth(100);
		lwSS.getItems().addAll(service.getAllSalgSted());

		ChangeListener<SalgSted> listener = (op, oldProduct, newProduct) -> updateControls();
		lwSS.getSelectionModel().selectedItemProperty().addListener(listener);

		// ---------- VBox 2 ----------------

		VBox vboks2 = new VBox();
		pane.add(vboks2, 1, 0);

		lbPK = new Label("Produktkategorier");
		vboks2.getChildren().add(lbPK);

		lwPK = new ListView<>();
		vboks2.getChildren().add(lwPK);
		lwPK.setMaxWidth(150);

		ChangeListener<ProduktKategori> listener1 = (op, oldProduct, newProduct) -> updateControlsTwo();
		lwPK.getSelectionModel().selectedItemProperty().addListener(listener1);

		// ---------- VBox 3 ----------------

		VBox vboks3 = new VBox();
		pane.add(vboks3, 2, 0);

		lbP = new Label("Produkter");
		vboks3.getChildren().add(lbP);

		lwP = new ListView<>();
		vboks3.getChildren().add(lwP);
		lwP.setMaxWidth(220);

		ChangeListener<Produkt> listener2 = (op, oldProduct, newProduct) -> updateControlsThree();
		lwP.getSelectionModel().selectedItemProperty().addListener(listener2);

		// ---------- VBox 4 ----------------

		VBox vboks4 = new VBox(10);
		pane.add(vboks4, 3, 0);

		lbAntal = new Label("Antal");
		vboks4.getChildren().add(lbAntal);

		txfAntal = new TextField();
		vboks4.getChildren().add(txfAntal);
		txfAntal.setMaxWidth(50);

		btnPilHojre = new Button("--->");
		vboks4.getChildren().add(btnPilHojre);
		btnPilHojre.setOnAction(event -> pilHojreAction());

		btnPilVenstre = new Button("<---");
		vboks4.getChildren().add(btnPilVenstre);
		btnPilVenstre.setOnAction(event -> pilVenstreAction());

		// ---------- VBox 5 ----------------

		VBox vboks5 = new VBox(10);
		pane.add(vboks5, 4, 0);

		lbTilfojet = new Label("Tilføjet til køblist");
		vboks5.getChildren().add(lbTilfojet);

		lwTilfojet = new ListView<>();
		vboks5.getChildren().add(lwTilfojet);

		lbTP = new Label("Total Pris");
		vboks5.getChildren().add(lbTP);

		txfTP = new TextField();
		vboks5.getChildren().add(txfTP);
		txfTP.setEditable(false);

		// ---------- HBox 1 ----------------

		btnLuk = new Button("Luk");
		pane.add(btnLuk, 0, 1);

		// ---------- HBox 2 ----------------

		btnVidere = new Button("Videre");
		pane.add(btnVidere, 4, 1);

	}

	private void updateControls() {
		SalgSted ss = lwSS.getSelectionModel().getSelectedItem();
		lwPK.getItems().clear();
		if (ss != null) {
			lwPK.getItems().addAll(ss.getProduktKategorier());
		}
	}

	private void updateControlsTwo() {
		ProduktKategori pk = lwPK.getSelectionModel().getSelectedItem();
		lwP.getItems().clear();
		if (pk != null) {
			lwP.getItems().addAll(pk.getProdukter());
		}
	}

	private void updateControlsThree() {
		txfAntal.setText("1");
	}

	private void initAllSalgsPK() {
		lwTilfojet.getItems().clear();
		lwTilfojet.getItems().addAll(s.getProdukter());
		txfTP.setText(Double.toString(s.getTotalPris()));
	}

	private void pilHojreAction() {
		if (s == null && lwP.getSelectionModel().getSelectedItem() != null) {
			s = service.createSalg(lwSS.getSelectionModel().getSelectedItem());
			if (lwP.getSelectionModel().getSelectedItem() != null) {
				int antal = Integer.parseInt(txfAntal.getText().trim());
				Produkt p = lwP.getSelectionModel().getSelectedItem();

				service.tilfojProdukt(s, antal, p);
			}
		} else if (lwP.getSelectionModel().getSelectedItem() != null) {
			int antal = Integer.parseInt(txfAntal.getText().trim());
			Produkt p = lwP.getSelectionModel().getSelectedItem();
			service.tilfojProdukt(s, antal, p);
		}
		initAllSalgsPK();

	}

	private void pilVenstreAction() {
		if (lwTilfojet.getSelectionModel().getSelectedItem() != null) {
			s.getProdukter().remove(lwTilfojet.getSelectionModel().getSelectedItem());
			initAllSalgsPK();
		}
	}

}
