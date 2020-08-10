package utils;

import java.io.File;
import java.io.IOException;

import org.ini4j.Wini;

public class IniLoader {
	
	/*
	 * I handle the initial creation of the .ini file inside the constructor
	 */
	public IniLoader()
	{
		File f = new File("Settings.ini");
		if (f.getParentFile() != null) {
		  f.getParentFile().mkdirs();
		}
		try {
			f.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void loadIniSettings()
	{
		Wini ini = null;
		try {
			ini = new Wini(new File("Settings.ini"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DataSingleton.connectionString = ini.get("database", "connstr");
	}

}
