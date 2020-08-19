package gui;

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
import javafx.scene.control.TitledPane;
import javafx.scene.web.HTMLEditor;
import model.TextFile;
import utils.DataSingleton;

public class GuiController {
	


	private ObservableSet<TextFile> observableSetTextFiles;
	private ObservableSet<TitledPane> observableSetCharFiles;

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
	MenuItem menu_open;
	@FXML
	MenuItem menu_close;
	@FXML
	MenuItem menu_save;
	@FXML
	MenuItem menu_save_as;
	@FXML
	MenuItem menu_revert;
	@FXML
	MenuItem menu_preferences;
	@FXML
	MenuItem menu_quit;

	@FXML
	MenuItem menu_undo;
	@FXML
	MenuItem menu_redo;
	@FXML
	MenuItem menu_cut;
	@FXML
	MenuItem menu_copy;
	@FXML
	MenuItem menu_paste;
	@FXML
	MenuItem menu_delete;
	@FXML
	MenuItem menu_select_all;
	@FXML
	MenuItem menu_unselect_all;
	@FXML
	MenuItem menu_mongodb;

	@FXML
	MenuItem menu_about;

	@FXML
	HTMLEditor textBox;
	
	@FXML
	Button refreshButton;

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

	}

	@FXML
	private void fileOpen() {

	}

	@FXML
	private void fileClose() {

	}

	@FXML
	private void fileSave() {
		DataSingleton.getInstance().saveOperation();
	}

	@FXML
	private void fileSaveAs() {

	}

	@FXML
	private void fileRevert() {

	}

	@FXML
	private void filePreferences() {

	}

	@FXML
	private void quitTeoMongo() {
		System.exit(0);
	}

	/*
	*  
	*/

	@FXML
	private void editUndo() {

	}

	@FXML
	private void editRedo() {

	}

	@FXML
	private void editCut() {

	}

	@FXML
	private void editCopy() {

	}

	@FXML
	private void editPaste() {

	}

	@FXML
	private void editDelete() {

	}

	@FXML
	private void editSelectAll() {

	}

	@FXML
	private void editUnselectAll() {

	}

	@FXML
	private void setConnectionString() {

		new ConnectionStringInputWindow();
		
		performRefresh();

	}

	/*
	 *  
	 */

	@FXML
	private void helpAbout() {

	}

	@FXML
	private void performRefresh()
	{
		loadDataIntoLists();
	}
	

	void populateFileList() {
		
		file_list_id.getItems().removeAll(observableSetTextFiles);
		observableSetTextFiles.clear();

		if (DataSingleton.getInstance().getTextFiles() != null && DataSingleton.getInstance().getTextFiles().isEmpty() == false) {
			DataSingleton.getInstance().getTextFiles().forEach((c) -> observableSetTextFiles.add(c));
			
			file_list_id.getItems().addAll(FXCollections.observableArrayList(observableSetTextFiles));

			DataSingleton.getInstance().setCurrentFile(DataSingleton.getInstance().getTextFiles().get(0));

			textBox.setHtmlText(DataSingleton.getInstance().getCurrentFile().getFileContent().substring(1, DataSingleton.getInstance().getCurrentFile().getFileContent().length() - 1));
		}

	}

	void populateCharactersList() {
		
		char_list_id.getPanes().removeAll(observableSetCharFiles);
		observableSetCharFiles.clear();
		
		if (DataSingleton.getInstance().getCharFiles() != null && DataSingleton.getInstance().getCharFiles().isEmpty() == false) {
			DataSingleton.getInstance().getCharFiles().forEach((c) -> {
				Label l = new Label(c.getDetails());
				l.setWrapText(true);
				observableSetCharFiles.add(new TitledPane(c.getName().replace("\"", ""), l));
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
					DataSingleton.getInstance().setCurrentFile(cell.getItem());
					textBox.setHtmlText(
							DataSingleton.getInstance().getCurrentFile().getFileContent().substring(1, DataSingleton.getInstance().getCurrentFile().getFileContent().length() - 1));
					System.out.println("You clicked on " + DataSingleton.getInstance().getCurrentFile().getFileName());
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

}
