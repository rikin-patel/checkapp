/**
 * 
 */
package org.rick.checkappspringboot.ws.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Siddharth
 * 
 */
@Entity(name = "users")
public class User {

	@Id
	@Column(name = "userid")
	@GeneratedValue
	private Long userId;
	@Column(name = "username")
	private String userName;
	@Column(name = "countrycode")
	private String countryCode;
	@Column(name = "phonenumber")
	private String phoneNumber;
	@Column(name = "email")
	private String emailAddress;
	@Column(name = "reg_date")
	private Date regDate;
	@Column(name = "passwd")
	private String password;

	public User() {

	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
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

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
