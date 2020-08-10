package gui;

import java.io.File;
import java.io.IOException;


import org.ini4j.Wini;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.web.HTMLEditor;

public class GuiController{
	
	/**
	 *  Labels
	 */
	
	@FXML
	Label text_label;
	
	@FXML
	Label files_label;
	
	@FXML
	Label characters_label;
	
	/**
	 *  Menu Bar
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
	
	/**
	 *  Methods
	 */
	
    public GuiController() 
    {
    }
     
    @FXML
    private void initialize() 
    {
    	//textBox.setVisible(false);
    	textBox.autosize();
    }
    
    @FXML
    private void fileNew()
    {
    	
    }
    
    @FXML
    private void fileOpen()
    {
    	
    }
    
    @FXML
    private void fileClose()
    {
    	
    }
    
    @FXML
    private void fileSave()
    {
    	
    }
    
    @FXML
    private void fileSaveAs()
    {
    	
    }
    
    @FXML
    private void fileRevert()
    {
    	
    }
    
    @FXML
    private void filePreferences()
    {
    	
    }
    
    @FXML
    private void quitTeoMongo()
    {
    	System.exit(0);
    }
    
   /*
    *  
    */
    
    @FXML
    private void editUndo()
    {
    	
    }
     
    @FXML
    private void editRedo()
    {
    	
    }
    
    @FXML
    private void editCut()
    {
    	
    }
    
    @FXML
    private void editCopy()
    {
    	
    }
    
    @FXML
    private void editPaste()
    {
    	
    }
    
    @FXML
    private void editDelete()
    {
    	
    }
    
    @FXML
    private void editSelectAll()
    {
    	
    }
    
    @FXML
    private void editUnselectAll()
    {
    	
    }
    
    @FXML
    private void setConnectionString()
    {
//    	Wini ini = null;
//		try {
//			ini = new Wini(new File("Settings.ini"));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        
//        ini.put("database", "connstr", "value");
    }
    
    /*
     *  
     */
    
    @FXML
    private void helpAbout()
    {
    	
    }

}
