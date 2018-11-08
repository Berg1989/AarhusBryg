package gui;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.ProduktKategori;
import service.Service;

public class ProduktkategoriWindow extends Stage {
    private Service service;

    public ProduktkategoriWindow() {
        service = Service.getService();
        initStyle(StageStyle.UTILITY);
        initModality(Modality.APPLICATION_MODAL);
        setResizable(false);
        setTitle("Produktkategori Window");

        GridPane pane = new GridPane();
        Scene scene = new Scene(pane);
        initContent(pane);
        setScene(scene);
    }

    private ListView<ProduktKategori> lwPKategori;
    private Button btnOpret, btnRemove, btnLuk, btnGaaTil;
    private Label lbPKategori;
    private ProduktKategori pk;

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        lbPKategori = new Label("Produktkategori:");
        pane.add(lbPKategori, 0, 0);

        lwPKategori = new ListView<>();
        pane.add(lwPKategori, 0, 1, 1, 3);
        lwPKategori.setPrefHeight(250);
        lwPKategori.setPrefWidth(180);
        lwPKategori.getItems().addAll(service.getAllProduktKategorier());

        btnOpret = new Button("Opret Kategori");
        pane.add(btnOpret, 1, 1);
        btnOpret.setOnAction(event -> btnOpretAction());

        btnRemove = new Button("Slet Kategori");
        pane.add(btnRemove, 1, 2);
        btnRemove.setOnAction(event -> btnRemoveAction());

        btnLuk = new Button("Luk");
        pane.add(btnLuk, 1, 4);
        btnLuk.setOnAction(event -> btnLukAction());

        btnGaaTil = new Button("G� Til");
        pane.add(btnGaaTil, 0, 4);
        btnGaaTil.setOnAction(event -> btnGaaTilAction());

    }

    // Denne metode holder listen opdateret med nye objekter
    private List<ProduktKategori> initAllProdukter() {
        List<ProduktKategori> list = new ArrayList<>();
        for (ProduktKategori pk : service.getAllProduktKategorier()) {
            list.add(pk);
        }
        return list;
    }

    // Denne metode er Button action for Opret Kategori Button, hvor den åbner
    // vinduet
    // OpretKategoriWindow, og venter på OpretKategoriWindow er lukket, hvorefter
    // den
    // tager alle objekter fra InitAllProdukter og tilføjer dem til Produkt
    // Listviewet
    private void btnOpretAction() {
        OpretKategoriWindow okw = new OpretKategoriWindow();
        okw.showAndWait();
        lwPKategori.getItems().setAll(initAllProdukter());

    }

    // Denne metode er Button action for slet Button, hvor den sletter det objekt
    // man har klikket på i Produktkategori Listviewet, hvorefter den tager alle
    // objekter
    // fra InitAllProdukter og tilføjer dem til Produktkategori Listviewet
    private void btnRemoveAction() {
        service.sletProduktKategori(lwPKategori.getSelectionModel().getSelectedItem());
        lwPKategori.getItems().setAll(initAllProdukter());
    }

    // Denne metode er Button action for Gaa til Button, hvor den åbner
    // vinduet GaaTilWindow, hvis man har klikket på en produktkategori fra
    // Listviewet. Hvorefter den venter på GaaTilWindow er lukket.
    private void btnGaaTilAction() {
        if (lwPKategori.getSelectionModel().getSelectedItem() != null) {
            pk = lwPKategori.getSelectionModel().getSelectedItem();
            GaaTilWindow gtw = new GaaTilWindow(pk);
            gtw.showAndWait();
        }
    }

    // Denne metode lukker for vinduet
    private void btnLukAction() {
        hide();

    }

}
