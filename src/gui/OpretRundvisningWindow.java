package gui;

import java.time.LocalDate;
import java.time.LocalTime;

import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import model.Rundvisning;
import service.Service;

public class OpretRundvisningWindow extends Stage {
    private Service service;

    public OpretRundvisningWindow() {
        service = Service.getService();
        initStyle(StageStyle.UTILITY);
        initModality(Modality.APPLICATION_MODAL);
        setResizable(false);
        setTitle("Opretning af Rundvisning");

        GridPane pane = new GridPane();
        Scene scene = new Scene(pane);
        initContent(pane);
        setScene(scene);
    }

    private DatePicker dp;
    private Label lbDatePicker, lbAntalS, lbTidspunkt, lbAntal, lbTotalPris, lbKundeNavn;
    private CheckBox chbSpisning, chbStuderende;
    private TextField txfTidspunkt, txfAntalM, txfAntalS, txfTotalPris, txfKundeNavn;
    private Button btnReserver, btnLuk;
    private PauseTransition pause = new PauseTransition(Duration.seconds(1)); // NOT IN USE?
    Rundvisning rv;

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        HBox Hnavn = new HBox();
        pane.add(Hnavn, 0, 0);
        Hnavn.setAlignment(Pos.TOP_LEFT);

        lbKundeNavn = new Label("Kunde's navn:  ");
        Hnavn.getChildren().add(lbKundeNavn);

        txfKundeNavn = new TextField();
        Hnavn.getChildren().add(txfKundeNavn);
        addListenerKunde();

        lbDatePicker = new Label("Vælg dato");
        pane.add(lbDatePicker, 0, 1);

        dp = new DatePicker();
        pane.add(dp, 0, 2);
        addListenerdp();

        chbStuderende = new CheckBox("Studerende");
        pane.add(chbStuderende, 0, 3);
        chbStuderende.setOnAction(event -> selectedStuderende());

        lbTidspunkt = new Label("Tidspunkt");
        pane.add(lbTidspunkt, 0, 5);

        txfTidspunkt = new TextField();
        pane.add(txfTidspunkt, 0, 6);
        addListenerTid();

        lbAntal = new Label("Antal");
        pane.add(lbAntal, 1, 5);

        txfAntalM = new TextField();
        pane.add(txfAntalM, 1, 6);
        addListenerAntalM();

        chbSpisning = new CheckBox("Spisning Ønskes - 120 kr.- pr. person");
        pane.add(chbSpisning, 0, 7);
        chbSpisning.setOnAction(event -> selectedSpisning());

        lbAntalS = new Label("Antal for spisning");
        pane.add(lbAntalS, 0, 8);

        txfAntalS = new TextField();
        pane.add(txfAntalS, 0, 9);
        txfAntalS.setDisable(true);
        addListenerAntalS();

        lbTotalPris = new Label("Total Pris");
        pane.add(lbTotalPris, 0, 10);

        txfTotalPris = new TextField();
        pane.add(txfTotalPris, 0, 11);
        txfTotalPris.setEditable(false);

        btnReserver = new Button("Reserver");
        pane.add(btnReserver, 0, 12);
        btnReserver.setOnAction(event -> btnReserverAction());

