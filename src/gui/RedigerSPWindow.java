package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Produkt;
import model.SalgSted;
import service.Service;
import storage.Storage;

public class RedigerSPWindow extends Stage {

    private Produkt p;

    public RedigerSPWindow(Produkt p) {

        this.p = p;

        initStyle(StageStyle.UTILITY);
        initModality(Modality.APPLICATION_MODAL);
        setResizable(false);
        setTitle("Special Pris");

        GridPane pane = new GridPane();
        Scene scene = new Scene(pane);
        initContent(pane);
        setScene(scene);
    }

    private Label lbGammelP, lbNyP, lbSalgsSted;
    private TextField txfNyP, txfGammelP;
    private Button btnRedigere, btnLuk;
    private ComboBox<SalgSted> cbSalgsSted;

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        VBox vboks = new VBox();
        pane.add(vboks, 0, 0);

        lbSalgsSted = new Label("Salgs sted");
        vboks.getChildren().add(lbSalgsSted);

        cbSalgsSted = new ComboBox<>();
        vboks.getChildren().add(cbSalgsSted);
        cbSalgsSted.getItems().addAll(Storage.getAllSalgSted());

        lbNyP = new Label("Ny Pris");
        vboks.getChildren().add(lbNyP);

        txfNyP = new TextField();
        vboks.getChildren().add(txfNyP);

        HBox hboks = new HBox(20);
        pane.add(hboks, 0, 2);
        hboks.setPadding(new Insets(0, 0, 0, 10));
        hboks.setAlignment(Pos.BASELINE_LEFT);

        btnRedigere = new Button("Rediger");
        hboks.getChildren().add(btnRedigere);
        btnRedigere.setOnAction(event -> btnRedigerAction());

        btnLuk = new Button("Luk");
        hboks.getChildren().add(btnLuk);
        btnLuk.setOnAction(event -> btnLukAction());

    }

    private void btnRedigerAction() {
        if (p.stedPris(cbSalgsSted.getSelectionModel().getSelectedItem()) == null) {
            Service.opretStedPris(cbSalgsSted.getSelectionModel().getSelectedItem(), p,
                    Double.parseDouble(txfNyP.getText().trim()));
        } else {
            p.stedPris(cbSalgsSted.getSelectionModel().getSelectedItem())
                    .setPris(Double.parseDouble(txfNyP.getText().trim()));
        }

        hide();
    }

    private void btnLukAction() {
        hide();
    }
}
