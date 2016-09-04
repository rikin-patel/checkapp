/**
 * 
 */
package org.rick.checkapp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.solr.client.solrj.beans.Field;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.jasypt.hibernate4.type.EncryptedStringType;

/**
 * @author Rikin Patel
 *
 */
@TypeDef(
	    name="password", 
	    typeClass=EncryptedStringType.class, 
	    parameters= {
	        @Parameter(name="encryptorRegisteredName", value="checkappStringEncryptor")
	    }
	)


@Table(name="users")
@Entity
public class Users implements Serializable {
	
	private Long userId;
	private String userName;
	private String countryCode;
	private String phoneNumber;
	private String emailAddress;
	private Date regDate;
	private String password;
	
	@Transient
	private String modelClass = this.getClass().getName();
	private Set<Group> groups;
	
	@Id  
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getUserId() {
		return userId;
	}
	@Field("id")
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	@Field("name")
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
	@Field("phone")
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	@Column(name="email")
	public String getEmailAddress() {
		return emailAddress;
	}
	@Field("emailAddress")
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	@Column(name="reg_date")
	public Date getRegDate() {
		return regDate;
	}
	@Field("createDate")
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	@Transient
	public String getModelClass(){
		return this.modelClass;
	}
	@Field("modelClass")
	public void setModelClass(String className){
		this.modelClass=className;
	}
	@OneToMany(mappedBy = "owner")
	public Set<Group> getGroups() {
		return groups;
	}
	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}
	@Type(type="password")
	@Column(name="passwd")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
