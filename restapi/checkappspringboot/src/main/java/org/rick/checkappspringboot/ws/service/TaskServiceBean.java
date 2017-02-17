/**
 * 
 */
package org.rick.checkappspringboot.ws.service;

import java.util.Collection;
import java.util.Date;

import org.rick.checkappspringboot.ws.model.Task;
import org.rick.checkappspringboot.ws.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author pateriki
 *
 */
@Service
@Transactional(propagation=Propagation.SUPPORTS, 
readOnly=true)
public class TaskServiceBean implements TaskService {
	
	@Autowired
	private TaskRepository taskRepository;

	/* (non-Javadoc)
	 * @see org.rick.checkappspringboot.ws.service.TaskService#findAll()
	 */
	@Override
	public Collection<Task> findAll() {
		return taskRepository.findAll();
	}

	/* (non-Javadoc)
	 * @see org.rick.checkappspringboot.ws.service.TaskService#findOne(long)
	 */
	@Override
	public Task findOne(long taskId) {
		return taskRepository.findOne(taskId);
	}

	/* (non-Javadoc)
	 * @see org.rick.checkappspringboot.ws.service.TaskService#createUser(org.rick.checkappspringboot.ws.model.Task)
	 */
	@Override
	public Task createTask(Task task) {
		if(task.getTaskId() != null && task.getTaskId() != 0){
			return null;
		}
		task.setCreateDate(new Date());
		return taskRepository.save(task);
	}

	/* (non-Javadoc)
	 * @see org.rick.checkappspringboot.ws.service.TaskService#updateUser(org.rick.checkappspringboot.ws.model.Task)
	 */
	@Override
	public Task updateTask(Task task) {
		Task existingTask = taskRepository.findOne(task.getTaskId());
		if(existingTask == null){
			return null;
		}
		return taskRepository.save(task);
	}

	/* (non-Javadoc)
	 * @see org.rick.checkappspringboot.ws.service.TaskService#delete(long)
	 */
	@Override
	public void delete(long id) {
		taskRepository.delete(id);

	}

}
