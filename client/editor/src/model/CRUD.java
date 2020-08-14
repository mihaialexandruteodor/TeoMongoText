package model;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.json.JSONObject;

import com.mongodb.client.MongoCollection;
import utils.DataSingleton;

//import static com.mongodb.client.model.Filters.gte;

public class CRUD implements Runnable{

	public void readDB() {

		// collection = files

		MongoCollection<Document> filesCollection = DataSingleton.getInstance().getDatabase()
				.getCollection("files");

		List<Document> docsList = filesCollection.find().into(new ArrayList<>());
		
		for (Document doc : docsList) {
			
			TextFile text = new TextFile();
			JSONObject jsonObj = new JSONObject(doc.toJson());
			text.setFileName(jsonObj.get("file_name").toString());
			text.setFileContent(jsonObj.get("contents").toString());
			
			DataSingleton.getInstance().getTextFiles().add(text);
		}
			
		MongoCollection<Document> charactersCollection = DataSingleton.getInstance().getDatabase()
				.getCollection("characters");

		List<Document> chrsList = charactersCollection.find().into(new ArrayList<>());
		
		for (Document chr : chrsList) {
			
			CharacterFile charact = new CharacterFile();
			JSONObject jsonObj = new JSONObject(chr.toJson());
			charact.setName(jsonObj.get("character_name").toString());
			charact.setDetails(jsonObj.get("details").toString());
			
			DataSingleton.getInstance().getCharFiles().add(charact);
		}

		
	}

	@Override
	public void run() {
		readDB();
	}
}
