package br.com.ufape.petshare.controller.dto.request.updatedto;

import java.time.LocalDate;

import br.com.ufape.petshare.controller.dto.request.PostRequest;
import br.com.ufape.petshare.model.ReceivedItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ReceivedItemUpdateRequest {
	private Long id;
	private Double quantity;
	private PostRequest post;

	public ReceivedItem toEntity() {
		return new ReceivedItem(id, LocalDate.now(), null, quantity, null, null, null, post.toEntity());
	}
}
