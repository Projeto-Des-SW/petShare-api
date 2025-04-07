package br.com.ufape.petshare.controller.dto.request.newdto;

import java.time.LocalDate;

import br.com.ufape.petshare.controller.dto.request.PostRequest;
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
	private NewAnimalRequest animal;
	private Long userId;
	private PostRequest post;

	public DonateAnimal toEntity() {
		User user = new User();
		user.setId(userId);
		return new DonateAnimal(null, LocalDate.now(), "Em aberto", animal.toEntity(), user, post.toEntity());
	}
}
