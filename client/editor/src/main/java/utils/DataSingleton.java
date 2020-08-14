package utils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import gui.GuiController;
import model.CRUD;
import model.CharacterFile;
import model.TextFile;

public class DataSingleton {
	
	private static DataSingleton single_instance = null;

	MongoDbConnector mongoDbConnector;
	GuiController guiController;
	String connectionString;
	MongoClient mongoClient;
	MongoDatabase database;
	CRUD crudObj;
	List<TextFile> textFiles;
	List<CharacterFile> charFiles;
	String iniFilePath;

	private DataSingleton() {
		mongoDbConnector = new MongoDbConnector();
		guiController = new GuiController();
		connectionString = null;
		mongoClient = null;
		database = null;
		crudObj = new CRUD();
		textFiles = new ArrayList<>();
		charFiles = new ArrayList<>();
		setIniFilePath();
	}

	

	public static DataSingleton getInstance() {
		if (single_instance == null)
			single_instance = new DataSingleton();

		return single_instance;
	}

	public GuiController getGuiController() {

		return guiController;
	}

	public void setGuiController(GuiController guiController) {
		this.guiController = guiController;
	}

	public String getConnectionString() {
		return connectionString;
	}

	public void setConnectionString(String connectionString) {
		this.connectionString = connectionString;
	}

	public MongoDbConnector getMongoDbConnector() {
		return mongoDbConnector;
	}

	public MongoClient getMongoClient() {
		return mongoClient;
	}

	public void setMongoClient(MongoClient mongoClient) {
		this.mongoClient = mongoClient;
	}

	public MongoDatabase getDatabase() {
		return database;
	}

	public void setDatabase(MongoDatabase database) {
		this.database = database;
	}

	public CRUD getCrudObj() {
		return crudObj;
	}

	public void setCrudObj(CRUD crudObj) {
		this.crudObj = crudObj;
	}

	public List<TextFile> getTextFiles() {
		return textFiles;
	}

	public void setTextFiles(List<TextFile> textFiles) {
		this.textFiles = textFiles;
	}

	public List<CharacterFile> getCharFiles() {
		return charFiles;
	}

	public void setCharFiles(List<CharacterFile> charFiles) {
		this.charFiles = charFiles;
	}
	
	
	public String getIniFilePath() {
		return iniFilePath;
	}

	public void setIniFilePath() {
		
		String osName = System.getProperty("os.name").toLowerCase();
		boolean isMac = osName.startsWith("mac");
		boolean isLinux = osName.startsWith("linux");
		boolean isWindows = osName.startsWith("windows");
		if (isMac) 
		{
			iniFilePath = System.getProperty("file.separator") + "Users" + System.getProperty("file.separator")
			+ System.getProperty("user.name") + System.getProperty("file.separator") + "Documents"
			+ System.getProperty("file.separator") + "TeoMongoText" + System.getProperty("file.separator")
			+ "Settings.ini";
		}
		else
			if(isLinux)
			{
				iniFilePath = System.getProperty("file.separator") + "home" + System.getProperty("file.separator")
				+ System.getProperty("user.name") + System.getProperty("file.separator") + "Documents"
				+ System.getProperty("file.separator") + "TeoMongoText" + System.getProperty("file.separator")
				+ "Settings.ini";
			}
			else
				if(isWindows)
				{
					String myDocuments = null;

					try {
					    Process p =  Runtime.getRuntime().exec("reg query \"HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Explorer\\Shell Folders\" /v personal");
					    p.waitFor();

					    InputStream in = p.getInputStream();
					    byte[] b = new byte[in.available()];
					    in.read(b);
					    in.close();

					    myDocuments = new String(b);
					    myDocuments = myDocuments.split("\\s\\s+")[4];

					} catch(Throwable t) {
					    t.printStackTrace();
					}
					
					iniFilePath = myDocuments + System.getProperty("file.separator") + "TeoMongoText" + System.getProperty("file.separator")
					+ "Settings.ini";
				}
		
	}

}
