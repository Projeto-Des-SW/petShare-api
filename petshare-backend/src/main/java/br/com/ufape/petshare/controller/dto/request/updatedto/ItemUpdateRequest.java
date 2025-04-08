package br.com.ufape.petshare.controller.dto.request.updatedto;

import br.com.ufape.petshare.model.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ItemUpdateRequest {
	private Long id;
	private String name;
	private String description;
	private String status;
	private String brand;
	private String category;

	public Item toEntity() {
		return new Item(id, name, description, status, brand, category);
	}
}
