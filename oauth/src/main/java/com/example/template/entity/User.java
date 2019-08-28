package com.example.template.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Entity
@Data
@Table(name = "user")
public class User implements UserDetails{

	@Id
	private Long id;
	
	private String username;
	
	@Column(length=400)
	private String password;
	
	@Column
	private String role;
	
	@Transient
	private Collection<? extends GrantedAuthority> authorities;
	
	private boolean accountNonExpired = true;
	
	private boolean accountNonLocked = true;

	private boolean credentialsNonExpired = true;
	
	private boolean enabled = true;


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

}
