package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Produkt;

public class UdlejningsWindow extends Stage {
	public UdlejningsWindow() {
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
	private ListView<Produkt> lwAnlag;

	// SKal lige kigges igennem
	private ListView<Produkt> lwPK;
	private Label lbMuligAnlag, lbUdlej, lbEmail, lbKNavn, lbTlf, lbLejAnlaeg, lbFustager, lbFNavn, lbFStr, lbAntalF,
			lbSpace;
	private TextField txfUdlej, txfEmail, txfKNavn, txfTlf, txfFNavn, txfFStr, txfAntalF;
	private CheckBox chbLevering;
	private ComboBox<?> cbbAnlag;
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

		// -----------------VBox 2 ---------------------------------------

		VBox vboks2 = new VBox();
		pane.add(vboks2, 1, 0);

		lbUdlej = new Label("Udlejningsperiode: ");
		vboks2.getChildren().add(lbUdlej);

		txfUdlej = new TextField();
		vboks2.getChildren().add(txfUdlej);

		lbEmail = new Label("Email: ");
		vboks2.getChildren().add(lbEmail);

		txfEmail = new TextField();
		vboks2.getChildren().add(txfEmail);

		lbKNavn = new Label("Kunde navn: ");
		vboks2.getChildren().add(lbKNavn);

		txfKNavn = new TextField();
		vboks2.getChildren().add(txfKNavn);

		lbTlf = new Label("Telefon nummer: ");
		vboks2.getChildren().add(lbTlf);

		txfTlf = new TextField();
		vboks2.getChildren().add(txfTlf);

		chbLevering = new CheckBox("Levering 800 kr.-");
		vboks2.getChildren().add(chbLevering);

		lbLejAnlaeg = new Label("Leje af anlæg");
		vboks2.getChildren().add(lbLejAnlaeg);

		cbbAnlag = new ComboBox<>();
		vboks2.getChildren().add(cbbAnlag);
		cbbAnlag.setPrefWidth(150);

		// -----------------VBox 3 ---------------------------------------

		VBox vboks3 = new VBox();
		pane.add(vboks3, 2, 0);

		lbFustager = new Label("Fustager");
		vboks3.getChildren().add(lbFustager);

		lwPK = new ListView<>();
		vboks3.getChildren().add(lwPK);

		// -----------------VBox 4 ---------------------------------------

		VBox vboks4 = new VBox();
		pane.add(vboks4, 3, 0);

		lbFNavn = new Label("Fustage navn: ");
		vboks4.getChildren().add(lbFNavn);

		txfFNavn = new TextField();
		vboks4.getChildren().add(txfFNavn);

		lbFStr = new Label("Fustage størrelse: ");
		vboks4.getChildren().add(lbFStr);

		txfFStr = new TextField();
		vboks4.getChildren().add(txfFStr);

		lbAntalF = new Label("Antal fustager: ");
		vboks4.getChildren().add(lbAntalF);

		txfAntalF = new TextField();
		vboks4.getChildren().add(txfAntalF);

		lbSpace = new Label();
		vboks4.getChildren().add(lbSpace);

		btnTilfoj = new Button("Tilføj til udlejning");
		vboks4.getChildren().add(btnTilfoj);

		// -----------------HBox 1 ---------------------------------------

		HBox hboks1 = new HBox();
		pane.add(hboks1, 0, 3);
		hboks1.setAlignment(Pos.BASELINE_LEFT);

		btnOpret = new Button("Opret Reservering");
		hboks1.getChildren().add(btnOpret);

		HBox hboks2 = new HBox();
		pane.add(hboks2, 3, 3);
		hboks2.setAlignment(Pos.BASELINE_RIGHT);

		btnLuk = new Button("Luk");
		hboks2.getChildren().add(btnLuk);
		btnLuk.setOnAction(event -> btnLukAction());

	}
	
	
	public void btnLukAction(){
		hide();
	}

}
