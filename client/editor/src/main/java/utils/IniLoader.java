package utils;

import java.io.File;
import java.io.IOException;

import org.ini4j.Wini;

public class IniLoader {

	/*
	 * I handle the initial creation of the .ini file inside the constructor
	 */
	public IniLoader() {
		File f = new File(DataSingleton.getInstance().getIniFilePath());
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

	public void loadIniSettings() {
		Wini ini = null;
		try {
			ini = new Wini(new File(DataSingleton.getInstance().getIniFilePath()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DataSingleton.getInstance().setConnectionString(ini.get("database", "connstr"));
	}

}
