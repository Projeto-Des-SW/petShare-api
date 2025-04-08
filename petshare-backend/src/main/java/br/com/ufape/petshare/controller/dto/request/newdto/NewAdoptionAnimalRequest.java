package br.com.ufape.petshare.controller.dto.request.newdto;

import java.time.LocalDate;

import br.com.ufape.petshare.model.AdoptionAnimal;
import br.com.ufape.petshare.model.DonateAnimal;
import br.com.ufape.petshare.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewAdoptionAnimalRequest {
    private Long donateAnimalId;
    private Long userId;
    
     public AdoptionAnimal toEntity() {
    	 DonateAnimal donateAnimal = new DonateAnimal();
    	 donateAnimal.setId(donateAnimalId);
    	 User user = new User();
    	 user.setId(userId);
		return new AdoptionAnimal(null, LocalDate.now(), "Em interesse", user, donateAnimal);
     }
}
