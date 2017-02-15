/**
 * 
 */
package org.rick.checkappspringboot.ws.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author pateriki
 *
 */
@Entity(name="users_groups")
public class UsersGroups implements Serializable{

	@Id
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="userid")
	@JsonIgnore
	private User user;
	
	@Id
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="groupid")
	@JsonIgnore
	private Group group;
	
	@Column(name="assign_date")
	private Date assignDate;
	
	@Column(name="isowner")
	private boolean isOwner;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public Date getAssignDate() {
		return assignDate;
	}

	public void setAssignDate(Date assignDate) {
		this.assignDate = assignDate;
	}

	public boolean isOwner() {
		return isOwner;
	}

	public void setOwner(boolean isOwner) {
		this.isOwner = isOwner;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((assignDate == null) ? 0 : assignDate.hashCode());
		result = prime * result + ((group == null) ? 0 : group.hashCode());
		result = prime * result + (isOwner ? 1231 : 1237);
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		UsersGroups other = (UsersGroups) obj;
		if (assignDate == null) {
			if (other.assignDate != null)
				return false;
		} else if (!assignDate.equals(other.assignDate))
			return false;
		if (group == null) {
			if (other.group != null)
				return false;
		} else if (!group.equals(other.group))
			return false;
		if (isOwner != other.isOwner)
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
}
