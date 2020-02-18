package io.ojw.sbp.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import io.ojw.sbp.domain.User;

@RunWith(SpringRunner.class)
//@WebMvcTest(HelloController.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class HelloControllerTest {
	/*@Autowired
	MockMvc mock;*/
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void testHello() throws Exception {
		ResponseEntity<String> hello = restTemplate.getForEntity("/hello", String.class);
		System.out.println("hello=" + hello);
		assertThat(hello.getStatusCode()).isEqualTo(HttpStatus.OK);
		
		String id = "admin";
		ResponseEntity<User> helloUser = restTemplate.getForEntity("/helloUser/" + id, User.class);
		assertThat(helloUser.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(helloUser.getBody().getId()).isEqualTo(id);
//		assertThat(hello.getBody().equals("Hello 스프링부트!!"));
		
		/*mock.perform(get("/hello"))
			.andExpect(status().isOk())
			.andExpect(content().string("Hello 스프링부트!!"));
		
		MvcResult result = mock.perform(get("/hello"))
								.andExpect(status().isOk())
								.andReturn();
		
		assertEquals("Hello 스프링부트!!", result.getResponse().getContentAsString());
		assertThat(result.getResponse().getContentAsString()).isEqualTo("Hello 스프링부트!!");
		
		System.out.println("RRR>>" + result.getResponse().getContentAsString());*/
	}

}
