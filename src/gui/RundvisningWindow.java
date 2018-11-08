package gui;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Rundvisning;
import service.Service;

public class RundvisningWindow extends Stage {
    private Service service;

    public RundvisningWindow() {
        service = Service.getService();
        initStyle(StageStyle.UTILITY);
        initModality(Modality.APPLICATION_MODAL);
        setResizable(false);
        setTitle("Booking");

        GridPane pane = new GridPane();
        Scene scene = new Scene(pane);
        initContent(pane);
        setScene(scene);
        pane.setGridLinesVisible(false);
    }

    private ListView<Rundvisning> lwRundvisning;
    private Button btnOpret, btnSlet, btnLuk, btnBetal, btnUpdate;
    private Label lbBookings, lblName, lblDato, lblAntal, lblAntalS, lblTid, lblStuderende, lblSpisende;
    private Rundvisning rv;
    private TextField txfName, txfDato, txfAntal, txfAntalS, txfTid;
    private CheckBox cbxSpisende, cbxStuderende;

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);

        lbBookings = new Label("Rundvisninger:");
        pane.add(lbBookings, 0, 0);

        lwRundvisning = new ListView<>();
        pane.add(lwRundvisning, 0, 1, 1, 6);
        lwRundvisning.setPrefHeight(50);
        lwRundvisning.getItems().addAll(service.getAllRundvisninger()); // check me
        lwRundvisning.setOnMouseClicked(event -> {
            rv = lwRundvisning.getSelectionModel().getSelectedItem();
            if (rv == null) {
                // do nothing
            } else {
                // do something
                txfName.setText(rv.getKunde());
                txfDato.setText(rv.getDato().toString());
                txfAntal.setText("" + rv.getAntalGaester());
                txfTid.setText(rv.getTid().toString());
                if (rv.hasSpisning()) {
                    cbxSpisende.setSelected(true);
                    txfAntalS.setText("" + rv.antalSpisende());
                    txfAntalS.setEditable(true);
                } else {
                    cbxSpisende.setSelected(true);
                    txfAntalS.setText("");
                    txfAntalS.setEditable(false);
                }
                if (rv.isStuderende()) {
                    cbxStuderende.setSelected(true);
                } else {
                    cbxStuderende.setSelected(false);
                }
                if (!rv.isBetalt()) {
                    Label l = new Label("IKKE BETALT");
                    l.setTextFill(Color.RED);
                    pane.add(l, 2, 7, 4, 1);
                }
            }
        });

        btnOpret = new Button("Opret Rundvisning");
        pane.add(btnOpret, 0, 7);
        btnOpret.setOnAction(event -> btnOpretAction());

        btnSlet = new Button("Slet Rundvisning");
        pane.add(btnSlet, 0, 8);
        btnSlet.setOnAction(event -> btnSletAction());

        btnBetal = new Button("Betal");
        pane.add(btnBetal, 3, 8);
        // btnBetal.setOnAction(event -> btnBetalAction());

        btnLuk = new Button("Luk");
        pane.add(btnLuk, 5, 8);
        btnLuk.setOnAction(event -> btnLukAction());

        btnUpdate = new Button("Opdater");
        pane.add(btnUpdate, 1, 8);
        btnUpdate.setOnAction(event -> btnUpdateAction());

        // NEW
        lblName = new Label("Kunde");
        pane.add(lblName, 1, 1);

        txfName = new TextField();
        pane.add(txfName, 2, 1, 4, 1);

        lblDato = new Label("Dato");
        pane.add(lblDato, 1, 2);

        txfDato = new TextField();
        pane.add(txfDato, 2, 2, 4, 1);

        lblTid = new Label("Tid");
        pane.add(lblTid, 1, 3);

        txfTid = new TextField();
        pane.add(txfTid, 2, 3, 4, 1);

        lblAntal = new Label("Antal");
        pane.add(lblAntal, 1, 4);

        txfAntal = new TextField();
        pane.add(txfAntal, 2, 4, 4, 1);

        cbxSpisende = new CheckBox();
        pane.add(cbxSpisende, 2, 5);
        cbxSpisende.setOnAction(event -> selectedSpisning());

        lblSpisende = new Label("Spisende");
        pane.add(lblSpisende, 3, 5);

        cbxStuderende = new CheckBox();
        pane.add(cbxStuderende, 4, 5);

        lblStuderende = new Label("Studerende");
        pane.add(lblStuderende, 5, 5);

        lblAntalS = new Label("Spisende");
        pane.add(lblAntalS, 1, 6);

        txfAntalS = new TextField();
        pane.add(txfAntalS, 2, 6, 4, 1);

    }

    private List<Rundvisning> initAllProdukter() {
        List<Rundvisning> list = new ArrayList<>();
        for (Rundvisning r : service.getAllRundvisninger()) {
            list.add(r);
        }
        return list;
    }

    // Denne metode er en buttom Action for Opret Rundvisning, hvor den åbner for
    // OpretRundvisningWindow og venter på windowet er lukket, hvorefter den lukker
    // RundvisningWindow ned.
    private void btnOpretAction() {
        OpretRundvisningWindow orw = new OpretRundvisningWindow();
        orw.showAndWait();
        hide();
    }

    // Denne metode lukker for vinduet
    private void btnLukAction() {
        hide();
    }

    // Denne metode er en button action for Slet, hvor den sletter den rundvisning,
    // du har klikket på i listviewet. Hvorefter den
    // tager alle objekter fra InitAllProdukter og tilføjer dem til Rundvisning
    // Listviewet
    private void btnSletAction() {
        Rundvisning r = lwRundvisning.getSelectionModel().getSelectedItem();
        service.sletRundvisning(r);
        lwRundvisning.getItems().setAll(initAllProdukter());

    }

    // Made help??
    private void btnUpdateAction() {
        if (!(rv == null)) {
            String navn = txfName.getText().trim();
            String tid = txfTid.getText();
            String dato = txfDato.getText();
            String antal = txfAntal.getText();
            String antalS = txfAntalS.getText();

            if (!(navn.equals(rv.getKunde()))) {
                rv.setKunde(navn);
            }
            if (!(tid.equals(rv.getTid().toString()))) {
                try {
                    LocalTime t = LocalTime.parse(tid);
                    rv.setTid(t);
                } catch (Exception e) {
                    System.out.println("You shall not parse");
                }
            }
            if (!(dato.equals(rv.getDato().toString()))) {
                try {
                    LocalDate d = LocalDate.parse(dato);
                    rv.setDato(d);
                } catch (Exception e) {
                    System.out.println("You shall not parse");
                }
            }
            if (!(antal.equals(rv.getAntalGaester() + ""))) {
                try {
                    int a = Integer.parseInt(antal);
                    rv.setAntalGaester(a);
                } catch (Exception e) {
                    System.out.println("I am a servant of the Secret Fire, wielder of the Flame of Anor");
                }
            }

            if (cbxSpisende.isSelected()) {
                if (txfAntalS.getText().length() > 0) {
                    try {
                        int aS = Integer.parseInt(antalS);
                        rv.tilmeldSpsning(aS);
                    } catch (Exception e) {
                        System.out.println("Fly, you fools");
                    }
                } else {
                    rv.tilmeldSpisning();
                }
            } else {
                rv.frameldSpisning();
                txfAntalS.setText("" + rv.getAntalGaester());
            }

            if (cbxStuderende.isSelected()) {
                rv.setStuderende(true);
            } else {
                rv.setStuderende(false);
            }

        }
    }

    // Wuut? Hvorfor er den her??
    private void selectedSpisning() {
        if (cbxSpisende.isSelected()) {
            txfAntalS.setEditable(true);
        } else {
            txfAntalS.setEditable(false);
        }
    }

}
