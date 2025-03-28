package br.com.ufape.petshare.controller.dto.request.updatedto;

import java.time.LocalDate;

import br.com.ufape.petshare.controller.dto.request.PostRequest;
import br.com.ufape.petshare.controller.dto.request.newdto.NewItemRequest;
import br.com.ufape.petshare.model.Request;
import br.com.ufape.petshare.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RequestUpdateRequest {
	private Long id;
	private Double quantity;
	private Long userId;
	private NewItemRequest item;
	private PostRequest post;
	
	public Request toEntity() {
		User user = new User();
		user.setId(userId);
		return new Request(id, (double) 0, quantity, "Em Aberto", LocalDate.now(), user, item.toEntity(),
				post.toEntity());
	}
}
