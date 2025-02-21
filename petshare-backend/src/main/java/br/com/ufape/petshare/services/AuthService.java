package br.com.ufape.petshare.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ufape.petshare.exceptions.InvalidCredentialsException;
import br.com.ufape.petshare.model.users.User;
import br.com.ufape.petshare.repository.UserRepository;

@Service
public class AuthService {

	@Autowired
	UserRepository userRepository;

	public User authenticationUser(String login, String password) {
		User user = userRepository.findByEmailAndPassword(login, password)
				.orElseThrow(() -> new InvalidCredentialsException("Invalid credentions"));
		return user;
	}

}
