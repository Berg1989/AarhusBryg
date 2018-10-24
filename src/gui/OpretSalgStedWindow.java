package gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import service.Service;

public class OpretSalgStedWindow extends Stage {
	private Service service;

	public OpretSalgStedWindow() {
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

	private Label lbNavn;
	private TextField txfNavn;
	private Button btnLuk, btnOpret;

	private void initContent(GridPane pane) {
		pane.setPadding(new Insets(10));
		pane.setHgap(10);
		pane.setVgap(10);
		pane.setGridLinesVisible(false);

		lbNavn = new Label("Salgssted navn: ");
		pane.add(lbNavn, 0, 0);

		txfNavn = new TextField();
		pane.add(txfNavn, 0, 1);

		btnOpret = new Button("Opret");
		pane.add(btnOpret, 0, 2);
		btnOpret.setOnAction(event -> btnOpretAction());

		btnLuk = new Button("Luk");
		pane.add(btnLuk, 1, 2);
		btnLuk.setOnAction(event -> btnLukAction());

	}

	private void btnOpretAction() {

		String navn = txfNavn.getText().trim();

		service.opretSalgSted(navn);
		hide();

	}

	private void btnLukAction() {
		hide();
	}

}
