/**
 * 
 */
package org.rick.checkapp.model;

import java.util.Date;

/**
 * @author Rikin Patel
 *
 */
public class Group {

	private long groupId;
	private String groupName;
	private String description;
	private long ownerId;
	private Date createDate;
	
	public Group(){
		
	}
	
	public Group(long groupId, String groupName, String description, long ownerId, Date createDate){
		this.groupId = groupId;
		this.groupName = groupName;
		this.description = description;
		this.ownerId = ownerId;
		this.createDate = createDate;
	}
	
	public long getGroupId() {
		return groupId;
	}
	public void setGroupId(long groupId) {
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
	public long getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(long ownerId) {
		this.ownerId = ownerId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}
