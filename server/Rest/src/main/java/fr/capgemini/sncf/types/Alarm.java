package fr.capgemini.sncf.types;

import com.mongodb.DBObject;

public class Alarm {
	private int mDismiss, mCode;
	private String mID, mUP, mLabel;
	
	public Alarm(DBObject alarm){
		mID = (String)alarm.get("_id");
		mUP = (String)alarm.get("up");
		mDismiss = ((Number)alarm.get("dismiss")).intValue();
		mLabel = (String)alarm.get("label");
		mCode = ((Number)alarm.get("code")).intValue();
	}
	public String getId(){
		return mID;
	}
	
	public int getDismiss(){
		return mDismiss;
	}
	public int getCode(){
		return mCode;
	}
	
	public String getUp(){
		return mUP;
	}
	
	public String getLabel(){
		return mLabel;
	}
	
	public int getCity(){
		String[] nArr = mUP.split("_");
		return Integer.parseInt(nArr[0]);
	}
}
