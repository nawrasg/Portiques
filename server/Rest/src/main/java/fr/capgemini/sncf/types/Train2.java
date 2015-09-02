package fr.capgemini.sncf.types;

import com.mongodb.DBObject;

public class Train2 {
	private int mID, mCapacity, mVoitures, mTickets, mValidate;
	
	public Train2(DBObject train){
		mID = ((Number)train.get("_id")).intValue();
		mCapacity = ((Number)train.get("capacity")).intValue();
		mVoitures = ((Number)train.get("voitures")).intValue();
		mTickets = ((Number)train.get("tickets")).intValue();
		mValidate = ((Number)train.get("validate")).intValue();
	}
	
	public int getId(){
		return mID;
	}
	
	public int getCapacity(){
		return mCapacity;
	}
	
	public int getVoitures(){
		return mVoitures;
	}
	
	public int getTickets(){
		return mTickets;
	}
	
	public int getValidate(){
		return mValidate;
	}
}
