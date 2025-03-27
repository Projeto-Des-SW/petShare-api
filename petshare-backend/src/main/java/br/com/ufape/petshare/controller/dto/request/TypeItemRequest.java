package br.com.ufape.petshare.controller.dto.request;

import br.com.ufape.petshare.model.TypeItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TypeItemRequest {
	private Long id;
	private String name;

	public TypeItem toEntity() {
		return new TypeItem(id, name);
	}
}
