package br.com.ufape.petshare.controller.dto.request;

public class AuthRequest {
	private String email;
	private String password;

	public AuthRequest(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

}
