/**
 * 
 */
package org.rick.checkappspringboot.ws.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.rick.checkappspringboot.AbstractTest;
import org.rick.checkappspringboot.ws.model.Task;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author pateriki
 *
 */
public class TaskServiceTest extends AbstractTest{

	@Autowired
	private TaskService taskService;

	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreateTask(){
		Task task = new Task();
		task.setTaskName("Test Task Name");
		task.setDescription("Test Task Description");
		task.setTaskCreator(1L);
		
		Task savedTask = taskService.createTask(task);
		Assert.assertNotNull(savedTask);
		Assert.assertEquals("Failure,  Expected 1, Found:" + savedTask.getTaskCreator(), task.getTaskCreator(), savedTask.getTaskCreator());
	}
}
