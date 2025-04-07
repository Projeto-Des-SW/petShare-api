package br.com.ufape.petshare.controller.dto.response;

import java.time.LocalDate;

import br.com.ufape.petshare.model.DonateAnimal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DonateAnimalResponse {
	private Long id;
	private LocalDate date;
    private String status;

    private AnimalResponse animal;

    private UserResponse donor;
	
	public DonateAnimalResponse(DonateAnimal obj) {
		this.id = obj.getId();
		this.date = obj.getDate();
		this.status = obj.getStatus();
		this.animal = new AnimalResponse(obj.getAnimal());
		this.donor = new UserResponse(obj.getDonor());
	}

}
