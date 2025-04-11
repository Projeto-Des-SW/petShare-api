package br.com.ufape.petshare.controller.dto.request.newdto;

import java.time.LocalDate;

import br.com.ufape.petshare.model.DonateItem;
import br.com.ufape.petshare.model.ReceivedItem;
import br.com.ufape.petshare.model.Request;
import br.com.ufape.petshare.model.User;
import br.com.ufape.petshare.model.enums.ReceivedItemStatus;
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
		if (requestItemId != null)
			request = new Request(requestItemId, 0d, 0d, null, null, null, null, null);
		if(donateItemId != null)
			donate = new DonateItem(donateItemId, null, null, 0d, null, null, null);
		
		return new ReceivedItem(null, LocalDate.now(),  ReceivedItemStatus.EM_INTERESSE, quantity, request, donate, user);
	}
}
