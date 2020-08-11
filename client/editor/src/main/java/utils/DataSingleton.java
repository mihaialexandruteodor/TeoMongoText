package utils;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class DataSingleton {

	private static DataSingleton single_instance = null;

	public MongoDbConnector mongoDbConnector;
	public String connectionString;
	public MongoClient mongoClient;
	public MongoDatabase database;

	private DataSingleton() {
		mongoDbConnector = new MongoDbConnector();
		connectionString = null;
		mongoClient = null;
		database = null;
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

	public static DataSingleton getInstance() {
		if (single_instance == null)
			single_instance = new DataSingleton();

		return single_instance;
	}

}
