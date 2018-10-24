package gui;

import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.Scene;
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
import model.SalgSted;
import storage.Storage;

public class SalgsWindow extends Stage {

	public SalgsWindow() {

		initStyle(StageStyle.UTILITY);
		initModality(Modality.APPLICATION_MODAL);
		setResizable(false);
		setTitle("Administrator Window");

		GridPane pane = new GridPane();
		Scene scene = new Scene(pane);
		initContent(pane);
		setScene(scene);

	}

	private Label lbSS, lbPK, lbP, lbAntal;
	private TextField txfAntal;
	private ListView<SalgSted> lwSS;
	private ListView<ProduktKategori> lwPK;
	private ListView<Produkt> lwP;

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
		lwSS.getItems().addAll(Storage.getAllSalgSted());

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
		lwP.setMaxWidth(150);

		// ---------- VBox 4 ----------------

		VBox vboks4 = new VBox();
		pane.add(vboks4, 3, 0);

		lbAntal = new Label("Antal");
		vboks4.getChildren().add(lbAntal);

		txfAntal = new TextField();
		vboks4.getChildren().add(txfAntal);
		txfAntal.setMaxWidth(50);

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

}
