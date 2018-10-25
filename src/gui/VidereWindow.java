package gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Salg;
import service.Service;

public class VidereWindow extends Stage {
    private Service service;
    private Salg s;

    public VidereWindow(Salg s) {
        service = Service.getService();

        initStyle(StageStyle.UTILITY);
        initModality(Modality.APPLICATION_MODAL);
        setResizable(false);
        setTitle("Salgslinie Window");

        GridPane pane = new GridPane();
        Scene scene = new Scene(pane);
        initContent(pane);
        setScene(scene);

    }

    private ListView<?> lwTilFojet;
    private Label lbTF, lbTP, lbPD, lbTPNy;
    private TextField txfTP, txfTPNy;
    private CheckBox cbBP;
    private ComboBox<?> cbbPD;
    private Button btnLuk, btnGTB;

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        // ---------- VBox 1 ----------------

        VBox vboks1 = new VBox();
        pane.add(vboks1, 0, 0);

        lbTF = new Label("Tilføjet produkter");
        vboks1.getChildren().add(lbTF);

        lwTilFojet = new ListView<>();
        vboks1.getChildren().add(lwTilFojet);

        // ---------- VBox 2 ----------------

        VBox vboks2 = new VBox();
        pane.add(vboks2, 1, 0);

        lbTP = new Label("Total pris");
        vboks2.getChildren().add(lbTP);

        txfTP = new TextField();
        vboks2.getChildren().add(txfTP);

        cbBP = new CheckBox("Bestemt pris");
        vboks2.getChildren().add(cbBP);

        lbPD = new Label("Procent discount");
        vboks2.getChildren().add(lbPD);

        cbbPD = new ComboBox<>();
        vboks2.getChildren().add(cbbPD);

        lbTPNy = new Label("Ny total pris");
        vboks2.getChildren().add(lbTPNy);

        txfTPNy = new TextField();
        vboks2.getChildren().add(txfTPNy);

        btnLuk = new Button("Luk");
        pane.add(btnLuk, 0, 1);

        btnGTB = new Button("Gå til Betaling");
        pane.add(btnGTB, 1, 1);

    }

}
