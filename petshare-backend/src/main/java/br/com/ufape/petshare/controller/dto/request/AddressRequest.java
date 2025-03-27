package br.com.ufape.petshare.controller.dto.request;

import br.com.ufape.petshare.model.Address;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequest {

    @NotBlank(message = "O número não pode estar vazio")
    private String number;

    @NotBlank(message = "A rua não pode estar vazia")
    private String street;

    @NotBlank(message = "O bairro não pode estar vazio")
    private String neighborhood;

    @NotBlank(message = "O CEP não pode estar vazio")
    @Size(min = 8, max = 9, message = "O CEP deve ter entre 8 e 9 caracteres")
    private String cep;

    @NotBlank(message = "A cidade não pode estar vazia")
    private String city;

    @NotBlank(message = "O estado não pode estar vazio")
    private String state;
    
    public Address toEntity() {
		return new Address(null, number, street, neighborhood, cep, city, state);
	}
}
