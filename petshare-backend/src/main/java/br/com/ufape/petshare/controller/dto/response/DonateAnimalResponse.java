package br.com.ufape.petshare.controller.dto.response;

import java.time.LocalDate;
import java.util.List;

import br.com.ufape.petshare.model.AdoptionAnimal;
import br.com.ufape.petshare.model.DonateAnimal;
import br.com.ufape.petshare.model.enums.AdoptionStatus;
import br.com.ufape.petshare.model.enums.DonationStatus;
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

	private AdoptionAnimalResponse adoptionAnimal;

	public DonateAnimalResponse(DonateAnimal obj) {
		this.id = obj.getId();
		this.date = obj.getDate();
		this.status = obj.getStatus().name();
		this.animal = new AnimalResponse(obj.getAnimal());
		this.donor = new UserResponse(obj.getDonor());
		List<AdoptionAnimal> adoptions = obj.getAdoptionAnimals().stream()
				.filter(x -> !x.getStatus().equals(AdoptionStatus.CANCELADA)).filter(x -> !x.getStatus().equals(AdoptionStatus.FINALIZADA))
				.filter(x -> !x.getStatus().equals(DonationStatus.RECUSADO)).toList();
		if (!adoptions.isEmpty()) {
			AdoptionAnimal adoption = adoptions.getFirst();
			adoption.setDonateAnimal(null);
			this.adoptionAnimal = new AdoptionAnimalResponse(adoption);
		}
	}

}
