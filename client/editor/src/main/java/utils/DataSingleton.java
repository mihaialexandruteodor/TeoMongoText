package utils;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import model.CRUD;

public class DataSingleton {

	private static DataSingleton single_instance = null;

	 MongoDbConnector mongoDbConnector;
	 String connectionString;
	 MongoClient mongoClient;
	 MongoDatabase database;
	 CRUD crudObj;


	private DataSingleton() {
		mongoDbConnector = new MongoDbConnector();
		connectionString = null;
		mongoClient = null;
		database = null;
		crudObj = new CRUD();
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

	public static DataSingleton getInstance() {
		if (single_instance == null)
			single_instance = new DataSingleton();

		return single_instance;
	}

}
