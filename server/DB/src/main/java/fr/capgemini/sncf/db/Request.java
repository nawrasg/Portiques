package fr.capgemini.sncf.db;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

public class Request {
	private String mServer, mDBName;
	private DB mDB;
	
	public Request() throws UnknownHostException{
		mServer = "localhost";
		mDBName = "sncf";
		getDB();
	}

	public Request(String server, String db) throws UnknownHostException{
		mServer = server;
		mDBName = db;
		getDB();
	}

	private void getDB() throws UnknownHostException{
		mDB = new MongoClient(mServer, 27017).getDB(mDBName);
	}
	
	public DBCursor get(String collection, String key, Object val){
		if(mDB == null){
			return null;
		}
		DBCollection nTable = mDB.getCollection(collection);
		BasicDBObject nQuery = new BasicDBObject();
		nQuery.put(key, val);
		return nTable.find(nQuery);
	}
	
	public DBCursor get(String collection){
		if(mDB == null){
			return null;
		}
		DBCollection nTable = mDB.getCollection(collection);
		return nTable.find();
	}

}
