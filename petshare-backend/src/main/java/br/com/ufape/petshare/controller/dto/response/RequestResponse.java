package br.com.ufape.petshare.controller.dto.response;

import java.time.LocalDate;

import br.com.ufape.petshare.model.Request;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RequestResponse {
	private Long id;
	private Double receivedQuantity;
    private Double quantity;
    private String status;
    private LocalDate date;

    private UserResponse user;

    private ItemResponse item;
    
    private PostResponse post;

	public RequestResponse(Request obj) {
		this.id = obj.getId();
		this.receivedQuantity = obj.getReceivedQuantity();
		this.quantity = obj.getQuantity();
		this.status = obj.getStatus();
		this.date = obj.getDate();
		this.user = new UserResponse(obj.getUser());
		this.item = new ItemResponse(obj.getItem());
		this.post = new PostResponse(obj.getPost());
	}

}
