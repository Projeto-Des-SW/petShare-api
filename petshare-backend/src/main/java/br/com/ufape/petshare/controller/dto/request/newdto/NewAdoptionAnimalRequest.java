package br.com.ufape.petshare.controller.dto.request.newdto;

import java.time.LocalDate;

import br.com.ufape.petshare.model.AdoptionAnimal;
import br.com.ufape.petshare.model.Animal;
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
    private Long animalId;
    private Long userId;
    
     public AdoptionAnimal toEntity() {
    	 Animal animal = new Animal();
    	 animal.setId(animalId);
    	 User user = new User();
    	 user.setId(userId);
		return new AdoptionAnimal(null, LocalDate.now(), "Em Aberto", animal, user);
     }
}
