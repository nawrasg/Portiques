package fr.capgemini.sncf.types;

import com.mongodb.DBObject;

public class Statistique {
	private int mValue;
	private String mID, mUP, mType, mUnit; 

	public Statistique(DBObject obj){
		mValue = ((Number)obj.get("value")).intValue();
		mID = (String)obj.get("idd");
		mUP = (String)obj.get("up");
		mType = (String)obj.get("type");
		mUnit = (String)obj.get("unit");
	}
	
	public int getValue(){
		return mValue;
	}
	
	public String getId(){
		return mID;
	}
	
	public String getUp(){
		return mUP;
	}
	public String getType(){
		return mType;
	}
	
	public String getUnit(){
		return mUnit;
	}
}
