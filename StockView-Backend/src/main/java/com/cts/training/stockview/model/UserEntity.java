package com.cts.training.stockview.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name = "users")
public class UserEntity implements Serializable {
	
	private static final long serialVersionUID = 5463522255309500638L;
	@Id
	@GeneratedValue
	private int id;
	@Column(unique = true)
	private String username;
	private String email;
	private String password;
	private long phoneNo;
	private boolean isAdmin = false;
	private boolean confirmed = false;
	public UserEntity() {
		
	}
	public UserEntity(int id, String username, String email, String password, long phoneNo, boolean isAdmin,
			boolean confirmed) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.phoneNo = phoneNo;
		this.isAdmin = isAdmin;
		this.confirmed = confirmed;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(long phoneNo) {
		this.phoneNo = phoneNo;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public boolean isConfirmed() {
		return confirmed;
	}
	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password
				+ ", phoneNo=" + phoneNo + ", isAdmin=" + isAdmin + ", confirmed=" + confirmed + "]";
	}
	
}