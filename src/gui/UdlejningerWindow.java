package gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Anlaeg;
import model.Fustage;
import model.Kulsyre;
import model.Rentable;
import model.Udlejning;
import service.Service;

public class UdlejningerWindow extends Stage {

	private Service service;

	public UdlejningerWindow() {		

		service = Service.getService();

		initStyle(StageStyle.UTILITY);
		initModality(Modality.APPLICATION_MODAL);
		setResizable(false);
		setTitle("Udlejninger");

		GridPane pane = new GridPane();
		Scene scene = new Scene(pane);
		initContent(pane);
		setScene(scene);
	}
	
	private ListView<Udlejning> lwUdlejning;
	private Label lblUdlejninger, lblNavn, lblEmail, lblTlf, lblKvittering;
	private ListView<Rentable> kvittering;
	private TextField txfNavn, txfEmail, txfTlf;
	private CheckBox chbLevering;
	private Button btnFjern, btnLuk;
	
	
	private void initContent(GridPane pane) {
		pane.setPadding(new Insets(10));
		pane.setHgap(10);
		pane.setVgap(10);
		pane.setGridLinesVisible(false);
		
		lblUdlejninger = new Label("Udlejninger:");
		pane.add(lblUdlejninger, 0, 0);
		
		lwUdlejning = new ListView<>();
		pane.add(lwUdlejning, 0,1, 2, 6);
		
		btnFjern = new Button("Fjern");
		pane.add(btnFjern, 0, 8);
		
		lblNavn = new Label("Navn:");
		pane.add(lblNavn, 1, 0);
		
		txfNavn = new TextField();
		pane.add(txfNavn, 1, 1);
		
		lblEmail = new Label("Email:");
		pane.add(lblEmail, 1, 2);
		
		txfEmail = new TextField();
		pane.add(txfEmail, 1, 3);
		
		lblTlf = new Label("Telefon");
		pane.add(lblTlf, 1, 4);
		
		txfTlf = new TextField();
		pane.add(txfTlf, 1, 5);
		
		chbLevering = new CheckBox("Levering");
		pane.add(chbLevering, 1, 6);
		
		
	}
}


