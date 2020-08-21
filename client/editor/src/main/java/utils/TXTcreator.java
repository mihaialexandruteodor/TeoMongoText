package utils;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class TXTcreator {

	public TXTcreator() {

	}

	void newFile(String path, String content) {
		try (PrintWriter out = new PrintWriter(path)) {
			out.println(content);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
