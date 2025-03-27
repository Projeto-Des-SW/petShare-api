package br.com.ufape.petshare.controller.dto.response;

import br.com.ufape.petshare.model.TypeItem;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TypeItemResponse {
	private Long id;
	private String name;

	public TypeItemResponse(TypeItem obj) {
		this.id = obj.getId();
		this.name = obj.getName();
	}

}
