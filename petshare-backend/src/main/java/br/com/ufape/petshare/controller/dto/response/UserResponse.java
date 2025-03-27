package br.com.ufape.petshare.controller.dto.response;

import java.time.LocalDate;

import br.com.ufape.petshare.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserResponse {
	private Long id;
	private String name;
	private String email;
	private String phone;
	private String status;
	private LocalDate bornDate;
	private String cpf;
	private AddressResponse address;

	public UserResponse(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
		this.phone = user.getPhone();
		this.status = user.getStatus();
		this.bornDate = user.getBornDate();
		this.cpf = user.getCpf();
		this.address = new AddressResponse(user.getAddress());
	}

}
