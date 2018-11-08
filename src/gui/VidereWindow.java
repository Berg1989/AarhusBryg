package gui;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.EnumSet;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
import model.Betalingsmetode;
import model.Salg;
import model.SalgsLinie;
import service.Service;

public class VidereWindow extends Stage {
    private Service service;
    private Salg s;

    public VidereWindow(Salg s) {
        service = Service.getService();
        this.s = s;

        initStyle(StageStyle.UTILITY);
        initModality(Modality.APPLICATION_MODAL);
        setResizable(false);
        setTitle("Salgslinie Window");

        GridPane pane = new GridPane();
        Scene scene = new Scene(pane);
        initContent(pane);
        setScene(scene);

    }

    private ListView<SalgsLinie> lwTilFojet;
    private Label lbTF, lbTP, lbTPNy, lbBetalling, lbKredit;
    private TextField txfTP, txfTPNy, txfBP, txfPD, txfKlippekort;
    private CheckBox cbBP, cbPD, cbKlippekort;
    private Button btnLuk, btnBetal;
    private ComboBox<Betalingsmetode> cbbKredit;
    private EnumSet<Betalingsmetode> enumBetaling = EnumSet.allOf(Betalingsmetode.class);

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        // ---------- VBox 1 ----------------

        VBox vboks1 = new VBox();
        pane.add(vboks1, 0, 0);

        lbTF = new Label("Tilfoejede produkter");
        vboks1.getChildren().add(lbTF);

        lwTilFojet = new ListView<>();
        vboks1.getChildren().add(lwTilFojet);
        lwTilFojet.getItems().addAll(s.getProdukter());
        lwTilFojet.setDisable(false);

        // ---------- VBox 2 ----------------

        VBox vboks2 = new VBox(10);
        pane.add(vboks2, 1, 0);

        lbTP = new Label("Total pris");
        vboks2.getChildren().add(lbTP);

        txfTP = new TextField();
        vboks2.getChildren().add(txfTP);
        txfTP.setEditable(false);
        txfTP.setText(Double.toString(s.getTotalPris()));

        cbBP = new CheckBox("Giv absolut rabat");
        vboks2.getChildren().add(cbBP);
        cbBP.setOnAction(event -> cbBPIsSelected());

        txfBP = new TextField();
        vboks2.getChildren().add(txfBP);
        txfBP.setDisable(true);
        txfBP.setOnMouseExited(event -> updateNyPrisAbs());

        cbPD = new CheckBox("Procent Discount %");
        vboks2.getChildren().add(cbPD);
        cbPD.setOnAction(event -> cbPDIsSelected());

        txfPD = new TextField();
        vboks2.getChildren().add(txfPD);
        txfPD.setDisable(true);
        txfPD.setOnMouseExited(event -> updateNyPrisPro());

        lbTPNy = new Label("Ny total pris");
        vboks2.getChildren().add(lbTPNy);

        txfTPNy = new TextField();
        vboks2.getChildren().add(txfTPNy);
        txfTPNy.setEditable(false);
        txfTPNy.setText(txfTP.getText().trim());

        btnLuk = new Button("Luk");
        pane.add(btnLuk, 0, 1);
        btnLuk.setOnAction(event -> btnLukAction());

        btnBetal = new Button("Betal");
        pane.add(btnBetal, 1, 1);
        btnBetal.setOnAction(event -> btnBetalAction());

        lbBetalling = new Label("----------Betalling---------");
        vboks2.getChildren().add(lbBetalling);

        lbKredit = new Label("Betalingstype");
        vboks2.getChildren().add(lbKredit);

        cbbKredit = new ComboBox<>();
        vboks2.getChildren().add(cbbKredit);
        cbbKredit.getItems().addAll(enumBetaling);
        // cbbKredit.setOnAction(event -> updateNyPris());

        cbKlippekort = new CheckBox("Klippekort");
        vboks2.getChildren().add(cbKlippekort);
        cbKlippekort.setOnAction(event -> cbKlippekortSelected());

