package br.com.ufape.petshare.controller.dto.request.newdto;

import java.time.LocalDate;

import br.com.ufape.petshare.controller.dto.request.PostRequest;
import br.com.ufape.petshare.model.Request;
import br.com.ufape.petshare.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewRequestRequest {

	private Double quantity;

	private Long userId;

	private NewItemRequest item;

	private PostRequest post;

	public Request toEntity() {
		User user = new User();
		user.setId(userId);
		return new Request(null, (double) 0, quantity, "Em aberto", LocalDate.now(), user, item.toEntity(), post.toEntity());
	}
}
