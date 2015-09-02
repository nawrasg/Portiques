package fr.capgemini.sncf.types;

public class Moment {
	private Integer mValue, mDuration;
	
	public Moment(Integer value, Integer duration){
		mValue = value;
		mDuration = duration;
	}
	
	public Integer getValue(){
		return mValue;
	}
	
	public Integer getDuration(){
		return mDuration;
	}
}
