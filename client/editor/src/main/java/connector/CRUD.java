package connector;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;

import model.CharacterFile;
import model.TextFile;
import utils.DataSingleton;



@SuppressWarnings("deprecation")
public class CRUD implements Runnable{

	public void readDB() {

		DataSingleton.getInstance().setTextFiles( new ArrayList<>() );
		DataSingleton.getInstance().setCharFiles( new ArrayList<>() );

		MongoCollection<Document> filesCollection = DataSingleton.getInstance().getDatabase()
				.getCollection("files");

		List<Document> docsList = filesCollection.find().into(new ArrayList<>());
		
		for (Document doc : docsList) {
			
			TextFile text = new TextFile();
			
			JsonParser parser = new JsonParser();
			JsonElement neoJsonElement = parser.parse(doc.toJson());

			text.setId(neoJsonElement.getAsJsonObject().get("_id").getAsJsonObject().get("$oid").toString());
			text.setFileName(neoJsonElement.getAsJsonObject().get("file_name").toString());
			text.setFileContent(neoJsonElement.getAsJsonObject().get("contents").toString());
			
			DataSingleton.getInstance().getTextFiles().add(text);
		}
			
		MongoCollection<Document> charactersCollection = DataSingleton.getInstance().getDatabase()
				.getCollection("characters");

		List<Document> chrsList = charactersCollection.find().into(new ArrayList<>());
		
		for (Document chr : chrsList) {
			
			CharacterFile charact = new CharacterFile();
			JsonParser parser = new JsonParser();
			JsonElement neoJsonElement = parser.parse(chr.toJson());
			charact.setName(neoJsonElement.getAsJsonObject().get("character_name").toString());
			charact.setDetails(neoJsonElement.getAsJsonObject().get("details").toString());
			
			DataSingleton.getInstance().getCharFiles().add(charact);
		}

		
	}
	
	public void updateTextFileContents(TextFile file)
	{
		MongoCollection<Document> collection = DataSingleton.getInstance().getDatabase().getCollection("files");
  
        BasicDBObject updateFields = new BasicDBObject();
        updateFields.append("contents", file.getFileContent());

        BasicDBObject setQuery = new BasicDBObject();
        setQuery.append("$set", updateFields);

        BasicDBObject searchQuery = new BasicDBObject("_id", new ObjectId(file.getId().replace("\"", "")));

        collection.updateOne(searchQuery, setQuery);
	}

	@Override
	public void run() {
		readDB();
	}
}