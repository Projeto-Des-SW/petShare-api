package br.com.ufape.petshare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ufape.petshare.model.Login;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {
    // Custom query methods can be defined here if needed
    
}
