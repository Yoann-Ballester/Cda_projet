package com.xprodcda.spring.xprodcda.domain;

import java.io.Serializable;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity	
public class User implements Serializable{
	
	
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(nullable = false, updatable = false)
private Long id;// id for database
private String userId; // id user
private String firstName;
private String lastName;
private String username;
private String password;
private String email;
private String profileImageUrl;
private String lastLoginDate;
private String lastLoginDateDisplay;
private String joinDate;
private String role; // ROLE_USER(read,edit) ROLE_ADMIN (delete)
private String[] authorities; // Are permissions: read, edit, delete
private boolean isActive;
private boolean isNotLocked;









public User(Long id, String userId, String firstName, String lastName, String username, String password, String email,
		String profileImageUrl, String lastLoginDate, String lastLoginDateDisplay, String joinDate, String role,
		String[] authorities, boolean isActive, boolean isNotLocked) {
	super();
	this.id = id;
	this.userId = userId;
	this.firstName = firstName;
	this.lastName = lastName;
	this.username = username;
	this.password = password;
	this.email = email;
	this.profileImageUrl = profileImageUrl;
	this.lastLoginDate = lastLoginDate;
	this.lastLoginDateDisplay = lastLoginDateDisplay;
	this.joinDate = joinDate;
	this.role = role;
	this.authorities = authorities;
	this.isActive = isActive;
	this.isNotLocked = isNotLocked;
}




public User() {
	super();
	// TODO Auto-generated constructor stub
}




public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getUserId() {
	return userId;
}
public void setUserId(String userId) {
	this.userId = userId;
}
public String getFirstName() {
	return firstName;
}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}
public String getLastName() {
	return lastName;
}
public void setLastName(String lastName) {
	this.lastName = lastName;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getProfileImageUrl() {
	return profileImageUrl;
}
public void setProfileImageUrl(String profileImageUrl) {
	this.profileImageUrl = profileImageUrl;
}
public String getLastLoginDate() {
	return lastLoginDate;
}
public void setLastLoginDate(String lastLoginDate) {
	this.lastLoginDate = lastLoginDate;
}
public String getLastLoginDateDisplay() {
	return lastLoginDateDisplay;
}
public void setLastLoginDateDisplay(String lastLoginDateDisplay) {
	this.lastLoginDateDisplay = lastLoginDateDisplay;
}
public String getJoinDate() {
	return joinDate;
}
public void setJoinDate(String joinDate) {
	this.joinDate = joinDate;
}
public String getRole() {
	return role;
}
public void setRole(String role) {
	this.role = role;
}
public String[] getAuthorities() {
	return authorities;
}
public void setAuthorities(String[] authorities) {
	this.authorities = authorities;
}
public boolean isActive() {
	return isActive;
}
public void setActive(boolean isActive) {
	this.isActive = isActive;
}
public boolean isNotLocked() {
	return isNotLocked;
}
public void setNotLocked(boolean isNotLocked) {
	this.isNotLocked = isNotLocked;
}





}
