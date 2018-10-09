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

public class OpretKlippeKortWindow extends Stage {

    public OpretKlippeKortWindow() {

        initStyle(StageStyle.UTILITY);
        initModality(Modality.APPLICATION_MODAL);
        setResizable(false);
        setTitle("Administrator Window");

        GridPane pane = new GridPane();
        Scene scene = new Scene(pane);
        initContent(pane);
        setScene(scene);
    }

    private Label lbNavn, lbAntalKlip, lbPris;
    private TextField txfNavn, txfAntalKlip, txfPris;
    private Button btnOpret, btnLuk;

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        lbNavn = new Label("Klippekort navn: ");
        pane.add(lbNavn, 0, 0);

        txfNavn = new TextField();
        pane.add(txfNavn, 1, 0);

        lbAntalKlip = new Label("Antal Klip: ");
        pane.add(lbAntalKlip, 0, 1);

        txfAntalKlip = new TextField();
        pane.add(txfAntalKlip, 1, 1);

        lbPris = new Label("Produkt Pris:");
        pane.add(lbPris, 0, 2);

        txfPris = new TextField();
        pane.add(txfPris, 1, 2);

        btnOpret = new Button("Opret");
        pane.add(btnOpret, 0, 3);
        btnOpret.setOnAction(event -> btnOpretAction());

        btnLuk = new Button("Luk");
        pane.add(btnLuk, 1, 3);
        btnLuk.setOnAction(event -> btnLukAction());

    }

    private void btnOpretAction() {
        String navn = txfNavn.getText().trim();
        int antal = Integer.parseInt(txfAntalKlip.getText().trim());
        Double pris = Double.parseDouble(txfPris.getText().trim());
        Service.getService();
        Service.opretKlippeKort(navn, antal, pris);
        hide();
    }

    private void btnLukAction() {
        hide();
    }

}
