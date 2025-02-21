package br.com.ufape.petshare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ufape.petshare.controller.dto.request.AuthRequest;
import br.com.ufape.petshare.model.users.User;
import br.com.ufape.petshare.services.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
    private AuthService service;
	
	 @PostMapping("/login")
	    public ResponseEntity<User> login(@RequestBody AuthRequest obj) {
	        User createdUser = service.authenticationUser(obj.getEmail(), obj.getPassword());
	        return ResponseEntity.ok(createdUser);
	    }
}
