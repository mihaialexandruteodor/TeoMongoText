package gui;

import java.io.File;

import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class LocalSaveWindow {

	public LocalSaveWindow() {

	}

	public String getDirectory() {
		DirectoryChooser chooser = new DirectoryChooser();
		chooser.setTitle("Select a location to save the file...");
		File selectedDirectory = chooser.showDialog(new Stage());

		if (selectedDirectory == null) {
			return null;
		} else {
			return selectedDirectory.getAbsolutePath();
		}
	}

}
