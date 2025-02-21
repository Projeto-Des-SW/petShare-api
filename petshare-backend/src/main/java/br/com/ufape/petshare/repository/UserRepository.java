package br.com.ufape.petshare.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.ufape.petshare.model.users.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	@Transactional(readOnly = true)
	Optional<User> findByEmail(String email);
	@Transactional(readOnly = true)
    Optional<User> findByEmailAndPassword(String email, String password);
}
