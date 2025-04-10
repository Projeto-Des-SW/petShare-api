package br.com.ufape.petshare.controller.dto.request;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthRequest {
	@NotEmpty(message = "Preenchimento obrigatório")
	@Email
	private String email;
	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 8, message = "A senha deve conter no mínimo 8 caracteres")
	private String password;
}