        txfKlippekort = new TextField();
        vboks2.getChildren().add(txfKlippekort);
        txfKlippekort.setDisable(true);

    }

    // Denne metode tjekker om checkbox nummer et er valgt, hvis checkboxen er valgt
    // så
    // åbner den op for txfBP textfield og disabler checkbox nummer 2 og clear hvad
    // der står i checkbox nummer 2's textfield.
    private void cbBPIsSelected() {
        if (cbBP.isSelected()) {
            txfBP.setDisable(false);
            cbPD.setDisable(true);
            txfPD.clear();
        } else {
            txfBP.setDisable(true);
            cbPD.setDisable(false);
        }

    }

    // Denne metode tjekker om checkbox nummer to er valgt, hvis checkboxen er valgt
    // så
    // åbner den op for txfPD textfield og disabler checkbox nummer et og clear hvad
    // der står i checkbox nummer et's textfield.
    private void cbPDIsSelected() {
        if (cbPD.isSelected()) {
            txfPD.setDisable(false);
            cbBP.setDisable(true);
            txfBP.clear();
        } else {
            txfPD.setDisable(true);
            cbBP.setDisable(false);
        }

    }

    // Denne metode tjekker om checkboxen for klippekort bliver valgt, og åbner op
    // for dets textfield
    private void cbKlippekortSelected() {
        if (cbKlippekort.isSelected()) {
            txfKlippekort.setDisable(false);
        } else {
            txfKlippekort.setDisable(true);
        }
    }

    // Denne metode holder den nye pris opdateret
    private void updateNyPris() {
        Double nyPris;
        Double nyPris1;
        if (cbBP.isSelected() && !txfBP.getText().isEmpty()) {
            nyPris = Double.parseDouble(txfBP.getText().trim());
            txfTPNy.setText(Double.toString(nyPris));
        } else if (cbPD.isSelected() && !txfPD.getText().isEmpty()) {
            nyPris = (Double.parseDouble(txfTP.getText().trim())
                    * ((Double.parseDouble(txfPD.getText().trim()) / 100)));
            nyPris1 = (Double.parseDouble(txfTP.getText().trim()) - nyPris);
            txfTPNy.setText(Double.toString(nyPris1));
        } else {
            nyPris = Double.parseDouble(txfTP.getText().trim());
            txfTPNy.setText(Double.toString(nyPris));
        }
    }

    // Denne beton er en buttom action, som laver et salg.
    private void btnBetalAction() {
        if (!txfTPNy.getText().isEmpty()) {
            s.setPris(Double.parseDouble(txfTPNy.getText().trim()));
        } else {
            s.setPris(Double.parseDouble(txfTP.getText().trim()));
        }

        if (cbbKredit.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information mangler");
            alert.setHeaderText("Mangler betaling");
            alert.setContentText("Mangler at vælge betalingsmulighed!");

            alert.showAndWait();
        } else {
            s.setBetalingsMetode(cbbKredit.getSelectionModel().getSelectedItem());
            s.setDato(LocalDate.now());
            s.setTid(LocalTime.now());
            service.completeSalg(s);
            System.out.println("Et Salg er blevet lavet");
            SalgsWindow sw = new SalgsWindow();
            sw.showAndWait();
            hide();
            
        }

    }
    
    private void updateNyPrisPro() {
    	String str = txfPD.getText().trim();
    	try {
    		double rabat = Double.parseDouble(str);
    		s.givRabatProcent(rabat);
    	}
    	catch  (Exception e){
    		txfPD.setText("" + s.getRabat());
    	}
    	txfTPNy.setText("" + s.getTotalPris());
    }
    
    
    private void updateNyPrisAbs() {
    	String str = txfBP.getText().trim();
    	try {
    		double rabat = Double.parseDouble(str);
    		s.givRabatAbsolut(rabat);
    	}
    	catch  (Exception e){
    		txfBP.setText("" + s.getRabat());
    	}
    	txfTPNy.setText("" + s.getTotalPris());
    }


    // Denne metode er en buttom action, som lukker for det nuværende vindue
    private void btnLukAction() {
        hide();
    }
}
