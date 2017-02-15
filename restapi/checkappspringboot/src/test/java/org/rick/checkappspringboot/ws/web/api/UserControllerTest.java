/**
 * 
 */
package org.rick.checkappspringboot.ws.web.api;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.rick.checkappspringboot.AbstractControllerTest;
import org.rick.checkappspringboot.ws.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author pateriki
 *
 */
@Transactional
public class UserControllerTest extends AbstractControllerTest {
	
	@Autowired
	private UserService userService;

	/* (non-Javadoc)
	 * @see org.rick.checkappspringboot.AbstractControllerTest#setUp()
	 */
	@Before
	public void setUp() {
		super.setUp();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetUsers() throws Exception {
		String uri= "/api/users";
		MvcResult results = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON)).andReturn();
		
		String content = results.getResponse().getContentAsString();
		int status = results.getResponse().getStatus();
		
		Assert.assertEquals("Failure - Expected Return Type 200", 200, status);
		Assert.assertTrue("Failure - Expected Size of Content Greater Than 0", content.length() > 0);
	}
	
	@Test
	public void testGetUserGroups() throws Exception {
		String uri= "/api/users/1/groups";
		MvcResult results = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON)).andReturn();
		
		String content = results.getResponse().getContentAsString();
		int status = results.getResponse().getStatus();
		
		Assert.assertEquals("Failure - Expected Return Type 200", 200, status);
		Assert.assertTrue("Failure - Expected Size of Content Greater Than 0", content.length() > 0);
		
	}
	
	@Test
	public void testGetInvalidUserGroups() throws Exception {
		String uri= "/api/users/10/groups";
		MvcResult results = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON)).andReturn();
		int status = results.getResponse().getStatus();
		
		Assert.assertEquals("Failure - Expected Return Type 404", 404, status);
		
	}

}
