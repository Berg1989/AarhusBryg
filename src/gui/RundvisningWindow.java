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
import javafx.scene.control.TextField;
import javafx.scene.control.CheckBox;

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
		pane.setGridLinesVisible(true);
	}

	private ListView<Rundvisning> lwRundvisning;
	private Button btnOpret, btnSlet, btnLuk, btnBetal;
	private Label lbBookings, lblName, lblDato, lblAntal, lblAntalS, lblTid;
	private Rundvisning rv;
	private TextField txfName, txfDato, txfAntal, txfAntalS, txfTid;
	private CheckBox cbxSpisende, cbxStuderende;

	private void initContent(GridPane pane) {
		pane.setPadding(new Insets(10));
		pane.setHgap(10);
		pane.setVgap(10);
		pane.setGridLinesVisible(false);

		lbBookings = new Label("Rundvisninger:");
		pane.add(lbBookings, 0, 0);

		lwRundvisning = new ListView<>();
		pane.add(lwRundvisning, 0, 1, 1, 6);
		lwRundvisning.setPrefHeight(250);
		lwRundvisning.setPrefWidth(180);
		lwRundvisning.getItems().addAll(service.getAllRundvisninger());
		lwRundvisning.setOnMouseClicked(event -> {
			rv = lwRundvisning.getSelectionModel().getSelectedItem();
			if (rv == null) {
				//do nothing
			}
			else {
				//do something
				txfName.setText(rv.getKunde());
				txfDato.setText(rv.getDato().toString());
				txfAntal.setText("" + rv.getAntalGaester());
				txfTid.setText(rv.getTid().toString());
				
			}
		});
	
		btnOpret = new Button("Opret Rundvisning");
		pane.add(btnOpret, 0, 7);
		btnOpret.setOnAction(event -> btnOpretAction());

		btnSlet = new Button("Slet Rundvisning");
		pane.add(btnSlet, 0, 8);
		btnSlet.setOnAction(event -> btnSletAction());

		btnBetal = new Button("Betal");
		pane.add(btnBetal, 1, 8);
		// btnBetal.setOnAction(event -> btnBetalAction());

		btnLuk = new Button("Luk");
		pane.add(btnLuk, 3, 8);
		btnLuk.setOnAction(event -> btnLukAction());
		
		//NEW
		lblName = new Label("Kunde");
		pane.add(lblName, 1, 2);
		
		txfName = new TextField();
		pane.add(txfName, 2, 2);
		
		lblDato = new Label("Dato");
		pane.add(lblDato, 1, 3);
		
		txfDato = new TextField();
		pane.add(txfDato, 2, 3);
		
		lblTid = new Label("Tid");
		pane.add(lblTid, 1, 4);
		
		lblAntal = new Label("Antal");
		pane.add(lblAntal, 1, 5);
		
		txfAntal = new TextField();
		pane.add(txfAntal, 2, 5);
		
		cbxSpisende = new CheckBox();
		pane.add(cbxSpisende, 2, 6);
		
		cbxStuderende = new CheckBox();
		pane.add(cbxStuderende, 3, 6);
		
		lblAntalS = new Label("Spisende");
		pane.add(lblAntalS, 1, 7);
		
		txfAntalS = new TextField();
		pane.add(txfAntalS, 2, 7);
		
		

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
		hide();
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
