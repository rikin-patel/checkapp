/**
 * 
 */
package org.rick.checkappspringboot.ws.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

/**
 * @author pateriki
 *
 */
@Entity(name = "groups")
//@NamedQuery(name = "Group.findByOwnerId",
//query = "select g from Groups g where g.ownerid = ?1")
public class Group {

	@Id
	@Column(name = "groupid")
	@GeneratedValue
	private Long groupId;
	
	@Column(name = "groupname")
	private String groupName;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "create_date")
	private Date createDate;
	
//	@JsonIgnore
//	@ManyToMany(fetch=FetchType.EAGER, mappedBy="groups")
//	private List<User> users = new ArrayList<User>();
	
	@OneToMany(mappedBy="group", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<UsersGroups> usersGroups = new ArrayList<UsersGroups>();
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name="userId")
	@Column(name="ownerid")
	private Long ownerId;
	
	@OneToMany
	@JoinColumn(name="groupid", referencedColumnName="groupid")
	private Collection<Task> groupTasks = new ArrayList<Task>();
	
	public Group(){
		
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
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

//	public List<User> getUsers() {
//		return users;
//	}
//
//	public void setUsers(List<User> users) {
//		this.users = users;
//	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long owner) {
		this.ownerId = owner;
	}

	public List<UsersGroups> getUsersGroups() {
		return usersGroups;
	}

	public void setUsersGroups(List<UsersGroups> usersGroups) {
		this.usersGroups = usersGroups;
	}

	public Collection<Task> getGroupTasks() {
		return groupTasks;
	}

	public void setGroupTasks(Collection<Task> groupTasks) {
		this.groupTasks = groupTasks;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result + ((groupId == null) ? 0 : groupId.hashCode());
		result = prime * result
				+ ((groupName == null) ? 0 : groupName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Group other = (Group) obj;
		if (createDate == null) {
			if (other.createDate != null)
				return false;
		} else if (!createDate.equals(other.createDate))
			return false;
		if (groupId == null) {
			if (other.groupId != null)
				return false;
		} else if (!groupId.equals(other.groupId))
			return false;
		if (groupName == null) {
			if (other.groupName != null)
				return false;
		} else if (!groupName.equals(other.groupName))
			return false;
		return true;
	}
}
