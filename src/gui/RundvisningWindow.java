package gui;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Rundvisning;
import service.Service;

public class RundvisningWindow extends Stage {
	private Service service;

	public RundvisningWindow() {
		service = Service.getService();
		initStyle(StageStyle.UTILITY);
		initModality(Modality.APPLICATION_MODAL);
		setResizable(false);
		setTitle("Booking");

		GridPane pane = new GridPane();
		Scene scene = new Scene(pane);
		initContent(pane);
		setScene(scene);
	}

	private ListView<Rundvisning> lwRundvisning;
	private Button btnOpret, btnSlet, btnLuk, btnBetal;
	private Label lbGroen, lbRoed, lbBookings;
	private Rundvisning rv;

	private void initContent(GridPane pane) {
		pane.setPadding(new Insets(10));
		pane.setHgap(10);
		pane.setVgap(10);
		pane.setGridLinesVisible(false);

		lbGroen = new Label("Groent navn = betalt");
		pane.add(lbGroen, 0, 0);
		lbGroen.setTextFill(Color.GREEN);

		lbGroen = new Label("Roedt navn = ikke betalt");
		pane.add(lbGroen, 0, 1);
		lbGroen.setTextFill(Color.RED);

		lbBookings = new Label("Rundvisninger:");
		pane.add(lbBookings, 0, 2);

		lwRundvisning = new ListView<>();
		pane.add(lwRundvisning, 0, 2, 1, 4);
		lwRundvisning.setPrefHeight(250);
		lwRundvisning.setPrefWidth(180);
		lwRundvisning.getItems().addAll(service.getAllRundvisninger());

		btnOpret = new Button("Opret Rundvisning");
		pane.add(btnOpret, 1, 2);
		btnOpret.setOnAction(event -> btnOpretAction());

		btnSlet = new Button("Slet Rundvisning");
		pane.add(btnSlet, 1, 3);
		btnSlet.setOnAction(event -> btnSletAction());

		btnBetal = new Button("Betal");
		pane.add(btnBetal, 1, 4);
		// btnBetal.setOnAction(event -> btnBetalAction());

		btnLuk = new Button("Luk");
		pane.add(btnLuk, 2, 6);
		btnLuk.setOnAction(event -> btnLukAction());

	}

	private List<Rundvisning> initAllProdukter() {
		List<Rundvisning> list = new ArrayList<>();
		for (Rundvisning r : service.getAllRundvisninger()) {
			list.add(r);
		}
		return list;
	}

	private void btnOpretAction() {
		OpretRundvisningWindow orw = new OpretRundvisningWindow();
		orw.showAndWait();
	}

	private void btnLukAction() {
		hide();
	}

	private void btnSletAction() {
		Rundvisning r = lwRundvisning.getSelectionModel().getSelectedItem();

		service.sletRundvisning(r);
		lwRundvisning.getItems().setAll(initAllProdukter());

	}

}
