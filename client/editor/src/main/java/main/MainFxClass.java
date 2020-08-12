package main;

import gui.GuiController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.DataSingleton;
import utils.IniLoader;

/**
 * 
 * @author teo
 * This class is a workaround to the JavaFX11 requirement to specify module path as VM argument
 * See: https://github.com/javafxports/openjdk-jfx/issues/236#issuecomment-426583174
 * Thanks so much, Oracle... -_-
 */

public class MainFxClass extends Application{
	
	public static GuiController guiController = new GuiController();

	double version = 1.0;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/gui/GUI.fxml"));
			Scene scene = new Scene(root, 1200, 600);

			primaryStage.setScene(scene);
			primaryStage.setTitle("TeoMongoText v" + version);
			primaryStage.setMinWidth(1200);
			primaryStage.setMinHeight(600);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

    public static void main(String[] args) {
        launch(args);

		IniLoader iniLoader = new IniLoader();
		iniLoader.loadIniSettings();

		/**
		 *  ASYNC connect to database
		 */
		Thread t = new Thread(DataSingleton.getInstance().getMongoDbConnector());
		t.start();
		try {
			t.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		DataSingleton.getInstance().getCrudObj().readDB();
    }

	

}
