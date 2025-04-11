package br.com.ufape.petshare.controller.dto.request.newdto;

import java.time.LocalDate;

import br.com.ufape.petshare.controller.dto.request.PostRequest;
import br.com.ufape.petshare.model.DonateItem;
import br.com.ufape.petshare.model.User;
import br.com.ufape.petshare.model.enums.ItemDonationStatus;
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
	private NewItemRequest item;
	private Long userId;
	private PostRequest post;

	public DonateItem toEntity() {
		User user = new User();
		user.setId(userId);
		String status = "Disponível";
		return new DonateItem(null, LocalDate.now(), ItemDonationStatus.DISPONIVEL, quantity, item.toEntity(), user, post.toEntity());
	}
}