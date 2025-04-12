package br.com.ufape.petshare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ufape.petshare.controller.dto.request.AuthRequest;
import br.com.ufape.petshare.controller.dto.request.PasswordUpdateRequest;
import br.com.ufape.petshare.controller.dto.response.UserResponse;
import br.com.ufape.petshare.facade.PetShare;
import br.com.ufape.petshare.model.User;
import br.com.ufape.petshare.security.AuthUser;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private PetShare facade;

	@PostMapping("/login")
	public ResponseEntity<UserResponse> login(@Valid @RequestBody AuthRequest data) {
		UsernamePasswordAuthenticationToken userNamePassword = new UsernamePasswordAuthenticationToken(data.getEmail(),
				data.getPassword());
		Authentication auth = this.authenticationManager.authenticate(userNamePassword);
		String token = facade.generateLoginToken((AuthUser) auth.getPrincipal());
		User user = facade.findUserByEmail(data.getEmail());
		return ResponseEntity.ok().header("Authorization", "Bearer " + token)
				.header("access-control-expose-headers", "Authorization").body(new UserResponse(user));
	}
	
	@PostMapping("/refresh_token")
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
		User user = facade.findLoggedUser();
		String token = facade.generateLoginToken(user.getEmail());
		response.addHeader("Authorization", "Bearer " + token);
		response.addHeader("access-control-expose-headers", "Authorization");
		return ResponseEntity.noContent().build();
	}

	@PatchMapping("/newpassword")
	public ResponseEntity<String> updatePassword(@Valid @RequestBody PasswordUpdateRequest data) {
		facade.updatePassword(data.password, data.newPassword);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/user/current")
	public Authentication getCurrentUser() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
}
