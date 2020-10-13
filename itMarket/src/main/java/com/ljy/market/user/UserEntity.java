package com.ljy.market.user;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "USERS")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserEntity extends User implements UserDetails {
	private static final long serialVersionUID = 1L;

	private String email;
	private String name;
	
	private String provider;
	private String providerId;
	
	@Enumerated(EnumType.STRING)
	private UserRole role;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		ArrayList<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
		list.add(new GrantedAuthority() {
			@Override
			public String getAuthority() {
				return role.toString();
			}
		});
		return list;
	}

	@Override
	public String getPassword() {
		return getPw();
	}

	@Override
	public String getUsername() {
		return getId();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
