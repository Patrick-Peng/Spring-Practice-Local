package com.aaxis.microservice.training.demo1;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=Demo1Application.class)
@WebAppConfiguration
public class RestUserControllerTests {
	@Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;
	
	@Before 
    public void setupMockMvc() throws Exception { 
    	mockMvc = MockMvcBuilders.webAppContextSetup(context).build(); 
    }

	@Test
	public void testLogin() throws Exception{
		MvcResult mvcResult = mockMvc.perform(
				MockMvcRequestBuilders.post("/rest/doLogin")
						.param("username", "aa@aa.com")
						.param("password", "a111111")
						.accept(MediaType.APPLICATION_JSON_UTF8)).andReturn();
		System.out.println(mvcResult.getResponse().getContentAsString());
	}
	
	@Test
	public void testRegist() throws Exception{
		MvcResult mvcResult = mockMvc.perform(
				MockMvcRequestBuilders.post("/rest/doRegist")
				.param("username", "aa@aa.com")
				.param("password", "a111111")
				.accept(MediaType.APPLICATION_JSON_UTF8)).andReturn();
		System.out.println(mvcResult.getResponse().getContentAsString());
	}
	
	
}
