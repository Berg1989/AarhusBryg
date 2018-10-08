package gui;

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

public class ProduktkategoriWindow extends Stage {

    public ProduktkategoriWindow() {
        initStyle(StageStyle.UTILITY);
        initModality(Modality.APPLICATION_MODAL);
        setResizable(false);
        setTitle("Administrator Window");

        GridPane pane = new GridPane();
        Scene scene = new Scene(pane);
        initContent(pane);
        setScene(scene);
    }

    private ListView<ProduktKategori> lwPKategori;
    private Button btnOpret, btnRemove, btnLuk, btnGaaTil;
    private Label lbPKategori;

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        lbPKategori = new Label("Produktkategori:");
        pane.add(lbPKategori, 0, 0);

        lwPKategori = new ListView();
        pane.add(lwPKategori, 0, 1, 1, 3);
        lwPKategori.setPrefHeight(250);
        lwPKategori.setPrefWidth(180);

        btnOpret = new Button("Opret Kategori");
        pane.add(btnOpret, 1, 1);

        btnRemove = new Button("Slet Kategori");
        pane.add(btnRemove, 1, 2);

        btnLuk = new Button("Luk");
        pane.add(btnLuk, 1, 4);

        btnGaaTil = new Button("Gå Til");
        pane.add(btnGaaTil, 0, 4);

    }

}
