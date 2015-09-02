package fr.capgemini.sncf.db;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class Update {
	private String mServer, mDBName;
	private DB mDB;
	
	public Update() throws UnknownHostException{
		mServer = "localhost";
		mDBName = "sncf";
		getDB();
	}

	public Update(String server, String db) throws UnknownHostException{
		mServer = server;
		mDBName = db;
		getDB();
	}

	private void getDB() throws UnknownHostException{
		mDB = new MongoClient(mServer, 27017).getDB(mDBName);
	}
	
	public void updateValue(Object value){
		if(mDB == null){
			return;
		}
		BasicDBObject nDoc = new BasicDBObject();
		nDoc.put("_id", 8667);
		nDoc.put("capacity", 512);
		nDoc.put("voiture", 12);
		nDoc.put("go", 1);
		nDoc.put("embarque", value);
		nDoc.put("quai", 3);
		nDoc.put("buy", 384);
		DBCollection nTable = mDB.getCollection("gt35000");
		nTable.save(nDoc);
	}
	
	public void updateStatus(int train, int value){
		if(mDB == null){
			return;
		}
		DBCollection nCollection = mDB.getCollection("gt35000");
		BasicDBObject nQuery = new BasicDBObject();
		nQuery.put("_id", train);
		DBObject nResult = nCollection.findOne(nQuery);
		nResult.put("go", value);
		nCollection.save(nResult);
	}
	
	public void removeAlarm(String id){
		if(mDB == null){
			return;
		}
		DBCollection nCollection = mDB.getCollection("alarms");
		BasicDBObject nQuery = new BasicDBObject();
		nQuery.put("_id", id);
		DBObject nResult = nCollection.findOne(nQuery);
		nResult.put("dismiss", 1);
		nCollection.save(nResult);
	}
	
	public void updateGateTrain(String gate, int train){
		if(mDB == null){
			return;
		}
		DBCollection nCollection = mDB.getCollection("gates");
		BasicDBObject nQuery = new BasicDBObject();
		nQuery.put("_id", gate);
		DBObject nResult = nCollection.findOne(nQuery);
		nResult.put("train", train);
		nCollection.save(nResult);
	}
	
	public void updateTrainTickets(int train, int value){
		if(mDB == null){
			return;
		}
		DBCollection nCollection = mDB.getCollection("trains");
		BasicDBObject nQuery = new BasicDBObject();
		nQuery.put("_id", train);
		DBObject nResult = nCollection.findOne(nQuery);
		nResult.put("validate", value);
		nCollection.save(nResult);
	}
}
