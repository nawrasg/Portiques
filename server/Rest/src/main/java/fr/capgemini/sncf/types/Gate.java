package fr.capgemini.sncf.types;

import com.mongodb.DBObject;

public class Gate {
	private String mID, mHeureOn, mHeureOff;
	private int mQuai, mCP, mNumero, mClasse, mStatus, mTrain, mGare, mDirection, mTicket, mLuggage, mOpen, mAccepted, mPmr, mRejected, mRemain;
	
	public Gate(DBObject gate){
		mID = (String)gate.get("_id");
		mQuai = ((Number)gate.get("quai")).intValue();
		mCP = ((Number)gate.get("cp")).intValue();
		mNumero = ((Number)gate.get("numero")).intValue();
		mClasse = ((Number)gate.get("classe")).intValue();
		mStatus = ((Number)gate.get("status")).intValue();
		mTrain = ((Number)gate.get("train")).intValue();
		mGare = ((Number)gate.get("gare")).intValue();
		mHeureOn = (String)gate.get("heureOn");
		mHeureOff = (String)gate.get("heureOff");
		mDirection = ((Number)gate.get("direction")).intValue();
		mTicket = ((Number)gate.get("ticket")).intValue();
		mLuggage = ((Number)gate.get("luggage")).intValue();
		mOpen = ((Number)gate.get("open")).intValue();
		mAccepted = ((Number)gate.get("accepted")).intValue();
		mPmr = ((Number)gate.get("pmr")).intValue();
		mRejected = ((Number)gate.get("rejected")).intValue();
		mRemain = ((Number)gate.get("remain")).intValue();
	}
	
	public int getAccepted(){
		return mAccepted;
	}
	
	public int getPmr(){
		return mPmr;
	}
	
	public int getRejected(){
		return mRejected;
	}
	
	public int getRemain(){
		return mRemain;
	}
	
	public int getOpen(){
		return mOpen;
	}
	
	public int getTicket(){
		return mTicket;
	}
	
	public int getLuggage(){
		return mLuggage;
	}
	
	public String getHeureOn(){
		return mHeureOn;
	}
	
	public String getHeureOff(){
		return mHeureOff;
	}
	
	public int getDirection(){
		return mDirection;
	}
	
	public int getGare(){
		return mGare;
	}
	
	public int getTrain(){
		return mTrain;
	}
	
	public String getId(){
		return mID;
	}
	
	public int getQuai(){
		return mQuai;
	}
	
	public int getCp(){
		return mCP;
	}
	
	public int getNumero(){
		return mNumero;
	}
	
	public int getClasse(){
		return mClasse;
	}
	
	public int getStatus(){
		return mStatus;
	}

}
