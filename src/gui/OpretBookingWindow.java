package gui;

import java.awt.TextField;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class OpretBookingWindow extends Stage {

    public OpretBookingWindow() {
        initStyle(StageStyle.UTILITY);
        initModality(Modality.APPLICATION_MODAL);
        setResizable(false);
        setTitle("Booking");

        GridPane pane = new GridPane();
        Scene scene = new Scene(pane);
        initContent(pane);
        setScene(scene);
    }
    
    private DatePicker dpRundvisning;
    private ComboBox cbMuligheder;
    private TextField tfTid, tfAntal, tfMadAntal, tfPris;
    private Button btnReserver, btnLuk;
    
    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        btnRundvisning = new Button("Rundvisning");
        pane.add(btnRundvisning, 0, 1);
        btnRundvisning.setOnAction(event -> btnRundvisningAction());
        btnRundvisning.setPrefSize(200, 100);

        btnAnlaeg = new Button("Anlæg");
        pane.add(btnAnlaeg, 0, 2);
        btnAnlaeg.setOnAction(event -> btnAnlaegAction());
        btnAnlaeg.setPrefSize(200, 100);

    }
}
