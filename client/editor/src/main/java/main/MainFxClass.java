package main;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
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
			fxmlLoader = new FXMLLoader(getClass().getResource("/GUI.fxml")); 
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
			
			addEventHandlers(scene);

			DataSingleton.getInstance().getMongoDbConnector().init();
			
			DataSingleton.getInstance().getCrudObj().readDB();
			
			DataSingleton.getInstance().getGuiController().loadDataIntoLists();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

    public static void main(String[] args) {
        launch(args);
		
		
    }

	void addEventHandlers(Scene scene)
	{
		final KeyCombination saveEvent = new KeyCodeCombination(KeyCode.S, KeyCombination.SHORTCUT_DOWN);
		final KeyCombination copyEvent = new KeyCodeCombination(KeyCode.C, KeyCombination.SHORTCUT_DOWN);
		final KeyCombination pasteEvent = new KeyCodeCombination(KeyCode.V, KeyCombination.SHORTCUT_DOWN);
		final KeyCombination undoEvent = new KeyCodeCombination(KeyCode.Z, KeyCombination.SHORTCUT_DOWN);
		final KeyCombination cutEvent = new KeyCodeCombination(KeyCode.X, KeyCombination.SHORTCUT_DOWN);

		scene.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (saveEvent.match(event)) {
					DataSingleton.getInstance().saveOperation();
				}
				else if(copyEvent.match(event))
				{
					
				}else if(pasteEvent.match(event))
				{
					
				}else if(undoEvent.match(event))
				{
					
				}else if(cutEvent.match(event))
				{
					
				}
			}
		});
	}

}
