package br.com.ufape.petshare.controller.dto.request.updatedto;

import java.time.LocalDate;

import br.com.ufape.petshare.controller.dto.request.PostRequest;
import br.com.ufape.petshare.model.DonateAnimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DonateAnimalUpdateRequest {
	private Long id;
	private PostRequest post;

	public DonateAnimal toEntity() {
		return new DonateAnimal(id, LocalDate.now(), null, null, null, post.toEntity());
	}
}
