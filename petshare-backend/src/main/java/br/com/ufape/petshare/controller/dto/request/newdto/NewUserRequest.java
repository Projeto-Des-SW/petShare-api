package br.com.ufape.petshare.controller.dto.request.newdto;

import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CPF;

import br.com.ufape.petshare.controller.dto.request.AddressRequest;
import br.com.ufape.petshare.model.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewUserRequest {
	@NotBlank(message = "O nome não pode estar vazio")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
    private String name;

    @NotBlank(message = "O e-mail não pode estar vazio")
    @Email(message = "Formato de e-mail inválido")
    private String email;
    
    private String image;

    @NotBlank(message = "O telefone não pode estar vazio")
    @Size(min = 10, max = 15, message = "O telefone deve ter entre 10 e 15 caracteres")
    private String phone;

    @NotBlank(message = "O status não pode estar vazio")
    private String status;

    @NotNull(message = "A data de nascimento não pode estar vazia")
    private LocalDate bornDate;

    @NotBlank(message = "O CPF não pode estar vazio")
    @Size(min = 11, max = 14, message = "O CPF deve ter entre 11 e 14 caracteres")
    @CPF
    private String cpf;

    @NotBlank(message = "A senha não pode estar vazia")
    @Size(min = 6, message = "A senha deve ter pelo menos 6 caracteres")
    private String password;
    
    @Valid
    @NotNull
    private AddressRequest address;
    
    public User toEntity() {
		return new User(null, name, image, email, phone, status, bornDate, cpf, password, address.toEntity());
	}
}
