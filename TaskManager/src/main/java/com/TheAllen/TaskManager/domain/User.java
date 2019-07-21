package com.TheAllen.TaskManager.domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User implements UserDetails {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Email(message = "Username needs to a email")
	@NotBlank(message = "Username field cannot be blank")
	@Column(unique = true)
	private String username;
	
	@NotBlank(message = "Please enter a name")
	private String name;
	
	@NotBlank(message = "Password cannot be blank")
	private String password;
	
	@Transient
	private String confirmPassword;
	
	private Date createdAt;
	private Date updatedAt;
	
	
	
	public User() {
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@PrePersist
	protected void onCreated() {
		this.createdAt = new Date();
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}

	@Override
	@JsonIgnore
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return null;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonLocked() {
		
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Override
	@JsonIgnore
	public boolean isEnabled() {
		
		return true;
	}
}
