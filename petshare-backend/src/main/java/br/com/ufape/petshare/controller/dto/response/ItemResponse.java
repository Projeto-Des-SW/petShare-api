package br.com.ufape.petshare.controller.dto.response;

import br.com.ufape.petshare.model.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ItemResponse {
	private Long id;
	private String name;
    private String description;
    private String status;
    private String brand;
    private String photo;
    private TypeItemResponse typeItem;
    
	public ItemResponse(Item obj) {
		this.id = obj.getId();
		this.name = obj.getName();
		this.description = obj.getDescription();
		this.status = obj.getStatus();
		this.brand = obj.getBrand();
		this.photo = obj.getPhoto();
		this.typeItem = new TypeItemResponse(obj.getTypeItem());
	}

}
