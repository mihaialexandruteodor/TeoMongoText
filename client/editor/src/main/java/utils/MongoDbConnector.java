package utils;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

public class MongoDbConnector {

	public boolean connectToDatabase()
	{
		MongoClientURI uri = new MongoClientURI(DataSingleton.connectionString);

			MongoClient mongoClient = new MongoClient(uri);
			MongoDatabase database = mongoClient.getDatabase("teomongotext");

			
		return true;
	}

}
