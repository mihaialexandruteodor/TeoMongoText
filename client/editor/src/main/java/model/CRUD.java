package model;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import utils.DataSingleton;

//import static com.mongodb.client.model.Filters.gte;

public class CRUD {

	public void readDB() {

		// collection = files

		MongoCollection<Document> filesCollection = DataSingleton.getInstance().getDatabase()
				.getCollection("files");

		List<Document> docsList = filesCollection.find().into(new ArrayList<>());
		
		
		System.out.println("The files: ");
		for (Document doc : docsList) {
			System.out.println(doc.toJson());

		}
	}
}
