package br.com.ufape.petshare.controller.dto.request.newdto;

import java.time.LocalDate;

import br.com.ufape.petshare.controller.dto.request.PostRequest;
import br.com.ufape.petshare.model.Animal;
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
public class NewDonateAnimalRequest {
	private Long animalId;
	private Long userId;
	private PostRequest post;

	public DonateAnimal toEntity() {
		Animal animal = new Animal();
		animal.setId(animalId);
		User user = new User();
		user.setId(userId);
		return new DonateAnimal(null, LocalDate.now(), "Em aberto", animal, user, post.toEntity());
	}
}
