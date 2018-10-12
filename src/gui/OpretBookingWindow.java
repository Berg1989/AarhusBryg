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

        btnReserver = new Button("Reserver");
        pane.add(btnReserver, 0, 1);
//        btnReserver.setOnAction(event -> btnRundvisningAction());
        btnReserver.setPrefSize(200, 100);

        btnLuk = new Button("Luk");
        pane.add(btnLuk, 0, 2);
        btnLuk.setOnAction(event -> btnLukAction());
        btnLuk.setPrefSize(200, 100);

    }
    
    private void btnLukAction(){
    	hide();
    }
}
