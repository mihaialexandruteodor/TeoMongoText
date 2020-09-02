package gui;

import java.awt.Desktop;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.web.HTMLEditor;
import model.CharacterFile;
import model.TextFile;
import utils.DataSingleton;

public class GuiController implements PropertyChangeListener {

	private ObservableSet<TextFile> observableSetTextFiles;
	private ObservableSet<CharacterFile> observableSetCharFiles;

	/**
	 * Labels
	 */

	@FXML
	Label text_label;

	/**
	 * Lists
	 */

	@FXML
	ListView<TextFile> file_list_id;

	@FXML
	Accordion char_list_id;

	/**
	 * Menu Bar
	 */

	@FXML
	MenuBar menu_bar;

	@FXML
	Menu menu_file;
	@FXML
	Menu menu_edit;
	@FXML
	Menu menu_help;
	@FXML
	Menu menu_open_recent;

	@FXML
	MenuItem menu_new;

	@FXML
	MenuItem menu_save;
	@FXML
	MenuItem menu_save_as;
	@FXML
	MenuItem menu_quit;

	@FXML
	MenuItem menu_mongodb;

	@FXML
	MenuItem menu_about;

	@FXML
	HTMLEditor textBox;

	@FXML
	Button refreshButton;
	
	@FXML
	Button characterButton;

	@FXML
	Button editCharacterButton;

	@FXML
	Button deleteChButton;

	@FXML
	Button deleteFileButton;

	@FXML
	Button fileTxtButton;

	/**
	 * Methods
	 */

	public GuiController() {

	}

	public void loadDataIntoLists() {

		populateFileList();
		populateCharactersList();

	}

	@FXML
	private void fileNew() {
		NewTextFileWindow window = new NewTextFileWindow();
		window.addPropertyChangeListener(this);
	}

	@FXML
	private void fileSave() {
		DataSingleton.getInstance().saveOperation();
	}

	@FXML
	private void fileSaveAs() {
		DataSingleton.getInstance().generateTXTfile();
	}

	@FXML
	private void quitTeoMongo() {
		System.exit(0);
	}

	@FXML
	private void setConnectionString() {

		ConnectionStringInputWindow window = new ConnectionStringInputWindow();
		window.addPropertyChangeListener(this);

	}

	/*
	 *  
	 */

	@FXML
	private void helpAbout() {
		if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
			try {
				Desktop.getDesktop().browse(new URI("https://github.com/mihaialexandruteodor/TeoMongoText"));
			} catch (IOException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
		}
	}

	@FXML
	private void newCharacterWindow() {
		CharacterWindow window = new CharacterWindow();
		window.addPropertyChangeListener(this);

	}

	@FXML
	private void editCharacter() {
		String name, details;
		name = DataSingleton.getInstance().getCurrentCharacter().getName();
		details = DataSingleton.getInstance().getCurrentCharacter().getDetails();
		name = name.substring(1, name.length() - 1);
		details = details.substring(1, details.length() - 1);
		CharacterWindow window = new CharacterWindow(name, details);
		window.addPropertyChangeListener(this);
	}

	@FXML
	private void performRefresh() {
		loadDataIntoLists();
	}

	@FXML
	private void deleteCh() {
		DataSingleton.getInstance().getCrudObj().removeCharacter();

		try {
			DataSingleton.getInstance().getMongoDbConnector().connectToDatabase();

			DataSingleton.getInstance().getCrudObj().readDB();

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}

		performRefresh();
	}

	@FXML
	private void deleteFile() {
		DataSingleton.getInstance().getCrudObj().removeFile();

		try {
			DataSingleton.getInstance().getMongoDbConnector().connectToDatabase();

			DataSingleton.getInstance().getCrudObj().readDB();

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}

		performRefresh();
	}

	void populateFileList() {

		file_list_id.getItems().removeAll(observableSetTextFiles);
		observableSetTextFiles.clear();

		if (DataSingleton.getInstance().getTextFiles() != null
				&& DataSingleton.getInstance().getTextFiles().isEmpty() == false) {
			DataSingleton.getInstance().getTextFiles().forEach((c) -> observableSetTextFiles.add(c));

			file_list_id.getItems().addAll(FXCollections.observableArrayList(observableSetTextFiles));

			DataSingleton.getInstance().setCurrentFile(DataSingleton.getInstance().getTextFiles().get(0));

			String content = DataSingleton.getInstance()
					.prepareHTMLtext(DataSingleton.getInstance().getCurrentFile().getFileContent());

			textBox.setHtmlText(content);
		}

	}

	void populateCharactersList() {

		char_list_id.getPanes().removeAll(observableSetCharFiles);
		observableSetCharFiles.clear();

		if (DataSingleton.getInstance().getCharFiles() != null
				&& DataSingleton.getInstance().getCharFiles().isEmpty() == false) {
			DataSingleton.getInstance().getCharFiles().forEach((c) -> {

				observableSetCharFiles.add(c);
			});

			char_list_id.getPanes().addAll(FXCollections.observableArrayList(observableSetCharFiles));

		}

	}

	public void initialize() {

		observableSetTextFiles = FXCollections.observableSet();
		observableSetCharFiles = FXCollections.observableSet();

		DataSingleton.getInstance().setTextBox(textBox);

		file_list_id.setCellFactory(lv -> {
			ListCell<TextFile> cell = new ListCell<TextFile>() {

				@Override
				protected void updateItem(TextFile item, boolean empty) {
					super.updateItem(item, empty);
					if (item == null || empty) {
						setText(null);
					} else {
						setText(item.getFileName().replace("\"", ""));

					}
				}
			};

			cell.setOnMouseClicked(e -> {
				if (!cell.isEmpty()) {
					Document doc = Jsoup.parse(textBox.getHtmlText());
					DataSingleton.getInstance().getCurrentFile().setFileContent(
							DataSingleton.getInstance()
							.prepareHTMLtext(textBox.getHtmlText()));
					DataSingleton.getInstance().setCurrentFile(cell.getItem());
					String content = DataSingleton.getInstance()
							.prepareHTMLtext(DataSingleton.getInstance().getCurrentFile().getFileContent());

					textBox.setHtmlText(content);
					e.consume();
				}
			});

			return cell;
		});

		file_list_id.setOnMouseClicked(e -> {
			System.out.println("You clicked on an empty cell");
		});

		textBox.autosize();

	}

	@FXML
	void fileTxtExport() {
		DataSingleton.getInstance().generateTXTfile();
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("refresh")) {
			Platform.runLater(() -> {
				loadDataIntoLists();
			});
		} else if (evt.getPropertyName().equals("refreshCharacters")) {
			Platform.runLater(() -> {
				DataSingleton.getInstance().getCrudObj().readCharacters();
				populateCharactersList();
			});
		}
	}

}
