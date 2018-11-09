package gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import service.Service;

public class MainWindow extends Application {
    private Service service;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void init() {
        service = Service.getService();
        service.initStorage();
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Start Window");
        GridPane pane = new GridPane();
        initContent(pane);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();

    }

    private Label lblAdmin, lblBooking, lblSalg, lblStatistik;
    private Button btnBar, btnRundvisning, btnAnlaeg, btnPKategori, btnSSteder, btnStatistik;
    private int buttonHeight = 75;
    private int buttonWidth = 125;
    private int pad = 10;

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(pad));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        lblSalg = new Label("Salg");
        pane.add(lblSalg, 0, 0);

        lblBooking = new Label("Booking");
        pane.add(lblBooking, 1, 0);

        lblAdmin = new Label("Administration");
        pane.add(lblAdmin, 2, 0);

        lblStatistik = new Label("Statistik");
        pane.add(lblStatistik, 3, 0);

        btnBar = new Button("Aaben\nSalgsystem");
        pane.add(btnBar, 0, 1, 1, 2);
        btnBar.setOnAction(event -> barAction());
        btnBar.setPrefSize(buttonWidth, buttonHeight * 2 + pad);

        btnAnlaeg = new Button("Anlaeg");
        pane.add(btnAnlaeg, 1, 1);
        btnAnlaeg.setOnAction(event -> anlaegAction());
        btnAnlaeg.setPrefSize(buttonWidth, buttonHeight);

        btnRundvisning = new Button("Rundvisning");
        pane.add(btnRundvisning, 1, 2);
        btnRundvisning.setOnAction(event -> rundvisningAction());
        btnRundvisning.setPrefSize(buttonWidth, buttonHeight);

        btnPKategori = new Button("Produkt\nKategorier");
        pane.add(btnPKategori, 2, 1);
        btnPKategori.setOnAction(event -> pKategoriAction());
        btnPKategori.setPrefSize(buttonWidth, buttonHeight);

        btnSSteder = new Button("Salgssted");
        pane.add(btnSSteder, 2, 2);
        btnSSteder.setOnAction(event -> salgStedAction());
        btnSSteder.setPrefSize(buttonWidth, buttonHeight);

        btnStatistik = new Button("Statistik");
        pane.add(btnStatistik, 3, 1);
        btnStatistik.setOnAction(event -> statistikAction());
        btnStatistik.setPrefSize(buttonWidth, buttonHeight);
    }

    private void barAction() {
        SalgsWindow sw = new SalgsWindow();
        sw.showAndWait();
    }

    private void anlaegAction() {
        UdlejningerWindow uw = new UdlejningerWindow();
        uw.showAndWait();
    }

    private void rundvisningAction() {
        RundvisningWindow rw = new RundvisningWindow();
        rw.showAndWait();
    }

    private void salgStedAction() {
        SalgStedWindow ssw = new SalgStedWindow();
        ssw.showAndWait();
    }

    private void statistikAction() {
        ListeoversalgWindow lsw = new ListeoversalgWindow();
        lsw.showAndWait();
    }

    private void pKategoriAction() {
        ProduktkategoriWindow pkw = new ProduktkategoriWindow();
        pkw.showAndWait();
    }

}
