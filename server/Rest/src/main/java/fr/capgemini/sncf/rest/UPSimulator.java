package fr.capgemini.sncf.rest;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import fr.capgemini.sncf.types.Moment;

public class UPSimulator extends Thread{
	private ArrayList<Moment> mRecordList;
	private MockMvc mMock;

	private WebApplicationContext mContext;	
	
	private static final String SIM_VALUE = "Valeur";
	private static final String SIM_SLEEP_DURATION = "Sleep";

	public UPSimulator() throws IOException {
		mMock = MockMvcBuilders.webAppContextSetup(mContext).build();
		mRecordList = new ArrayList<Moment>();
		
		Reader nReader = new FileReader("sim.csv");
		Iterable<CSVRecord> nRecords = CSVFormat.EXCEL.parse(nReader);
		for(CSVRecord nRecord : nRecords){
			Integer nValue = Integer.parseInt(nRecord.get(SIM_VALUE));
			Integer nSleep = Integer.parseInt(nRecord.get(SIM_SLEEP_DURATION));
			Moment nMoment = new Moment(nValue, nSleep);
			mRecordList.add(nMoment);
		}
	}
	
	@Override
	public void run(){
		super.run();
		for(int i = 0; i < mRecordList.size(); i++){
			System.out.println(i);
			try {
				mMock.perform(MockMvcRequestBuilders.put("/sncf/trains/" + mRecordList.get(i).getValue()));
				Thread.sleep(mRecordList.get(i).getDuration() * 1000);
			} catch (Exception e) {
				System.out.println(e.toString());
			}
		}
	}
}
