package connector;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import model.CharacterFile;
import model.TextFile;
import utils.DataSingleton;

@SuppressWarnings("deprecation")
public class CRUD {

	public void readDB() {

		DataSingleton.getInstance().setTextFiles(new ArrayList<>());
		DataSingleton.getInstance().setCharFiles(new ArrayList<>());

		MongoCollection<Document> filesCollection = DataSingleton.getInstance().getDatabase().getCollection("files");

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
			JsonParser parser = new JsonParser();
			JsonElement neoJsonElement = parser.parse(chr.toJson());

			Label l = new Label();
			l.setMaxWidth(180);
			String details = neoJsonElement.getAsJsonObject().get("details").toString();
			l.setText(details.substring(1, details.length() - 1).replace("█", "\n"));
			l.setWrapText(true);

			String name = neoJsonElement.getAsJsonObject().get("character_name").toString();
			CharacterFile charact = new CharacterFile(name.substring(1, name.length() - 1), l);
			charact.setObjId(neoJsonElement.getAsJsonObject().get("_id").getAsJsonObject().get("$oid").toString());

			charact.expandedProperty().addListener(new ChangeListener<Boolean>() {
				@Override
				public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
					DataSingleton.getInstance().setCurrentCharacter(charact);
				}
			});

			charact.setName(neoJsonElement.getAsJsonObject().get("character_name").toString());
			charact.setDetails(neoJsonElement.getAsJsonObject().get("details").toString());
			DataSingleton.getInstance().getCharFiles().add(charact);
		}

	}
	
	public void readFiles() {
		DataSingleton.getInstance().setTextFiles(new ArrayList<>());
		
		MongoCollection<Document> filesCollection = DataSingleton.getInstance().getDatabase().getCollection("files");

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

	}
	
	public void readCharacters() {
		DataSingleton.getInstance().setCharFiles(new ArrayList<>());
		
		MongoCollection<Document> charactersCollection = DataSingleton.getInstance().getDatabase()
				.getCollection("characters");

		List<Document> chrsList = charactersCollection.find().into(new ArrayList<>());

		for (Document chr : chrsList) {
			JsonParser parser = new JsonParser();
			JsonElement neoJsonElement = parser.parse(chr.toJson());

			Label l = new Label();
			l.setMaxWidth(180);
			String details = neoJsonElement.getAsJsonObject().get("details").toString();
			l.setText(details.substring(1, details.length() - 1).replace("█", "\n"));
			l.setWrapText(true);

			String name = neoJsonElement.getAsJsonObject().get("character_name").toString();
			CharacterFile charact = new CharacterFile(name.substring(1, name.length() - 1), l);
			charact.setObjId(neoJsonElement.getAsJsonObject().get("_id").getAsJsonObject().get("$oid").toString());

			charact.expandedProperty().addListener(new ChangeListener<Boolean>() {
				@Override
				public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
					DataSingleton.getInstance().setCurrentCharacter(charact);
				}
			});

			charact.setName(neoJsonElement.getAsJsonObject().get("character_name").toString());
			charact.setDetails(neoJsonElement.getAsJsonObject().get("details").toString());
			DataSingleton.getInstance().getCharFiles().add(charact);
		}
	}

	public void updateTextFileContents() {
		MongoCollection<Document> collection = DataSingleton.getInstance().getDatabase().getCollection("files");

		BasicDBObject updateFields = new BasicDBObject();
		updateFields.append("contents", DataSingleton.getInstance().getCurrentFile().getFileContent());

		BasicDBObject setQuery = new BasicDBObject();
		setQuery.append("$set", updateFields);

		BasicDBObject searchQuery = new BasicDBObject("_id",
				new ObjectId(DataSingleton.getInstance().getCurrentFile().getId().replace("\"", "")));

		collection.updateOne(searchQuery, setQuery);
	}

	public void newCharacter(String name, String content) {
		MongoCollection<Document> collection = DataSingleton.getInstance().getDatabase().getCollection("characters");

		Document newCh = new Document("item", "character").append("character_name", name).append("details", content);

		collection.insertOne(newCh);

	}
	
	public void updateCharacter(String name, String content) {
		MongoCollection<Document> collection = DataSingleton.getInstance().getDatabase().getCollection("characters");

		BasicDBObject updateFields = new BasicDBObject();
		updateFields.append("character_name", name).append("details", content);

		BasicDBObject setQuery = new BasicDBObject();
		setQuery.append("$set", updateFields);

		BasicDBObject searchQuery = new BasicDBObject("_id",
				new ObjectId(DataSingleton.getInstance().getCurrentCharacter().getObjId().replace("\"", "")));

		collection.updateOne(searchQuery, setQuery);

	}

	public void newTextFile(String name) {
		MongoCollection<Document> collection = DataSingleton.getInstance().getDatabase().getCollection("files");

		Document newCh = new Document("item", "file").append("file_name", name).append("contents", "");

		collection.insertOne(newCh);

	}

	public void removeCharacter() {
		MongoCollection<Document> collection = DataSingleton.getInstance().getDatabase().getCollection("characters");

		collection.deleteOne(new Document("_id",
				new ObjectId(DataSingleton.getInstance().getCurrentCharacter().getObjId().replace("\"", ""))));
	}

	public void removeFile() {
		MongoCollection<Document> collection = DataSingleton.getInstance().getDatabase().getCollection("files");

		collection.deleteOne(new Document("_id",
				new ObjectId(DataSingleton.getInstance().getCurrentFile().getId().replace("\"", ""))));
	}

}
