/**
 * 
 */
package org.rick.checkappspringboot.ws.service;

import java.util.Collection;

import org.rick.checkappspringboot.ws.model.Task;

/**
 * @author pateriki
 *
 */
public interface TaskService {

	Collection<Task> findAll();
	
	Task findOne(long taskId);
	
	Task createTask(Task task);
	
	Task updateTask(Task task);
	
	void delete (long id);
}
