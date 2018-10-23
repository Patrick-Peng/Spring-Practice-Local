package com.aaxis.microservice.training.demo1;

import java.util.HashMap;
import java.util.Map;

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

import com.aaxis.microservice.training.demo1.domain.User;

import net.minidev.json.JSONObject;

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
		Map<String, String> user = new HashMap<>();
		user.put("username", "a");
		user.put("password", "1");
		MvcResult mvcResult = mockMvc.perform(
				MockMvcRequestBuilders.post("/rest/doLogin")
						.contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(user))
						.accept(MediaType.APPLICATION_JSON_UTF8)).andReturn();
		System.out.println(mvcResult.getResponse().getContentAsString());
	}
	
	@Test
	public void testRegist() throws Exception{
		Map<String, String> user = new HashMap<>();
		user.put("username", "aa@aa.com");
		user.put("password", "a111111");
		MvcResult mvcResult = mockMvc.perform(
				MockMvcRequestBuilders.post("/rest/doRegist")
				.contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(user))
				.accept(MediaType.APPLICATION_JSON_UTF8)).andReturn();
		System.out.println(mvcResult.getResponse().getContentAsString());
	}
	
	
}
