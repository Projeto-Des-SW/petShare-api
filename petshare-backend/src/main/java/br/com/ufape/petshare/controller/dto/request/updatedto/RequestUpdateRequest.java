package br.com.ufape.petshare.controller.dto.request.updatedto;

import java.time.LocalDate;

import br.com.ufape.petshare.controller.dto.request.PostRequest;
import br.com.ufape.petshare.model.Request;
import br.com.ufape.petshare.model.enums.RequestStatus;
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
		return new Request(id, (double) 0, quantity, RequestStatus.EM_ABERTO, LocalDate.now(), null, item.toEntity(),
				post.toEntity());
	}
}
