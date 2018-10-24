package gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

	private Button btnBar, btnAdministration;

	private void initContent(GridPane pane) {
		pane.setPadding(new Insets(10));
		pane.setHgap(10);
		pane.setVgap(10);
		pane.setGridLinesVisible(false);

		btnBar = new Button("Bar");
		pane.add(btnBar, 0, 0);
		btnBar.setOnAction(event -> barBtnAction());
		btnBar.setPrefSize(200, 100);

		btnAdministration = new Button("Administration");
		pane.add(btnAdministration, 0, 1);
		btnAdministration.setOnAction(event -> administrationBtnAction());
		btnAdministration.setPrefSize(200, 100);

	}

	private void barBtnAction() {
		SalgsWindow sw = new SalgsWindow();
		sw.showAndWait();

	}

	private void administrationBtnAction() {
		AdministrationsWindow aw = new AdministrationsWindow();
		aw.showAndWait();
	}

}
