package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
import model.ProduktKategori;

public class GaaTilWindow extends Stage {

    private ProduktKategori pk;

    public GaaTilWindow(ProduktKategori pk) {
        this.pk = pk;
        initStyle(StageStyle.UTILITY);
        initModality(Modality.APPLICATION_MODAL);
        setResizable(false);
        setTitle("Administrator Window");

        GridPane pane = new GridPane();
        Scene scene = new Scene(pane);
        initContent(pane);
        setScene(scene);
    }

    private ListView<Produkt> lwProdukter;
    private Label lbPiKategori, lbNavn, lbStørrelse, lbBeskrivelse, lbPris;
    private TextField txfNavn, txfStørrelse, txfBeskrivelse, txfPris;
    private Button btnOpret, btnRemove, btnRedigere, btnLuk;

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        lbPiKategori = new Label("Produkter i kategori");
        pane.add(lbPiKategori, 0, 0);

        lwProdukter = new ListView<>();
        pane.add(lwProdukter, 0, 1);
        lwProdukter.setPrefHeight(250);
        lwProdukter.setPrefWidth(180);
        lwProdukter.getItems().addAll(pk.getProdukter());

        VBox vboks = new VBox();
        pane.add(vboks, 1, 1);
        vboks.setPadding(new Insets(10, 0, 0, 10));

        lbNavn = new Label("Produkt navn:");
        vboks.getChildren().add(lbNavn);

        txfNavn = new TextField();
        vboks.getChildren().add(txfNavn);

        lbStørrelse = new Label("Produktets Størrelse:");
        vboks.getChildren().add(lbStørrelse);

        txfStørrelse = new TextField();
        vboks.getChildren().add(txfStørrelse);

        lbBeskrivelse = new Label("Produkt beskrielse: ");
        vboks.getChildren().add(lbBeskrivelse);

        txfBeskrivelse = new TextField();
        vboks.getChildren().add(txfBeskrivelse);

        lbPris = new Label("Produkt Pris");
        vboks.getChildren().add(lbPris);

        txfPris = new TextField();
        vboks.getChildren().add(txfPris);

        HBox hboks = new HBox(20);
        pane.add(hboks, 0, 2);
        hboks.setPadding(new Insets(0, 0, 0, 10));
        hboks.setAlignment(Pos.BASELINE_LEFT);

        btnOpret = new Button("Opret");
        hboks.getChildren().add(btnOpret);

        btnRedigere = new Button("Redigere");
        hboks.getChildren().add(btnRedigere);

        btnRemove = new Button("Remove");
        hboks.getChildren().add(btnRemove);

        btnLuk = new Button("Luk");
        hboks.getChildren().add(btnLuk);

    }
}
