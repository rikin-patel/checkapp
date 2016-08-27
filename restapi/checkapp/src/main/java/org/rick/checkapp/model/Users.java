/**
 * 
 */
package org.rick.checkapp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Rikin Patel
 *
 */
@Table(name="users")
@Entity
public class Users implements Serializable {

	private long userId;
	private String userName;
	private String countryCode;
	private String phoneNumber;
	private String emailAddress;
	private Date regDate;
//	private List<Group> groups;
	
	@Id  
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	@Column(name="email")
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	@Column(name="reg_date")
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
//	@OneToMany(mappedBy = "owner")
//	public List<Group> getGroups() {
//		return groups;
//	}
//	public void setGroups(List<Group> groups) {
//		this.groups = groups;
//	}
}
