/**
 * 
 */
package org.rick.checkapp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlTransient;

import org.apache.solr.client.solrj.beans.Field;

/**
 * @author Rikin Patel
 *
 */
@Table(name="groups")
@Entity
public class Group implements Serializable{

	private Long groupId;
	private String groupName;
	private String description;
	private Date createDate;
	private Long ownerId;
	private Set<UsersGroups> usersGroups = new HashSet<UsersGroups>();
	
	@Transient
	private String modelClass = this.getClass().getName();
	
	@Id  
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getGroupId() {
		return groupId;
	}
	@Field("id")
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	@Field("name")
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getDescription() {
		return description;
	}
	@Field("description")
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name="create_date")
	public Date getCreateDate() {
		return createDate;
	}
	@Field("createDate")
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
//	@XmlTransient
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "ownerid")
//	public Users getOwner() {
//		return owner;
//	}
//	public void setOwner(Users owner) {
//		this.owner = owner;
//	}

	public Long getOwnerId() {
		return ownerId;
	}
	@Field("ownerId")
	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}
	@Transient
	public String getModelClass(){
		return this.modelClass;
	}
	@Field("modelClass")
	public void setModelClass(String className){
		this.modelClass=className;
	}
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "primaryKey.group",
            cascade = CascadeType.ALL)
	public Set<UsersGroups> getUsersGroups() {
		return usersGroups;
	}
	public void setUsersGroups(Set<UsersGroups> usersGroups) {
		this.usersGroups = usersGroups;
	}
	public void addUserGroup(UsersGroups userGroup) {
        this.usersGroups.add(userGroup);
    }
}
