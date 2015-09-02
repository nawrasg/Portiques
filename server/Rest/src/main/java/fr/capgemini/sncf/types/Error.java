package fr.capgemini.sncf.types;

/**
 * Custom error object.
 * 
 * @author ngeorgi
 *
 */
public class Error {
	private int mErrCode;
	private String mErrDescription;
	
	public Error(int code, String description){
		mErrCode = code;
		mErrDescription = description;
	}
	
	public int getCode(){
		return mErrCode;
	}
	
	public String getDescription(){
		return mErrDescription;
	}
}
