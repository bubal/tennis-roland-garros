package com.app.tennis.tests.webservices;

import org.hamcrest.Matchers;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RestTestConfig.class)
@WebAppConfiguration
public class TestJoueurRest {

	@Autowired
	private WebApplicationContext webContext;

	private MockMvc mockMvc;

	@Before
	public void init() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webContext).build();
	}

	@Test
	public void checkGetJoueurByIdUrl() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/api/joueurs/1"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
		.andExpect(MockMvcResultMatchers.jsonPath("$.nom", Matchers.is("Murray")));

	}
	@Test
	public void checkGetJoueurUrl() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/api/joueurs"))
		.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void getJoueurs() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/api/joueurs"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(8)));
	}

}
