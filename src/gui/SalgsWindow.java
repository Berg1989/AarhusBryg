package gui;

import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Produkt;
import model.ProduktKategori;
import model.SalgSted;
import storage.Storage;

public class SalgsWindow extends Stage {

    public SalgsWindow() {

        initStyle(StageStyle.UTILITY);
        initModality(Modality.APPLICATION_MODAL);
        setResizable(false);
        setTitle("Administrator Window");

        GridPane pane = new GridPane();
        Scene scene = new Scene(pane);
        initContent(pane);
        setScene(scene);

    }

    private Label lbSS, lbPK, lbP, lbAntal, lbTP, lbTilfojet;
    private TextField txfAntal, txfTP;
    private ListView<SalgSted> lwSS;
    private ListView<ProduktKategori> lwPK;
    private ListView<Produkt> lwP;
    private ListView<?> lwTiltojet;
    private Button btnPilVenstre, btnPilHojre, btnLuk, btnVidere;

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        // ---------- VBox 1 ----------------

        VBox vboks1 = new VBox();
        pane.add(vboks1, 0, 0);

        lbSS = new Label("Produktkategorier: ");
        vboks1.getChildren().add(lbSS);

        lwSS = new ListView<>();
        vboks1.getChildren().add(lwSS);
        lwSS.setMaxWidth(100);
        lwSS.getItems().addAll(Storage.getAllSalgSted());

        ChangeListener<SalgSted> listener = (op, oldProduct, newProduct) -> updateControls();
        lwSS.getSelectionModel().selectedItemProperty().addListener(listener);

        // ---------- VBox 2 ----------------

        VBox vboks2 = new VBox();
        pane.add(vboks2, 1, 0);

        lbPK = new Label("Produktkategorier");
        vboks2.getChildren().add(lbPK);

        lwPK = new ListView<>();
        vboks2.getChildren().add(lwPK);
        lwPK.setMaxWidth(150);

        ChangeListener<ProduktKategori> listener1 = (op, oldProduct, newProduct) -> updateControlsTwo();
        lwPK.getSelectionModel().selectedItemProperty().addListener(listener1);

        // ---------- VBox 3 ----------------

        VBox vboks3 = new VBox();
        pane.add(vboks3, 2, 0);

        lbP = new Label("Produkter");
        vboks3.getChildren().add(lbP);

        lwP = new ListView<>();
        vboks3.getChildren().add(lwP);
        lwP.setMaxWidth(220);

        // ---------- VBox 4 ----------------

        VBox vboks4 = new VBox(10);
        pane.add(vboks4, 3, 0);

        lbAntal = new Label("Antal");
        vboks4.getChildren().add(lbAntal);

        txfAntal = new TextField();
        vboks4.getChildren().add(txfAntal);
        txfAntal.setMaxWidth(50);

        btnPilHojre = new Button("--->");
        vboks4.getChildren().add(btnPilHojre);

        btnPilVenstre = new Button("<---");
        vboks4.getChildren().add(btnPilVenstre);

        // ---------- VBox 5 ----------------

        VBox vboks5 = new VBox(10);
        pane.add(vboks5, 4, 0);

        lbTilfojet = new Label("Tilføjet til køblist");
        vboks5.getChildren().add(lbTilfojet);

        lwTiltojet = new ListView<>();
        vboks5.getChildren().add(lwTiltojet);

        lbTP = new Label("Total Pris");
        vboks5.getChildren().add(lbTP);

        txfTP = new TextField();
        vboks5.getChildren().add(txfTP);
        txfTP.setEditable(false);

        // ---------- HBox 1 ----------------

        btnLuk = new Button("Luk");
        pane.add(btnLuk, 0, 1);

        // ---------- HBox 2 ----------------

        btnVidere = new Button("Videre");
        pane.add(btnVidere, 4, 1);

    }

    private void updateControls() {
        SalgSted ss = lwSS.getSelectionModel().getSelectedItem();
        lwPK.getItems().clear();
        if (ss != null) {
            lwPK.getItems().addAll(ss.getProduktKategorier());
        }
    }

    private void updateControlsTwo() {
        ProduktKategori pk = lwPK.getSelectionModel().getSelectedItem();
        lwP.getItems().clear();
        if (pk != null) {
            lwP.getItems().addAll(pk.getProdukter());
        }
    }

}