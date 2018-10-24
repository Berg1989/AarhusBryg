package gui;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.ProduktKategori;
import service.Service;
import storage.Storage;

public class ProduktkategoriWindow extends Stage {

	public ProduktkategoriWindow() {
		initStyle(StageStyle.UTILITY);
		initModality(Modality.APPLICATION_MODAL);
		setResizable(false);
		setTitle("Produktkategori Window");

		GridPane pane = new GridPane();
		Scene scene = new Scene(pane);
		initContent(pane);
		setScene(scene);
	}

	private ListView<ProduktKategori> lwPKategori;
	private Button btnOpret, btnRemove, btnLuk, btnGaaTil;
	private Label lbPKategori;
	private ProduktKategori pk;

	private void initContent(GridPane pane) {
		pane.setPadding(new Insets(10));
		pane.setHgap(10);
		pane.setVgap(10);
		pane.setGridLinesVisible(false);

		lbPKategori = new Label("Produktkategori:");
		pane.add(lbPKategori, 0, 0);

		lwPKategori = new ListView<>();
		pane.add(lwPKategori, 0, 1, 1, 3);
		lwPKategori.setPrefHeight(250);
		lwPKategori.setPrefWidth(180);
		lwPKategori.getItems().addAll(Storage.getAllProduktKategorier());

		btnOpret = new Button("Opret Kategori");
		pane.add(btnOpret, 1, 1);
		btnOpret.setOnAction(event -> btnOpretAction());

		btnRemove = new Button("Slet Kategori");
		pane.add(btnRemove, 1, 2);
		btnRemove.setOnAction(event -> btnRemoveAction());

		btnLuk = new Button("Luk");
		pane.add(btnLuk, 1, 4);
		btnLuk.setOnAction(event -> btnLukAction());

		btnGaaTil = new Button("G� Til");
		pane.add(btnGaaTil, 0, 4);
		btnGaaTil.setOnAction(event -> btnGaaTilAction());

	}

	private List<ProduktKategori> initAllProdukter() {
		List<ProduktKategori> list = new ArrayList<>();
		for (ProduktKategori pk : Storage.getAllProduktKategorier()) {
			list.add(pk);
		}
		return list;
	}

	private void btnOpretAction() {
		OpretKategoriWindow okw = new OpretKategoriWindow();
		okw.showAndWait();
		lwPKategori.getItems().setAll(initAllProdukter());

	}

	private void btnRemoveAction() {
		Service.sletProduktKategori(lwPKategori.getSelectionModel().getSelectedItem());
		lwPKategori.getItems().setAll(initAllProdukter());
	}

	private void btnGaaTilAction() {
		if (lwPKategori.getSelectionModel().getSelectedItem() != null) {
			pk = lwPKategori.getSelectionModel().getSelectedItem();
			GaaTilWindow gtw = new GaaTilWindow(pk);
			gtw.showAndWait();
		}
	}

	private void btnLukAction() {
		hide();

	}

}
