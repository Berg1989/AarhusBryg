package gui;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.ProduktKategori;
import model.SalgSted;
import service.Service;
import storage.Storage;

public class SalgStedWindow extends Stage {

    public SalgStedWindow() {
        initStyle(StageStyle.UTILITY);
        initModality(Modality.APPLICATION_MODAL);
        setResizable(false);
        setTitle("Administrator Window");

        GridPane pane = new GridPane();
        Scene scene = new Scene(pane);
        initContent(pane);
        setScene(scene);
    }

    private Label lbSalgSteder, lbSalgsPK, lbMuligePK;
    private ListView<SalgSted> lwSalgSteder;
    private ListView<ProduktKategori> lwSalgsPK;
    private ListView<ProduktKategori> lwMuligePK;
    private Button btnLuk, btnOpret, btnPilVenstre, btnPilHojre;

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(true);

        // ----- VBox 1 ------
        VBox vboks1 = new VBox();
        pane.add(vboks1, 0, 0);

        lbSalgSteder = new Label("Salgs steder");
        vboks1.getChildren().add(lbSalgSteder);

        lwSalgSteder = new ListView<>();
        vboks1.getChildren().add(lwSalgSteder);
        lwSalgSteder.getItems().addAll(Storage.getAllSalgSted());

        ChangeListener<SalgSted> listener = (op, oldProduct, newProduct) -> updateControls();
        lwSalgSteder.getSelectionModel().selectedItemProperty().addListener(listener);

        // ----- VBox 2 ------
        VBox vboks2 = new VBox();
        pane.add(vboks2, 1, 0);

        lbSalgsPK = new Label("Salgssted Produktkategorier");
        vboks2.getChildren().add(lbSalgsPK);

        lwSalgsPK = new ListView<>();
        vboks2.getChildren().add(lwSalgsPK);

        // lwSalgSteder.getSelectionModel().getSelectedItem().getProduktKategorier();

        // ----- VBox 3 ------
        VBox vboks3 = new VBox();
        pane.add(vboks3, 2, 0);
        vboks3.setAlignment(Pos.CENTER);

        btnPilHojre = new Button("---->");
        vboks3.getChildren().add(btnPilHojre);
        btnPilHojre.setOnAction(event -> pilHojreAction());

        btnPilVenstre = new Button("<----");
        vboks3.getChildren().add(btnPilVenstre);
        btnPilVenstre.setOnAction(event -> pilVenstreAction());

        // ----- VBox 4 ------
        VBox vboks4 = new VBox();
        pane.add(vboks4, 3, 0);

        lbMuligePK = new Label("Salgssted's mulige produktkategorier");
        vboks4.getChildren().add(lbMuligePK);

        lwMuligePK = new ListView<>();
        vboks4.getChildren().add(lwMuligePK);

        btnOpret = new Button("Opret");
        pane.add(btnOpret, 0, 1);
        btnOpret.setOnAction(event -> btnOpretAction());

        btnLuk = new Button("Luk");
        pane.add(btnLuk, 3, 1);
        btnLuk.setOnAction(event -> btnLukAction());

    }

    private List<SalgSted> initAllSalgSted() {
        List<SalgSted> list = new ArrayList<>();
        for (SalgSted ss : Storage.getAllSalgSted()) {
            list.add(ss);
        }
        return list;
    }

    private List<ProduktKategori> initAllSalgsPK() {
        List<ProduktKategori> list = new ArrayList<>();
        for (ProduktKategori pk : lwSalgSteder.getSelectionModel().getSelectedItem().getProduktKategorier()) {
            list.add(pk);
        }
        return list;
    }

    private void updateControls() {
        SalgSted ss = lwSalgSteder.getSelectionModel().getSelectedItem();
        lwSalgsPK.getItems().clear();
        lwMuligePK.getItems().clear();
        if (ss != null) {
            lwSalgsPK.getItems().addAll(lwSalgSteder.getSelectionModel().getSelectedItem().getProduktKategorier());
            lwMuligePK.getItems().addAll(Service.getMuligeKategorier(ss));
        }
    }

    private void btnOpretAction() {
        OpretSalgStedWindow ossw = new OpretSalgStedWindow();
        ossw.showAndWait();
        lwSalgSteder.getItems().setAll(initAllSalgSted());

    }

    private void btnLukAction() {
        hide();
    }

    private void pilVenstreAction() {

        Service.tilfoejKategori(lwSalgSteder.getSelectionModel().getSelectedItem(),
                lwMuligePK.getSelectionModel().getSelectedItem());
        lwSalgsPK.getItems().addAll(initAllSalgsPK());
        updateControls();

    }

    private void pilHojreAction() {
        lwSalgSteder.getSelectionModel().getSelectedItem()
                .removeProduktKategori(lwSalgsPK.getSelectionModel().getSelectedItem());
        lwSalgsPK.getItems().addAll(initAllSalgsPK());
        updateControls();
    }

}
