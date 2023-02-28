package com.xprod.spring.xprod.domain;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class User implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable=false, updatable=false)
	private Long id; //id pour la base de donnée
	
	private String idUser; // Id pour l'utilisitaeur
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private String email;
	private String profileImageURL;
	private Date lastLoginDate;
	private Date lastLoginDateDisplay;
	private Date joinDate; // Date ou il s'est inscrit
	private String role; //ROLE_USER {read, edit} ROLE_ADMIN {delete}
	private String [] authorities; // Are permissions : read, edit, delete
	private boolean isActive;
	private boolean isNotLocked;
	public Long getId() {
		return id;
	}
	public String getIdUser() {
		return idUser;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}	
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getEmail() {
		return email;
	}
	public String getProfileImageURL() {
		return profileImageURL;
	}
	public Date getLastLoginDate() {
		return lastLoginDate;
	}
	public Date getLastLoginDateDisplay() {
		return lastLoginDateDisplay;
	}
	public Date getJoinDate() {
		return joinDate;
	}
	public String getRole() {
		return role;
	}
	public String[] getAuthorities() {
		return authorities;
	}
	public boolean isActive() {
		return isActive;
	}
	public boolean isNotLocked() {
		return isNotLocked;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setProfileImageURL(String profileImageURL) {
		this.profileImageURL = profileImageURL;
	}
	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	public void setLastLoginDateDisplay(Date lastLoginDateDisplay) {
		this.lastLoginDateDisplay = lastLoginDateDisplay;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public void setAuthorities(String[] authorities) {
		this.authorities = authorities;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public void setNotLocked(boolean isNotLocked) {
		this.isNotLocked = isNotLocked;
	}
	public User() {
		super();
	}
	public User(Long id, String idUser, String firstName, String lastName, String username, String password,
			String email, String profileImageURL, Date lastLoginDate, Date lastLoginDateDisplay, Date joinDate,
			String role, String[] authorities, boolean isActive, boolean isNotLocked) {
		super();
		this.id = id;
		this.idUser = idUser;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.profileImageURL = profileImageURL;
		this.lastLoginDate = lastLoginDate;
		this.lastLoginDateDisplay = lastLoginDateDisplay;
		this.joinDate = joinDate;
		this.role = role;
		this.authorities = authorities;
		this.isActive = isActive;
		this.isNotLocked = isNotLocked;
	}
	
	

}
