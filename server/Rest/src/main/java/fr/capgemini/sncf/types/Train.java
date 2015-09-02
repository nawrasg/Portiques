package fr.capgemini.sncf.types;

import com.mongodb.DBObject;

/**
 * Object that encapsulate train data.
 * 
 * @author ngeorgi
 *
 */
public class Train {

	private int mID, mCapacity, mVoituresNumber, mGo, mEmbarquesNumber, mQuai,
			mBuyNumber;

	public Train(DBObject train) {
		mID = ((Number) train.get("_id")).intValue();
		mCapacity = ((Number) train.get("capacity")).intValue();
		mVoituresNumber = ((Number) train.get("voiture")).intValue();
		mGo = ((Number) train.get("go")).intValue();
		mEmbarquesNumber = ((Number) train.get("embarque")).intValue();
		mQuai = ((Number) train.get("quai")).intValue();
		mBuyNumber = ((Number) train.get("buy")).intValue();
	}

	public int getTrainId() {
		return mID;
	}

	public int getTrainCapacity() {
		return mCapacity;
	}

	public int getVoicturesNumber() {
		return mVoituresNumber;
	}

	public int getStatus() {
		return mGo;
	}

	public int getEmbarquesNumber() {
		return mEmbarquesNumber;
	}

	public int getQuaiNumber() {
		return mQuai;
	}

	public int getBuyNumber() {
		return mBuyNumber;
	}

	public int getCode() {
		return 200;
	}

}
