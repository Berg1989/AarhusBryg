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
import model.KlippeKort;
import service.Service;
import storage.Storage;

public class KlippeKortWindow extends Stage {

    public KlippeKortWindow() {
        initStyle(StageStyle.UTILITY);
        initModality(Modality.APPLICATION_MODAL);
        setResizable(false);
        setTitle("Klippekort");

        GridPane pane = new GridPane();
        Scene scene = new Scene(pane);
        initContent(pane);
        setScene(scene);
    }

    private ListView<KlippeKort> lwKlippeKort;
    private Label lbMuligekort, lbNavn, lbAntalKlip, lbPris;
    private TextField txfNavn, txfAntalKlip, txfPris;
    private Button btnOpret, btnLuk, btnSlet;

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        lbMuligekort = new Label("Produkter i kategori");
        pane.add(lbMuligekort, 0, 0);

        lwKlippeKort = new ListView<>();
        pane.add(lwKlippeKort, 0, 1);
        lwKlippeKort.setPrefHeight(250);
        lwKlippeKort.setPrefWidth(180);
        lwKlippeKort.getItems().addAll(Storage.getAllKlippeKort());

        ChangeListener<KlippeKort> listener = (op, oldProduct, newProduct) -> updateControls();
        lwKlippeKort.getSelectionModel().selectedItemProperty().addListener(listener);

        VBox vboks = new VBox();
        pane.add(vboks, 1, 1);
        vboks.setPadding(new Insets(10, 0, 0, 10));

        lbNavn = new Label("Klippekort navn: ");
        vboks.getChildren().add(lbNavn);

        txfNavn = new TextField();
        vboks.getChildren().add(txfNavn);

        lbAntalKlip = new Label("Antal Klip: ");
        vboks.getChildren().add(lbAntalKlip);

        txfAntalKlip = new TextField();
        vboks.getChildren().add(txfAntalKlip);

        lbPris = new Label("Pris");
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

        btnSlet = new Button("Slet");
        hboks.getChildren().add(btnSlet);
        btnSlet.setOnAction(event -> btnSletAction());

        btnLuk = new Button("Luk");
        hboks.getChildren().add(btnLuk);
        btnLuk.setOnAction(event -> btnLukAction());

    }

    private List<KlippeKort> initAllProdukter() {
        List<KlippeKort> list = new ArrayList<>();
        for (KlippeKort k : Storage.getAllKlippeKort()) {
            list.add(k);
        }
        return list;
    }

    private void btnOpretAction() {
        OpretKlippeKortWindow opw = new OpretKlippeKortWindow();
        opw.showAndWait();
        lwKlippeKort.getItems().setAll(initAllProdukter());

    }

    private void btnSletAction() {
        KlippeKort k = lwKlippeKort.getSelectionModel().getSelectedItem();
        Service.getService();
        Service.sletKlippeKort(k);
        lwKlippeKort.getItems().setAll(initAllProdukter());

    }
    
    private void btnLukAction(){
    	hide();
    }

    private void updateControls() {
        KlippeKort k = lwKlippeKort.getSelectionModel().getSelectedItem();
        if (k != null) {
            txfNavn.setText(k.getNavn());
            txfAntalKlip.setText(Integer.toString(k.getAntalKlip()));
            txfPris.setText(Double.toString(k.getPris()));

        } else {
            txfNavn.clear();
            txfAntalKlip.clear();
            txfPris.clear();
        }
    }
}
