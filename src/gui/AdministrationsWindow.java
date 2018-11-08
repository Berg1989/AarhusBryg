package gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AdministrationsWindow extends Stage {

	public AdministrationsWindow() {
		initStyle(StageStyle.UTILITY);
		initModality(Modality.APPLICATION_MODAL);
		setResizable(false);
		setTitle("Administrator Window");

		GridPane pane = new GridPane();
		Scene scene = new Scene(pane);
		initContent(pane);
		setScene(scene);
	}

	private Button btnProduktategori, btnBooking, btnSalgSted;

	private void initContent(GridPane pane) {
		pane.setPadding(new Insets(10));
		pane.setHgap(10);
		pane.setVgap(10);
		pane.setGridLinesVisible(false);

		btnProduktategori = new Button("Produktkategorier");
		pane.add(btnProduktategori, 0, 1);
		btnProduktategori.setOnAction(event -> btnProduktkategoriAction());
		btnProduktategori.setPrefSize(200, 100);

		btnBooking = new Button("Booking");
		pane.add(btnBooking, 0, 3);
		btnBooking.setOnAction(event -> btnBookingAction());
		btnBooking.setPrefSize(200, 100);

		btnSalgSted = new Button("Salgs Steder");
		pane.add(btnSalgSted, 0, 4);
		btnSalgSted.setOnAction(event -> btnSalgStedAction());
		btnSalgSted.setPrefSize(200, 100);

	}

	private void btnProduktkategoriAction() {
		ProduktkategoriWindow pk = new ProduktkategoriWindow();
		pk.showAndWait();

	}

	private void btnBookingAction() {
		BookingWindow bw = new BookingWindow();
		bw.showAndWait();
	}

	private void btnSalgStedAction() {

		SalgStedWindow ossw = new SalgStedWindow();
		ossw.showAndWait();

	}
}
