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
	private String image;

	public UserResponse(User obj) {
		this.id = obj.getId();
		this.name = obj.getName();
		this.email = obj.getEmail();
		this.phone = obj.getPhone();
		this.status = obj.getStatus();
		this.bornDate = obj.getBornDate();
		this.cpf = obj.getCpf();
		this.image = obj.getImage();
		if(obj.getAddress() != null)
			this.address = new AddressResponse(obj.getAddress());
	}

}
