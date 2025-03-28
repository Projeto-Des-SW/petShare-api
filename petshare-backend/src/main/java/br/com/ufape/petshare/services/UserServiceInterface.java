package br.com.ufape.petshare.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import br.com.ufape.petshare.model.User;

public interface UserServiceInterface {

	User saveUser(User user);

	User findUserById(Long id);

	List<User> getAllUsers();

	User updateUser(Long id, User userDetails);

	void deleteUser(Long id);

	Page<User> findUserPage(PageRequest pageRequest);

}