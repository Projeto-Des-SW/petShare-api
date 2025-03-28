package br.com.ufape.petshare.controller.dto.request.newdto;

import java.time.LocalDate;

import br.com.ufape.petshare.controller.dto.request.PostRequest;
import br.com.ufape.petshare.model.DonateItem;
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
public class NewDonateItemRequest {

	private Double quantity;
	private Long requestId;
	private Long userId;
	private PostRequest post;

	public DonateItem toEntity() {
		User user = new User();
		user.setId(userId);
		Request request = new Request();
		request.setId(requestId);
		String status = "Em Andamento";
		return new DonateItem(null, LocalDate.now(), status, quantity, request, user, post.toEntity());
	}
}