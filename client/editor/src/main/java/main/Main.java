package main;

import java.io.File;
import java.net.URL;

import gui.GuiController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.IniLoader;

public class Main extends Application{
	
	public static GuiController guiController = new GuiController();
	
	double version = 1.0;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			URL url = new File("src/main/java/gui/GUI.fxml").toURI().toURL();
			Parent root  = FXMLLoader.load(url);
			Scene scene = new Scene(root,900,600);

			primaryStage.setScene(scene);
			primaryStage.setTitle("TeoMongoText v" + version);
			primaryStage.setMinWidth(900);
			primaryStage.setMinHeight(600);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
		
		IniLoader iniLoader = new IniLoader();
	}
}
