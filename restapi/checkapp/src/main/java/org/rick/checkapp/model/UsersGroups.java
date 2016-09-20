/**
 * 
 */
package org.rick.checkapp.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * @author Rikin Patel
 *
 */
@Entity
@Table(name = "USERS_GROUPS")
@AssociationOverrides({
    @AssociationOverride(name = "primaryKey.user",
        joinColumns = @JoinColumn(name = "USERID")),
    @AssociationOverride(name = "primaryKey.group",
        joinColumns = @JoinColumn(name = "GROUPID")) })
public class UsersGroups implements Serializable{

	private UserGroupId primaryKey = new UserGroupId();
	private boolean isOwner;
	private Date assignDate;
	
	@EmbeddedId
	public UserGroupId getPrimaryKey() {
		return primaryKey;
	}
	public void setPrimaryKey(UserGroupId primaryKey) {
		this.primaryKey = primaryKey;
	}
	
	@Transient
	public Users getUser() {
		return primaryKey.getUser();
	}
	public void setUser(Users user) {
		this.primaryKey.setUser(user);;
	}
	@Transient
	public Group getGroup() {
		return primaryKey.getGroup();
	}
	public void setGroup(Group group) {
		this.primaryKey.setGroup(group);;
	}
	@Column(name = "isowner")
	public boolean isOwner() {
		return isOwner;
	}
	public void setOwner(boolean isOwner) {
		this.isOwner = isOwner;
	}
	@Column(name = "ASSIGN_DATE")
	@Temporal(TemporalType.DATE)
	public Date getAssignDate() {
		return assignDate;
	}
	public void setAssignDate(Date assignDate) {
		this.assignDate = assignDate;
	}
	
	
}
