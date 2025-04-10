package br.com.ufape.petshare.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.ufape.petshare.model.User;
import br.com.ufape.petshare.model.enums.Profile;
import lombok.Getter;

@Getter
public class AuthUser implements UserDetails {

	private static final long serialVersionUID = 1L;

	private String email;
	private String senha;
	private Set<Profile> roles;

	public AuthUser(User user) {
		this.email = user.getEmail();
		this.senha = user.getPassword();
		this.roles = user.getProfiles();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles.stream().map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.email;
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
