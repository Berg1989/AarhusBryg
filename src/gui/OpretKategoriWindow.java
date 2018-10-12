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

public class OpretKategoriWindow extends Stage {

    public OpretKategoriWindow() {
        initStyle(StageStyle.UTILITY);
        initModality(Modality.APPLICATION_MODAL);
        setResizable(false);
        setTitle("Opret Kategori Window");

        GridPane pane = new GridPane();
        Scene scene = new Scene(pane);
        initContent(pane);
        setScene(scene);
    }

    private Label lbKategoriNavn;
    private TextField txfKategoriNavn;
    private Button btnOpret, btnLuk;

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        lbKategoriNavn = new Label("Navn på kategori:");
        pane.add(lbKategoriNavn, 0, 0);

        txfKategoriNavn = new TextField();
        pane.add(txfKategoriNavn, 0, 1);

        btnOpret = new Button("Opret");
        pane.add(btnOpret, 0, 2);
        btnOpret.setOnAction(event -> btnOpretAction());

        btnLuk = new Button("Luk");
        pane.add(btnLuk, 1, 2);
        btnLuk.setOnAction(event -> btnLukAction());
    }

    private void btnOpretAction() {
        String navn = txfKategoriNavn.getText().trim();
        Service.getService();
        Service.opretProduktKategori(navn);
        hide();

    }

    private void btnLukAction() {
        hide();

    }

}
