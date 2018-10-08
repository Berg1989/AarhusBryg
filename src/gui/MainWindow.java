package gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainWindow extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void init() {
        // Service.initStorage();
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

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        Button btnBar = new Button("Bar");
        pane.add(btnBar, 0, 1);
        btnBar.setOnAction(event -> barBtnAction());
        btnBar.setPrefSize(200, 100);

        Button btnAdministration = new Button("Administration");
        pane.add(btnAdministration, 0, 2);
        btnAdministration.setOnAction(event -> administrationBtnAction());
        btnAdministration.setPrefSize(200, 100);

    }

    private void barBtnAction() {

    }

    private void administrationBtnAction() {
        AdministrationsWindow aw = new AdministrationsWindow();
        aw.showAndWait();
    }

}
