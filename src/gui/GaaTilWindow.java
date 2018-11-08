package gui;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
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
import model.StedPris;
import service.Service;

public class GaaTilWindow extends Stage {

    private ProduktKategori pk;
    private Service service;

    public GaaTilWindow(ProduktKategori pk) {
        service = Service.getService();
        this.pk = pk;
        initStyle(StageStyle.UTILITY);
        initModality(Modality.APPLICATION_MODAL);
        setResizable(false);
        setTitle("Produkter");

        GridPane pane = new GridPane();
        Scene scene = new Scene(pane);
        initContent(pane);
        setScene(scene);
    }

    private ListView<Produkt> lwProdukter;
    private ListView<StedPris> lwProduktPriser;
    private Label lbPiKategori, lbNavn, lbStoerrelse, lbPris, lbStedPriser;
    private TextField txfNavn, txfStr, txfPris;
    private Button btnOpret, btnSlet, btnRediger, btnLuk, btnRedigerSP;

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        VBox vboks = new VBox();
        pane.add(vboks, 1, 1);
        vboks.setPadding(new Insets(10, 0, 0, 10));

        lbPiKategori = new Label("Produkter i kategori");
        pane.add(lbPiKategori, 0, 0);

        lwProdukter = new ListView<>();
        pane.add(lwProdukter, 0, 1);
        lwProdukter.setPrefHeight(250);
        lwProdukter.setPrefWidth(180);
        lwProdukter.getItems().addAll(pk.getProdukter());

        ChangeListener<Produkt> listener = (op, oldProduct, newProduct) -> updateControls();
        lwProdukter.getSelectionModel().selectedItemProperty().addListener(listener);

        lbNavn = new Label("Produkt navn:");
        vboks.getChildren().add(lbNavn);

        txfNavn = new TextField();
        vboks.getChildren().add(txfNavn);
        txfNavn.setEditable(false);

        lbStoerrelse = new Label("Produktets St�rrelse:");
        vboks.getChildren().add(lbStoerrelse);

        txfStr = new TextField();
        vboks.getChildren().add(txfStr);
        txfStr.setEditable(false);

        lbPris = new Label("Produkt Pris");
        vboks.getChildren().add(lbPris);

        txfPris = new TextField();
        vboks.getChildren().add(txfPris);
        txfPris.setEditable(false);

        lbStedPriser = new Label("Special priser");
        vboks.getChildren().add(lbStedPriser);

        lwProduktPriser = new ListView<>();
        vboks.getChildren().add(lwProduktPriser);
        lwProduktPriser.setMaxHeight(150);

        HBox hboks = new HBox(20);
        pane.add(hboks, 0, 2);
        hboks.setPadding(new Insets(0, 0, 0, 10));
        hboks.setAlignment(Pos.BASELINE_LEFT);

        btnOpret = new Button("Opret");
        hboks.getChildren().add(btnOpret);
        btnOpret.setOnAction(event -> btnOpretAction());

        btnRediger = new Button("Rediger");
        hboks.getChildren().add(btnRediger);
        btnRediger.setOnAction(event -> btnRedigerAction());

        btnSlet = new Button("Slet");
        hboks.getChildren().add(btnSlet);
        btnSlet.setOnAction(event -> btnSletAction());

        btnLuk = new Button("Luk");
        hboks.getChildren().add(btnLuk);
        btnLuk.setOnAction(event -> btnLukAction());

        btnRedigerSP = new Button("Rediger Special");
        vboks.getChildren().add(btnRedigerSP);
        btnRedigerSP.setOnAction(event -> btnRedigerSPAction());

    }

    // Denne metode holder listen opdateret med nye objekter

    private List<Produkt> initAllProdukter() {
        List<Produkt> list = new ArrayList<>();
        for (Produkt p : pk.getProdukter()) {
            list.add(p);
        }
        return list;
    }

    // Denne metode holder listen opdateret med nye objekter

    private List<StedPris> initAllStedPriser() {
        List<StedPris> list = new ArrayList<>();
        for (StedPris sp : lwProdukter.getSelectionModel().getSelectedItem().getStedPriser()) {
            list.add(sp);
        }
        return list;
    }

    // Denne metode er Button action for Opret Button, hvor den åbner vinduet
    // OpretProduktWindow, og venter på OpretProduktWindow er lukket, hvorefter den
    // tager alle objekter fra InitAllProdukter og tilføjer dem til Produkt
    // Listviewet

    private void btnOpretAction() {
        OpretProduktWindow opw = new OpretProduktWindow(this.pk);
        opw.showAndWait();
        lwProdukter.getItems().setAll(initAllProdukter());

    }

    // Denne metode er Button action for redigere Button, hvor den åbner vinduet
    // OpretProduktWindow, hvis man har klikket på et produkt i Produkt listviewet,
    // og venter på OpretProduktWindow er lukket, hvorefter den
    // tager alle objekter fra InitAllProdukter og tilføjer dem til Produkt
    // Listviewet
    private void btnRedigerAction() {
        if (lwProdukter.getSelectionModel().getSelectedItem() != null) {
            Produkt p = lwProdukter.getSelectionModel().getSelectedItem();
            RedigerProduktWindow rpw = new RedigerProduktWindow(this.pk, p);
            rpw.showAndWait();
            lwProdukter.getItems().setAll(initAllProdukter());
        }

    }

    // Denne metode er Button action for slet Button, hvor den sletter det objekt
    // man har klikket på i Produkt Listviewet, hvorefter den tager alle objekter
    // fra InitAllProdukter og tilføjer dem til Produkt Listviewet
    private void btnSletAction() {
        Produkt p = lwProdukter.getSelectionModel().getSelectedItem();
        service.sletProdukt(pk, p);
        lwProdukter.getItems().setAll(initAllProdukter());

    }

    // Denne metode holder TextFieldene "Navn,str,Pris" opdateret, når man klikker
    // på diverse produkter i Listviewet
    private void updateControls() {
        Produkt produkt = lwProdukter.getSelectionModel().getSelectedItem();
        lwProduktPriser.getItems().clear();
        if (produkt != null) {
            lwProduktPriser.getItems().addAll(lwProdukter.getSelectionModel().getSelectedItem().getStedPriser());
            txfNavn.setText(produkt.getNavn());
            txfStr.setText(produkt.getStr());
            txfPris.setText(Double.toString(produkt.getPris()));

        } else {
            txfNavn.clear();
            txfStr.clear();
            txfPris.clear();
        }
    }

    // Denne metode er Button action for RedigereSP Button, hvor den åbner vinduet
    // RedigereSPWindow, venter på RedigereSPWindow er lukket, hvorefter den
    // tager alle objekter fra InitAllStedPriser og tilføjer dem til ProduktPriser
    // Listviewet
    private void btnRedigerSPAction() {
        Produkt produkt = lwProdukter.getSelectionModel().getSelectedItem();
        if (produkt != null) {
            RedigerSPWindow rsp = new RedigerSPWindow(lwProdukter.getSelectionModel().getSelectedItem());
            rsp.showAndWait();
            lwProduktPriser.getItems().setAll(initAllStedPriser());
        }

    }

    // Denne metode lukker for vinduet
    private void btnLukAction() {
        hide();
    }

}
