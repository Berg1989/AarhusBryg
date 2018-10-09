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

public class OpretProduktWindow extends Stage {
    public OpretProduktWindow() {
        initStyle(StageStyle.UTILITY);
        initModality(Modality.APPLICATION_MODAL);
        setResizable(false);
        setTitle("Opret produkt window");

        GridPane pane = new GridPane();
        Scene scene = new Scene(pane);
        initContent(pane);
        setScene(scene);
    }

    private Label lbNavn, lbStr, lbPris;
    private TextField txfNavn, txfStr, txfPris;
    private Button btnOpret, btnLuk;

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        lbNavn = new Label("Produkt navn:");
        pane.add(lbNavn, 0, 0);

        txfNavn = new TextField();
        pane.add(txfNavn, 1, 0);

        lbStr = new Label("Produktets Størrelse:");
        pane.add(lbStr, 0, 1);

        txfStr = new TextField();
        pane.add(txfStr, 1, 1);

        lbPris = new Label("Produkt Pris:");
        pane.add(lbPris, 0, 2);

        txfPris = new TextField();
        pane.add(txfPris, 1, 2);

    }

}
