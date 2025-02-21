package br.com.ufape.petshare.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ufape.petshare.exceptions.UserNotFoundException;
import br.com.ufape.petshare.model.users.User;
import br.com.ufape.petshare.repository.UserRepository; 

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User createUser(User user) {
		return userRepository.save(user);
	}
	
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public User getUserById(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("User not found with id = " + id));
		return user;
	}

	public User getUserByEmail(String email) {
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new UserNotFoundException("User not found with email = " + email));
		return user;
	}

	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}

	public User updateUser(Long id, User updatedUser) {
        return userRepository.findById(id).map(user -> {
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            user.setPhone(updatedUser.getPhone());
            user.setStatus(updatedUser.getStatus());
            user.setBornDate(updatedUser.getBornDate());
            user.setCpf(updatedUser.getCpf());
            user.setPassword(updatedUser.getPassword());
            return userRepository.save(user);
        }).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));
    }

}