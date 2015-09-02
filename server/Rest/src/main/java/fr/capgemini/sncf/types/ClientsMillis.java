package fr.capgemini.sncf.types;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Object that stores the clients cache last update timestamp.
 * Not used in this project.
 * 
 * @author ngeorgi
 *
 */
public class ClientsMillis {
	private long mLastUpdate;
	Map<String, Long> mClientsLastUpdate;
	
	public ClientsMillis(){
		long nTime = System.currentTimeMillis();
		mLastUpdate = nTime;
		mClientsLastUpdate = new HashMap<String, Long>();
	}
	
	public void update(){
		mLastUpdate = System.currentTimeMillis();
	}
	
	public void updateClient(String client){
		mClientsLastUpdate.put(client, System.currentTimeMillis());
	}
	
	public Set<String> getNonUpdatedClients(){
		Set<String> nClientSet = new HashSet<String>();
		Iterator nIterator = mClientsLastUpdate.entrySet().iterator();
		while(nIterator.hasNext()){
			Map.Entry<String, Long> nPair = (Map.Entry<String, Long>)nIterator.next();
			if(nPair.getValue() < mLastUpdate) nClientSet.add(nPair.getKey());
//			nIterator.remove();
		}
		return nClientSet;
	}
	
}
