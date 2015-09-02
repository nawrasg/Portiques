package fr.capgemini.sncf.rest.controllers;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import fr.capgemini.sncf.rest.Sncf;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {Sncf.class})
public class SncfControllerTest {

	@Autowired
	private WebApplicationContext mContext;
	
	private MockMvc mMock;
	
	@Before
	public void setup(){
		mMock = MockMvcBuilders.webAppContextSetup(mContext).build();
	}
	
	@Test
	public void  updateTest() throws Exception{
		mMock.perform(MockMvcRequestBuilders.put("/sncf/trains/137")).andExpect(MockMvcResultMatchers.status().isAccepted())
		.andExpect(MockMvcResultMatchers.jsonPath("$.code").value(200)).andExpect(MockMvcResultMatchers.jsonPath("$.nombreBilletsCompostes").value(137));
	}
	
	@After
	public void downUp(){
		mMock = null;
	}
	
}