        btnLuk = new Button("Luk");
        pane.add(btnLuk, 1, 12);
        btnLuk.setOnAction(event -> btnLukAction());

    }

    // Action methods

    private void btnReserverAction() {
        if (rv != null && rv.beregnPris() != 0.0) {
            service.gemRundvisning(rv);
            RundvisningWindow rvw = new RundvisningWindow();
            rvw.showAndWait();
            hide();
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Ikke tilgaengelig");
            a.showAndWait();
        }

    }

    // Denne metode lukker for vinduet
    private void btnLukAction() {
        hide();
    }

    //
    // Methods
    //

    private boolean checkObject() {
        if (rv != null) {
            return true;
        } else {
            return false;
        }
    }

    private void createObject() {
        LocalDate dato = dp.getValue();
        String kunde = txfKundeNavn.getText();
        String a = txfAntalM.getText();
        String t = txfTidspunkt.getText();
        int antal;
        LocalTime tid;
        try {
            antal = Integer.parseInt(a);
            tid = LocalTime.parse(t);
            rv = service.opretRundvisning(kunde, dato, antal, tid);
            System.out.println(rv.getKunde()); // DELETE ME
            System.out.println(rv.beregnPris());
            if (chbStuderende.isSelected()) {
                rv.setStuderende(true);
            }
            if (chbSpisning.isSelected()) {
                if (txfAntalS.getText().length() > 0) {
                    String antalS = txfAntalS.getText();
                    try {
                        int aS = Integer.parseInt(antalS);
                        rv.tilmeldSpsning(aS);
                    } catch (Exception w) {
                        rv.tilmeldSpisning();
                        txfAntalS.setText("" + rv.getAntalGaester());
                    }
                } else {
                    rv.tilmeldSpisning();
                    txfAntalS.setText("" + rv.getAntalGaester());
                }
            }
            txfTotalPris.setText("" + rv.beregnPris());
            System.out.println("Object created");
        } catch (Exception e) {
            System.out.println("Kelly, can you handle this?");
        }

    }

    //
    // Add Listener methods;
    //

    private void addListenerAntalM() {
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        txfAntalM.textProperty().addListener((observable, oldValue, newValue) -> {
            pause.setOnFinished(event -> listenerAntalMHelper());
            pause.playFromStart();
        });
    }

    private void addListenerAntalS() {
        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        txfAntalS.textProperty().addListener((observable, oldValue, newValue) -> {
            pause.setOnFinished(event -> listenerAntalSHelper());
            pause.playFromStart();
        });
    }

    private void addListenerKunde() {
        txfKundeNavn.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (checkObject()) {
                String kunde = txfKundeNavn.getText();
                if (kunde.length() > 0) {
                    rv.setKunde(kunde);
                }
            } else {
                createObject();
            }
        });
    }

    private void addListenerTid() {
        txfTidspunkt.focusedProperty().addListener((obs, oldVal, newVal) -> {

            if (checkObject()) {
                String t = txfTidspunkt.getText();
                if (t.length() > 0) {
                    try {

                        LocalTime tid = LocalTime.parse(t);
                        rv.setTid(tid);
                        txfTotalPris.setText("" + rv.beregnPris());
                    } catch (Exception e) {
                        System.out.println("Michelle, can you handle this?");
                    }
                }
            } else {
                createObject();
            }
        }

        );
    }

    private void listenerAntalMHelper() {
        if (checkObject()) {
            String a = txfAntalM.getText();
            if (a.length() > 0) {
                try {

                    int antal = Integer.parseInt(a);
                    rv.setAntalGaester(antal);
                    txfTotalPris.setText("" + rv.beregnPris());
                } catch (Exception e) {
                    System.out.println("Beyoncé, can you handle this?");
                }
            }
        } else {
            createObject();
        }

    }

    private void listenerAntalSHelper() {

        if (checkObject()) {
            String a = txfAntalS.getText();
            if (a.length() > 0) {
                try {

                    int antal = Integer.parseInt(a);
                    rv.tilmeldSpsning(antal);
                    txfTotalPris.setText("" + rv.beregnPris());
                } catch (Exception e) {
                    System.out.println("I don't think they can handle this!");
                }
            }
        } else {
            createObject();
        }

    }

    private void addListenerdp() {
        dp.valueProperty().addListener((ov, oldValue, newValue) -> {
            if (checkObject()) {
                LocalDate d = dp.getValue();
                rv.setDato(d);
                txfTotalPris.setText("" + rv.beregnPris());
            }
        });
    }

    //
    // CHECK BOX EVENTS
    //

    private void selectedSpisning() {
        if (chbSpisning.isSelected()) {
            // txfAntalS.setEditable(true);
            txfAntalS.setDisable(false);
            System.out.println(txfAntalS.isEditable());
        } else {
            txfAntalS.setDisable(true);
        }
        if (!(txfAntalS.getText().length() > 0) && chbSpisning.isSelected()) {
            txfAntalS.setText(txfAntalM.getText());
        }
        if (checkObject()) {
            if (chbSpisning.isSelected()) {
                txfAntalS.setDisable(false);
                rv.tilmeldSpisning();
                txfTotalPris.setText("" + rv.beregnPris());

            } else {
                txfAntalS.setDisable(true);
                rv.frameldSpisning();
                txfTotalPris.setText("" + rv.beregnPris());
            }
            txfTotalPris.setText("" + rv.beregnPris());
        }

    }

    private void selectedStuderende() {
        if (checkObject()) {
            if (chbStuderende.isSelected()) {
                rv.setStuderende(true);
            } else {
                rv.setStuderende(false);
            }
        }
    }

}
