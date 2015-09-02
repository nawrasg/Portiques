package fr.capgemini.sncf.db;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class Auth {
	private String mServer, mDBName;
	private DB mDB;
	
	public Auth() throws UnknownHostException{
		mServer = "localhost";
		mDBName = "sncf";
		getDB();
	}

	public Auth(String server, String db) throws UnknownHostException{
		mServer = server;
		mDBName = db;
		getDB();
	}

	/**
	 * Get the database object for making requests.
	 * 
	 * @throws UnknownHostException
	 */
	private void getDB() throws UnknownHostException{
		mDB = new MongoClient(mServer, 27017).getDB(mDBName);
	}
	
	/**
	 * Search for a user through his id.
	 * 	
	 * @param id
	 * @return
	 */
	private DBCursor getUser(Object id){
		if(mDB == null){
			return null;
		}
		DBCollection nTable = mDB.getCollection("users");
		BasicDBObject nQuery = new BasicDBObject();
		nQuery.put("_id", id);
		return nTable.find(nQuery);		
	}
	
	/**
	 * Check if the requested user exists.
	 * 
	 * @param id
	 * @return
	 */
	public boolean auth(Object id){
		if(getUser(id).size()  == 1){
			return true;
		}
		return false;
	}
	
	/**
	 * Check if the requested user has the requested security level.
	 * 
	 * @param id: user id
	 * @param level: min security level 
	 * @return
	 */
	public boolean autho(Object id, int level){
		DBCursor nCursor = getUser(id);
		if(nCursor.size() == 1){
			DBObject nResult = nCursor.next();
			if((Integer)nResult.get("level") == level){
				return true;
			}
		}
		return false;
	}
	

	
}
