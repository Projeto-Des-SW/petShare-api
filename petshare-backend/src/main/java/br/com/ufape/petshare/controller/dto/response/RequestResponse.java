package br.com.ufape.petshare.controller.dto.response;

import java.time.LocalDate;
import java.util.List;

import br.com.ufape.petshare.model.ReceivedItem;
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
    
	private ReceivedItemResponse receivedItem;

	public RequestResponse(Request obj) {
		this.id = obj.getId();
		this.receivedQuantity = obj.getReceivedQuantity();
		this.quantity = obj.getQuantity();
		this.status = obj.getStatus();
		this.date = obj.getDate();
		this.user = new UserResponse(obj.getUser());
		this.item = new ItemResponse(obj.getItem());
		this.post = new PostResponse(obj.getPost());
		
		List<ReceivedItem> receivedItems = obj.getReceivedItems().stream()
				.filter(x -> !x.getStatus().equals("Cancelado"))
				.filter(x -> !x.getStatus().equals("Finalizado"))
				.filter(x -> !x.getStatus().equals("Recusado")).toList();
		if(!receivedItems.isEmpty()) {
			ReceivedItem receivedItem = receivedItems.getFirst();
			receivedItem.setRequest(null);
			this.receivedItem = new ReceivedItemResponse(receivedItem);
		}
	}

}
