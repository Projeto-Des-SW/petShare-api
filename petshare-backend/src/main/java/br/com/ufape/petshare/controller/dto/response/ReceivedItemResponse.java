package br.com.ufape.petshare.controller.dto.response;

import java.time.LocalDate;

import br.com.ufape.petshare.model.ReceivedItem;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReceivedItemResponse {
	private Long id;
	private LocalDate date;
	private String status;
	private Double quantity;

	private RequestResponse request;

	private UserResponse receiver;
	
	private DonateItemResponse donateItem;

	public ReceivedItemResponse(ReceivedItem obj) {
		this.id = obj.getId();
		this.date = obj.getDate();
		this.status = obj.getStatus();
		this.quantity = obj.getQuantity();
		if(obj.getDonateItem() != null)
			this.donateItem = new DonateItemResponse(obj.getDonateItem());
		if(obj.getRequest() != null)
			this.request = new RequestResponse(obj.getRequest());
		this.receiver = new UserResponse(obj.getReceiver());
	}

}