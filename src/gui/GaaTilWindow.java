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
import service.Service;

public class GaaTilWindow extends Stage {

    private ProduktKategori pk;

    public GaaTilWindow(ProduktKategori pk) {
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
    private Label lbPiKategori, lbNavn, lbStoerrelse, lbPris;
    private TextField txfNavn, txfStr, txfPris;
    private Button btnOpret, btnSlet, btnRediger, btnLuk;

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

        ChangeListener<Produkt> listener = (op, oldProduct, newProduct) -> updateControls();
        lwProdukter.getSelectionModel().selectedItemProperty().addListener(listener);

        VBox vboks = new VBox();
        pane.add(vboks, 1, 1);
        vboks.setPadding(new Insets(10, 0, 0, 10));

        lbNavn = new Label("Produkt navn:");
        vboks.getChildren().add(lbNavn);

        txfNavn = new TextField();
        vboks.getChildren().add(txfNavn);

        lbStoerrelse = new Label("Produktets Størrelse:");
        vboks.getChildren().add(lbStoerrelse);

        txfStr = new TextField();
        vboks.getChildren().add(txfStr);

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

    }

    private List<Produkt> initAllProdukter() {
        List<Produkt> list = new ArrayList<>();
        for (Produkt p : pk.getProdukter()) {
            list.add(p);
        }
        return list;
    }

    private void btnOpretAction() {
        OpretProduktWindow opw = new OpretProduktWindow(this.pk);
        opw.showAndWait();
        lwProdukter.getItems().setAll(initAllProdukter());

    }

    private void btnRedigerAction() {
        if (lwProdukter.getSelectionModel().getSelectedItem() != null) {
            Produkt p = lwProdukter.getSelectionModel().getSelectedItem();
            RedigerProduktWindow rpw = new RedigerProduktWindow(this.pk, p);
            rpw.showAndWait();
            lwProdukter.getItems().setAll(initAllProdukter());
        }

    }

    private void btnSletAction() {
        Produkt p = lwProdukter.getSelectionModel().getSelectedItem();
        Service.getService();
        Service.sletProdukt(this.pk, p);
        lwProdukter.getItems().setAll(initAllProdukter());

    }

    private void updateControls() {
        Produkt produkt = lwProdukter.getSelectionModel().getSelectedItem();
        if (produkt != null) {
            txfNavn.setText(produkt.getNavn());
            txfStr.setText(produkt.getStr());
            txfPris.setText(Double.toString(produkt.getPris()));

        } else {
            txfNavn.clear();
            txfStr.clear();
            txfPris.clear();
        }
    }
    
    private void btnLukAction(){
    	hide();
    }

}
