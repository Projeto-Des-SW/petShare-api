package br.com.ufape.petshare.controller.dto.request.updatedto;

import java.time.LocalDate;

import br.com.ufape.petshare.controller.dto.request.PostRequest;
import br.com.ufape.petshare.model.DonateItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DonateItemUpdateRequest {
	private Long id;
	private Double quantity;
	private PostRequest post;

	public DonateItem toEntity() {
		return new DonateItem(id, LocalDate.now(), null, quantity, null, null, post.toEntity());
	}
}
