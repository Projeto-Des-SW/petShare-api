package br.com.ufape.petshare.controller.dto.request.updatedto;

import java.time.LocalDate;

import br.com.ufape.petshare.controller.dto.request.AddressRequest;
import br.com.ufape.petshare.model.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserUpdateRequest {
	@NotBlank(message = "O nome não pode estar vazio")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
    private String name;

	private String image;
	
    @NotBlank(message = "O e-mail não pode estar vazio")
    @Email(message = "Formato de e-mail inválido")
    private String email;

    @NotBlank(message = "O telefone não pode estar vazio")
    @Size(min = 10, max = 15, message = "O telefone deve ter entre 10 e 15 caracteres")
    private String phone;

    @NotBlank(message = "O status não pode estar vazio")
    private String status;

    @NotNull(message = "A data de nascimento não pode estar vazia")
    private LocalDate bornDate;
    
    @Valid
    @NotNull
    private AddressRequest address;
    
    public User toEntity() {
		return new User(null, name, image, email, phone, status, bornDate, null, null, address.toEntity(), null);
	}
}
