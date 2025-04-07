package br.com.ufape.petshare.controller.dto.request.updatedto;

import java.time.LocalDate;

import br.com.ufape.petshare.controller.dto.request.PostRequest;
import br.com.ufape.petshare.model.Request;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RequestUpdateRequest {
	private Long id;
	private Double quantity;
	private ItemUpdateRequest item;
	private PostRequest post;
	
	public Request toEntity() {
		return new Request(id, (double) 0, quantity, "Em Aberto", LocalDate.now(), null, item.toEntity(),
				post.toEntity());
	}
}
