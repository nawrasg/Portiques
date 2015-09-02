package fr.capgemini.sncf.types;

import com.mongodb.DBObject;

public class Gare {
	private int mID, mX, mY;
	private String mName;

	public Gare(DBObject gare){
		mID = ((Number)gare.get("_id")).intValue();
		mX =  ((Number)gare.get("x")).intValue();
		mY =  ((Number)gare.get("y")).intValue();
		mName = (String)gare.get("name");
	}
	
	public int getId(){
		return mID;
	}
	public int getX(){
		return mX;
	}
	
	public int getY(){
		return mY;
	}
	
	public String getName(){
		return mName;
	}
}
