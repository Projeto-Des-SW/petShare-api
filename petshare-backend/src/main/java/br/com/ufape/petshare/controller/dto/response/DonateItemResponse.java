package br.com.ufape.petshare.controller.dto.response;

import java.time.LocalDate;

import br.com.ufape.petshare.model.DonateItem;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DonateItemResponse {
	private Long id;
	private LocalDate date;
	private String status;
	private Double quantity;

	private RequestResponse request;

	private UserResponse user;

	public DonateItemResponse(DonateItem obj) {
		this.id = obj.getId();
		this.date = obj.getDate();
		this.status = obj.getStatus();
		this.quantity = obj.getQuantity();
		this.request = new RequestResponse(obj.getRequest());
		this.user = new UserResponse(obj.getUser());
	}

}
