package gui;

import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.Scene;
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

    private Label lbSS, lbPK, lbP, lbPris, lbAntal;
    private TextField txfPris, txfAntal;
    private ListView<SalgSted> lwSS;
    private ListView<ProduktKategori> lwPK;
    private ListView<Produkt> lwP;

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

        // ---------- VBox 3 ----------------

        VBox vboks3 = new VBox();
        pane.add(vboks3, 2, 0);

        lbP = new Label("Produkter");
        vboks3.getChildren().add(lbP);

        lwP = new ListView<>();
        vboks3.getChildren().add(lwP);

        // ---------- VBox 4 ----------------

        VBox vboks4 = new VBox();
        pane.add(vboks4, 3, 0);

        lbPris = new Label("Produkt pris");
        vboks4.getChildren().add(lbPris);

        txfPris = new TextField();
        vboks4.getChildren().add(txfPris);

        lbAntal = new Label("Antal");
        vboks4.getChildren().add(lbAntal);

        txfAntal = new TextField();
        vboks4.getChildren().add(txfAntal);

    }

    private void updateControls() {
        SalgSted ss = lwSS.getSelectionModel().getSelectedItem();
        ProduktKategori pk = lwPK.getSelectionModel().getSelectedItem();
        lwPK.getItems().clear();

        if (ss != null) {
            lwPK.getItems().addAll(lwSS.getSelectionModel().getSelectedItem().getProduktKategorier());
            lwP.getItems().addAll(lwPK.getSelectionModel().getSelectedItem().getProdukter());

        }
    }

}
