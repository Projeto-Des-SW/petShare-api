package br.com.ufape.petshare.controller.dto.request.newdto;

import br.com.ufape.petshare.controller.dto.request.TypeItemRequest;
import br.com.ufape.petshare.model.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewItemRequest {
	private String name;
	private String description;
	private String status;
	private String brand;
	private String photo;
	private TypeItemRequest typeItem;

	public Item toEntity() {
		return new Item(null, name, description, status, brand, photo, typeItem.toEntity());
	}
}
