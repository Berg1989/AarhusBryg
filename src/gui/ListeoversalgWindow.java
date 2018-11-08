package gui;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Salg;
import service.Service;

public class ListeoversalgWindow extends Stage {

	private Service service;

	public ListeoversalgWindow() {

		service = Service.getService();

		initStyle(StageStyle.UTILITY);
		initModality(Modality.APPLICATION_MODAL);
		setResizable(false);
		setTitle("Liste over salg");

		GridPane pane = new GridPane();
		Scene scene = new Scene(pane);
		initContent(pane);
		setScene(scene);

	}

	private DatePicker startDate, endDate;
	private Label lbDate;
	private Button btnGetListe, btnLuk;

	private void initContent(GridPane pane) {
		pane.setPadding(new Insets(10));
		pane.setHgap(10);
		pane.setVgap(10);
		pane.setGridLinesVisible(false);

		lbDate = new Label("Vælg de to dato'er som du vil have data imellem");
		pane.add(lbDate, 0, 0);

		startDate = new DatePicker();
		pane.add(startDate, 0, 1);

		endDate = new DatePicker();
		pane.add(endDate, 0, 2);

		HBox hboks = new HBox(40);
		pane.add(hboks, 0, 3);

		btnGetListe = new Button("List over Salg");
		hboks.getChildren().add(btnGetListe);
		btnGetListe.setOnAction(event -> btnGetListeAction());

		btnLuk = new Button("Luk");
		hboks.getChildren().add(btnLuk);
		btnLuk.setOnAction(event -> btnLukAction());
	}

	// Denne metode er til at generere en fil.txt. Hvor man vaelger 2 dato'ere og
	// traekker data'en som er imellem de valgte datoer, og tilfoejer det til en
	// textfil
	private void btnGetListeAction() {
		LocalDate start = startDate.getValue().minusDays(1);
		LocalDate end = endDate.getValue().plusDays(1);

		if (start.isBefore(end)) {
			try {
				String fileName = "../../List.txt";
				PrintWriter printwriter = new PrintWriter(fileName);
				Scanner scan = new Scanner(System.in);
				List<Salg> salg = service.getAllSalg();

				for (int i = 0; i < salg.size(); i++) {
					if (salg.get(i).getDato().isAfter(start) && salg.get(i).getDato().isBefore(end)) {
						printwriter.println("Salg Nummer" + service.getAllSalg().get(i).getProdukter());
					}

				}
				printwriter.flush();
				printwriter.close();
				scan.close();
				System.out.println("Salg over List er blevet lavet");
			} catch (IOException e) {
				System.out.println(e.getMessage());

			}
		}
	}

	// Denne metode lukker for vinduet
	private void btnLukAction() {
		hide();
	}

}
