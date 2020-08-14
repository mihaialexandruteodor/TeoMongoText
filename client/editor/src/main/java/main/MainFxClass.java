package main;

import gui.GuiController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
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

	double version = 1.0;
	static FXMLLoader fxmlLoader;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			fxmlLoader = new FXMLLoader(getClass().getResource("/TeoMongoText/editor/GUI.fxml")); 
			Pane root = fxmlLoader.load();
			Scene scene = new Scene(root, 1200, 600);

			primaryStage.setScene(scene);
			primaryStage.setTitle("TeoMongoText v" + version);
			primaryStage.setMinWidth(1200);
			primaryStage.setMinHeight(600);
			primaryStage.show();
			
			IniLoader iniLoader = new IniLoader();
			iniLoader.loadIniSettings();
			
			DataSingleton.getInstance().setGuiController(fxmlLoader.getController());

			
			Thread t = new Thread(DataSingleton.getInstance().getMongoDbConnector());
			t.start();
			try {
				t.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Thread t2 = new Thread(DataSingleton.getInstance().getCrudObj());
			t2.start();
			try {
				t2.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			DataSingleton.getInstance().getGuiController().loadDataIntoLists();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

    public static void main(String[] args) {
        launch(args);
		
		
    }

	

}
