package br.com.ufape.petshare.controller.dto.response;

import br.com.ufape.petshare.model.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddressResponse {
	private Long id;
	private String number;
	private String street;
	private String neighborhood;
	private String cep;
	private String city;
	private String state;

	public AddressResponse(Address address) {
		this.id = address.getId();
		this.number = address.getNumber();
		this.street = address.getStreet();
		this.neighborhood = address.getNeighborhood();
		this.cep = address.getCep();
		this.city = address.getCity();
		this.state = address.getState();
	}

}
