package gui;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.ProduktKategori;
import model.SalgSted;
import service.Service;
import storage.Storage;

public class SalgStedWindow extends Stage {

	public SalgStedWindow() {
		initStyle(StageStyle.UTILITY);
		initModality(Modality.APPLICATION_MODAL);
		setResizable(false);
		setTitle("Administrator Window");

		GridPane pane = new GridPane();
		Scene scene = new Scene(pane);
		initContent(pane);
		setScene(scene);
	}

	private Label lbSalgSteder, lbSalgsPK, lbMuligePK;
	private ListView<SalgSted> lwSalgSteder;
	private ListView<ProduktKategori> lwSalgsPK, lwMuligePK;
	private Button btnLuk, btnOpret, btnPilVenstre, btnPilHojre, btnSlet;

	private void initContent(GridPane pane) {
		pane.setPadding(new Insets(10));
		pane.setHgap(10);
		pane.setVgap(10);
		pane.setGridLinesVisible(false);

		// ----- VBox 1 ------
		VBox vboks1 = new VBox();
		pane.add(vboks1, 0, 0);

		lbSalgSteder = new Label("Salgs steder");
		vboks1.getChildren().add(lbSalgSteder);

		lwSalgSteder = new ListView<>();
		vboks1.getChildren().add(lwSalgSteder);
		lwSalgSteder.getItems().addAll(Storage.getAllSalgSted());

		ChangeListener<SalgSted> listener = (op, oldProduct, newProduct) -> updateControls();
		lwSalgSteder.getSelectionModel().selectedItemProperty().addListener(listener);

		// ----- VBox 2 ------
		VBox vboks2 = new VBox();
		pane.add(vboks2, 1, 0);

		lbSalgsPK = new Label("Salgssted Produktkategorier");
		vboks2.getChildren().add(lbSalgsPK);

		lwSalgsPK = new ListView<>();
		vboks2.getChildren().add(lwSalgsPK);

		// ----- VBox 3 ------
		VBox vboks3 = new VBox();
		pane.add(vboks3, 2, 0);
		vboks3.setAlignment(Pos.CENTER);

		btnPilHojre = new Button("---->");
		vboks3.getChildren().add(btnPilHojre);
		btnPilHojre.setOnAction(event -> pilHojreAction());

		btnPilVenstre = new Button("<----");
		vboks3.getChildren().add(btnPilVenstre);
		btnPilVenstre.setOnAction(event -> pilVenstreAction());

		// ----- VBox 4 ------
		VBox vboks4 = new VBox();
		pane.add(vboks4, 3, 0);

		lbMuligePK = new Label("Salgssted's mulige produktkategorier");
		vboks4.getChildren().add(lbMuligePK);

		lwMuligePK = new ListView<>();
		vboks4.getChildren().add(lwMuligePK);

		HBox hboks = new HBox(170);
		pane.add(hboks, 0, 1);
		hboks.setAlignment(Pos.BASELINE_LEFT);
		hboks.setPadding(new Insets(10, 0, 0, 0));

		btnOpret = new Button("Opret");
		hboks.getChildren().add(btnOpret);
		btnOpret.setOnAction(event -> btnOpretAction());

		btnSlet = new Button("Slet");
		hboks.getChildren().add(btnSlet);
		btnSlet.setOnAction(event -> btnSletAction());

		btnLuk = new Button("Luk");
		pane.add(btnLuk, 3, 1);
		btnLuk.setOnAction(event -> btnLukAction());

	}

	// Method that updates updates an specific list.
	private List<SalgSted> initAllSalgSted() {
		List<SalgSted> list = new ArrayList<>();
		for (SalgSted ss : Storage.getAllSalgSted()) {
			list.add(ss);
		}
		return list;
	}

	// Method that updates updates an specific list.
	private List<ProduktKategori> initAllSalgsPK() {
		List<ProduktKategori> list = new ArrayList<>();
		for (ProduktKategori pk : lwSalgSteder.getSelectionModel().getSelectedItem().getProduktKategorier()) {
			list.add(pk);
		}
		return list;
	}

	private void updateControls() {
		SalgSted ss = lwSalgSteder.getSelectionModel().getSelectedItem();
		lwSalgsPK.getItems().clear();
		lwMuligePK.getItems().clear();
		if (ss != null) {
			lwSalgsPK.getItems().addAll(lwSalgSteder.getSelectionModel().getSelectedItem().getProduktKategorier());
			lwMuligePK.getItems().addAll(Service.getMuligeKategorier(ss));
		}
	}

	// This buttom opens a new Window.
	private void btnOpretAction() {
		OpretSalgStedWindow ossw = new OpretSalgStedWindow();
		ossw.showAndWait();
		lwSalgSteder.getItems().setAll(initAllSalgSted());

	}

	// This buttom closes the current window.
	private void btnLukAction() {
		hide();
	}

	// This buttom deletes the current salg that you have clicked on from the
	// SalgSteder List,
	private void btnSletAction() {
		if (lwSalgSteder.getSelectionModel().getSelectedItem() != null) {
			Service.sletSalgSted(lwSalgSteder.getSelectionModel().getSelectedItem());
		}
		lwSalgSteder.getItems().setAll(initAllSalgSted());
	}

	// This method adds a produktkategori from the "mulige produktkategori" list to
	// "salgssted produktkategori"
	private void pilVenstreAction() {
		if (lwMuligePK.getSelectionModel().getSelectedItem() != null) {
			Service.tilfoejKategori(lwSalgSteder.getSelectionModel().getSelectedItem(),
					lwMuligePK.getSelectionModel().getSelectedItem());
			lwSalgsPK.getItems().addAll(initAllSalgsPK());
			updateControls();
		}

	}

	// This method removes a produktkategori from the "salgssted produktkategori".
	private void pilHojreAction() {
		if (lwSalgSteder.getSelectionModel().getSelectedItem() != null) {
			lwSalgSteder.getSelectionModel().getSelectedItem()
					.removeProduktKategori(lwSalgsPK.getSelectionModel().getSelectedItem());
			lwSalgsPK.getItems().addAll(initAllSalgsPK());
			updateControls();
		}
	}

}
