package br.com.ufape.petshare.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.ufape.petshare.model.User;
import br.com.ufape.petshare.repository.UserRepository;
import br.com.ufape.petshare.services.exceptions.ObjectNotFoundException; 

@Service
public class UserService implements UserServiceInterface{

    @Autowired
    private UserRepository userRepository;

    @Override
	@Transactional
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
	public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("User not found with ID = " + id));
    }
    
	@Override
    public Page<User> findUserPage(PageRequest pageRequest) {
		return userRepository.findAll(pageRequest);
	}

    @Override
	public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
	@Transactional
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ObjectNotFoundException("User not found with ID = " + id);
        }
        userRepository.deleteById(id);
    }

    public User updateUser(Long id, User updatedUser) {
    	
        return userRepository.findById(id).map(user -> {
        	updatedUser.getAddress().setId(user.getAddress().getId());
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            user.setPhone(updatedUser.getPhone());
            user.setStatus(updatedUser.getStatus());
            user.setBornDate(updatedUser.getBornDate());
            user.setAddress(updatedUser.getAddress());
            return userRepository.save(user);
        }).orElseThrow(() -> new ObjectNotFoundException("User not found with ID = " + id));
    }
}