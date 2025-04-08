package br.com.ufape.petshare.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ufape.petshare.model.Login;
import br.com.ufape.petshare.repository.LoginRepository;


@RestController
@RequestMapping("/api")
public class LoginController {
    
    @Autowired
    private LoginRepository loginRepository;

    @GetMapping("/login/{id}")
    public Login searchLoginById(@PathVariable Long id) {
        return loginRepository.findById(id).orElse(null);
    }

    @GetMapping("/login")
    public List<Login> listAllLogins() {
        return loginRepository.findAll();
    }
    
}
