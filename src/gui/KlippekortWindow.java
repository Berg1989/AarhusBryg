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
import model.KlippeKort;

public class KlippekortWindow extends Stage {

    public KlippekortWindow() {
        initStyle(StageStyle.UTILITY);
        initModality(Modality.APPLICATION_MODAL);
        setResizable(false);
        setTitle("Administrator Window");

        GridPane pane = new GridPane();
        Scene scene = new Scene(pane);
        initContent(pane);
        setScene(scene);
    }

    private Label lbMuligKlip, lbAntalKlip, lbPris;
    private ListView<KlippeKort> lwKlippekort;
    private Button btnOpret, btnSlet, btnLuk;

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

    }
}
