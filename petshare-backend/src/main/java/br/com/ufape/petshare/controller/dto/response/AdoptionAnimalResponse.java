package br.com.ufape.petshare.controller.dto.response;

import java.time.LocalDate;

import br.com.ufape.petshare.model.AdoptionAnimal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AdoptionAnimalResponse {
	private Long id;
	private LocalDate date;
	private String status;
	private UserResponse adopter;
	private DonateAnimalResponse donateAnimal;

	public AdoptionAnimalResponse(AdoptionAnimal obj) {
		this.id = obj.getId();
		this.date = obj.getDate();
		this.status = obj.getStatus().name();
		this.adopter = new UserResponse(obj.getAdopter());
		if(obj.getDonateAnimal() != null)
			this.donateAnimal = new DonateAnimalResponse(obj.getDonateAnimal());
	}

}