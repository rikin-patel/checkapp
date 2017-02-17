/**
 * 
 */
package org.rick.checkappspringboot.ws.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author pateriki
 *
 */
@Entity(name="tasks")
public class Task implements Serializable {

	@Id
	@GeneratedValue
	@Column(name="taskid")
	private Long taskId;
	
	@Column(name="taskname")
	private String taskName;
	
	@Column(name="description")
	private String description;
	
	@Column(name="create_date")
	private Date createDate;
	
	@Column(name="task_creator")
	private Long taskCreator;
	
	@Column(name="complitionby_date")
	private Date complitionByDate;
	
	@Column(name="groupid")
	private Long groupId;

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Long getTaskCreator() {
		return taskCreator;
	}

	public void setTaskCreator(Long taskCreator) {
		this.taskCreator = taskCreator;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	
}
