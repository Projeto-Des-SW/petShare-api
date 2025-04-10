package br.com.ufape.petshare.model.enums;

import lombok.Getter;

@Getter
public enum Profile {
	ADMIN("ROLE_ADMIN"), USER("ROLE_USER");

	private String role;

	Profile(String role) {
		this.role = role;
	}

	public String getRole() {
		return role;
	}
}
