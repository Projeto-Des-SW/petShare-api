package br.com.ufape.petshare.controller.dto.request.newdto;

import java.time.LocalDate;

import br.com.ufape.petshare.model.DonateItem;
import br.com.ufape.petshare.model.Post;
import br.com.ufape.petshare.model.ReceivedItem;
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
public class NewReceivedItemRequest {

	private Double quantity;

	private Long userId;

	private Long requestItemId;

	private Long donateItemId;

	public ReceivedItem toEntity() {
		User user = new User();
		user.setId(userId);
		Request request = null;
		DonateItem donate = null;
		if (requestItemId != null) {
			request = new Request(requestItemId, 0d, 0d, null, null, null, null, null);
			Post post = new Post();
			post.setText("Doação referente a requisição " + request.getId());
			donate = new DonateItem(null, LocalDate.now(), "Reservado", quantity, null , user, post);
		} else {
			donate = new DonateItem(donateItemId, null, null, 0d, null, null, null);
		}
		return new ReceivedItem(null, LocalDate.now(),  "Em interesse", quantity, request, donate, user);
	}
}
